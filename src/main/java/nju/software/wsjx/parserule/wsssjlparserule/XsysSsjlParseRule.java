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

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsssjlZkjlModel;
import nju.software.wsjx.service.model.WsssjlZkzmModel;
import nju.software.wsjx.util.FileUtil;
/**
 * 刑事一审诉讼记录解析规则
 * @author wangzh
 *
 */
public class XsysSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{
	public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist,
			String wsssjl) {
		// System.out.println(wsssjl);
		WsssjlModel wsssjlModel = new WsssjlModel();
		ArrayList<WsssjlZkjlModel> zkjlModellist = new ArrayList<WsssjlZkjlModel>();
		wsssjlModel.setWsssjlZkjl(zkjlModellist);
		// String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
		String[] contentArray = wsssjl.split("\\.|。|、|;|；");
		ArrayList<String> zkjl = new ArrayList<String>();
		String zkzm = null;
		String qszay = null;
		String wzzm = null;
		String zmdm = null;
		FileUtil fileUtil = new FileUtil();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			ArrayList<String> ayList = new ArrayList<String>();
			String[] wsssjlBegin = wsssjl.substring(0,
					wsssjl.lastIndexOf("罪") + 1).split("罪");
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
				wsssjlModel.getWsssjlZkjl().add(zkjlModel);
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
						else if (!wsssjlBegin[j].contains("犯")) {
							// System.out.println(wsssjlBegin[j] + "--罪-" + k);
							WsssjlZkzmModel zkzmModel = new WsssjlZkzmModel();
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
					wsssjlModel.getWsssjlZkjl().add(zkjlModel);
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
		// 解析刑事一审案件来源
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

		// 解析一审案件适用程序
		String ysajsycx = "普通程序";
		String jyzpt = "否";
		String jysyjycx = "无";
		if (wsssjl.contains("简易程序") || wsssjl.contains("小额诉讼程序")) {
			if (wsssjl.contains("不宜适用简易")) {
				ysajsycx = "普通程序";
			} else if (wsssjl.contains("建议适用简易")) {
				ysajsycx = "简易程序";
				jysyjycx = "是";
			} else {
				ysajsycx = "简易程序";
			}
		}
		if (wsssjl.contains("转为普通") || wsssjl.contains("转入普通")) {
			ysajsycx = "普通程序";
			jyzpt = "是";
		}
		wsssjlModel.setYsajsycx(ysajsycx);
		wsssjlModel.setJyzpt(jyzpt);
		wsssjlModel.setJysyjycx(jysyjycx);

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
		if (wsssjl.contains("公诉") || wsssjl.contains("起诉书")) {
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
		// 解析公诉机关，公诉案号
		// 这段文字的写作规律是:XXX检察院以XX号起诉书
		String gsjg = null;
		String gsah = null;
		if (wsssjl.contains("检察院") && wsssjl.contains("起诉书")) {
			String str = wsssjl.substring(0, wsssjl.indexOf("起诉书"));
			if (str.contains("由")) {
				gsjg = str.substring(str.indexOf("由") + 1,
						str.lastIndexOf("检察院") + 3);
			} else {
				gsjg = str.substring(0, str.lastIndexOf("检察院") + 3);
			}
			if (str.contains("以")) {
				gsah = str.substring(str.indexOf("以") + 1, str.length());
			}
			// else{
			// gsah = str.substring(str.indexOf("检察院")+1, str.length());
			// }
			// System.out.println(gsjg);
			// System.out.println(gsah);
		}
		wsssjlModel.setGsjg(gsjg);
		wsssjlModel.setGsah(gsah);

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

		// 解析到庭与未到庭当事人信息
		String[] content = wsssjl.split("\\.|。|;|；");
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
										if(!model.getSscyr().contains("检察院")){
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
							}
							// 未出庭人信息在前，到庭人信息在后
							else {
								for (WssscyrModel model : wssscyrModellist) {
									if (model.getSscyr() != null) {
										if(!model.getSscyr().contains("检察院")){
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
				}
				// 文中只出现已出庭人信息，没有出现未出庭人信息
				if (index == -1) {
					for (WssscyrModel model : wssscyrModellist) {
						if (model.getSscyr() != null) {
							if(!model.getSscyr().contains("检察院")){
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
				}
			} else if (beginIndex != -1) {
				// 文中只出现未出庭人信息，没有出现已出庭人信息
				for (int i = 0; i < qxgjc.size(); i++) {
					if (wsssjl.contains(qxgjc.get(i))) {
						index = wsssjl.lastIndexOf(qxgjc.get(i));
						for (WssscyrModel model : wssscyrModellist) {
							if (model.getSscyr() != null) {
								if(!model.getSscyr().contains("检察院")){
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
		}
		wsssjlModel.setQxrxx(qxrmap);
		wsssjlModel.setCtrxx(ctrmap);
		return wsssjlModel;
	}
}
