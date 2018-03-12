package nju.software.wsjx.parserule.wsssjlparserule;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.util.DateUtil;
import nju.software.wsjx.util.FileUtil;
/**
 * 民事再审诉讼记录解析规则
 * @author ningxuejiao
 *
 */
public class MszsSsjlParseRule extends GeneralSsjlCommonRule implements
		SsjlParseRule {
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) throws ParseException {
		WsssjlModel wsssjlModel = new WsssjlModel();
		String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
		String[] Arraycontent = wsssjl.split("\\.|。|、|;|；|,|，");
		// 解析开庭审理,开庭审理信息
		String ktsl = "否";
		String ktslxx = null;
		if (wsssjl.contains("开庭")
				&& wsssjl.contains("审理")&&!wsssjl.contains("不开庭")) {
			ktsl = "是";
			if (wsssjl.contains("不公开")) {
				ktslxx = "不公开审理";
				} else {
				ktslxx = "公开审理";
			}
		}
		wsssjlModel.setKtsl(ktsl);
		wsssjlModel.setKtslxx(ktslxx);
		// 解析案由,案由代码，完整案由
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
				//解析案件由来与审理经过段
				//解析前审案号
				ArrayList<String> qsah = new ArrayList<String>();
				String reg1 = "[（\\(（〔]\\d{4}[）\\)〕）].+?[^鉴]字第?\\d+-?\\d+号";
				Pattern p1 = Pattern.compile(reg1);
				Matcher m1= p1.matcher(wsssjl);
				while (m1.find()) {
					qsah.add(m1.group());
				}
				wsssjlModel.setQsah(qsah);
				//解析再审审查案件案号
				ArrayList<String> zsscajah = new ArrayList<String>();
				String reg2 = "[（\\(（〔]\\d{4}[）\\)〕）].+?[^鉴]字第?\\d+-?\\d+号";
				Pattern p2 = Pattern.compile(reg2);
				Matcher m2 = p2.matcher(wsssjl);
				while (m2.find()) {
					qsah.add(m2.group());
				}
				wsssjlModel.setZsscajah(zsscajah);
				// 解析前审法院
				String qsfy = null;
				String fyjb = "基层";
				for (int i = 0; i < Arraycontent.length; i++) {
					if (Arraycontent[i].contains("人民法院")
							&& (Arraycontent[i].contains("审理")
									|| Arraycontent[i].contains("作出") || Arraycontent[i]
										.contains("于"))) {
						int key = Arraycontent[i].indexOf("人民法院") + 4;
						if (key == Arraycontent[i].indexOf("审理")
								|| key == Arraycontent[i].indexOf("作出")
								|| key == Arraycontent[i].indexOf("于")) {
							qsfy = Arraycontent[i].substring(0,
									Arraycontent[i].lastIndexOf("人民法院") + 4);
						}
						if (qsfy != null) {
							if (qsfy.contains("最高")) {
								fyjb = "最高级";
							} else if (qsfy.contains("高级")) {
								fyjb = "高级";
							} else if (qsfy.contains("中级")) {
								fyjb = "中级";
							}
						}
						break;
					}
				}
				wsssjlModel.setQsfy(qsfy);
				wsssjlModel.setFyjb(fyjb);
				// 解析标准法院名称
				String bzfymc = null;
				if (qsfy != null) {
					if (qsfy.contains("县") && qsfy.contains("省")) {
						if (qsfy.contains("市")) {
							bzfymc = qsfy.substring(qsfy.indexOf("市") + 1);
						} else {
							bzfymc = qsfy.substring(qsfy.indexOf("省") + 1);
						}
					} else if (qsfy.contains("县") && qsfy.contains("市")) {
						bzfymc = qsfy.substring(qsfy.indexOf("市") + 1);
					} else if (qsfy.contains("县") && qsfy.contains("自治区")) {
						bzfymc = qsfy.substring(qsfy.indexOf("自治区") + 3);
					} else if (qsfy.contains("市") && qsfy.contains("自治区")) {
						bzfymc = qsfy.substring(qsfy.indexOf("自治区") + 3);
					} else if (qsfy.contains("市") && qsfy.contains("省")) {
						bzfymc = qsfy.substring(qsfy.indexOf("省") + 1);
					} else {
						bzfymc = qsfy;
					}
				}
				wsssjlModel.setBzfymc(bzfymc);
				// 解析前审裁判时间
				String qscpsj = null;
				for (int i = 0; i < Arraycontent.length; i++) {
					if (Arraycontent[i].contains("作出")
							&& Arraycontent[i].contains("字")
							&& Arraycontent[i].contains("民")
							&& (Arraycontent[i].contains("判决") || Arraycontent[i]
									.contains("裁定"))) {
						qscpsj = getDate(Arraycontent[i]);
						break;
					}
				}
				if (qscpsj != null) {
					if (qscpsj.contains("再") || qscpsj.contains("O")) {
						qscpsj = DateUtil.convertToCNDate(qscpsj);
					}
				}
				wsssjlModel.setQscpsj(qscpsj);
				// 解析前审文书种类，前审审级
				String qswszl = null;
				String qssj = "二审";
				if (wsssjl.contains("民事判决")) {
					qswszl = "民事判决书";
					} else if (wsssjl.contains("民事裁定")) {
					qswszl = "民事裁定书";
					
				} 
				wsssjlModel.setQswszl(qswszl);
				wsssjlModel.setQssj(qssj);
				// 解析案件来源
				String ajly = null;
				if(wsssjl.contains("下级")){
					if(wsssjl.contains("报请")) {
						ajly="下级法院报请";
					}else if(wsssjl.contains("生效")){
						ajly="发现下级法院生效裁判错误";
				}}else if(wsssjl.contains("不服")) {
					if(wsssjl.contains("判决")) {
						ajly="对判决不服";
					}else if(wsssjl.contains("决定不服")){
					ajly="对决定不服";
				    }else if(wsssjl.contains("管辖")) {
					ajly="对管辖异议裁定不服";
				    }else if(wsssjl.contains("受理")) {
					ajly="对裁定不予受理不服";
				    }else if(wsssjl.contains("驳回")) {
					ajly="对裁定驳回起诉时间（申请）不服";
				    }else if(wsssjl.contains("决议书")) {
					ajly="对决议书不服";
				}}else if(wsssjl.contains("上级")) {
					if(wsssjl.contains("指定再审")) {
					    ajly="上级法院指定再审";
					}else if(wsssjl.contains("指令再审")) {
					    ajly="上级法院指令再审";
					}else if(wsssjl.contains("移交")) {
						ajly="上级法院移交管辖";
					}else if(wsssjl.contains("指定")) {
						ajly="上级法院指定管辖";
					}else if(wsssjl.contains("审理")) {
				        ajly="上级法院指令审理";
					}else if(wsssjl.contains("受理")) {
						ajly="上级法院指令受理";
					}else if(wsssjl.contains("重申")) {
						ajly="上级法院发回重申";
					}
				}else if(wsssjl.contains("异议")) {
					if(wsssjl.contains("利害")) {
						ajly="利害关系人异议";
					}else if(wsssjl.contains("原案")) {
						ajly="原案当事人异议";
					}else if(wsssjl.contains("指定")) {
						ajly="指定监护人异议";
					}
				}else if(wsssjl.contains("请求")) {
					ajly="当事人请求";
				}else if(wsssjl.contains("当事人起诉")) {
					ajly="当事人起诉";
				}else if(wsssjl.contains("当事人上诉")) {
					ajly="当事人上诉";
				}else if(wsssjl.contains("抗诉")) {
					ajly="抗诉";
				}else if(wsssjl.contains("职权")) {
					if(wsssjl.contains("监督")) {
						ajly="依职权审查";
					}else {
						ajly="依职权审查";
					}
				}else if(wsssjl.contains("二审程序")) {
					ajly="再审撤销按撤诉处理裁定并恢复二审程序";
				}else if(wsssjl.contains("支付令")) {
					ajly="因支付令失效转入";
				}else if(wsssjl.contains("移送")) {
					ajly="其他法院裁定移送管辖";
				}else if(wsssjl.contains("第三人")){
					ajly="第三人撤销之诉案件所作裁判（含不予受理或驳回起诉裁定）";
				}else if(wsssjl.contains("代位")) {
					ajly="对确认代位受偿权利或驳回申请裁定";
				}else if(wsssjl.contains("发回")) {
					ajly="再审发回重审";
				}else if(wsssjl.contains("检察")) {
					ajly="检察院提出建议";
				}else if(wsssjl.contains("移交")) {
					ajly="其他法院移交";
				}else if(wsssjl.contains("破产")) {
					ajly="重整或和解程序裁定宣告破产";	
				}else if(wsssjl.contains("本院")) {
					 if(wsssjl.contains("提审")) {
						 ajly="本院提审";
					 }else if(wsssjl.contains("再审")) {
					 ajly="本院再审";
		             }else if(wsssjl.contains("生效裁判")) {
					 ajly="发现本院生效裁判错误";
				    }else if(wsssjl.contains("提级")){
					 ajly="本院提级管辖";}
					}else if (wsssjl.contains("申请")){
						if(wsssjl.contains("当事人")) {
							ajly="当事人申请";
						}else if(wsssjl.contains("指定")) {
							ajly="指定代管人申请";
						}else if(wsssjl.contains("申请再审")) {
							ajly="申请再审";
						}
				}else {
					ajly="其他";
				}
				wsssjlModel.setAjly(ajly);
				//解析审判组织
				String spzz = null;
				String drsp = null;
				if (wsssjl.contains("合议庭")) {
					spzz = "合议庭";
					drsp = "否";
				} else if (wsssjl.contains("审判员")) {
					if (wsssjl.contains("代理审判员") || wsssjl.contains("陪审员")) {
						// spzz = "合议庭";
						// drsp = "否";
					}
				} else {
					spzz = "独任庭";
					drsp = "是";
				}
				wsssjlModel.setSpzz(spzz);
				wsssjlModel.setDrsp(drsp);
				//
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
					int beginIndex = wsssjl.indexOf("开庭");
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
											if (model.getSscyr() != null) {
												if (wsssjl.substring(beginIndex, index)
														.contains(model.getSscyr())) {
													if (!ctrmap.containsKey((model
															.getSscyr()))) {
														ctrmap.put(model.getSscyr(),
																model.getSssf());
													}
												} else if (wsssjl.substring(index,
														wsssjl.lastIndexOf("到庭"))
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
				return wsssjlModel;
			

				



		
			
	}

}
