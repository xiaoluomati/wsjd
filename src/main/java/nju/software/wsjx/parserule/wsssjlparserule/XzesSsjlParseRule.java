package nju.software.wsjx.parserule.wsssjlparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

/**
 * 行政二审诉讼记录解析
 * @author wangzh
 *
 */
public class XzesSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
		// 解析行政案由
		String ay = null;
		if (wssscyrModellist != null && wsssjl != null) {
			// 规则：ssjl，第一行，去除括号，最后一个当事人到最后（不包括一案）的内容
			String content = "";
			content = WsAnalyse.getContent(wsssjl);
			int prefix = 0;
			int suffix = content.indexOf("一案");
			if (suffix != -1) {
				for (WssscyrModel model : wssscyrModellist) {
					if (model.getSscyr() != null) {
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
					}
				}
			}
		}
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
		String qsrq = null;
		String slrq = null;
		String sqcsrq = null;
		ArrayList<String> ktrq = new ArrayList<String>();
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("立案")
					|| contentArray[i].contains("受理")
					|| contentArray[i].contains("起诉")) {
				qsrq = getDate(contentArray[i]);
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
		if (qsrq != null) {
			wsssjlModel.setQsrq(qsrq);
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

		// 解析到庭与未到庭当事人信息
		// 缺席人信息，出庭人信息map<姓名，诉讼身份>
		HashMap<String, String> qxrmap = new HashMap<String, String>();
		HashMap<String, String> ctrmap = new HashMap<String, String>();
		ArrayList<String> qxgjc = new ArrayList<String>();// 缺席关键词
		qxgjc.add("未到");
		qxgjc.add("没有到");
		qxgjc.add("拒不到");
		int index = -1;
		if (wsssjlModel.getKtsl() == "是") {
			int beginIndex = wsssjl.indexOf("一案");
			if (wsssjl.contains("到庭") && beginIndex != -1) {
				for (int i = 0; i < qxgjc.size(); i++) {
					if (wsssjl.contains(qxgjc.get(i))) {
						index = wsssjl.lastIndexOf(qxgjc.get(i));
						// 文中既出现已出庭人信息，也出现未出庭人信息
						if (index != -1) {
							// 到庭人信息在前，未出庭人信息在后
							if (wsssjl.indexOf("到庭") < index) {
								for (WssscyrModel model : wssscyrModellist) {
									if(model.getSscyr()!=null){
										if (wsssjl.substring(beginIndex,
												wsssjl.indexOf("到庭")).contains(
														model.getSscyr())) {
											if (!ctrmap.containsKey((model
													.getSscyr()))) {
												ctrmap.put(model.getSscyr(),
														model.getSssf());
											}
										} else if (wsssjl.substring(
												wsssjl.substring(0, index)
												.lastIndexOf("到庭"), index)
												.contains(model.getSscyr())) {
											qxrmap.put(model.getSscyr(),
													model.getSssf());
										}
									}
								}
							}
							// 未出庭人信息在前，到庭人信息在后
							else {
								for (WssscyrModel model : wssscyrModellist) {
									if(model.getSscyr()!=null){
										if (wsssjl.substring(beginIndex, index)
												.contains(model.getSscyr())) {
											if (!ctrmap.containsKey((model
													.getSscyr()))) {
												ctrmap.put(model.getSscyr(),
														model.getSssf());
											}
										} else if (wsssjl.substring(index,
												wsssjl.lastIndexOf("到庭")).contains(
														model.getSscyr())) {
											qxrmap.put(model.getSscyr(),
													model.getSssf());
										}
									}
								}
							}
						}
					}
				}
				// 文中只出现已出庭人信息，没有出现未出庭人信息
				if (index == -1) {
					for (WssscyrModel model : wssscyrModellist) {
						if(model.getSscyr()!=null){
							if (wsssjl.substring(beginIndex,
									wsssjl.lastIndexOf("到庭")).contains(
											model.getSscyr())) {
								if (!ctrmap.containsKey((model.getSscyr()))) {
									ctrmap.put(model.getSscyr(), model.getSssf());
								}
							}
						}
					}
				}
			} else if (beginIndex != -1) {
				// 文中只出现未出庭人信息，没有出现已出庭人信息
				for (int i = 0; i < qxgjc.size(); i++) {
					if (wsssjl.contains(qxgjc.get(i))) {
						index = wsssjl.lastIndexOf(qxgjc.get(i));
						for (WssscyrModel model : wssscyrModellist) {
							if(model.getSscyr()!=null){
								if (wsssjl.substring(beginIndex, index).contains(
										model.getSscyr())) {
									qxrmap.put(model.getSscyr(), model.getSssf());
								}
							}
						}
					}
				}
			}
		}
		wsssjlModel.setQxrxx(qxrmap);
		wsssjlModel.setCtrxx(ctrmap);

		// 解析行政二审行政行为种类
		String xzxwzl = null;
		if (wsssjl.contains("确认") || wsssjl.contains("认定")) {
			xzxwzl = "行政确认";
		} else if (wsssjl.contains("行政复议")) {
			xzxwzl = "行政复议";
		} else if (wsssjl.contains("不予受理")) {
			xzxwzl = "行政受理";
		} else if (wsssjl.contains("撤销")) {
			xzxwzl = "行政撤销";
		} else if (wsssjl.contains("补偿")) {
			xzxwzl = "行政补偿";
		} else if (wsssjl.contains("处罚")) {
			xzxwzl = "行政处罚";
		} else if (wsssjl.contains("登记")) {
			xzxwzl = "行政登记";
		} else if (wsssjl.contains("监督") || wsssjl.contains("监查")) {
			xzxwzl = "行政监督";
		} else if (wsssjl.contains("征收")) {
			xzxwzl = "行政征收";
		} else if (wsssjl.contains("裁决")
				|| (wsssjl.contains("不服") && wsssjl.contains("决定"))) {
			xzxwzl = "行政裁决";
		} else if (wsssjl.contains("强制") || wsssjl.contains("强拆")
				|| wsssjl.contains("拆除")) {
			xzxwzl = "行政强制";
		} else if (wsssjl.contains("许可") || wsssjl.contains("林权证")
				|| wsssjl.contains("核实证")) {
			xzxwzl = "行政许可";
		} else if (wsssjl.contains("批准") || wsssjl.contains("审批")) {
			xzxwzl = "行政批准";
		} else if (wsssjl.contains("行政处理")) {
			xzxwzl = "行政处理";
		} else if (wsssjl.contains("给付") || wsssjl.contains("养老保险待遇")) {
			xzxwzl = "行政给付";
		} else if (wsssjl.contains("行政纠纷")) {
			xzxwzl = "行政纠纷";
		} else {
			xzxwzl = "其他行政行为";
		}
		wsssjlModel.setXzxwzl(xzxwzl);

		// 解析行政侵权行为种类
		String xzqqxwzl = null;
		if (wsssjl.contains("不履行") || wsssjl.contains("不作为")) {
			xzqqxwzl = "行政不作为";
		} else {
			xzqqxwzl = "行政乱作为";
		}
		wsssjlModel.setXzqqxwzl(xzqqxwzl);

		// 解析前审裁判时间
		String qscpsj = null;
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("人民法院")
					&& contentArray[i].contains("字")
					&& contentArray[i].contains("号")) {
				qscpsj = getDate(contentArray[i]);
				wsssjlModel.setQscpsj(qscpsj);
				break;
			}
		}

		// 解析行政二审前审案号
		String qsah = null;
		String xzesqsah = null;
		String reg1 = "[（\\(（〔]\\d{4}[）\\)〕）].+?[^鉴]字?第?\\d+-?\\d+号";
		String reg2 = "\\W{1,30}";
		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		for (int i = 0; i < contentArray.length; i++) {
			Matcher m1 = p1.matcher(contentArray[i]);
			while (m1.find()) {
				qsah = m1.group();
				Matcher m2 = p2.matcher(qsah);
				while (m2.find()) {
					xzesqsah = qsah;
					if(xzesqsah!= null){
						xzesqsah = xzesqsah.replace("（", "(");
						xzesqsah = xzesqsah.replace("）", ")");
					}					
					wsssjlModel.setXzesqsah(xzesqsah);
					// System.out.println(xzesqsah);
					break;
				}
				break;
			}
		}
		if(xzesqsah!= null){ 
			xzesqsah = xzesqsah.replace("（", "(");
			xzesqsah = xzesqsah.replace("）", ")");
		}	
		wsssjlModel.setXzesqsah(xzesqsah);
		// 解析二审案件来源
		String qsajyl = "起诉";
		if(xzesqsah!=null){
			if (xzesqsah.contains("重字")) {
				qsajyl = "发回重审";
			} else if (xzesqsah.contains("终字")) {
				qsajyl = "上诉";
			}
		}
		wsssjlModel.setQsajyl(qsajyl);

		// 解析前审法院简称，前审案件立案年度，前审案号顺序号
		String qsland = null;
		String qsfyjc = null;
		String qsahsxh = null;
		if (xzesqsah != null) {
			qsland = xzesqsah.substring(1, 5);
			// System.out.println(qsland);
			int end = 0;
			if (xzesqsah.contains("行")) {
				if (xzesqsah.indexOf("行") - 1 == xzesqsah.indexOf("知")) {
					end = xzesqsah.indexOf("知");
				} else if (xzesqsah.contains("立") && xzesqsah.contains("经")) {
					end = xzesqsah.indexOf("立");
				} else if (xzesqsah.indexOf("行") - 1 == xzesqsah.indexOf("立")) {
					end = xzesqsah.indexOf("立");
				} else if (xzesqsah.contains("经")) {
					end = xzesqsah.indexOf("经");
				} else {
					end = xzesqsah.indexOf("行");
				}
			} else if (xzesqsah.contains("立") && xzesqsah.contains("经")) {
				end = xzesqsah.indexOf("立");
			} else if (xzesqsah.contains("立")) {
				end = xzesqsah.indexOf("立");
			} else if (xzesqsah.contains("经")) {
				end = xzesqsah.indexOf("经");
			}
			if (end >= 6) {
				qsfyjc = xzesqsah.substring(6, end);
			}
			// System.out.println(qsfyjc);
			if (xzesqsah.contains("第")) {
				qsahsxh = xzesqsah.substring(xzesqsah.lastIndexOf("第") + 1,
						xzesqsah.lastIndexOf("号"));
			} else {
				qsahsxh = xzesqsah.substring(xzesqsah.lastIndexOf("字") + 1,
						xzesqsah.lastIndexOf("号"));
			}
			// System.out.println(qsahsxh);
		}
		wsssjlModel.setQsland(qsland);
		wsssjlModel.setQsfyjc(qsfyjc);
		wsssjlModel.setQsahsxh(qsahsxh);

		// 解析前审法院
		String qsfy = null;
		String fyjb = "基层";
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("人民法院")
					&& contentArray[i].contains("不服")
					&& (contentArray[i].lastIndexOf("不服") < contentArray[i]
							.lastIndexOf("人民法院"))) {
				qsfy = contentArray[i].substring(
						contentArray[i].lastIndexOf("不服") + 2,
						contentArray[i].lastIndexOf("人民法院") + 4);
				if (qsfy.contains("最高")) {
					fyjb = "最高级";
				} else if (qsfy.contains("高级")) {
					fyjb = "高级";
				} else if (qsfy.contains("中级")) {
					fyjb = "中级";
				}
				break;
			}
		}
		wsssjlModel.setQsfy(qsfy);
		wsssjlModel.setFyjb(fyjb);

		// 解析前审文书种类，前审结案方式，前审审级
		String qswszl = null;
		String qsjafs = null;
		String qssj = "一审";
		if (wsssjl.contains("行政判决")) {
			qswszl = "行政判决书";
			qsjafs = "判决";
		} else if (wsssjl.contains("行政裁定")) {
			qswszl = "行政裁定书";
			qsjafs = "裁定";
		} else if (xzesqsah!=null) {
			if(xzesqsah.contains("重字")){
				qsjafs = "驳回起诉";
			}else if(xzesqsah.contains("终字")){
				qsjafs = "发回重审";
			}
		} 
		wsssjlModel.setQswszl(qswszl);
		wsssjlModel.setQsjafs(qsjafs);
		wsssjlModel.setQssj(qssj);

		// 解析审判组织
		String spzz = null;
		if (wsssjl.contains("合议庭")) {
			spzz = "合议庭";
		} else if (wsssjl.contains("审判员")) {
			if (wsssjl.contains("代理审判员") || wsssjl.contains("陪审员")) {
				// spzz = "合议庭";
			}
		} else {
			spzz = "独任庭";
		}
		wsssjlModel.setSpzz(spzz);

		// 解析被告主要领导出庭
		String bgzyldct = "否";
		for (int i = 0; i < contentArray.length; i++) {
			if (!contentArray[i].contains("未出庭")
					&& (contentArray[i].contains("出庭"))
					|| contentArray[i].contains("到庭")) {
				if (contentArray[i].contains("被告")
						&& contentArray[i].contains("法定代表")) {
					bgzyldct = "是";
				}
			}
		}
		wsssjlModel.setBgzyldct(bgzyldct);

		return wsssjlModel;
	}

}
