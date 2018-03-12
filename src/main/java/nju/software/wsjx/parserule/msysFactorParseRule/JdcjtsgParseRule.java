package nju.software.wsjx.parserule.msysFactorParseRule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.formula.functions.Match;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.msysFactorModel.JdcjtsgModel;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.service.impl.jtsg.CmssdpreServiceImpl;

public class JdcjtsgParseRule {

	/**
	 * 抽取机动车交通事故的要素，返回机动车交通事故要素model
	 * @param wsAnalyse 包含文书各个部分的分段信息，以及每段的具体内容
	 * @param wsModel 已经抽取完成的文书model
	 * @return jdcjtsgModel 机动车交通事故模型
	 */
	public JdcjtsgModel getJdcjtsgModel(WsAnalyse wsAnalyse,WsModel wsModel){
		JdcjtsgModel jdcjtsgModel=new JdcjtsgModel();
		jdcjtsgModel=getCommonElement(wsModel, jdcjtsgModel);
		jdcjtsgModel=getCommonDamageElement(wsModel, jdcjtsgModel);
        jdcjtsgModel=getMaimElement(wsModel, jdcjtsgModel);
        jdcjtsgModel=getDeathElement(wsModel, jdcjtsgModel);
		jdcjtsgModel=getPropertyLossElement(wsModel, jdcjtsgModel);
		return jdcjtsgModel;
	}
	
	/**
	 * 从案件基本情况中提取共用要素
	 * @param wsModel 已经抽取完成的文书model
	 * @param jdcjtsgModel 机动车交通事故模型
	 * @return
	 */
	public JdcjtsgModel getCommonElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		CmssdpreServiceImpl cmssPre = new CmssdpreServiceImpl();//创建查明事实预处理对象，调用其部分函数
		List<String> cmssIni = wsModel.getWsajjbqkModel().getCmssd();//查明事实段内容
		List<String> cmssList = new ArrayList<String>();//存放截取之后的查明事实段内容
		

		String ajjbqksegement = wsModel.getWsajjbqSegment();
		String[] ajjbqksegements = ajjbqksegement.split("。");
		
		
		String sgsj=null;//事故时间
		String sgdd=null;//事故地点
		String sgjg=null;//事故经过
		List<String> sgzr=new ArrayList<>();//事故责任
		List<String> sgclph=new ArrayList<>();//五、事故车辆牌号
		List<String> sgclsyz=new ArrayList<>();//六、事故车辆所有者
		List<String> sgcljsy=new ArrayList<>();//七、事故车辆驾驶员
		List<String> sgcljqxbxgs=new ArrayList<>();//八、事故车辆交强险保险公司
		List<String> sgclsyszxbxgs=new ArrayList<>();//九、事故车辆商业三者险保险公司
		List<String> syszxpcxe=new ArrayList<>();//十、商业三者险赔偿限额
		List<String> sgqtpczrzt=new ArrayList<>();//十一、事故其他赔偿责任主体
		String ydfpckse=null;//十二、已垫付赔偿款数额
		String dfr=null;//十三、垫付人

        String sglx="";//事故类型
        String shlx="";//损害类型，分为致人伤残、致人死亡
        String sfCcss="";//是否有财产损失
        String sfsjjqx="";//是否上交交强险
        String sfsjsyx="";//是否上交商业险
        String hasZrrds="";//是否有交通责任认定书
        String tcmz="";//保险公司是否提出免责
		
		boolean contentFlag=false;//截取标志
		
		for (String it : cmssIni) {//截取适当的内容
			if (it.contains("本院认定事实如下")||it.contains("经审理查明")||it.contains("事实和理由")) {
				contentFlag=true;
			}
			
			if (contentFlag) {
				cmssList.add(it);
//				contentFlag=false;
			}
		}
		if (cmssList.isEmpty()) {
			cmssList=cmssIni;
		}
		
		sgjg=cmssPre.getSgjg(cmssPre.getSgxq(cmssList));//事故经过
		sgsj=getSgsj(sgjg);//事故时间
		sgdd=getSgdd(sgjg);//事故地点
		sgqtpczrzt=getSgqtpczrzt(wsModel.getWssscyrModels());//事故其他赔偿责任主体		
		
		for (String it : ajjbqksegements) {
//			if (sgsj==null) {
//				sgsj=getSgsj(it);//事故时间
//			}
//			if (sgdd==null) {
//				sgdd=getSgdd(it);//事故地点
//			}
//			if (sgzr.isEmpty()) {
//				sgzr = getSgzr(it);//事故责任
//			}
			if (sgclph.isEmpty()||sgclph==null) {
				sgclph=getSgclph(it);//事故车辆牌照
			}
			if (sgclsyz.isEmpty()||sgclsyz==null) {
				sgclsyz=getSgclsyz(it, sgclph);//事故车辆所有者
			}
//			if (sgcljsy.isEmpty()) {
//				sgcljsy=getSgcljsy(it, sgclph);//事故车辆驾驶员
//			}
			if (sgcljqxbxgs.isEmpty()||sgcljqxbxgs==null) {
				sgcljqxbxgs=getJqxbxgs(it, sgclph);//事故车辆交强险保险公司
			}
			if (sgclsyszxbxgs.isEmpty()||sgclsyszxbxgs==null) {
				sgclsyszxbxgs=getSzxbxgs(it, sgclph);//事故车辆商业三者险保险公司
			}
//			if (syszxpcxe.isEmpty()) {
//				syszxpcxe=getSzxpcxe(it, sgclph, sgclsyszxbxgs);//商业三者险赔偿限额
//			}
//			if (ydfpckse==null||ydfpckse=="") {
//				ydfpckse=getYdfpckse(it);//已垫付赔偿款数额
//			}
//			if (dfr==null||dfr=="") {
//				dfr=getDfr(it);//垫付人
//			}
		}
		for (String it_list : cmssList) {
			if (sgzr.isEmpty()||sgzr==null) {
				sgzr = getSgzr(it_list);//事故责任
			}
			if (syszxpcxe.isEmpty()||syszxpcxe==null) {
				syszxpcxe=getSzxpcxe(it_list, sgclph, sgclsyszxbxgs);//商业三者险赔偿限额
			}
			if (ydfpckse==null||ydfpckse=="") {
				ydfpckse=getYdfpckse(it_list);//已垫付赔偿款数额
			}
			if (dfr==null||dfr=="") {
				dfr=getDfr(it_list);//垫付人
			}
            if (shlx == "" || shlx.isEmpty()) {
                if (it_list.contains("残疾赔偿金")) {
                    shlx = "致人伤残";
                } else if (it_list.contains("丧葬费")||it_list.contains("死亡赔偿金")) {
                    shlx = "致人死亡";
                }
            }
            if (sfCcss == "" || sfCcss.isEmpty()) {
                if (it_list.contains("车辆维修费") || it_list.contains("车载物品损失费") || it_list.contains("施救费")
                        || it_list.contains("车辆重置费") || it_list.contains("停运") ||
                        it_list.contains("通常替代性交通工具费")) {
                    sfCcss = "是";
                }
            }
            if (hasZrrds == "" || !hasZrrds.isEmpty()) {
                if (it_list.contains("责任认定书")) {
                    hasZrrds = "是";
                }
            }

		}
		sgcljsy=getSgcljsy(sgjg, sgclph);//事故车辆驾驶员

        String cmssnr = wsModel.getWsajjbqSegment();
        String[] cmssnrs = cmssnr.split("\\n");
        for (String it :
                cmssnrs) {
            if (tcmz == "" || !tcmz.isEmpty()) {
                if (it.contains("免罪")) {
                    tcmz = "是";
                }
            }
            if (sglx == "" || !sglx.isEmpty()) {
                if (it.contains("两车相撞")) {
                    sglx = "两机动车相撞";
                } else if (it.contains("身体相撞")) {
                    sglx = "机动车与行人相撞";
                } else if (it.contains("自行车") || it.contains("电动车") || it.contains("非机动车")) {
                    sglx = "机动车与非机动车相撞";
                }
            }
        }
        if (sgcljqxbxgs != null && !sgcljqxbxgs.isEmpty()) {
            sfsjjqx = "是";
        } else {
            sfsjjqx = "否";
        }

        if (sgclsyszxbxgs != null && !sgclsyszxbxgs.isEmpty()) {
            sfsjsyx = "是";
        } else {
            sfsjsyx = "否";
        }
        if (sfCcss == "" || sfCcss.isEmpty()) {
            sfCcss = "否";
        }
        if (hasZrrds == "" || !hasZrrds.isEmpty()) {
            hasZrrds = "否";
        }
        if (tcmz == "" || !tcmz.isEmpty()) {
            tcmz = "否";
        }

        if (sgclph.size() > 1) {
            if (!sglx.equals("两机动车相撞")) {
                sglx = "两机动车相撞";
            }
        }

