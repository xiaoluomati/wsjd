package nju.software.wsjx.parserule.wsssjlparserule;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.util.FileUtil;
/**
 * 民事二审诉讼记录解析规则
 * @author wangzh
 *
 */
public class MsesSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
		// 解析案由,完整案由，案由代码
		String ay = null;
		String aydm = null;
		FileUtil fileUtil = new FileUtil();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			ArrayList<String> ayList = new ArrayList<String>();
			String str = "";
			is = getClass().getClassLoader().getResourceAsStream("msaymc_aydm.txt");
			isr = new InputStreamReader(is,"utf-8");			
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				ayList.add(str);
			}
			for (int i = 0; i < ayList.size(); i++) {
				String wzay = ayList.get(i)
						.substring(5, ayList.get(i).length());
				if (wsssjl.contains(wzay)) {
					ay = wzay;
					aydm = ayList.get(i).substring(0, 4);
				}
			}
			if (ay != null && aydm != null) {
				wsssjlModel.setAy(ay);
				wsssjlModel.setWzay(ay);
				wsssjlModel.setAydm(aydm);
			} else if (wssscyrModellist != null && wsssjl != null) {
				// 规则：ajjbqk，第一行，去除括号，最后一个当事人到最后（不包括一案）的内容
				String content = "";
				content = WsAnalyse.getContent(wsssjl);
				int prefix = 0;
				int suffix = content.indexOf("一案");
				if (suffix != -1) {
					for (WssscyrModel model : wssscyrModellist) {
						int temp = content.indexOf(model.getSscyr());
						if (temp != -1
								&& (temp + model.getSscyr().length()) > prefix) {
							if (content.indexOf("因") == (temp + 1)) {
								prefix = temp + model.getSscyr().length() + 1;
							} else {
								prefix = temp + model.getSscyr().length();
							}
						}
					}
					ay = content.substring(prefix, suffix);
					if (ay != null) {
						wsssjlModel.setAy(ay);
						wsssjlModel.setWzay(ay);
					}
					// System.out.println(wsssjlModel.getAy());
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 解析案件来源
		String ajly = null;
		if (wsssjl.contains("管辖权")) {
			ajly = "对管辖权异议裁定不服上诉";
		} else if (wsssjl.contains("不服")) {
			if (wsssjl.contains("判决")) {
				ajly = "对判决不服上诉";
			} else if (wsssjl.contains("裁定")) {
				if (wsssjl.contains("不予受理")) {
					ajly = "对裁定不予受理不服上诉";
				} else if (wsssjl.contains("驳回起诉")) {
					ajly = "对裁定驳回起诉不服上诉";
				}
			}
		}
		wsssjlModel.setAjly(ajly);
		// 解析是否开庭审理
		String ktsl = "否";
		String ktslxx = null;
		String bgkslyy = "其他";
		if ((wsssjl.contains("开庭") || wsssjl.contains("合议庭"))
				&& wsssjl.contains("审理")&&!wsssjl.contains("不开庭")) {
			ktsl = "是";
			if (wsssjl.contains("不公开")) {
				ktslxx = "不公开审理";
				if (wsssjl.contains("离婚")) {
					bgkslyy = "当事人申请不公开审理的离婚案件";
				} else if (wsssjl.contains("未成年")) {
					bgkslyy = "当事人申请不公开审理的涉及未成年案件";
				} else if (wsssjl.contains("商业秘密")) {
					bgkslyy = "当事人申请不公开审理的涉及商业秘密案件";
				} else if (wsssjl.contains("隐私")) {
					bgkslyy = "当事人申请不公开审理的涉及个人隐私的案件";
				} else if (wsssjl.contains("国家秘密")) {
					bgkslyy = "涉及国家秘密案件";
				}
			} else {
				ktslxx = "公开审理";
			}
		}
		wsssjlModel.setKtsl(ktsl);
		wsssjlModel.setKtslxx(ktslxx);
		wsssjlModel.setBgkslyy(bgkslyy);

		// 解析开庭日期,立案日期,受理日期,申请撤诉日期
		String larq = null;
		String slrq = null;
		String sqcsrq = null;
		ArrayList<String> ktrq = new ArrayList<String>();
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("立案")
					|| contentArray[i].contains("受理")) {
				larq = getDate(contentArray[i]);
				if (contentArray[i].contains("受理")) {
					slrq = getDate(contentArray[i]);
				}
			} else if (contentArray[i].contains("开庭")) {
				ktrq.add(getDate(contentArray[i]));
			} else if (contentArray[i].contains("申请撤")
					|| contentArray[i].contains("撤诉申请")
					|| contentArray[i].contains("提出撤")
					|| contentArray[i].contains("提出申请")) {
				sqcsrq = getDate(contentArray[i]);
			} else {
				ktrq.add(getDate(contentArray[i]));
			}
		}
		if (larq != null) {
			wsssjlModel.setLarq(larq);
		}
		if (ktrq != null) {
			wsssjlModel.setKtrq(ktrq);
		}
		if (slrq != null) {
			wsssjlModel.setSlrq(slrq);
		}
		if (sqcsrq != null) {
			wsssjlModel.setSqcsrq(sqcsrq);
		}
		// 解析案件涉及
		String ajsj = null;
		if (wsssjl.contains("农民工工资")) {
			ajsj = "涉农民工工资";
		} else if (wsssjl.contains("征地补偿")) {
			ajsj = "涉征地补偿";

		} else if (wsssjl.contains("拆迁安置补偿")) {
			ajsj = "涉拆迁安置补偿";

		} else if (wsssjl.contains("产品质量")) {
			ajsj = "涉产品质量";

		} else if (wsssjl.contains("环境污染")) {
			ajsj = "涉环境污染";

		} else if (wsssjl.contains("医院")) {
			ajsj = "涉医疗纠纷";

		} else if (wsssjl.contains("合同欺诈")) {
			ajsj = "涉合同欺诈";

		} else if (wsssjl.contains("服务欺诈")) {
			ajsj = "涉服务欺诈";

		} else if (wsssjl.contains("保险欺诈")) {
			ajsj = "涉保险欺诈";

		} else if (wsssjl.contains("贷款欺诈")) {
			ajsj = "涉贷款欺诈";

		} else if (wsssjl.contains("婚外情")) {
			ajsj = "涉婚外情";

		} else if (wsssjl.contains("知识产权")) {
			ajsj = "涉知识产权";

		} else if (wsssjl.contains("海事海商")) {
			ajsj = "涉海事海商";

		} else if (wsssjl.contains("WTO规则")) {
			ajsj = "涉WTO规则";

		} else if (wsssjl.contains("军队")) {
			ajsj = "涉军";

		}
		wsssjlModel.setAjsj(ajsj);
		// 解析前审案号
		ArrayList<String> qsah = new ArrayList<String>();
		String reg = "[（\\(（〔]\\d{4}[）\\)〕）].+?[^鉴]字第?\\d+-?\\d+号";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(wsssjl);
		while (m.find()) {
			qsah.add(m.group());
		}
		wsssjlModel.setQsah(qsah);

		// 解析一审案件适用程序
		// System.out.println(wsssjl);
		String ysajsycx = "普通程序";
		String jyzpt = "否";
		if (wsssjl.contains("简易程序") || wsssjl.contains("小额诉讼程序")) {
			if (wsssjl.contains("不宜适用简易")) {
				ysajsycx = "普通程序";
			} else {
				ysajsycx = "简易程序";
			}
		} else if (wsssjl.contains("转为普通")) {
			ysajsycx = "普通程序";
			jyzpt = "是";
		}
		wsssjlModel.setYsajsycx(ysajsycx);
		wsssjlModel.setJyzpt(jyzpt);

		return wsssjlModel;
	}

}
