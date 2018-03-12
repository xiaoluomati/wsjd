package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFdlxModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcZdlxModel;
import nju.software.wsjx.parserule.wssscyrparserule.SscyrParseRule;
import nju.software.wsjx.parserule.wssscyrparserule.XsesSscyrParseRule;
/**
 * 刑事二审裁判分析过程解析
 * @author wangzh
 *
 */
public class XsesCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule{
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		List<String> cpfxgc = wsAnalyse.getCpfxgc();
		XsesSscyrParseRule xsesSscyrParseRule = new XsesSscyrParseRule();
		List<WssscyrModel> wssscyrModellist = xsesSscyrParseRule.jxWssscyrModelList(wsAnalyse);
		
		WscpfxgcModel wscpfxgcModel = new WscpfxgcModel();
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(cpfxgc
				.get(cpfxgc.size() - 1));
		int index = 0;
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("综上")) {
				index = i;
				break;
			} else if (contentlist.get(i).contains("依照")
					|| contentlist.get(i).contains("依据")
					|| contentlist.get(i).contains("根据")) {
				index = i;
			}
		}
		// 解析法条名称
		String ftString = "";// 法条
		for (int j = index; j < contentlist.size(); j++) {
			ftString += contentlist.get(j);
		}
		ftString = delUrl(ftString);
		String[] ftfz = ftString.split("《");
		//删除数组最后一个里面的规定等字符
		ftfz[ftfz.length-1] = delGd(ftfz[ftfz.length-1]);
		ArrayList<WscpfxgcFtModel> ftModellist = new ArrayList<WscpfxgcFtModel>();
		wscpfxgcModel.setFtModellist(ftModellist);
		for (int j = 0; j < ftfz.length; j++) {
			String content = ftfz[j];
			if (content.indexOf("》") != -1) {
				WscpfxgcFtModel ftModel = new WscpfxgcFtModel();
				String flftmc = content.substring(0, content.indexOf("》"));
				ftModel.setFlftmc(flftmc);
				//获取条目款目
				ftModel.setFtMap(getTmkm(content));
				wscpfxgcModel.getFtModellist().add(ftModel);
			}
		}
		// 结案方式类型
		String lastContent = contentlist.get(contentlist.size() - 1);
		if (lastContent != null) {
			int jaindex = lastContent.indexOf("如下");
			if (jaindex != -1) {
				String jafslx = lastContent.substring(jaindex - 2, jaindex);
				wscpfxgcModel.setJafslx(jafslx);
			}
		}
		// 解析刑事二审案件来源
		String ysajly = null;
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("又起诉")
					|| contentlist.get(i).contains("再次起诉")
					|| contentlist.get(i).contains("再行起诉")
					|| contentlist.get(i).contains("再次诉讼")
					|| contentlist.get(i).contains("曾诉")) {
				ysajly = "重新起诉";
			}
		}
		wscpfxgcModel.setYsajly(ysajly);
		// 解析是否共同犯罪
		String gtfz = "否";
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("共同犯罪")
					|| contentlist.get(i).contains("轮流")
					|| contentlist.get(i).contains("放风")
					|| contentlist.get(i).contains("相互配合")
					|| contentlist.get(i).contains("从犯")
					|| contentlist.get(i).contains("主犯")) {
				gtfz = "是";
			}
		}
		wscpfxgcModel.setGtfz(gtfz);
		// 被告人同意认罪程序
		String bgrtyrzcx = "是";
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("事实不符")
					|| contentlist.get(i).contains("辩护意见不成立")) {
				bgrtyrzcx = "否";
			}
		}
		wscpfxgcModel.setBgrtyrzcx(bgrtyrzcx);

		// 解析开庭前申请撤回上诉
		String ktqsqchss = "否";
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("撤诉")) {
				ktqsqchss = "是";
			}
		}
		wscpfxgcModel.setKtqsqchss(ktqsqchss);
		
		// 解析量刑情况
		ArrayList<String> lxqk = new ArrayList<String>();
		for (int i = 0; i < cpfxgc.size(); i++) {
			String cpfxgcString = cpfxgc.get(i);
			String[] cpfxgcArray = cpfxgcString.split("\\.|。|;|；");
			for (int j = 0; j < cpfxgcArray.length; j++) {
				lxqk.add(cpfxgcArray[j]);
			}
		}
		ArrayList<WscpfxgcZdlxModel> zdlxModelist = new ArrayList<WscpfxgcZdlxModel>();
		ArrayList<WscpfxgcFdlxModel> fdlxModelist = new ArrayList<WscpfxgcFdlxModel>();
		wscpfxgcModel.setFdlxModel(fdlxModelist);
		wscpfxgcModel.setZdlxModel(zdlxModelist);
		for (int i = 0; i < lxqk.size(); i++) {
			WscpfxgcZdlxModel zdlxModel = new WscpfxgcZdlxModel();
			WscpfxgcFdlxModel fdlxModel = new WscpfxgcFdlxModel();
			ArrayList<String> lxqjlb = new ArrayList<String>();
			String qj = null;
			// 解析相关人
			ArrayList<String> xgr = new ArrayList<String>();
			for (WssscyrModel model : wssscyrModellist) {
				if (model.getSscyr() != null) {
					if (lxqk.get(i).contains(model.getSscyr())) {
						xgr.add(model.getSscyr());
					}
				}
			}
			// if (lxqk.get(i).contains("酌")) {
			if (xgr != null) {
				fdlxModel.setXgr(xgr);
				zdlxModel.setXgr(xgr);
			}
			// 解析酌定从轻情节
			if (lxqk.get(i).contains("表现好") || lxqk.get(i).contains("表现良好")) {
				qj = "一贯表现好";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("如实") || lxqk.get(i).contains("坦白")) {
				qj = "如实交代犯罪事实（坦白）";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("认罪")) {
				qj = "认罪态度好";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("民愤小")) {
				qj = "民愤小";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("家庭") && lxqk.get(i).contains("困难")) {
				qj = "家庭有困难";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("初犯")) {
				qj = "初犯";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("偶犯")) {
				qj = "偶犯";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("老年") || lxqk.get(i).contains("智障")
					|| lxqk.get(i).contains("残疾")) {
				qj = "老年人、智障人、残疾人犯";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("被害人")
					&& lxqk.get(i).contains("过错")
					&& !lxqk.get(i).contains("不能认定")) {
				qj = "被害人有过错";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("被害人有过错")) {
				qj = "被害人有过错";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("主动取得被害人谅解")) {
				qj = "主动取得被害人谅解";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("退赃")) {
				if (lxqk.get(i).contains("主动")
						|| lxqk.get(i).contains("积极")
						|| (!lxqk.get(i).contains("拒绝退赃") && !lxqk.get(i)
								.contains("不主动退赃"))) {
					qj = "主动退赃";
					lxqjlb.add("酌定从轻情节");
					zdlxModel.setQj(qj);
					zdlxModel.setLxqjlb(lxqjlb);
				}
			} else if ((lxqk.get(i).contains("全部") || lxqk.get(i).contains(
					"大部分"))
					&& (lxqk.get(i).contains("赃款") || lxqk.get(i)
							.contains("赃物")) && lxqk.get(i).contains("追缴")) {
				qj = "赃款赃物全部或者大部分被追缴";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("采取补救")) {
				qj = "犯罪后采取补救措施降低损失";
				lxqjlb.add("酌定从轻情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			}

			// 解析酌定从重情节
			else if (lxqk.get(i).contains("劣迹")) {
				qj = "犯罪前有劣迹";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("前科")) {
				qj = "犯罪前有前科";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("惯犯")) {
				qj = "惯犯";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("认罪态度不好")) {
				qj = "认罪态度不好";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("民愤大")) {
				qj = "民愤大";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("手段恶劣")
					|| lxqk.get(i).contains("动机卑劣")) {
				qj = "手段恶劣，动机卑劣";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("放任危害结果")) {
				qj = "放任危害结果";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("拒绝退赃")) {
				qj = "拒绝退赃";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("拒绝赔偿损失")) {
				qj = "拒绝赔偿损失";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			} else if (lxqk.get(i).contains("逃跑")) {
				qj = "犯罪后逃跑";
				lxqjlb.add("酌定从重情节");
				zdlxModel.setQj(qj);
				zdlxModel.setLxqjlb(lxqjlb);
			}
			// wscpfxgcModellist.get(wscpfxgcModellist.size() - 1)
			// .getZdlxModel().add(zdlxModel);
			// }
			// else {
			// WscpfxgcFdlxModel fdlxModel = new WscpfxgcFdlxModel();
			// if (xgr.get(0) != null) {
			// fdlxModel.setXgr(xgr);
			// }

			// 解析法定量刑情节
			else if (lxqk.get(i).contains("从犯")) {
				if (lxqk.get(i).contains("协从犯")) {
					qj = "协从犯";
					lxqjlb.add("法定应当从轻情节");
				} else {
					qj = "从犯";
					if (lxqk.get(i).contains("从轻")) {
						lxqjlb.add("法定应当从轻情节");
					}
				}
				if (lxqk.get(i).contains("减轻")) {
					lxqjlb.add("法定应当减轻情节");
				}
				if (lxqk.get(i).contains("免除")) {
					lxqjlb.add("法定应当免除情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("未成年")) {
				qj = "未成年";
				if (lxqk.get(i).contains("免处死刑")) {
					lxqjlb.add("免处死刑情节");
				}
				if (lxqk.get(i).contains("从轻")) {
					lxqjlb.add("法定应当从轻情节");
				}
				if (lxqk.get(i).contains("减轻")) {
					lxqjlb.add("法定应当减轻情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("过当")) {
				if (lxqk.get(i).contains("防卫")) {
					qj = "防卫过当";
					if (lxqk.get(i).contains("免除")) {
						lxqjlb.add("法定应当免除情节");
					}
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定应当减轻情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else if (lxqk.get(i).contains("避险")) {
					qj = "避险过当";
					if (lxqk.get(i).contains("免除")) {
						lxqjlb.add("法定应当免除情节");
					}
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定应当减轻情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("没有造成损害的中止犯")) {
				qj = "没有造成损害的中止犯";
				if (lxqk.get(i).contains("免除")) {
					lxqjlb.add("法定应当免除情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("造成损害的中止犯")) {
				qj = "造成损害的中止犯";
				if (lxqk.get(i).contains("减轻")) {
					lxqjlb.add("法定应当减轻情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("自首") || lxqk.get(i).contains("投案")) {
				if (lxqk.get(i).contains("重大立功")) {
					qj = "自首并重大立功";
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定应当减轻情节");
						lxqjlb.add("法定可以减轻情节");
					}
					if (lxqk.get(i).contains("免除")) {
						lxqjlb.add("法定应当免除情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
				if (lxqk.get(i).contains("重大立功")) {
					qj = "自首并重大立功";
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定应当减轻情节");
						lxqjlb.add("法定可以减轻情节");
					}
					if (lxqk.get(i).contains("免除")) {
						lxqjlb.add("法定应当免除情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else if (lxqk.get(i).contains("罪行较轻")) {
					qj = "罪行较轻且自首";
					if (lxqk.get(i).contains("免除")) {
						lxqjlb.add("法定应当免除情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else {
					qj = "自首";
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定应当减轻情节");
						lxqjlb.add("法定可以减轻情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("聋") || lxqk.get(i).contains("哑")
					|| lxqk.get(i).contains("盲人")) {
				qj = "又聋又哑或盲人犯罪";
				if (lxqk.get(i).contains("从轻")) {
					lxqjlb.add("法定可以从轻情节");
				}
				if (lxqk.get(i).contains("减轻")) {
					lxqjlb.add("法定可以减轻情节");
				}
				if (lxqk.get(i).contains("免除")) {
					lxqjlb.add("法定可以免除情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("预备犯")) {
				qj = "预备犯";
				if (lxqk.get(i).contains("从轻")) {
					lxqjlb.add("法定可以从轻情节");
				}
				if (lxqk.get(i).contains("减轻")) {
					lxqjlb.add("法定可以减轻情节");
				}
				if (lxqk.get(i).contains("免除")) {
					lxqjlb.add("法定可以免除情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("未遂")) {
				qj = "未遂犯";
				if (lxqk.get(i).contains("从轻")) {
					lxqjlb.add("法定可以从轻情节");
				}
				if (lxqk.get(i).contains("减轻")) {
					lxqjlb.add("法定可以减轻情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("教唆")) {
				if (lxqk.get(i).contains("没有犯")) {
					qj = "教唆他人犯罪被教唆的人没有犯被教唆的罪";
					if (lxqk.get(i).contains("从轻")) {
						lxqjlb.add("法定可以从轻情节");
					}
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定可以减轻情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else if (lxqk.get(i).contains("未成年")) {
					qj = "教唆未成年人犯罪";
					if (lxqk.get(i).contains("从重")) {
						lxqjlb.add("法定从重情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("立功")) {
				if (lxqk.get(i).contains("重大立功")) {
					qj = "重大立功";
					if (lxqk.get(i).contains("从轻")) {
						lxqjlb.add("法定可以从轻情节");
					}
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定可以减轻情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				} else {
					qj = "立功";
					if (lxqk.get(i).contains("从轻")) {
						lxqjlb.add("法定可以从轻情节");
					}
					if (lxqk.get(i).contains("减轻")) {
						lxqjlb.add("法定可以减轻情节");
					}
					fdlxModel.setLxqjlb(lxqjlb);
					fdlxModel.setQj(qj);
				}
			} else if (lxqk.get(i).contains("在国外已受过刑罚处罚")) {
				qj = "在国外已受过刑罚处罚";
				if (lxqk.get(i).contains("免除")) {
					lxqjlb.add("法定可以免除情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("累犯")) {
				qj = "累犯";
				if (lxqk.get(i).contains("从重")) {
					lxqjlb.add("法定从重情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			} else if (lxqk.get(i).contains("再犯") && lxqk.get(i).contains("毒品")) {
				qj = "毒品再犯";
				if (lxqk.get(i).contains("从重")) {
					lxqjlb.add("法定从重情节");
				}
				fdlxModel.setLxqjlb(lxqjlb);
				fdlxModel.setQj(qj);
			}
			if (fdlxModel.getQj() != null) {
				wscpfxgcModel.getFdlxModel().add(fdlxModel);
			}
			if (zdlxModel.getQj() != null) {
				wscpfxgcModel.getZdlxModel().add(zdlxModel);
			}
			// System.out.println(lxqk.get(i));
		}

		return wscpfxgcModel;
	}
}