		jdcjtsgModel.setSgfssj(sgsj);
		jdcjtsgModel.setSgfsdd(sgdd);
		jdcjtsgModel.setSgjg(sgjg);
		jdcjtsgModel.setSgzrrdqk(sgzr);
		jdcjtsgModel.setSgclph(sgclph);
		jdcjtsgModel.setSgclsyz(sgclsyz);
		jdcjtsgModel.setSgcljsy(sgcljsy);
		jdcjtsgModel.setSgcljqxbxgs(sgcljqxbxgs);
		jdcjtsgModel.setSgclsyszxbxgs(sgclsyszxbxgs);
		jdcjtsgModel.setSyszxpcxe(syszxpcxe);
		jdcjtsgModel.setSgqtpczrzt(sgqtpczrzt);
		jdcjtsgModel.setYdfpckse(ydfpckse);
		jdcjtsgModel.setDfr(dfr);

        jdcjtsgModel.setSglx(sglx);
        jdcjtsgModel.setShlx(shlx);
        jdcjtsgModel.setSfCcss(sfCcss);
        jdcjtsgModel.setSfsjjqx(sfsjjqx);
        jdcjtsgModel.setSfsjsyx(sfsjsyx);
        jdcjtsgModel.setHasZrrds(hasZrrds);
        jdcjtsgModel.setTcmz(tcmz);

		return jdcjtsgModel;
	}
	
	/**
	 * 提取一般伤害适用要素
	 * @param wsModel 文书模型
	 * @param jdcjtsgModel 机动车交通事故模型
	 * @return jdcjtsgModel 机动车交通事故模型
	 */
	public JdcjtsgModel getCommonDamageElement(WsModel wsModel,JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("双方争议焦点")&&(cmssnr.contains("本院认定如下")||cmssnr.contains("经审理查明"))
//				&&(cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("本院认定如下")||
//				cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("经审理查明"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
//		}else if (cmssnr.contains("本院认定如下")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("本院认定如下"));
//		}else if (cmssnr.contains("经审理查明")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("经审理查明"));
//		}else if (cmssnr.contains("事实和理由")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("事实和理由"));
//		}
        if (cmssnr.contains("双方争议焦点") && (cmssnr.contains("本院认定如下") ||
                cmssnr.contains("经审理查明") )) {
            if (cmssnr.contains("双方争议焦点") && cmssnr.contains("本院认定如下")) {
                if (cmssnr.indexOf("本院认定如下") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定如下"));
                }
            } else if (cmssnr.contains("双方争议焦点") && cmssnr.contains("经审理查明")) {
                if (cmssnr.indexOf("经审理查明") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("经审理查明"));
                }
            }
        } else if (cmssnr.contains("本院认定如下")) {
            cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定如下"));
        } else if (cmssnr.contains("经审理查明")) {
            cmssnr = cmssnr.substring(cmssnr.indexOf("经审理查明"));
        } else if (cmssnr.contains("本院认定事实如下")) {
            cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定事实如下"));
        }

		String ylfse="";//十四、医疗费数额
		String zysj="";//十五、住院时间
		String zyhsbzfse="";//十六、住院伙食补助费数额
		String hlq="";//十七、护理期
		String hlfse="";//十八、护理费数额
		String yyq="";//十九、营养期
		String yyfse="";//二十、营养费数额
		String wgq="";//二十一、误工期
		String wgfse="";//二十二、误工费数额
		String jtfse="";//二十三、交通费数额
		String zsfse="";//二十四、住宿费数额

        boolean hlqSplitFlag = false;
		//以换行符分段
        String[] cmssnrs = cmssnr.split("\\n");
        for (String it :
                cmssnrs) {
            boolean twiceFlag = false;
            if (it.contains("护理费") && it.contains("主张") && it.contains("本院")) {
                if (it.contains("两部分")) {
                    twiceFlag = true;
                }
                String[] hlfdl = it.split("。");
                for (int i=0;i<hlfdl.length;i++) {
                    if (hlfdl[i].contains("两项合计")&&twiceFlag) {
                        hlfse = getJe(hlfdl[i]).get(0)+"元";
                    }
                }
            }
        }
        
        //以句号分段
		cmssnrs=cmssnr.split("。");
		for (String it : cmssnrs) {
			if (ylfse==null||ylfse.isEmpty()) {
				if (it.contains("医疗费")&&!it.contains("限额")) {
					ylfse=getIndemnity(it,"医疗费");
                } else if (it.contains("医药费") && it.contains("支持")) {
                    ylfse = getIndemnity(it, "医药费");
                }
			}
			if (zyhsbzfse==null||zyhsbzfse.isEmpty()) {
                if (it.contains("住院伙食补助费")) {
                    zyhsbzfse=getIndemnity(it, "住院伙食补助费");
                } else if (it.contains("伙食补助") && it.contains("予以确认")) {
                    String[] states = it.split("，");
                    for (String its :
                            states) {
                        if (its.contains("伙食补助") && !its.contains("日") && !its.contains("天")) {
                            zyhsbzfse = getIndemnity(its, "伙食补助");
                        } else if (its.contains("费用")) {
                            zyhsbzfse = getIndemnity(its, "费用");
                        }
                    }
                }
            }
			if (hlfse==null||hlfse.isEmpty()) {
                hlfse=getIndemnity(it, "护理费");
            }
			if (yyfse==null||yyfse.isEmpty()) {
                if (it.contains("营养费")) {
                    String[] yyfs = it.split("，");
                    for (String its :
                            yyfs) {
                        if (its.contains("酌定")) {
                            if (its.contains("（")) {
                                its = its.substring(0, its.indexOf("（"));
                                yyfse = getJe(its).get(0)+"元";
                            } else {
                                yyfse = getJe(its).get(0)+"元";
                            }
                        }
                    }
                }
                if (yyfse == null || yyfse.isEmpty()) {
                    yyfse = getIndemnity(it, "营养费");
                }
			}
			if (wgfse==null||wgfse.isEmpty()) {
				wgfse=getIndemnity(it, "误工费");
			}
			if (jtfse==null||jtfse.isEmpty()) {
                if (it.contains("交通费")) {
                    String[] jtfs = it.split("，");
                    for (String its :
                            jtfs) {
                        if (its.contains("酌定")) {
                            if (its.contains("（")) {
                                its = its.substring(0, its.indexOf("（"));
                                jtfse = getJe(its).get(0) + "元";
                            } else {
                                jtfse = getJe(its).get(0) + "元";
                            }
                        } else {
                            if (jtfse==null||jtfse.isEmpty()) {
                                jtfse = getIndemnity(its, "交通费");
                            }
                        }
                    }
                }
			}
			if (zsfse==null||zsfse.isEmpty()) {
                if (!it.contains("主张")) {
                    zsfse=getIndemnity(it, "住宿费");
                }
            }
			if (zysj==null||zysj.isEmpty()) {
				zysj=getZysj(it);
			}
			if (hlq==null||hlq.isEmpty()) {
				hlq=getHlq(it);
			}
            if (cmssnr.contains("非住院期间") || (cmssnr.contains("护理费") && cmssnr.contains("两部分"))) {
			    hlqSplitFlag=true;
            }
			if (yyq==null||yyq.isEmpty()) {
				yyq=getYyq(it);
			}
			if (wgq==null||wgq.isEmpty()) {
				wgq=getWgq(it);
			}
		}

        //判断住院时间是否分段，若分段将多个时间相加
        if (hlqSplitFlag) {
            int hlqs = Integer.valueOf(getJe(hlq).get(0));
            int zysjs = 0;
            if (zysj!=null&&!zysj.isEmpty()) {
                zysjs = Integer.valueOf(getJe(zysj).get(0));
            }
            hlq = String.valueOf(zysjs + hlqs) + "天";
        }

		//如果查明事实段没有预想内容，假定其与住院时间相同
        if (hlq==null||hlq.isEmpty()) {
            hlq=zysj;
        }
		if (wgq==null||wgq.isEmpty()) {
			wgq=zysj;
		}



		jdcjtsgModel.setZysj(zysj);
		jdcjtsgModel.setYlfse(ylfse);
		jdcjtsgModel.setZyhsbzfse(zyhsbzfse);
		jdcjtsgModel.setHlq(hlq);
		jdcjtsgModel.setHlfse(hlfse);
		jdcjtsgModel.setYyq(yyq);
		jdcjtsgModel.setYyfse(yyfse);
		jdcjtsgModel.setWgq(wgq);
		jdcjtsgModel.setWgfse(wgfse);
		jdcjtsgModel.setJtfse(jtfse);
		jdcjtsgModel.setZsfse(zsfse);
		return jdcjtsgModel;
	}
	
	/**
	 * 提取致人伤残适用要素
	 * @param wsModel 文书模型
	 * @param jdcjtsgModel 机动车交通事故模型
	 * @return jdcjtsgModel 机动车交通事故模型
	 */
	public JdcjtsgModel getMaimElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("双方争议焦点")&&(cmssnr.contains("本院认定如下")||cmssnr.contains("经审理查明"))
