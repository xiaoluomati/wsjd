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
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsssjlZkjlModel;
import nju.software.wsjx.service.model.WsssjlZkzmModel;
import nju.software.wsjx.util.DateUtil;
/**
 * 刑事二审诉讼记录解析规则
 * @author wangzh
 *
 */
public class XsesSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		// System.out.println(wsssjl);
		WsssjlModel wsssjlModel = new WsssjlModel();
		ArrayList<WsssjlZkjlModel> zkjlModellist = new ArrayList<WsssjlZkjlModel>();
		wsssjlModel.setWsssjlZkjl(zkjlModellist);
		// String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
		String[] content = wsssjl.split("\\.|。");
		String[] contentArray = wsssjl.split("\\.|。|、|;|；");
		String[] Arraycontent = wsssjl.split("\\.|。|、|;|；|,|，");
		String zkzm = null;
		String qszay = null;
		String wzzm = null;
		String zmdm = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			ArrayList<String> ayList = new ArrayList<String>();
			String wsssjlZkzm = wsssjl;
			if(wsssjl.contains("一案")){
				wsssjlZkzm = wsssjl.substring(0,wsssjl.indexOf("一案"));
			}
			String[] wsssjlBegin = wsssjlZkzm.substring(0,
					wsssjlZkzm.lastIndexOf("罪") + 1).split("罪");
			String str = "";
			is = getClass().getClassLoader().getResourceAsStream("enum/xszm_dm.txt");
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				ayList.add(str);
			}
			ArrayList<Integer> keylist = new ArrayList<Integer>();// 指控记录计数位
			for (int i = 0; i < wsssjlBegin.length; i++) {
				if (wsssjlBegin[i].contains("犯")) {
					keylist.add(i);
				}
			}
			keylist.add(wsssjlBegin.length);
			if (keylist.size() == 2) {
				// 如果诉讼记录指控信息只有一个"犯"，说明只有一组指控记录
				WsssjlZkjlModel zkjlModel = new WsssjlZkjlModel();// 新建一个指控记录对象
				ArrayList<WsssjlZkzmModel> zkzmModellist = new ArrayList<WsssjlZkzmModel>();// 新建一个指控记录list对象
				zkjlModel.setZkzmModelist(zkzmModellist);
				// 解析相关人姓名
				ArrayList<String> xgr = new ArrayList<String>();
				for (WssscyrModel model : wssscyrModellist) {
					if (model.getSscyr() != null) {
						if (wsssjlBegin[0].contains(model.getSscyr())) {
							xgr.add(model.getSscyr());
						}
					}
				}
				zkjlModel.setXgr(xgr);
				//
				for (int j = 0; j < wsssjlBegin.length; j++) {
					if (wsssjlBegin[j].contains("犯")) {
						// 如果字符串出现"犯"，说明是罪名是出现在"犯"之后
						// 解析指控罪名
						WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
						zkzm = wsssjlBegin[j].substring(
								wsssjlBegin[j].indexOf("犯") + 1,
								wsssjlBegin[j].length())
								+ "罪";
						wzzm = wsssjlBegin[j].substring(
								wsssjlBegin[j].indexOf("犯") + 1,
								wsssjlBegin[j].length())
								+ "罪";
						zkzmModel.setZkzm(zkzm);
						zkzmModel.setWzzm(wzzm);
						for (int i = 0; i < ayList.size(); i++) {
							// 提取的案由与‘罪名—罪名代码’表进行匹配，找到完整罪名和罪名代码
							if (wsssjlBegin[j].contains(ayList.get(i)
									.substring(4, ayList.get(i).length()))) {
								// zkzmModel = new WsssjlZkzmModel();
								zkzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "罪";
								wzzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "罪";
								zmdm = ayList.get(i).substring(0, 4).trim();
								zkzmModel.setZkzm(zkzm);
								zkzmModel.setWzzm(wzzm);
								zkzmModel.setZmdm(zmdm);
								break;
							}
						}
						zkjlModel.getZkzmModelist().add(zkzmModel);
					}
					// 如果如果字符串没有"犯",说明是字符串是以标点符号开头的，并且标点后面的就是罪名
					else if (!wsssjlBegin[j].contains("犯")) {
						WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
						zkzm = wsssjlBegin[j].substring(1,
								wsssjlBegin[j].length())
								+ "罪";
						wzzm = wsssjlBegin[j].substring(1,
								wsssjlBegin[j].length())
								+ "罪";
						zkzmModel.setZkzm(zkzm);
						zkzmModel.setWzzm(wzzm);
						for (int i = 0; i < ayList.size(); i++) {
							// 提取的案由与‘罪名—罪名代码’表进行匹配，找到完整罪名和罪名代码
							if (wsssjlBegin[j].contains(ayList.get(i)
									.substring(4, ayList.get(i).length()))) {
								zkzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "罪";
								wzzm = ayList.get(i).substring(4,
										ayList.get(i).length())
										+ "罪";
								zmdm = ayList.get(i).substring(0, 4).trim();
								zkzmModel.setZkzm(zkzm);
								zkzmModel.setWzzm(wzzm);
								zkzmModel.setZmdm(zmdm);
								break;
							}
						}
						zkjlModel.getZkzmModelist().add(zkzmModel);
					}
				}
				if(!wsssjlModel.getWsssjlZkjl().contains(zkjlModel)){
					wsssjlModel.getWsssjlZkjl().add(zkjlModel);
				}
			} else if (keylist.size() > 2) {
				// 如果诉讼记录指控信息有多个"犯"，出现"犯"的字符串前一句就是上一组指控记录的结束
				for (int k = 1; k < keylist.size(); k++) {
					WsssjlZkjlModel zkjlModel = new WsssjlZkjlModel();// 新建一个指控记录对象
					ArrayList<WsssjlZkzmModel> zkzmModellist = new ArrayList<WsssjlZkzmModel>();// 新建一个指控记录list对象
					zkjlModel.setZkzmModelist(zkzmModellist);
					// 解析相关人姓名
					ArrayList<String> xgr = new ArrayList<String>();
					for (WssscyrModel model : wssscyrModellist) {
						if (wsssjlBegin[keylist.get(k - 1)].contains(model
								.getSscyr())) {
							xgr.add(model.getSscyr());
						}
					}
					zkjlModel.setXgr(xgr);
					// 循环zkzm
					zkzm: for (int j = keylist.get(k - 1); j < keylist.get(k); j++) {
						if (wsssjlBegin[j].contains("犯")) {
							// 如果字符串出现"犯"，说明是罪名是出现在"犯"之后
							// System.out.println(wsssjlBegin[j] + "--第一个罪-" +
							// k);
							// 解析指控罪名
							WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
							zkzm = wsssjlBegin[j].substring(
									wsssjlBegin[j].indexOf("犯") + 1,
									wsssjlBegin[j].length()) + "罪";
							wzzm = wsssjlBegin[j].substring(
									wsssjlBegin[j].indexOf("犯") + 1,
									wsssjlBegin[j].length()) + "罪";
							zkzmModel.setZkzm(zkzm);
							zkzmModel.setWzzm(wzzm);
							for (int i = 0; i < ayList.size(); i++) {
								// 提取的案由与‘罪名—罪名代码’表进行匹配，找到完整罪名和罪名代码
								if (wsssjlBegin[j].contains(ayList.get(i)
										.substring(4, ayList.get(i).length()))) {
									// zkzmModel = new WsssjlZkzmModel();
									zkzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "罪";
									wzzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "罪";
									zmdm = ayList.get(i).substring(0, 4).trim();
									zkzmModel.setZkzm(zkzm);
									zkzmModel.setWzzm(wzzm);
									zkzmModel.setZmdm(zmdm);
									break;
								}
							}
							zkjlModel.getZkzmModelist().add(zkzmModel);
						}
						// 如果如果字符串没有"犯",说明是字符串是以标点符号开头的，并且标点后面的就是罪名
						else if (!wsssjlBegin[j].contains("犯")
								&& wsssjlBegin[j].length() != 0) {
							// System.out.println(wsssjlBegin[j] + "--罪-" + k);
							WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
							// System.out.println(wsssjlBegin[j]);
							zkzm = wsssjlBegin[j].substring(1,
									wsssjlBegin[j].length())
									+ "罪";
							wzzm = wsssjlBegin[j].substring(1,
									wsssjlBegin[j].length())
									+ "罪";
							// System.out.println(zkzm);
							zkzmModel.setZkzm(zkzm);
							zkzmModel.setWzzm(wzzm);
							for (int i = 0; i < ayList.size(); i++) {
								// 提取的案由与‘罪名—罪名代码’表进行匹配，找到完整罪名和罪名代码
								if (wsssjlBegin[j].contains(ayList.get(i)
										.substring(4, ayList.get(i).length()))) {
									zkzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "罪";
									wzzm = ayList.get(i).substring(4,
											ayList.get(i).length())
											+ "罪";
									zmdm = ayList.get(i).substring(0, 4).trim();
									zkzmModel.setZkzm(zkzm);
									zkzmModel.setWzzm(wzzm);
									zkzmModel.setZmdm(zmdm);
									break;
								}
							}
							zkjlModel.getZkzmModelist().add(zkzmModel);
						}
					}
					if(!wsssjlModel.getWsssjlZkjl().contains(zkjlModel)){
						wsssjlModel.getWsssjlZkjl().add(zkjlModel);
					}
				}
			}

			// 解析起诉主案由
			// if (wsssjlModel.getWsssjlZkjl() != null) {
			// // System.out.println(wsssjlModel.getWsssjlZkjl().get(0)!=null);
			// if (wsssjlModel.getWsssjlZkjl().get(0) != null) {
			// if (wsssjlModel.getWsssjlZkjl().get(0).getZkzmModelist()
			// .get(0).getZkzm() != null)
			// qszay = wsssjlModel.getWsssjlZkjl().get(0)
			// .getZkzmModelist().get(0).getZkzm();
			// wsssjlModel.setQszay(qszay);
			// }
			// }
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

		// 解析前审裁判时间
		String qscpsj = null;
		for (int i = 0; i < Arraycontent.length; i++) {
			if (Arraycontent[i].contains("作出")
					&& Arraycontent[i].contains("字")
					&& Arraycontent[i].contains("刑")
					&& (Arraycontent[i].contains("判决") || Arraycontent[i]
							.contains("裁定"))) {
				qscpsj = getDate(Arraycontent[i]);
				break;
			}
		}
		if (qscpsj != null) {
			if (qscpsj.contains("二") || qscpsj.contains("O")) {
				qscpsj = DateUtil.convertToCNDate(qscpsj);
			}
		}
		wsssjlModel.setQscpsj(qscpsj);

		// 解析刑事二审前审案号
		String qsah = null;
		String xsesqsah = null;
		String reg1 = "[（\\(（〔]\\d{4}[）\\)〕）].+?[^鉴]字第?\\d+-?\\d+号";
		String reg2 = "\\W{1,30}";
		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		for (int i = 0; i < Arraycontent.length; i++) {
			Matcher m1 = p1.matcher(Arraycontent[i]);
			while (m1.find()) {
				qsah = m1.group();
				Matcher m2 = p2.matcher(qsah);
				while (m2.find()) {
					xsesqsah = qsah;
					wsssjlModel.setXsesqsah(xsesqsah);
					break;
				}
				break;
			}
		}
		wsssjlModel.setXsesqsah(xsesqsah);
		// 解析二审案件来源
		String qsajyl = "检察院公诉";
		if (xsesqsah != null) {
			if (xsesqsah.contains("重字")) {
				qsajyl = "上级法院发回重审";
			} else if (xsesqsah.contains("终字")) {
				qsajyl = "上诉";
			}
		}
		if (wsssjl.contains("自诉")) {
			qsajyl = "自诉";
		}
		wsssjlModel.setQsajyl(qsajyl);

		// 解析前审法院简称，前审案件立案年度，前审案号顺序号
		String qsland = null;
		String qsfyjc = null;
		String qsahsxh = null;
		if (xsesqsah != null) {
			qsland = xsesqsah.substring(1, 5);
			int end = 0;
			if (xsesqsah.contains("刑")) {
				if (xsesqsah.indexOf("刑") - 1 == xsesqsah.indexOf("知")) {
					end = xsesqsah.indexOf("知");
				} else if (xsesqsah.contains("立") && xsesqsah.contains("经")) {
					end = xsesqsah.indexOf("立");
				} else if (xsesqsah.indexOf("刑") - 1 == xsesqsah.indexOf("立")) {
					end = xsesqsah.indexOf("立");
				} else if (xsesqsah.contains("经")) {
					end = xsesqsah.indexOf("经");
				} else {
					end = xsesqsah.indexOf("刑");
				}
			} else if (xsesqsah.contains("立") && xsesqsah.contains("经")) {
				end = xsesqsah.indexOf("立");
			} else if (xsesqsah.contains("立")) {
				end = xsesqsah.indexOf("立");
			} else if (xsesqsah.contains("经")) {
				end = xsesqsah.indexOf("经");
			}
			if (end >= 6) {
				qsfyjc = xsesqsah.substring(6, end);
			}
			if (xsesqsah.contains("第")) {
				qsahsxh = xsesqsah.substring(xsesqsah.lastIndexOf("第") + 1,
						xsesqsah.lastIndexOf("号"));
			} else {
				qsahsxh = xsesqsah.substring(xsesqsah.lastIndexOf("字") + 1,
						xsesqsah.lastIndexOf("号"));
			}
		}
		wsssjlModel.setQsland(qsland);
		wsssjlModel.setQsfyjc(qsfyjc);
		wsssjlModel.setQsahsxh(qsahsxh);

		// 解析案件涉及
		String ajsj = null;
		if (wsssjl.contains("毒品")) {
			ajsj = "涉毒";
		} else if (wsssjl.contains("枪")) {
			ajsj = "涉枪";

		} else if (wsssjl.contains("黑社会")) {
			ajsj = "涉黑";

		} else if (wsssjl.contains("国家安全")) {
			ajsj = "涉国家安全";

		} else if (wsssjl.contains("法轮功")) {
			ajsj = "涉法轮功";

		} else if (wsssjl.contains("知识产权")) {
			ajsj = "涉知识产权";

		}
		wsssjlModel.setAjsj(ajsj);

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

		// 解析原公诉机关
		String qsgsjg = null;
		for (int i = 0; i < Arraycontent.length; i++) {
			if (Arraycontent[i].contains("检察院")
					&& Arraycontent[i].contains("审理")) {
				if (Arraycontent[i].indexOf("审理") < Arraycontent[i]
						.indexOf("检察院")) {
					if (Arraycontent[i].indexOf("审理") + 2 == Arraycontent[i]
							.indexOf("的")
							|| Arraycontent[i].indexOf("审理") + 2 == Arraycontent[i]
									.indexOf("由")) {
						if (Arraycontent[i].contains("分院")
								&& Arraycontent[i].indexOf("检察院") < Arraycontent[i]
										.indexOf("分院")) {
							qsgsjg = Arraycontent[i].substring(
									Arraycontent[i].indexOf("审理") + 3,
									Arraycontent[i].indexOf("分院") + 2);
							break;
						} else {
							qsgsjg = Arraycontent[i].substring(
									Arraycontent[i].indexOf("审理") + 3,
									Arraycontent[i].indexOf("检察院") + 3);
							break;
						}
					} else if (Arraycontent[i].contains("分院")
							&& Arraycontent[i].indexOf("检察院") < Arraycontent[i]
									.indexOf("分院")) {
						qsgsjg = Arraycontent[i].substring(
								Arraycontent[i].indexOf("审理") + 3,
								Arraycontent[i].indexOf("分院") + 2);
						break;
					} else {
						qsgsjg = Arraycontent[i].substring(
								Arraycontent[i].indexOf("审理") + 2,
								Arraycontent[i].indexOf("检察院") + 3);
						break;
					}
				}
			}
		}
		wsssjlModel.setQsgsjg(qsgsjg);

		// 解析前审文书种类，前审结案方式，前审审级
		String qswszl = null;
		String qsjafs = null;
		String qssj = "一审";
		if (wsssjl.contains("刑事判决")) {
			qswszl = "刑事判决书";
			qsjafs = "判决";
		} else if (wsssjl.contains("刑事裁定")) {
			qswszl = "刑事裁定书";
			qsjafs = "裁定";
		} else if (xsesqsah != null) {
			if (xsesqsah.contains("重字")) {
				qsjafs = "驳回起诉";
			} else if (xsesqsah.contains("终字")) {
				qsjafs = "发回重审";
			}
		}
		wsssjlModel.setQswszl(qswszl);
		wsssjlModel.setQsjafs(qsjafs);
		wsssjlModel.setQssj(qssj);

		// 解析前审判决
		String qspj = null;
		for (int i = 0; i < content.length; i++) {
			if(!content[i].contains("不服")||!content[i].contains("上诉")){
				if (content[i].contains("刑事判决")) {
					qspj = content[i].substring(content[i].indexOf("刑事判决"));
				} else if (content[i].contains("刑事附带民事判决")) {
					qspj = content[i].substring(content[i].indexOf("刑事附带民事判决"));
				} else if (content[i].contains("据此")) {
					qspj = content[i].substring(content[i].indexOf("据此"));
				} else if (content[i].contains("综上")) {
					qspj = content[i].substring(content[i].indexOf("综上"));
				} else if (content[i].contains("为此")) {
					qspj = content[i].substring(content[i].indexOf("为此"));
				} else if (content[i].contains("依照")) {
					qspj = content[i].substring(content[i].indexOf("依照"));
				} else if (content[i].contains("依据")) {
					qspj = content[i].substring(content[i].indexOf("依据"));
				} else if (content[i].contains("根据")) {
					qspj = content[i].substring(content[i].indexOf("根据"));
				}
				else if (issurplus(content[i]) > -1) {
					qspj = qspj + "。" + content[i] + "。";
				} else if (isFzsurplus(content[i])) {
					qspj = qspj + "。" + content[i] + "。";
				} else if (content[i].contains("认定") || content[i].contains("徒刑")
						|| content[i].contains("死刑") || content[i].contains("罚金")
						|| content[i].contains("没收") || content[i].contains("驱逐出境")
						|| content[i].contains("剥夺军衔")|| content[i].contains("剥夺政治权利")
						|| content[i].contains("死缓")|| content[i].contains("拘役")
						|| content[i].contains("管制")) {
					qspj = qspj + "。" + content[i] + "。";
				}
			}
		}
		if(qspj != null){
			if(qspj.length()<10){
				qspj = null;
			}else if (qspj.lastIndexOf("。")!= qspj.length() - 1) {
				qspj += "。";
			}
		}
		wsssjlModel.setQspj(qspj);

		// 解析审判组织
		// 解析独任审判
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

		// 解析起诉日期
		String qsrq = null;
		for (int i = 0; i < contentArray.length; i++) {
			if (contentArray[i].contains("公诉")
					|| contentArray[i].contains("起诉")) {
				qsrq = getDate(contentArray[i]);
				break;
			}
		}
		wsssjlModel.setQsrq(qsrq);

		// 解析诉讼性质
		String ssxz = null;
		if (wsssjl.contains("侮辱") || wsssjl.contains("暴力干涉婚姻自由")
				|| wsssjl.contains("诽谤") || wsssjl.contains("虐待")
				|| wsssjl.contains("侵占")) {
			ssxz = "告诉才处理的案件";
		} else if (wsssjl.contains("故意伤害") || wsssjl.contains("非法侵入")
				|| wsssjl.contains("妨害通信自由") || wsssjl.contains("重婚")
				|| wsssjl.contains("遗弃") || wsssjl.contains("知识产权")
				|| wsssjl.contains("伪劣商品")) {
			ssxz = "被害人有证据证明的轻微刑事案件";
		}
		if (wsssjl.contains("公诉") || wsssjl.contains("检察院指控")) {
			if (wsssjl.contains("自诉")) {
				if (wsssjl.contains("转自诉")) {
					ssxz = "公诉转自诉案件";
				} else {
					ssxz = "既有公诉又有自诉";
				}
			}
			ssxz = "公诉";
		}
		wsssjlModel.setSsxz(ssxz);

		// 解析少年法庭
		String snft = "否";
		if (wsssjl.contains("少年法庭")) {
			snft = "是";
		}
		wsssjlModel.setSnft(snft);

		// 解析检察院建议延期审理
		String jcyjyyqsl = "否";
		if (wsssjl.contains("检察院") && wsssjl.contains("建议")
				&& wsssjl.contains("延期")) {
			jcyjyyqsl = "是";
		}
		wsssjlModel.setJcyjyyqsl(jcyjyyqsl);

		// 审理中提起附带民事诉讼
		String slztqfdmsss = "否";
		if (wsssjl.contains("附带民事")) {
			slztqfdmsss = "是";
			// 解析附带民事部分继续审理
			String msbfjxsl = "否";
			if (wsssjl.contains("")) {
				msbfjxsl = "是";
			}
			wsssjlModel.setMsbfjxsl(msbfjxsl);
		}
		wsssjlModel.setSlztqfdmsss(slztqfdmsss);

		// 解析出庭检察员姓名，检察员身份
		String jcy = null;
		String js = null;
		if (wsssjl.contains("出庭支持")) {
			if (wsssjl.contains("代理检察员")) {
				jcy = wsssjl.substring(wsssjl.indexOf("代理检察员") + 5,
						wsssjl.indexOf("出庭支持"));
				js = "代理检察员";
			} else if (wsssjl.contains("检察员")) {
				jcy = wsssjl.substring(wsssjl.indexOf("检察员") + 3,
						wsssjl.indexOf("出庭支持"));
				js = "检察员";
			}
		}
		wsssjlModel.setJcy(jcy);
		wsssjlModel.setJs(js);

		// 解析上诉或抗诉范围
		String sshksfw = null;
		if (wsssjl.contains("附带民事")) {
			sshksfw = "对刑事、民事判决均上（抗）诉";
		} else if (wsssjl.contains("自诉人") && wsssjl.contains("裁定")
				&& wsssjl.contains("上诉")) {
			sshksfw = "对驳回自诉裁定上诉";
		} else {
			sshksfw = "对刑事判决上（抗）诉";
		}
		wsssjlModel.setSshksfw(sshksfw);

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

	/**
	 * 判断字符串是否以中文数字+标点开头
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isFzsurplus(String content) {
		boolean b = false;
		String rexNu = "[一二三四五六七八九十]";
		Pattern patternNu = Pattern.compile(rexNu);
		if (content.length() >= 3)
			content = content.substring(0, 3);
		Matcher matcherNu = patternNu.matcher(content);
		if (matcherNu.find()
				&& (content.contains(".") || content.contains("、")
						|| content.contains("．") || content.contains("("))) {
			b = true;
		}
		return b;
	}

	/**
	 * 判断字符串是否以阿拉伯数字+标点开头
	 * 
	 * @param content
	 * @return
	 */
	public static int issurplus(String content) {
		int b = -1;
		String rexNu = "\\d{1,2}";
		Pattern patternNu = Pattern.compile(rexNu);
		if (content.length() >= 3)
			content = content.substring(0, 3);
		Matcher matcherNu = patternNu.matcher(content);
		if (matcherNu.find()
				&& (content.contains(".") || content.contains("、") || content
						.contains("．"))) {
			b = Integer.parseInt(matcherNu.group(0));
		}
		return b;
	}
}
