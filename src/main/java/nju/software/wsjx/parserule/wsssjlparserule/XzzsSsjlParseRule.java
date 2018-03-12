package nju.software.wsjx.parserule.wsssjlparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
/**
 * 行政再审诉讼记录解析规则
 * @author ningxuejiao
 *
 */
public class XzzsSsjlParseRule extends GeneralSsjlCommonRule implements
		SsjlParseRule {
public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) throws ParseException {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
		//解析开庭审理，开庭审理信息
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
		// 解析开庭日期,受理日期
	    ArrayList<String> ktrq = new ArrayList<String>();
		String slrq = null;
		for(int i=0;i<contentArray.length;i++){
			if(contentArray[i].contains("受理")){
				slrq=getDate(contentArray[i]);
			}else if(contentArray[i].contains("开庭")){
				ktrq.add(getDate(contentArray[i]));
				}
			}
		if(ktrq!=null){
				wsssjlModel.setKtrq(ktrq);
			}
		if (slrq != null) {
				wsssjlModel.setSlrq(slrq);
		//解析行政侵权行为种类
		String xzqqxwzl = null;
		if (wsssjl.contains("不履行") || wsssjl.contains("不作为")) {
			xzqqxwzl = "行政不作为";
			} else {
			xzqqxwzl = "行政乱作为";
			}
			wsssjlModel.setXzqqxwzl(xzqqxwzl);
		//解析行政行为种类
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
			// 解析前审案号
			String qsah = null;
			String xzzsqsah = null;
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
						xzzsqsah = qsah;
						if(xzzsqsah!= null){
							xzzsqsah = xzzsqsah.replace("（", "(");
							xzzsqsah = xzzsqsah.replace("）", ")");
						}					
						wsssjlModel.setXzesqsah(xzzsqsah);
						// System.out.println(xzesqsah);
						break;
					}
					break;
				}
			}
			if(xzzsqsah!= null){
				xzzsqsah = xzzsqsah.replace("（", "(");
				xzzsqsah = xzzsqsah.replace("）", ")");
			}	
			wsssjlModel.setXzesqsah(xzzsqsah);
			// 解析前审法院简称，前审案号立案时间
			String qsahlasj = null;
			String qsfyjc = null;
			if (xzzsqsah!= null) {
							qsahlasj= xzzsqsah.substring(1, 5);
							int end = 0;
							if (xzzsqsah.contains("行")) {
								if (xzzsqsah.indexOf("行") - 1 == xzzsqsah.indexOf("知")) {
									end = xzzsqsah.indexOf("知");
								} else if (xzzsqsah.contains("立") && xzzsqsah.contains("经")) {
									end = xzzsqsah.indexOf("立");
								} else if (xzzsqsah.indexOf("行") - 1 == xzzsqsah.indexOf("立")) {
									end = xzzsqsah.indexOf("立");
								} else if (xzzsqsah.contains("经")) {
									end = xzzsqsah.indexOf("经");
								} else {
									end = xzzsqsah.indexOf("行");
								}
							} else if (xzzsqsah.contains("立") && xzzsqsah.contains("经")) {
								end = xzzsqsah.indexOf("立");
							} else if (xzzsqsah.contains("立")) {
								end = xzzsqsah.indexOf("立");
							} else if (xzzsqsah.contains("经")) {
								end = xzzsqsah.indexOf("经");
							}
							if (end >= 6) {
								qsfyjc = xzzsqsah.substring(6, end);
							}
							
						}
						wsssjlModel.setQsahlasj(qsahlasj);
						wsssjlModel.setQsfyjc(qsfyjc);
			
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
			
			// 解析再审案件来源
			String qsajyl = "起诉";
			if(xzzsqsah!=null){
				if (xzzsqsah.contains("重字")) {
					qsajyl = "发回重审";
				} else if (xzzsqsah.contains("终字")) {
					qsajyl = "上诉";
				}
			}
			wsssjlModel.setQsajyl(qsajyl);
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
            // 解析前审文书种类，前审审级
			/*String qswszl = null;
			String qssj = "二审";
			if (wsssjl.contains("行政判决")) {
				qswszl = "行政判决书";
			   } else if (wsssjl.contains("行政裁定")) {
				qswszl = "行政裁定书";
				} else if (xzzsqsah!=null) {
				}
			wsssjlModel.setQswszl(qswszl);
			wsssjlModel.setQssj(qssj);*/
			//解析审判组织
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
        }
		return wsssjlModel;
		}
}