//				&&(cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("本院认定如下")||
//				cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("经审理查明"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
//		}else if (cmssnr.contains("本院认定如下")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("本院认定如下"));
//		}else if (cmssnr.contains("经审理查明")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("经审理查明"));
//		}else if (cmssnr.contains("事实和理由")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("事实和理由"));
//		}

        if ((cmssnr.contains("双方争议焦点")||cmssnr.contains("本院认定如下") )&& (cmssnr.contains("本院认定事实如下") ||
                cmssnr.contains("经审理查明") || cmssnr.contains("事实和理由"))) {
            if (cmssnr.contains("双方争议焦点") && cmssnr.contains("本院认定如下")) {
                if (cmssnr.indexOf("本院认定事实如下") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定事实如下"));
                }
            } else if (cmssnr.contains("双方争议焦点") && cmssnr.contains("经审理查明")) {
                if (cmssnr.indexOf("经审理查明") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("经审理查明"));
                }
            } else if (cmssnr.contains("双方争议焦点") && cmssnr.contains("事实和理由")) {
                if (cmssnr.indexOf("事实和理由") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("事实和理由"));
                }
            } else if (cmssnr.contains("本院认定如下") && cmssnr.contains("本院认定事实如下")) {
                cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定如下"));
            }
        }else if (cmssnr.contains("经审理查明")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("经审理查明"));
        }else if (cmssnr.contains("本院认定事实如下")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("本院认定事实如下"));
        }else if (cmssnr.contains("事实和理由")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("事实和理由"));
        }

		String cjdjjdjlsj="";//二十五、残疾等级鉴定结论时间
		String cjdjjdjl="";//二十六、残疾等级鉴定结论
		String bfyrshfse="";//二十七、被扶养人生活费数额
		String cjpcjse="";//二十八、残疾赔偿金数额
		String jsshfwj="";//二十九、精神损害抚慰金
		String cjshfzjfse="";//三十、残疾生活辅助具费数额
        String dchhlylcdjdyj="";//三十一、定残后护理依赖程度鉴定意见
        String dchhlfse="";//三十二、定残后护理费数额

        String[] cmssnrs = cmssnr.split("。");
        for (String it : cmssnrs) {
            if (bfyrshfse==null||bfyrshfse.isEmpty()) {
                if (it.contains("被抚养人生活费")&&!it.contains("不予支持")) {
                    bfyrshfse=getIndemnity(it, "被抚养人生活费");
                }
            }
            if (cjpcjse==null||cjpcjse.isEmpty()) {
				cjpcjse=getIndemnity(it, "残疾赔偿金");
			}
			if (jsshfwj==null||jsshfwj.isEmpty()) {
                if (it.contains("精神抚慰金")&&!it.contains("主张")) {
                    jsshfwj=getIndemnity(it, "精神抚慰金");
                } else if (it.contains("精神损害抚慰金")&&!it.contains("主张")){
                    jsshfwj = getIndemnity(it, "精神损害抚慰金");
                }
            }
			if (cjshfzjfse==null||cjshfzjfse.isEmpty()) {
				if (it.contains("残疾生活辅助具费")) {
					cjshfzjfse=getIndemnity(it, "残疾生活辅助具费");
				}else if (it.contains("残疾辅助具费")) {
					cjshfzjfse=getIndemnity(it, "残疾辅助具费");
                } else if (it.contains("残疾辅助器具费")) {
                    cjshfzjfse = getIndemnity(it, "残疾辅助器具费");
                }
			}
			if (cjdjjdjlsj==null||cjdjjdjlsj.isEmpty()) {
				cjdjjdjlsj=getCjdjjdjlsj(it);
			}
			if (cjdjjdjl==null||cjdjjdjl.isEmpty()) {
				cjdjjdjl=getCjdjjdjl(it);
			}
			if (dchhlylcdjdyj==null||dchhlylcdjdyj.isEmpty()) {
				dchhlylcdjdyj=getDchhlylcdjdyj(it);
			}
			if (dchhlfse==null||dchhlfse.isEmpty()) {
				dchhlfse=getDchhlfse(it);
			}
		}

        String cpjgnr = wsModel.getWscpjgSegment();
        String[] cpjgnrs = cpjgnr.split("。");
        for (String it :cpjgnrs) {
            if (jsshfwj==null||jsshfwj.isEmpty()) {
                if (it.contains("精神抚慰金")&&!it.contains("主张")) {
                    jsshfwj=getIndemnity(it, "精神抚慰金");
                } else if (it.contains("精神损害抚慰金")&&!it.contains("主张")){
                    jsshfwj = getIndemnity(it, "精神损害抚慰金");
                }
            }
        }
		jdcjtsgModel.setCjdjjdjlsj(cjdjjdjlsj);
		jdcjtsgModel.setCjdjjdjl(cjdjjdjl);
		jdcjtsgModel.setBfyrshfse_meim(bfyrshfse);
		jdcjtsgModel.setCjpcjse(cjpcjse);
		jdcjtsgModel.setJsshfwj_meim(jsshfwj);
		jdcjtsgModel.setCjshfzjfse(cjshfzjfse);
		jdcjtsgModel.setDchhlylcdjdyj(dchhlylcdjdyj);
		jdcjtsgModel.setDchhlfse(dchhlfse);

		return jdcjtsgModel;
	}
	
	/**
	 * 提取致人死亡适用要素
	 * @param wsModel 文书模型
	 * @param jdcjtsgModel 机动车交通事故模型
	 * @return jdcjtsgModel机动车交通事故模型
	 */
	public JdcjtsgModel getDeathElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("双方争议焦点")&&(cmssnr.contains("本院认定如下")||cmssnr.contains("经审理查明"))
