package nju.software.wsjx.parserule.wsssjlparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

/**
 * 行政一审诉讼记录解析
 * @author wangzh
 *
 */
public class XzysSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

	/**
	 * hufk
	 * @param wssscyrModellist
	 * @param wsssjl 文书诉讼记录
	 * @return
	 */
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
		String[] Arraycontent = wsssjl.split("\\.|。|;|；");
		// 解析行政案由
		String ay = null;
		if (wssscyrModellist != null && wsssjl != null) {
			// 规则：ssjl，第一行，去除括号，最后一个当事人到最后（不包括一案）的内容
			String content = "";
			content = WsAnalyse.getContent(wsssjl);
		//	System.out.println(wsssjl);
			int prefix = 0;
			int suffix = content.indexOf("一案");
		    suffix=suffix==-1?content.length():suffix;
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
				}
				if (prefix < suffix) {
					ay = content.substring(prefix, suffix);
				}
				if (ay != null) {
					wsssjlModel.setAy(ay);
				}
			//	System.out.println(wsssjlModel.getAy());
			}
		}
		// 解析行政一审案件来源
		String ysajly = null;
		if (wsssjl.contains("又起诉") || wsssjl.contains("再次起诉")
				|| wsssjl.contains("再行起诉") || wsssjl.contains("再次诉讼")
				|| wsssjl.contains("曾诉")) {
			ysajly = "重新起诉";
		} else if (wsssjl.contains("重审") || wsssjl.contains("重新审理")) {
			ysajly = "上级法院撤销一审判决发回重审";
		} else if (wsssjl.contains("移送本院") || wsssjl.contains("移送至本院")) {
			if (wsssjl.contains("上级")) {
				ysajly = "上级法院移送";
			} else if (wsssjl.contains("下级")) {
				ysajly = "下级法院请求移送";
			} else {
				ysajly = "同级法院移送";
			}
		} else if (wsssjl.contains("提级管辖")) {
			ysajly = "提级管辖";
		} else if (wsssjl.contains("指定管辖")) {
			ysajly = "上级法院指定管辖";
		} else if (wsssjl.contains("不予受理") && wsssjl.contains("指令")) {
			ysajly = "上级法院撤销一审不予受理裁定指令立案受理";
		} else if (wsssjl.contains("管辖异议") && wsssjl.contains("指令")) {
			ysajly = "上级法院撤销一审管辖异议裁定指令管辖";
		} else {
			ysajly = "新收";
		}
		wsssjlModel.setYsajly(ysajly);
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
									if (model.getSscyr() != null) {
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
														.lastIndexOf("到庭"),
												index).contains(
												model.getSscyr())) {
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
						if (model.getSscyr() != null) {
							if (wsssjl.substring(beginIndex,
									wsssjl.lastIndexOf("到庭")).contains(
									model.getSscyr())) {
								if (!ctrmap.containsKey((model.getSscyr()))) {
									ctrmap.put(model.getSscyr(),
											model.getSssf());
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
							if (model.getSscyr() != null) {
								if (wsssjl.substring(beginIndex, index)
										.contains(model.getSscyr())) {
									qxrmap.put(model.getSscyr(),
											model.getSssf());
								}
							}
						}
					}
				}
			}
		}
		wsssjlModel.setQxrxx(qxrmap);
		wsssjlModel.setCtrxx(ctrmap);

		// 解析行政一审行政行为种类
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
		} else {
			xzxwzl = "其他行政行为";
		}
		wsssjlModel.setXzxwzl(xzxwzl);

		String xzqqxwzl = null;
		if (wsssjl.contains("不履行") || wsssjl.contains("不作为")) {
			xzqqxwzl = "行政不作为";
		} else {
			xzqqxwzl = "行政乱作为";
		}
		wsssjlModel.setXzqqxwzl(xzqqxwzl);

		// 解析被告主要领导出庭
		String bgzyldct = "否";
		for (int j = 0; j < Arraycontent.length; j++) {
			if (!Arraycontent[j].contains("未出庭")
					&& !Arraycontent[j].contains("未到庭")
					&& (Arraycontent[j].contains("出庭") || Arraycontent[j]
							.contains("到庭"))) {
				String[] content = Arraycontent[j].split(",|，|、");
				for (int i = 0; i < content.length; i++) {
					if (content[i].contains("被告")
							&& (content[i].contains("法定代表")
									|| content[i].contains("站长")
									|| content[i].contains("法人")
									|| content[i].contains("局长")
									|| content[i].contains("行长")
									|| content[i].contains("镇长")
									|| content[i].contains("乡长")
									|| content[i].contains("县长")
									|| content[i].contains("市长")
									|| content[i].contains("书记")
									|| content[i].contains("村长")
									|| content[i].contains("总经理")
									|| content[i].contains("部长")
									|| content[i].contains("负责人")
									|| content[i].contains("主任")
									|| content[i].contains("董事")
									|| content[i].contains("队长"))
							&& !content[i].contains("的委托")) {
						bgzyldct = "是";
						break;
					}
				}
			}
		}
		wsssjlModel.setBgzyldct(bgzyldct);

		return wsssjlModel;
	}

}