//				&&(cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("本院认定如下")||
//				cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("经审理查明"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
//		}else if (cmssnr.contains("本院认定如下")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("本院认定如下"));
//		}else if (cmssnr.contains("经审理查明")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("经审理查明"));
//		}else if (cmssnr.contains("事实和理由")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("事实和理由"));
//		}

        if ((cmssnr.contains("双方争议焦点")||cmssnr.contains("本院认定如下") )&& (cmssnr.contains("本院认定事实如下") ||
                cmssnr.contains("经审理查明") || cmssnr.contains("事实和理由"))) {
            if (cmssnr.contains("双方争议焦点") && cmssnr.contains("本院认定如下")) {
                if (cmssnr.indexOf("本院认定事实如下") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定事实如下"));
                }
            } else if (cmssnr.contains("双方争议焦点") && cmssnr.contains("经审理查明")) {
                if (cmssnr.indexOf("经审理查明") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("经审理查明"));
                }
            } else if (cmssnr.contains("双方争议焦点") && cmssnr.contains("事实和理由")) {
                if (cmssnr.indexOf("事实和理由") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("事实和理由"));
                }
            } else if (cmssnr.contains("本院认定如下") && cmssnr.contains("本院认定事实如下")) {
                cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定如下"));
            }
        }else if (cmssnr.contains("经审理查明")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("经审理查明"));
        }else if (cmssnr.contains("本院认定事实如下")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("本院认定事实如下"));
        }else if (cmssnr.contains("事实和理由")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("事实和理由"));
        }

		
		String swpcjse="";//二十五、死亡赔偿金数额
		String bfyrshfse="";//二十四、被抚养人生活费数额
		String jsshfwjse="";//二十七、精神损害抚慰金数额
		String szfse="";//二十八、丧葬费数额
		String shrqsjtfse="";//二十九、受害人亲属办理丧葬事宜支出的交通费数额
		String shrqszsfse="";//三十、受害人亲属办理丧葬事宜支出的住宿费数额
		String shrqswgfse="";//三十一、受害人亲属办理丧葬事宜支出的误工费数额
		
		String[] cmssnrs = cmssnr.split("。");
		for (String it : cmssnrs) {
            String bfyrshfse2 = "";
			if (swpcjse==null||swpcjse.isEmpty()) {
				swpcjse=getIndemnity(it, "死亡赔偿金");
			}
            if (bfyrshfse==null||bfyrshfse.isEmpty()) {
                if (it.contains("被抚养人生活费")&&!it.contains("不予支持")) {
                    if (!it.contains("、")) {
                        bfyrshfse=getIndemnity(it, "被抚养人生活费");
                    } else {
                        String[] shfs = it.split("、");
                        List<String> bfyrshfs = new ArrayList<>();
                        for (String its : shfs) {
                            String temp = getIndemnity(its, "被抚养人生活费");
                            if (temp != null && !temp.isEmpty()) {
                                bfyrshfs.add(temp);
                            }
                        }
                        if (bfyrshfs.size() == 1) {
                            bfyrshfse = bfyrshfs.get(0);
                        } else if (bfyrshfs.size() == 2) {
                            bfyrshfse = jePlus(bfyrshfs.get(0), bfyrshfs.get(1));
                        } else if (bfyrshfs.size() >= 3) {
                            bfyrshfse = jePlus(bfyrshfs.get(0), bfyrshfs.get(1));
                            for (int i=2;i<bfyrshfs.size();i++) {
                                if (bfyrshfs.get(i) != null && !bfyrshfs.isEmpty()) {
                                    bfyrshfse = jePlus(bfyrshfse, bfyrshfs.get(i));
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (jsshfwjse==null||jsshfwjse.isEmpty()) {
                if (it.contains("精神抚慰金")&&!it.contains("主张")) {
                    jsshfwjse=getIndemnity(it, "精神抚慰金");
                } else if (it.contains("精神损害抚慰金")&&!it.contains("主张")){
                    jsshfwjse = getIndemnity(it, "精神损害抚慰金");
                }
            }
			if (szfse==null||szfse.isEmpty()) {
				szfse=getIndemnity(it, "丧葬费");
			}
			if (it.contains("丧事")) {
				if (shrqsjtfse==null||shrqsjtfse.isEmpty()) {
					shrqsjtfse=getIndemnity(it, "交通费");
				}
				if (shrqszsfse==null||shrqszsfse.isEmpty()) {
					shrqszsfse=getIndemnity(it, "住宿费");
				}
				if (shrqswgfse==null||shrqswgfse.isEmpty()) {
					shrqswgfse=getIndemnity(it, "误工费");
				}
			}
		}

		jdcjtsgModel.setSwpcjse(swpcjse);
		jdcjtsgModel.setJsshfwjse_death(jsshfwjse);
		jdcjtsgModel.setBfyrshfse_death(bfyrshfse);
		jdcjtsgModel.setSzfse(szfse);
		jdcjtsgModel.setShrqsblszsyzcdwgfse(shrqswgfse);
		jdcjtsgModel.setShrqsjtfse(shrqsjtfse);
		jdcjtsgModel.setShrqszsfse(shrqszsfse);

		return jdcjtsgModel;
	}
	
	/**
	 * 提取财产损失适用要素
	 * @param wsModel 文书模型
	 * @param jdcjtsgModel 机动车交通事故模型
	 * @return jdcjtsgModel 机动车交通事故模型
	 */
	public JdcjtsgModel getPropertyLossElement(WsModel wsModel, JdcjtsgModel jdcjtsgModel) {
		String cmssnr=wsModel.getWsajjbqSegment();
//		if (cmssnr.contains("双方争议焦点")&&(cmssnr.contains("本院认定如下")||cmssnr.contains("经审理查明"))
//				&&(cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("本院认定如下")||
//				cmssnr.indexOf("双方争议焦点")>cmssnr.indexOf("经审理查明"))) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
//		}else if (cmssnr.contains("本院认定如下")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("本院认定如下"));
//		}else if (cmssnr.contains("经审理查明")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("经审理查明"));
//		}else if (cmssnr.contains("事实和理由")) {
//			cmssnr=cmssnr.substring(cmssnr.indexOf("事实和理由"));
//		}

        if ((cmssnr.contains("双方争议焦点")||cmssnr.contains("本院认定如下") )&& (cmssnr.contains("本院认定事实如下") ||
                cmssnr.contains("经审理查明") || cmssnr.contains("事实和理由"))) {
            if (cmssnr.contains("双方争议焦点") && cmssnr.contains("本院认定如下")) {
                if (cmssnr.indexOf("本院认定事实如下") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定事实如下"));
                }
            } else if (cmssnr.contains("双方争议焦点") && cmssnr.contains("经审理查明")) {
                if (cmssnr.indexOf("经审理查明") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("经审理查明"));
                }
            } else if (cmssnr.contains("双方争议焦点") && cmssnr.contains("事实和理由")) {
                if (cmssnr.indexOf("事实和理由") > cmssnr.indexOf("双方争议焦点")) {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("双方争议焦点"));
                } else {
                    cmssnr = cmssnr.substring(cmssnr.indexOf("事实和理由"));
                }
            } else if (cmssnr.contains("本院认定如下") && cmssnr.contains("本院认定事实如下")) {
                cmssnr = cmssnr.substring(cmssnr.indexOf("本院认定如下"));
            }
        }else if (cmssnr.contains("经审理查明")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("经审理查明"));
        }else if (cmssnr.contains("本院认定事实如下")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("本院认定事实如下"));
        }else if (cmssnr.contains("事实和理由")) {
            cmssnr=cmssnr.substring(cmssnr.indexOf("事实和理由"));
        }

		String clwxfse="";//十四、车辆维修费数额
		String czwpssfse="";//十五、车载物品损失费数额
		String sjfse="";//十六、施救费数额
		String clczfse="";//十七、车辆重置费数额
		String tyqj="";//十八、停运期间
		String tyssse="";//十九、停运损失数额
		String tctdxjtgjfyse="";//二十、通常替代性交通工具费用数额
		
		String[] cmssnrs=cmssnr.split("。");
		for (String it : cmssnrs) {
			if (clwxfse==null||clwxfse.isEmpty()) {
				clwxfse=getIndemnity(it, "维修费");
			}
			if (sjfse==null||sjfse.isEmpty()) {
				if (it.contains("拖车费")) {
					sjfse=getIndemnity(it, "拖车费");
				}else if (it.contains("施救费")) {
					sjfse=getIndemnity(it, "施救费");
				}
			}
			if (czwpssfse==null||czwpssfse.isEmpty()) {
				czwpssfse=getIndemnity(it, "车载物品损失费");
			}
			if (clczfse==null||clczfse.isEmpty()) {
				clczfse=getIndemnity(it, "车辆重置费");
			}
			if (tctdxjtgjfyse==null||tctdxjtgjfyse.isEmpty()) {
				tctdxjtgjfyse=getIndemnity(it, "通常替代性交通工具费");
			}
			if (tyssse==null||tyssse.isEmpty()) {
				if (!(it.contains("不同意")||it.contains("不支持"))) {
					if (it.contains("停运损失")) {
						tyssse=getIndemnity(it, "停运损失");
					}else if (it.contains("停运损失费")&&cmssnr.contains("本院依据")&&
							cmssnr.contains("确定")) {
						tyssse=getIndemnity(it, "停运损失费");
					}else if (it.contains("停运损失费")) {
						tyssse=getIndemnity(it, "停运损失费");
					}else if (it.contains("停运")) {
						tyssse=getIndemnity(it, "停运");
					}
				}
			}
			if (tyqj==null||tyqj.isEmpty()) {
				tyqj=getTysj(it);
			}
		}
		//从证据段提取停运信息
        List<String> zjd = wsModel.getWsajjbqkModel().getZjd();
        if (zjd!=null&&!zjd.isEmpty()) {
            for (String it : zjd) {
                String[] its = it.split("。");
                for (String itss : its) {
                    if (tyqj == null || tyqj.isEmpty()) {
                        tyqj = getTysj(itss);
                    }
                }
            }
        }
        //裁判分析过程提取未提取信息
		String cpfxgc=wsModel.getWscpfxgcSegment();
		String[] cpfxgcs=cpfxgc.split("。");
		for (String it : cpfxgcs) {
			if (tyqj==null||tyqj.isEmpty()) {
				tyqj=getTysj(it);
			}
			if (tyssse==null||tyssse.isEmpty()) {
				if (!(it.contains("不同意")||it.contains("不支持"))) {
					if (it.contains("停运损失")) {
						tyssse=getIndemnity(it, "停运损失");
					}else if (it.contains("停运损失费")&&(cmssnr.contains("本院依据")&&
							cmssnr.contains("确定")||cmssnr.contains("本院予以确认"))) {
						tyssse=getIndemnity(it, "停运损失费");
					}else if (it.contains("停运损失费")) {
						tyssse=getIndemnity(it, "停运损失费");
					}else if (it.contains("停运")) {
						tyssse=getIndemnity(it, "停运");
					}
				}
			}
		}
				
		//若案件基本情况不能提取信息，就从裁判结果段提取
		String cpjgnr=wsModel.getWscpjgSegment();
		String[] cpjgnrs=cpjgnr.split("。");
		for (String it : cpjgnrs) {
			if (tyssse==null||tyssse.isEmpty()) {
				if (!(it.contains("不同意")||it.contains("不支持"))) {
					if (it.contains("停运损失")) {
						tyssse=getIndemnity(it, "停运损失");
					}else if (it.contains("停运损失费")&&cmssnr.contains("本院依据")&&
							cmssnr.contains("确定")) {
						tyssse=getIndemnity(it, "停运损失费");
					}else if (it.contains("停运损失费")) {
						tyssse=getIndemnity(it, "停运损失费");
					}else if (it.contains("停运")) {
						tyssse=getIndemnity(it, "停运");
					}
				}
			}
		}

		jdcjtsgModel.setClwxfse(clwxfse);
		jdcjtsgModel.setCzwpssfse(czwpssfse);
		jdcjtsgModel.setSjfse(sjfse);
		jdcjtsgModel.setClczfse(clczfse);
		jdcjtsgModel.setTyqj(tyqj);
		jdcjtsgModel.setTyssse(tyssse);
		jdcjtsgModel.setTctdxjtgjfyse(tctdxjtgjfyse);

		return jdcjtsgModel;
	}
	
	/**
	 * 提取停运时间
	 * @param cmssnr 查明事实段内容
	 * @return 停运时间
	 */
	public String getTysj(String cmssnr) {
		String tysj = null;
		if (cmssnr.contains("自")&&cmssnr.contains("到")&&cmssnr.contains("维修")) {
			tysj=cmssnr.substring(cmssnr.indexOf("自")+1,cmssnr.indexOf("维修"));
		}else if (cmssnr.contains("停运损失费")&&cmssnr.contains("（")&&cmssnr.contains("）")) {
			tysj=cmssnr.substring(cmssnr.indexOf("（")+1,cmssnr.indexOf("）"));
			if (tysj.contains("*")) {
				tysj.replaceAll("\\*", "*");
			}
			tysj=tysj.substring(tysj.indexOf("*")+1,tysj.indexOf("天"));
			tysj=tysj+"天";
		}else if (cmssnr.contains("停运天数")&&cmssnr.contains("经计算")) {
			tysj=cmssnr.substring(cmssnr.indexOf("自"));
		}
		return tysj;
	}

    /**
     * 两个形如XXX元的字符串数值部分相加
     * @param money1 待处理金额
     * @param money2 待处理金额
     * @return 数值部分相加后的字符串
     */
    public String jePlus(String money1, String money2) {
        String money1_t = getJe(money1).get(0);
        String money2_t = getJe(money2).get(0);
        int money1_i = Integer.valueOf(money1_t);
        int money2_i = Integer.valueOf(money2_t);
        int money_i = money1_i + money2_i;
        String money = String.valueOf(money_i)+"元";
        return money;
    }

	/**
	 * 提取定残后护理费数额
	 * @param cmssnr 查明事实段内容
	 * @return 定残后护理费数额
	 */
	public String getDchhlfse(String cmssnr) {
		// TODO 找到定残段落完成提取逻辑
		return null;
	}
	
	/**
	 * 提取定残后护理依赖程度意见,期望为生活能否自理，几级依赖。
	 * @param cmssnr 查明事实段内容
	 * @return 定残后护理依赖程度意见
	 */
	public String getDchhlylcdjdyj(String cmssnr) {
		// TODO 找到定残段落完成提取逻辑
		return null;
	}
	
	/**
	 * 获得残疾等级鉴定结论时间
	 * @param cmssnr 查明事实内容
	 * @return 残疾等级鉴定结论时间
	 */
	public String getCjdjjdjlsj(String cmssnr) {
		String cjdjjdjlsj="";
		if (cmssnr.contains("鉴定意见书")&&cmssnr.contains("年")&&
				cmssnr.contains("月")&&cmssnr.contains("日")||(cmssnr.contains("鉴") && cmssnr.contains("字"))) {
			cjdjjdjlsj=cmssnr.substring(0, cmssnr.indexOf("日")+1);
//			cjdjjdjlsj=getDate(cjdjjdjlsj);

            if (cjdjjdjlsj == null || cjdjjdjlsj.isEmpty()) {
                if (cmssnr.contains("鉴") && cmssnr.contains("字") &&
                        cmssnr.contains("［") && cmssnr.contains("］") || cmssnr.contains("【") && cmssnr.contains("】") ||
                        cmssnr.contains("[") && cmssnr.contains("]")) {
                    if (cmssnr.contains("［")) {
                        cjdjjdjlsj = cmssnr.substring(cmssnr.indexOf("［") + 1, cmssnr.indexOf("］"));
                    }
                }
            } else if (cjdjjdjlsj.contains("于")){
                cjdjjdjlsj = cjdjjdjlsj.substring(cjdjjdjlsj.lastIndexOf("于") + 1);
            }
		}

		return cjdjjdjlsj;
	}
	
	/**
	 * 获得句子中的时间
	 * @param cmssnr 待分离时间的语句
	 * @return 句子中的时间
	 */
	public String getDate(String cmssnr) {
		String number = "";
		int index = -1;
		char[] chars=cmssnr.toCharArray();
		for (int j=chars.length-1;j>=0;j--){
			if(!(Character.isDigit(cmssnr.charAt(j)) || (cmssnr.charAt(j) == '年') ||
					(cmssnr.charAt(j) == '月') || (cmssnr.charAt(j) == '日'))){
				index = j;
			}

//            if (!(Character.isDigit(cmssnr.charAt(j)) && cmssnr.charAt(j) == '年' &&
//                    cmssnr.charAt(j) == '月' && cmssnr.charAt(j) == '日')) {
//                index = j;
//            }
			
			String je_toAdd = StringUtil.ToDBC(cmssnr.substring(index+1, cmssnr.length()));
		    je_toAdd = je_toAdd.replaceAll(",", "");
		    	
		    if(!StringUtil.isBlank(je_toAdd)){
		    	number=je_toAdd.toString();
		    	break;
		    }
		}
		return number;
	}
	
	/**
	 * 获得残疾等级鉴定结论
	 * @param cmssnr 查明事实段
	 * @return 残疾等级鉴定结论
	 */
	public String getCjdjjdjl(String cmssnr) {
		String cjdjjdjl = "";
		if (cmssnr.contains("鉴定意见书")&&cmssnr.contains("级")) {
			if (cmssnr.contains("符合") && cmssnr.contains("伤残")) {
				cjdjjdjl = cmssnr.substring(cmssnr.lastIndexOf("符合")+2, cmssnr.lastIndexOf("伤残")+2);
			} else if (cmssnr.contains("伤残")) {
                cjdjjdjl=cmssnr.substring(cmssnr.lastIndexOf("为")+1);
                if (cjdjjdjl.contains("，")) {
                    cjdjjdjl=cjdjjdjl.substring(0,cjdjjdjl.indexOf("，"));
                }
            }
        }else if (cmssnr.contains("伤残")&&cmssnr.contains("级")) {
			if (cmssnr.contains("等级")) {
				cjdjjdjl=cmssnr.substring(cmssnr.lastIndexOf("为")+1);
			}
        }
		return cjdjjdjl;
	}
	
	/**
	 * 获得误工期
	 * @param cmssnr 查明事实内容
	 * @return 误工期
	 */
	public String getWgq(String cmssnr) {
		String wgq="";
        if (cmssnr.contains("丧葬费")||cmssnr.contains("死亡赔偿金")) {
            wgq="当事人逝世，无误工期。";
        }else if (cmssnr.contains("误工")) {
			if (cmssnr.contains("时间")&&cmssnr.contains("共计")) {
				wgq=cmssnr.substring(cmssnr.indexOf("共计")+2, cmssnr.indexOf("天"));
			}else if (cmssnr.contains("时间")&&cmssnr.contains("天")) {
				wgq=cmssnr.substring(cmssnr.lastIndexOf("误工时间")+4, cmssnr.indexOf("天"))+"天";
			}else if (cmssnr.contains("时间")&&cmssnr.contains("月")) {
				wgq=cmssnr.substring(cmssnr.lastIndexOf("误工时间")+4, cmssnr.indexOf("月"))+"月";
			}
		}
		return wgq;
	}
	
	/**
	 * 获得营养期
	 * @param cmssnr 查明事实内容
     * @return 营养期
	 */
	public String getYyq(String cmssnr) {
		String yyq="";
		if (cmssnr.contains("丧葬费")||cmssnr.contains("死亡赔偿金")) {
			yyq="当事人逝世，无营养期。";
		}else if (cmssnr.contains("营养期")&&cmssnr.contains("相关规定")) {
            String[] yyqs = cmssnr.split("，");
            for (String it :
                    yyqs) {
                if (!it.contains("过长")) {
                    if (it.contains("营养期") && it.contains("天")) {
                        yyq = getDate(it)+"天";
                    }
                }
            }
        }
		return yyq;
	}
	
	/**
	 * 获得护理期
	 * @param cmssnr 查明事实段内容
	 * @return 护理期
	 */
	public String getHlq(String cmssnr) {
		String hlq="";
		if (cmssnr.contains("丧葬费")||cmssnr.contains("死亡赔偿金")) {
			hlq="当事人逝世，无护理期。";
        } else if (!cmssnr.contains("辩称")&&!cmssnr.contains("委托")) {
            if (cmssnr.contains("证明")&&cmssnr.contains("于")&&cmssnr.contains("因")) {
                hlq=cmssnr.substring(cmssnr.indexOf("于")+1,cmssnr.indexOf("因"));
            }else if (cmssnr.contains("护理")&&cmssnr.contains("期限")&&cmssnr.contains("天")) {
                hlq=cmssnr.substring(cmssnr.indexOf("护理期限")+4, cmssnr.lastIndexOf("天"))+"天";
            }else if (cmssnr.contains("护理") && cmssnr.contains("（") && cmssnr.contains("）") && cmssnr.contains("×") && cmssnr.contains("+")) {
                hlq = cmssnr.substring(cmssnr.indexOf("（"), cmssnr.lastIndexOf("）"));
                List<String> days = getDay(hlq);
                int day = 0;
                for (String it : days) {
                    if (it.contains(" ")) {
                        it.replaceAll(" ", "");
                    }
                    if (Double.valueOf(it) != 365 && days.size() > 1) {
                        day += Double.valueOf(it);
                    } else if (days.size() == 1) {
                        day += Double.valueOf(it);
                    }
                }
                hlq = String.valueOf(day) + "天";
            } else if (cmssnr.contains("护理") && cmssnr.contains("期间")&&!cmssnr.contains("主张")&&!cmssnr.contains("不")) {
                hlq = getJe(cmssnr).get(0)+"天";
            }
        }
        return hlq;
	}
	
	/**
	 * 获得住院时间
	 * @param cmssnr 查明事实段内容
	 * @return 住院时间
	 */
	public String getZysj(String cmssnr) {
		String zysj="";
		if (cmssnr.contains("丧葬费")||cmssnr.contains("死亡赔偿金")) {
			zysj="当事人逝世，无住院时间。";
        } else if (!cmssnr.contains("辩称")) {
            if (cmssnr.contains("经查")&&cmssnr.contains("住院")&&cmssnr.contains("天")) {
                if (cmssnr.contains("住院天数")) {
//				zysj=cmssnr.substring(cmssnr.indexOf("住院天数")+4,cmssnr.indexOf("天"));
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("住院天数")+4,cmssnr.lastIndexOf("天"))+"天";
                }else {
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("住院")+2,cmssnr.indexOf("天"))+"天";
                }
            }else if (cmssnr.contains("伙食补助")&&cmssnr.contains("住院")&&cmssnr.contains("天")&&
                    ((!cmssnr.contains("主张")&&!cmssnr.contains("诉讼"))||(cmssnr.contains("主张")&&(cmssnr.contains("认可")||cmssnr.contains("支持"))))) {
                if (cmssnr.contains("住院天数")) {
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("住院天数")+4,cmssnr.lastIndexOf("天"))+"天";
                }else if (cmssnr.contains("共计")) {
                    zysj=cmssnr.substring(cmssnr.lastIndexOf("共计")+2, cmssnr.indexOf("天"))+"天";
                }else {
//                    zysj=cmssnr.substring(cmssnr.lastIndexOf("住院")+2,cmssnr.indexOf("天"))+"天";
                    cmssnr = cmssnr.substring(cmssnr.indexOf("住院") + 2);
                    zysj = cmssnr.substring(0, cmssnr.indexOf("天")) + "天";
                }
            } else if (cmssnr.contains("住院") && cmssnr.contains("支持")&&!cmssnr.contains("护理费")) {
                if (cmssnr.contains("住院期间")&&cmssnr.contains("天")) {
                    zysj = cmssnr.substring(cmssnr.lastIndexOf("住院期间") + 4, cmssnr.indexOf("天")) + "天";
                }
            } else if (cmssnr.contains("住院") && cmssnr.contains("支持")&&cmssnr.contains("护理费发票")) {
                if (cmssnr.contains("住院期间")&&cmssnr.contains("天")) {
                    zysj = cmssnr.substring(cmssnr.lastIndexOf("住院期间") + 4, cmssnr.indexOf("天")) + "天";
                }
            }
        }
		return zysj;
	}
	
	/**
	 * 获得各种赔偿金额
	 * @param cmssnr 查明事实段内容
	 * @param moneyType 需要提取的赔偿金类型
	 * @return 赔偿金额
	 */
	private String getIndemnity(String cmssnr, String moneyType) {
		String money=null;
		String[] cmssnrs = cmssnr.split("，|、");
		for (String it : cmssnrs) {
			if (it.contains(moneyType)) {
				List<String> moneys = getJe(it);
				if (!moneys.isEmpty()) {
					money=moneys.get(0)+"元";
				}
			}
		}
		return money;
	}

	/**
	 * 提取事故时间		
	 * @param it 目标语句
	 * @return 事故时间
     */
	private String getSgsj(String it) {
		String sgsj="未提及";
		String[] nr=it.split("，|。");
		for(int i=0;i<nr.length;i++){
			if(nr[i].contains("年")&&nr[i].contains("月")&&nr[i].contains("日")&&nr[i].contains("时")&&nr[i].contains("分")){
				if(nr[i].contains("："))
					sgsj=nr[i].substring(nr[i].indexOf("：")+1);
				else 
					sgsj=nr[i];
				break;
			}				
		}
		return sgsj;
	}

	/**
	 * 提取事故赔偿责任主体
	 * @param wssscyrModellist 文书诉讼参与人模型
	 * @return 事故赔偿责任主体
	 */
	public List<String> getSgqtpczrzt(List<WssscyrModel> wssscyrModellist) {
		List<String> sgpczrzt=new ArrayList<>();
		for(int i=0;i<wssscyrModellist.size();i++){
			WssscyrModel sscyr=(WssscyrModel) wssscyrModellist.get(i);
			if(sscyr.getSssf().equals("被告")){
				if (!sscyr.getSscyr().contains("公司")) {
					sgpczrzt.add(sscyr.getSscyr());
				}
			}
		}
		return sgpczrzt;
	}
	
	/**
	 * 提取垫付人	
	 * @param cmssnr 查明事实段内容
	 * @return 垫付人
     */
	public String getDfr(String cmssnr) {
		String dfr="";
		String[] cmssnrs=cmssnr.split("。");
		for (String it : cmssnrs) {
			if (it.contains("事故发生后")) {
				if (it.contains("向")&&it.contains("支付")) {
					dfr=it.substring(it.lastIndexOf("，")+1, it.indexOf("向"));
				}else if (it.contains("收取")&&it.contains("原告")&&it.contains("被告")) {
					dfr=it.substring(it.indexOf("被告")+2);
				}else if (it.contains("系")&&it.contains("支付")) {
					dfr=it.substring(it.indexOf("系")+1,it.lastIndexOf("支付"));
				}
			}
		}
		dfr=removeLastNum(dfr);
		return dfr;
	}
	
	/**
	 * 去除句子最后的数字信息
	 * @param target 待去除数字信息的语句
	 * @return 句子
	 */
	public String removeLastNum(String target) {
		String[] targets=target.split("元|万元|共计");
		char[] target2char = targets[0].toCharArray();
		int index=-1;
		for (int i = target2char.length-1; i > 0 ; i--) {
			if(!(Character.isDigit(target2char[i]))){
	    		index=i;
	    		break;
			}
		}
		return target=target.substring(0,index+1);
	}
	
	/**
	 * 提取已垫付赔偿款数额
	 * @param cmssnr 查明事实段内容
	 * @return 已垫付赔偿款数额
	 */
	public String getYdfpckse(String cmssnr) {
		String ydfpck="";
		String[] cmssnrs=cmssnr.split("。");
		for (String it : cmssnrs) {
			if (it.contains("另查")&&it.contains("事故发生后")&&
					(it.contains("支付")||it.contains("收取"))) {
				List<String> moneyList = getJe(it);
				if (moneyList.size()>0) {
					ydfpck=moneyList.get(0)+"元";
				}
			}else if (it.contains("事故发生后")&&
					(it.contains("支付")||it.contains("收取"))) {
				List<String> moneyList = getJe(it);
				if (moneyList.size()>0) {
					ydfpck=moneyList.get(0)+"元";
				}
			}
		}
		return ydfpck;
	}

	/**
	 * 提取商业三者险保险限额
	 * @param cmssnr 查明事实段内容
	 * @param sgclph 事故车辆牌照
	 * @param sgclszxbxgs 事故车辆三者险保险公司
	 * @return 商业三者险保险限额
	 */
	public List<String> getSzxpcxe(String cmssnr, List<String> sgclph, List<String> sgclszxbxgs) {
		List<String> szxpcxe = new ArrayList<>();
		String[] cmssnrs = cmssnr.split("。");
		
		for (int i=0;i<cmssnrs.length;i++) {
			String it_nr = cmssnrs[i];
			int jqxFlag=0;
			int szxFlag=0;
			if (it_nr.contains("保险金额")) {
				List<String> moneyList=getJe(it_nr);
//				如果一个句子中存在交强险和商业三者险两个保额，先判断两个险种的顺序
				if ((it_nr.contains("第三者责任强制保险")||it_nr.contains("交通事故责任强制保险")||it_nr.contains("交强险"))&&
						(it_nr.contains("商业保险")||it_nr.contains("商业三者险")||it_nr.contains("第三者商业责任保险"))) {
					
					if (it_nr.contains("第三者责任强制保险")) {
						jqxFlag=it_nr.indexOf("第三者责任强制保险");
					}else if (it_nr.contains("交通事故责任强制保险")) {
						jqxFlag=it_nr.indexOf("交通事故责任强制保险");
					}else if (it_nr.contains("交强险")) {
						jqxFlag=it_nr.indexOf("交强险");
					}
					
					if (it_nr.contains("商业保险")) {
						szxFlag=it_nr.indexOf("商业保险");
					}else if (it_nr.contains("商业三者险")) {
						szxFlag=it_nr.indexOf("商业三者险");
					}else if (it_nr.contains("第三者商业责任保险")) {
						szxFlag=it_nr.indexOf("第三者商业责任保险");
					}
					
//					判断交强险和商业三者险顺序，按序存储保额
					for (int j = 0; j < sgclszxbxgs.size(); j++) {
						for (String it_cp : sgclph) {
							if (sgclszxbxgs.get(j).contains(it_cp)) {
								if (jqxFlag<szxFlag) {
//									szxpcxe.add(jeFilter(moneyList.get(1))+"元");
									szxpcxe.add(it_cp+"三者险赔偿额度："+moneyList.get(1)+"元");
								}else {
//									szxpcxe.add(jeFilter(moneyList.get(0))+"元");
									szxpcxe.add(it_cp+"三者险赔偿额度："+moneyList.get(0)+"元");
								}
							}
						}
					}
				}else {
//					没有出现两个险种
					for (String it : moneyList) {
						szxpcxe.add(jeFilter(it)+"元");
					}
					for (int j = 0; j <sgclszxbxgs.size(); j++) {
						for (String it_cp : sgclph) {
							if (sgclszxbxgs.get(j).contains(it_cp)) {
								szxpcxe.set(j, it_cp+"三者险赔偿额度："+szxpcxe.get(j));
							}
						}
					}
				}
			}
		}
		return szxpcxe;
	}
	
	/**
	 * 对提取的金额进行进一步提纯	
	 * @param jeString 待提纯的金额
	 * @return 提取的金额
	 */
	public String jeFilter(String jeString) {
		String reg="([\u4e00-\u9fa5]?)([0-9]+\\.[0-9]+||[0-9]+)";
		Pattern pattern=Pattern.compile(reg);
		Matcher matcher=pattern.matcher(jeString);
		if (matcher.find()) {
			jeString=matcher.group(2);
		}
		return jeString;
	}

    /**
     * 提取句子中的日期
     * @param cmssnr 待分析内容
     * @return 日期
     */
    public List<String> getDay(String cmssnr) {
        List<String> dayList = new ArrayList<>();
        String reg = "天";
        String[] cmssnrs = cmssnr.split(reg);
        for (int i=0;i<cmssnrs.length;i++) {
            int index=-1;
            char[] chars=cmssnrs[i].toCharArray();
            for (int j=chars.length-1;j>=0;j--){
                if(!(Character.isDigit(cmssnrs[i].charAt(j))||cmssnrs[i].charAt(j)=='．'||
                        cmssnrs[i].charAt(j)=='.'||cmssnrs[i].charAt(j)=='万'||
                        cmssnrs[i].charAt(j)=='，'||cmssnrs[i].charAt(j)==',')||
                        cmssnrs[i].charAt(j)=='、'){
                    index=j;
                    break;
                }
            }
            String je_toAdd = StringUtil.ToDBC(cmssnrs[i].substring(index+1, cmssnrs[i].length()));

            if (!StringUtil.isBlank(je_toAdd)) {
                boolean flag = true;
                for (String it :
                        dayList) {
                    if (it.equals(je_toAdd)) {
                        flag=false;
                        break;
                    }
                }
                if (flag) {
                    dayList.add(je_toAdd);
                }
            }
        }
        return dayList;
    }

	/**
	 * 提取句子中的数字
	 * @param cmssnr 查明事实段内容
	 * @return 数字
	 */
	public List<String> getJe(String cmssnr) {
		List<String> moneyList = new ArrayList<>();
		String reg = "元|天";
		String[] cmssnrs=cmssnr.split(reg);
		for (int i = 0; i < cmssnrs.length; i++) {
			int index=-1;
			char[] chars=cmssnrs[i].toCharArray();
			for (int j=chars.length-1;j>=0;j--){
				if(!(Character.isDigit(cmssnrs[i].charAt(j))||cmssnrs[i].charAt(j)=='．'||
						cmssnrs[i].charAt(j)=='.'||cmssnrs[i].charAt(j)=='万'||
						cmssnrs[i].charAt(j)=='，'||cmssnrs[i].charAt(j)==',')||
						cmssnrs[i].charAt(j)=='、'){
		    		index=j;
		    		break;
				}
			}
			
			String je_toAdd = StringUtil.ToDBC(cmssnrs[i].substring(index+1, cmssnrs[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
	    	
	    	if(!StringUtil.isBlank(je_toAdd)){
	        	if(je_toAdd.endsWith("万")){
		    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
		    		try{
		    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                        je_toAdd=je_addDou+"";
                    }catch(Exception e){
                    	je_toAdd="";
		    		}
	        	}
	        	if (!StringUtil.isBlank(je_toAdd)) {
	        		moneyList.add(je_toAdd.toString());
				}
	    	}
	    }
		return moneyList;
	}
	
	/**
	 * 提取三者险保险公司
	 * @param cmssnr 查明事实段内容
	 * @param sgclph 事故车辆牌照
	 * @return 三者险保险公司
	 */
	public List<String> getSzxbxgs(String cmssnr, List<String> sgclph) {
		List<String> szxbxgs= new ArrayList<>();
		String[] cmssnrs = cmssnr.split("。");
		for (String its : cmssnrs) {
			for (String it : sgclph) {
				if (!(its.contains("未") || its.contains("没有"))) {
					if (its.contains(it)&&(its.contains("商业保险")||its.contains("商业三者险")||its.contains("第三者商业责任保险"))) {
						szxbxgs.add(it+"三者险保险公司："+its.substring(its.indexOf("被告")+2,its.indexOf("投保")));
					}
				}
			}
		}
		return szxbxgs;
	}
	
	/**
	 * 提取交强险保险公司
	 * @param cmssnr 查明事实段内容
	 * @param sgclph 事故车辆牌照
	 * @return 交强险保险公司
	 */
	public List<String> getJqxbxgs(String cmssnr, List<String> sgclph) {
		List<String> jqxbxgs = new ArrayList<>();
		String[] cmssnrs= cmssnr.split("。");
		for (String its : cmssnrs) {
			for (String it : sgclph) {
				if (!(its.contains("未") || its.contains("没有"))) {
					if (its.contains(it)&&(its.contains("交强险")||its.contains("交通事故责任强制保险")||its.contains("第三者责任强制保险"))) {
						if (its.contains("在")&&its.contains("投有")) {
							jqxbxgs.add(it+"交强险保险公司："+its.substring(its.indexOf("在")+1,its.indexOf("投有")));
						}else if (its.contains("在")&&its.contains("投保")) {
							jqxbxgs.add(it+"交强险保险公司："+its.substring(its.indexOf("在")+1,its.indexOf("投保")));
						}else if (its.contains("被告")&&its.contains("投保")) {
							jqxbxgs.add(it+"交强险保险公司："+its.substring(its.indexOf("被告")+2,its.indexOf("投保")));
						}
					}else{
//						jqxbxgs.add(it+"未投保交强险");
					}
				}
			}
		}
		return jqxbxgs;
	}
	
	/**
	 * 提取事故车辆驾驶员
	 * @param sjjg 事故经过
	 * @param sgclph 事故车辆牌照
	 * @return 事故车辆驾驶员
	 */
	public List<String> getSgcljsy(String sjjg, List<String> sgclph) {
		List<String> sgcljsy = new ArrayList<>();
		String[] sjjgs= sjjg.split("，|。");
		for (String it_jsynr : sjjgs) {
			for (String it_ph : sgclph) {
				if (it_jsynr.contains(it_ph)&&it_jsynr.contains("驾驶")) {
					String jsyName = it_jsynr.substring(0, it_jsynr.indexOf("驾驶"));
					String[] jsyName2split = jsyName.split("东|南|西|北|遇|原告|被告");
					if (jsyName2split.length>0) {
						if (!sgcljsy.contains(it_ph+"驾驶员："+jsyName2split[jsyName2split.length-1])) {
							sgcljsy.add(it_ph+"驾驶员："+jsyName2split[jsyName2split.length-1]);
						}	
					}
//					for (String it_jsy : sgcljsy) {
//						if (it_jsy.equals(it_ph+"驾驶员："+jsyName2split[jsyName2split.length-1])) {
//							break;
//						}
//						sgcljsy.add(it_ph+"驾驶员："+jsyName2split[jsyName2split.length-1]);
//					}
				}
			}
		}
//		String[] cmssnrs =cmssnr.split("。");
//		for (String its : cmssnrs) {
//			for (String it : sgclph) {
//				if (its.contains(it)&&(its.contains("驾驶人")||its.contains("驾驶"))&&its.contains("为")) {
//					if (its.indexOf("为")>its.indexOf("驾驶")) {
//						String jsy2split = its.substring(0,its.indexOf("驾驶"));
//						String[] jsy2splits = jsy2split.split("，");
//						for (String it_jsy : sgcljsy) {
//							if (it_jsy.equals(it+"驾驶员:"+jsy2splits[jsy2splits.length-1])) {
//								break;
//							}
//						}
//						sgcljsy.add(it+"驾驶员:"+jsy2splits[jsy2splits.length-1]);
//					}else if (its.indexOf("为")<its.indexOf("驾驶")) {
//						if (its.contains("系为")) {
//							String jsy2split = its.substring(0,its.indexOf("系为"));
//							String[] jsy2splits = jsy2split.split("，");
//							for (String it_jsy : sgcljsy) {
//								if (it_jsy.equals(it+"驾驶员:"+jsy2splits[jsy2splits.length-1])) {
//									break;
//								}
//							}
//							sgcljsy.add(it+"驾驶员:"+its.substring(0, its.indexOf("系为")));
//						}else {
//							String jsy2split = its.substring(0,its.indexOf("为"));
//							String[] jsy2splits = jsy2split.split("，");
//							for (String it_jsy : sgcljsy) {
//								if (it_jsy.equals(it+"驾驶员:"+jsy2splits[jsy2splits.length-1])) {
//									break;
//								}
//							}
//							sgcljsy.add(its.substring(0,its.indexOf("为")));
//						}
//					}
//				}
//			}
//		}
		return sgcljsy;
	}

	/**
	 * 提取事故车辆所有者
	 * @param cmssnr 查明事实段内容
	 * @param sgclph 事故车辆牌号
	 * @return 事故车辆所有者
	 */
	public List<String> getSgclsyz(String cmssnr, List<String> sgclph) {
		List<String> sgclsyz = new ArrayList<>();
		String[] cmssnrs =cmssnr.split("。");
		for (String its : cmssnrs) {
			for (String it : sgclph) {
				if (its.contains(it)&&its.contains("实际")&&its.contains("所有")) {
					if (its.contains("实际所有人")) {
						String subcmssr=its.substring(its.indexOf("实际所有人为")+6);
						String[] subcmssrs=subcmssr.split("，");
						sgclsyz.add(it+"所有人:"+subcmssrs[0]);
					}else if (its.contains("实际为")&&its.contains("所有")) {
						sgclsyz.add(it+"所有人:"+its.substring(its.indexOf("实际为")+3, its.lastIndexOf("所有")));
					}
				}
			}
		}
		return sgclsyz;
	}
	
	/**
	 * 提取事故车辆牌照
	 * @param cmssnr 查明事实段内容
	 * @return 事故车辆牌照
	 */
	public List<String> getSgclph(String cmssnr) {
		String[] cmssnrs=cmssnr.split("，|、");
		List<String> sgclph=new ArrayList<>();
		String provinceName="[\u4EAC]?[\u6D25]?[\u5180]?[\u664B]?[\u8499]?[\u8FBD]?[\u5409]?"
				+ "[\u9ED1]?[\u6CAA]?[\u82CF]?[\u6D59]?"
				+ "[\u7696]?[\u95FD]?[\u8D63]?[\u9C81]?[\u8C6B]?[\u9102]?[\u6E58]?[\u7CA4]?[\u6842]?"
				+ "[\u743C]?[\u5DDD]?[\u8D35]?[\u4E91]?[\u6E1D]?[\u85CF]?[\u9655]?[\u7518]?[\u9752]?"
				+ "[\u5B81]?[\u65B0]?[\u6E2F]?[\u6FB3]?[\u53F0]?";
//		String reg="([\u4e00-\u9fa5]||、)("+provinceName+"[0-9a-zA-Z]{5,6}[\u6302]?)([\u4e00-\u9fa5]?)";
		String reg="([\u4e00-\u9fa5]||、)([\u4e00-\u9fa5]{1}[A-Z]{1}[0-9a-zA-Z]{4,5}[\u6302]?)([\u4e00-\u9fa5]?)";
//		String reg2=provinceName+"[A-Z]{1}[0-9a-zA-Z]{4,5}[\u6302]?";
		String[] province={"京","津","冀","晋","蒙","辽","吉","黑","沪","苏","浙","皖","闽","赣","鲁","豫","鄂","湘","粤","桂"
		                   ,"琼","川","贵","云","渝","藏","陕","甘","青","宁","新","港","澳","台"};
		Pattern pattern=Pattern.compile(reg);
//		Pattern pattern2=pattern.compile(reg2);
		
		for (String it : cmssnrs) {
			Matcher matcher=pattern.matcher(it);
			while(matcher.find()) {
				String	sgclph_temp=matcher.group(2);
//				Matcher matcher2 = pattern2.matcher(sgclph_temp);
				boolean flag_repeat = false;
				boolean flag_province = false;
				if (sgclph.contains(sgclph_temp)) {
					flag_repeat=true;
				}
				if (!flag_repeat) {
//					if (matcher2.find()) {
//						sgclph.add(sgclph_temp);
//					}
					for (String it_p : province) {
						if (sgclph_temp.contains(it_p)) {
							flag_province=true;
							break;
						}
					}
					if (flag_province) {
						sgclph.add(sgclph_temp);
					}
				}
			}
		}
		return sgclph;
	}
	
	/**
	 * 获得事故责任认定
	 * @param cmssnr 查明事实段内容
	 * @return 事故责任认定
	 */
	public List<String> getSgzr(String cmssnr) {
		List<String>sgzr=new ArrayList<>();
		String[] sgzrnrSplit=cmssnr.split("，|,|：|。");
		
		for (String it : sgzrnrSplit) {
			if ((it.contains("承担")||it.contains("负"))&&(it.contains("主要责任")||it.contains("次要责任")||
					it.contains("全部责任")||it.contains("事故责任"))) {
				sgzr.add(it);
			}
		}
		return sgzr;
	}
	
	/**
	 * 获取事故地点
	 * @param cmssNr 查明事实段内容
	 * @return 事故地点
	 */
	public String getSgdd(String cmssNr) {
		String sgdd="未提及";

			int xzc=CmssdpreServiceImpl.worddis("行至","处", cmssNr);
			int xss=CmssdpreServiceImpl.worddis("行驶至","时",cmssNr);
			int zs=CmssdpreServiceImpl.worddis("在","时",cmssNr);
			int zy=CmssdpreServiceImpl.worddis("在","遇",cmssNr);
			int yxs=CmssdpreServiceImpl.worddis("沿","行驶",cmssNr);
			if (xzc>5&&xzc<30) {
				sgdd=cmssNr.substring(cmssNr.indexOf("行至")+2, cmssNr.lastIndexOf("处"));
			}else if(xss>6&&xss<30){
				sgdd=cmssNr.substring(cmssNr.indexOf("行驶至")+3,cmssNr.lastIndexOf("时"));
			}else if(zs>5&&zs<40){
				sgdd=cmssNr.substring(cmssNr.lastIndexOf("在")+1,cmssNr.lastIndexOf("时"));
			}else if(zy>5&&zy<30){
				sgdd=cmssNr.substring(cmssNr.indexOf("在")+1,cmssNr.indexOf("遇"));
			}else if(yxs>5&&yxs<30){
				sgdd=cmssNr.substring(cmssNr.indexOf("沿")+1,cmssNr.lastIndexOf("行驶"));
			}
//		String[] nr=cmssNr.split("，");
//
//		for(int i=0;i<nr.length;i++){
//			int xzc=CmssdpreServiceImpl.worddis("行至","处", nr[i]);
//			int xss=CmssdpreServiceImpl.worddis("行驶至","时",nr[i]);
//			int zs=CmssdpreServiceImpl.worddis("在","时",nr[i]);
//			int yxs=CmssdpreServiceImpl.worddis("沿","行驶",nr[i]);
//			if (xzc>0&&xzc<30) {
//				sgdd=nr[i].substring(nr[i].indexOf("行至")+2, nr[i].lastIndexOf("处"));
//			}else if(xss>0&&xss<30){
//				sgdd=nr[i].substring(nr[i].indexOf("行驶至")+3,nr[i].lastIndexOf("时"));
//				break;
//			}else if(zs>0&&zs<30){
//				sgdd=nr[i].substring(nr[i].lastIndexOf("在")+1,nr[i].lastIndexOf("时"));
//				break;
//			}else if(nr[i].contains("行驶至")){
//				sgdd=nr[i].substring(nr[i].indexOf("行驶至")+3);
//				break;
//			}else if(yxs>0&&yxs<30){
//				sgdd=nr[i].substring(nr[i].indexOf("沿")+1,nr[i].lastIndexOf("行驶"));
//				break;
//			}
//		}
		return sgdd;
	}
}
