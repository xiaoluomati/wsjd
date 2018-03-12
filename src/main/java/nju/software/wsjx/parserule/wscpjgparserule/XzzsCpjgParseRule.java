package nju.software.wsjx.parserule.wscpjgparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.MsesFhcsyyEnum;
import nju.software.wsjx.model.Enum.SsfEnum;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.service.impl.jtsg.DsrbxgsServiceImpl;
import nju.software.wsjx.service.impl.jtsg.PjjgServiceImpl;
import nju.software.wsjx.service.jtsg.DsrbxgsService;
import nju.software.wsjx.service.jtsg.PjjgService;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.util.NumberConverter;
import nju.software.wsjx.util.StringUtil;

/**
 * 行政再审裁判结果解析
 * @author LiYX
 *
 */
public class XzzsCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
		// TODO Auto-generated method stub
		List<String> cpjg = wsAnalyse.getCpjg();
		// TODO Auto-generated method stub
		WscpjgModel wscpjgModel = new WscpjgModel();
		List<String> cpjgnrList = new ArrayList<String>();
		List<PjjgnrModel> pjjgList=new ArrayList<PjjgnrModel>();
		String pjjgnr ="";
		String allnr="";
		boolean cpjgnrFlag = true;
		List<String> pjjelx = getPjjeLx();
		for (int i = 0; i < cpjg.size(); i++) {
			allnr = allnr+cpjg.get(i);
//			审理费之前的都是审判结果内容
			if(cpjgnrFlag&&!isAjslf(cpjg.get(i))){
				cpjgnrList.add(cpjg.get(i));
				pjjgList.add(new PjjgnrModel(cpjg.get(i)));
				pjjgnr = pjjgnr+cpjg.get(i);
			}else{
				cpjgnrFlag = false;
			}
//			案件受理费
			if (isAjslf(cpjg.get(i))) {
				if(StringUtil.isBlank(wscpjgModel.getAjslf())){
					wscpjgModel.setAjslf(cpjg.get(i));
				}else{
					wscpjgModel.setAjslf(wscpjgModel.getAjslf()+cpjg.get(i));
				}
				cpjgnrFlag = false;
			}

// 			上诉相关
			
			if(cpjg.get(i).indexOf("如不服本")>-1){	
				setSsqk(wscpjgModel, cpjg.get(i));
			}
			

//		解析案件受理费
		setSsfcd(wscpjgModel, wssscyrModellist);
		
		
//		结案方式
		wscpjgModel = setXzzsjafs(wscpjgModel, pjjgnr,allnr,wsAnalyse);
		}
		return wscpjgModel;
	}

	/**
	 * 解析行政再审结案方式
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @param allPjjg
	 * @param wsAnalyse
	 * @return
	 */
	private WscpjgModel setXzzsjafs(WscpjgModel wscpjgModel, String pjjgnr, String allPjjg, WsAnalyse wsAnalyse) {
		if(!StringUtil.equals(pjjgnr, "")){
			List<String> cpfxgc = wsAnalyse.getCpfxgc();
			String cpfxgcStr="";
			if(cpfxgc!=null){
				for(String s:cpfxgc){
					cpfxgcStr = cpfxgcStr+s;
				}
			}
			if(allPjjg.contains("判决")||StringUtil.contains(cpfxgcStr, "判决如下")){
				wscpjgModel.setJafs("判决");
			}else if(pjjgnr.contains("另行")&&pjjgnr.contains("再审")&&pjjgnr.contains("中止")&&pjjgnr.contains("执行")){
				wscpjgModel.setJafs("提起再审");
			}else if(pjjgnr.contains("撤销")&&pjjgnr.contains("由")||pjjgnr.contains("指令")&&pjjgnr.contains("予以立案")||pjjgnr.contains("受理")||pjjgnr.contains("审理")&&pjjgnr.contains("终审")){
				wscpjgModel.setJafs("撤销原裁定并指令受理");
			}
			if(StringUtil.isBlank(wscpjgModel.getJafs())){
				wscpjgModel.setJafs("其他");
			}
		}
		return wscpjgModel;
	}

	/**
	 * 解析诉讼费承担
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	private void setSsfcd(WscpjgModel wscpjgModel, List<WssscyrModel> wssscyrModellist) {
		String ssfcdjl = wscpjgModel.getAjslf();
		WsCpjgssfModel ssfModel = new WsCpjgssfModel();
		if(!StringUtil.isBlank(ssfcdjl)){
//			-----------诉讼费记录--------------
			ssfModel.setSsfjl(ssfcdjl);
//			------遍历诉讼费种类，解析诉讼费金额-----
			int indexOfSsf=-1,indexOfYuan=-1,lengthOfSsfName=-1;
			List<WsCpjgssfjeModel> jeModels = new ArrayList<WsCpjgssfjeModel>();
			int ssfzje=0;
			
			//按照元区分
			String[] ssfstrList = ssfcdjl.split("元");
//			for(String ssfstr:ssfstrList){
			for(int i=0;i<ssfstrList.length-1;i++){
				String ssfstr = ssfstrList[i];
//				对于每一个诉讼费
				for(SsfEnum ssfEnum:SsfEnum.values()){
					if(StringUtil.contains(ssfstr, ssfEnum.getSsfName())){
						ssfstr=StringUtil.trim(ssfstr);
						String je = ssfstr.substring(ssfEnum.getSsfName().length()+ssfstr.indexOf(ssfEnum.getSsfName()), ssfstr.length());//+"元";
						je = je.replace('．', '.');
						je=je.replaceAll("，", "");
						if(je.contains("减半收取")){
							je = je.substring(je.indexOf("减半收取")+4);
						}
						if(je.contains("（减半收取）")){
							je = je.substring(6);
						}
						if(StringUtil.contains(je,"共计")){
							je = je.substring(2);
						}else if(StringUtil.contains(je,"计")||StringUtil.contains(je,"各")||StringUtil.contains(je,"为")||StringUtil.contains(je,"即")){
							je = je.substring(1);
						}
//						je是中文数字
						je = je.replaceAll("人民币", "");
						if(!StringUtil.isNum(je)&&!StringUtil.contains(je, "人民币")){
							int jeInt = NumberConverter.convertFromChinese(je);
							if((jeInt>10 && je.length()>1)||jeInt==10){
								je=jeInt+"";
							} 
						}
						if(ssfEnum.equals(SsfEnum.JBSQ)&&jeModels.size()>0){
							jeModels.get(jeModels.size()-1).setValue(je+"元");
						}else{
							WsCpjgssfjeModel jeModel = new WsCpjgssfjeModel(je+"元", ssfEnum.getSsfName());
							jeModels.add(jeModel);
							try{
								ssfzje+=Integer.parseInt(ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan));
							}catch(Exception e){
								
							}
						}
						break;
					}
					
				}
			}
			
			if(jeModels.size()>0){
				ssfModel.setSsfjeModels(jeModels);
				ssfModel.setZje(ssfzje+"元");
			}
//			--------解析诉讼费承担-----------
			List<WscpjgssfcdModel> cdModels = new ArrayList<WscpjgssfcdModel>();
			HashMap<String, String> sscyrMap = new HashMap<String, String>();
			for(WssscyrModel sscyr:wssscyrModellist){
				sscyrMap.put(sscyr.getSscyr(), sscyr.getSsdw());
			}
		    String[] ssfs = ssfcdjl.split("，");
		    int count=0;
			for(String s:ssfs){
				String cdje = getNumberFromString(s);
				List<WscpjgssfcdModel> tempcdModels = new ArrayList<WscpjgssfcdModel>();
				if(StringUtil.contains(s, "负担")||StringUtil.contains(s, "承担")){
//					count++;
					for(Map.Entry<String, String> entry:sscyrMap.entrySet()){
						if(StringUtil.contains(s, entry.getKey())){
							count++;
							WscpjgssfcdModel cdmodel = new WscpjgssfcdModel();
							cdmodel.setCdr(entry.getKey());
							cdmodel.setCdrdw(entry.getValue());
							if(!StringUtil.equals(cdje, "0元")){
								cdmodel.setCdje(cdje);
							}
							cdmodel.setCdfs("与他人共同承担该金额");
							tempcdModels.add(cdmodel);
						}
					}
//					设置承担金额和承担方式
					if(tempcdModels.size()==1){
						tempcdModels.get(0).setCdfs("个人独立承担该金额");
					}
					cdModels.addAll(tempcdModels);
				}
			}
//			只有一方承担，承担总金额
//			if(count==1){
//				for(WscpjgssfcdModel cdmodel:cdModels){
//					cdmodel.setCdje(ssfModel.getZje());
//				}
//			}
			boolean jeflag = true;
			for(WscpjgssfcdModel cdmodel:cdModels){
				if(!StringUtil.isBlank(cdmodel.getCdje())){
					jeflag = false;
					break;
				}
			}
			if(jeflag){
				List<WsCpjgssfjeModel> mos = ssfModel.getSsfjeModels();
				if(mos!=null && mos.size()>0 && mos.get(0).getValue()!=null){
					String s = mos.get(0).getValue();
					for(WscpjgssfcdModel cdmodel:cdModels){
						cdmodel.setCdje(s);
					}
				}
				
			}
			if(cdModels.size()>0){
				ssfModel.setSsfcdModels(cdModels);
			}
			wscpjgModel.setSsfModel(ssfModel);
		}
	}
	
	public static String formatJe(String je){
		if(StringUtil.isBlank(je)){
			return null;
		}
//		替换所有中文符号
		StringUtil.replace(je, "，", "");
		StringUtil.replace(je, "．", ".");
//		测试是否规范数字
		try{
			Double jeTemp = Double.parseDouble(je);
		}catch(Exception e){
//			1.中文数字=
			je = NumberConverter.convertFromChinese(je)+"";
		}
		return je;
	}
	
	public static String getNumberFromString(String content){
		if(StringUtil.isBlank(content))
			return null;
		else{
			String reg1 = "[^0-9.,](\\d){1,3}(\\,\\d\\d\\d)*(\\d)*(\\.)?(\\d)*元";
			String reg = "(\\d+(\\.\\d+)?)元";//整数，小数匹配正则
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(content);
			if(m.find()){
				return m.group();
			}
			return null;
		}
	}

	/**
	 * 设置可上诉至、上诉材料、上诉期限
	 * 行政再审
	 * @param wscpjgModel
	 */
	private void setSsqk(WscpjgModel wscpjgModel, String cpjg) {
//		 可上诉至：上诉于XX法院
		 int indexStart = cpjg.indexOf("上诉于");
		 if(indexStart==-1){
			 indexStart = cpjg.indexOf("上诉至");
		 }
		 if (indexStart==-1) {
			indexStart = cpjg.indexOf("日内向");
		}
	     int fyIndex =-1; 
	     String temp="";
	     if(indexStart>-1){
	    	 temp = cpjg.substring(indexStart);
	    	 fyIndex = temp.indexOf("法院");
	    	 if(fyIndex>-1){
		    	 wscpjgModel.setKssz(temp.substring(3, fyIndex+2));
		     }
	     }
//      上诉材料
	     indexStart = cpjg.indexOf("院递交");
	     fyIndex  = getMinforSsqk(cpjg);
	     if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    	 String sscl = cpjg.substring(indexStart+3, fyIndex).trim();
	    	 if(sscl.endsWith("，")){
	    		 sscl=sscl.substring(0, sscl.length()-1);
	    	 }
	    	 wscpjgModel.setSstjcl(sscl);
	     }
	     //上诉日期
	     indexStart = cpjg.indexOf("送达之日起");
	     fyIndex  = cpjg.indexOf("日内");
	     if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    	 wscpjgModel.setSsqx(cpjg.substring(indexStart+5, fyIndex+1));
	     }
	     
	}
	public static int getMinforSsqk(String content){
		List<Integer> indexList = new ArrayList<Integer>();
		indexList.add(content.indexOf("预交"));
		indexList.add(content.indexOf("，交纳"));
		indexList.add(content.indexOf("，并交纳"));
		indexList.add(content.indexOf("交纳"));
		indexList.add(content.indexOf("，同时预交"));
		indexList.add(content.indexOf("上诉于"));
		indexList.add(content.indexOf("上诉至"));
		indexList.add(content.indexOf("按照国务院"));
		indexList.add(content.indexOf("，同时交纳"));
		int result=content.length();
		for(Integer index:indexList){
			if(index!=-1 && index<result){
				result = index;
			}
		}
		return result;
	}
	
	/**
	 * 获得判决金额类型
	 * @return
	 */
	private List<String> getPjjeLx() {
		String lxs = "[无, 交强险, 损害赔偿金, 价款, 借款, 利息, 罚息, 债权, 律师费, 医疗费, 营养费, 住院伙食补助费, 残疾赔偿金, 交通费, "
				+ "鉴定费, 护工费, 误工费, 续医费, 精神伤害补助, 工资, 加班工资, 货款, 租金, 卫生费, 本金, 日用品, 违约金, 房款, 损失, 保险赔偿, "
				+ "车损费, 施救费, 透支款, 贷款, 服务费, 电费, 使用费, 死亡补偿费, 安葬费, 补偿金, 抚养费, 遣返费, 公告费, 担保金, 财产保全费, 承揽费,"
				+ " 报酬, 购物款, 经济补偿金, 资料费, 保险金, 材料款, 工程款, 劳动报酬, 生活费, 经济损失, 折价款, 评估费, 欠款, 检查费, 残疾辅助器费,"
				+ " 食宿费, 聘金, 解除劳动合同赔偿金, 滞纳金, 手续费, 加工价款, 工程造价, 现金, 维修费, 复印费, 债款, 实现债权的费用, 合理费用, 房屋租金,"
				+ " 押金, 加班费, 理赔款, 代理费, 利润, 保险费, 诊疗费, 生活补助费, 住宿费, 被扶养人生活费, 余款, 租赁费, 垫付款, 货物托运费, 培训费, "
				+ "精神抚慰金, 安装费, 物业费, 抚育费, 投资款, 逾期利息, 伙食补助费, 资金, 遗产, 药费, 安置补助费, 保证金, 修复费, 被抚养人生活费, 本息,"
				+ " 罚款, 装修费, 停车费, 赡养费, 定金, 劳务费, 超限费, 保全申请费, 水费, 修理费, 货物损失, 邮寄费, 料款, 存款, 复利, 其他费用, 煤款,"
				+ " 转让费, 拖车费, 伤残就业补助金, 土地租金, 摩托车费, 住院费, 伤残补助费, 工伤医疗补助金, 律师服务费, 购车款, 转让款, 股权转让款, "
				+ "伤残鉴定费, 法医鉴定费, 砖款, 查询费, 承包金, 差价款, 交通事故损失, 调查费用, 化肥款, 承揽款, 救护车费, 信用社贷款, 经济帮助, "
				+ "土地补偿费, 申请费, 承包费, 餐费, 中介费, 设备款, 税费, 施工费, 检测费, 承包款, 物损费, 公证费, 诉讼合理支出, 设计费, 年费, 款项合计,"
				+ " 佣金, 公积金, 补偿费, 预付款, 不当得利款, 钢材款, 抵押金, 抚恤费, 保管费, 供暖费, 吊车费, 伙食费, 财物损失费, 管理费, 酒款, 伤残费, "
				+ "预约金, 质保费, 土地承包经营费, 高温费, 承揽价款, 石子款, 代通知金, 定损费, 技术服务费, 复诊费, 水泥款, 轮椅费, 教育费, 电话费, 差旅费,"
				+ " 取证费, 衣服损失费, 合同款, 房屋使用费, 查档费, 人工费, 生活帮助费, 照相费, 模具款, 死亡赔偿金, 丧葬费, 精神抚慰金, 经济补偿, 人身损害赔偿金, 出让价款, 停工费]";
		String[] strs = lxs.split(", ");
		List<String> jelx = new ArrayList<String>();
		for(String s:strs){
			if(StringUtil.equals(s, "[无")){
				s="无";
			}else if(StringUtil.equals(s, " 停工费]")){
				s="停工费";
			}
			jelx.add(s);
		}
		return jelx;
	}

	/**
	 * 是否是案件审理费
	 * @param content
	 * @return
	 */
	private static boolean isAjslf(String content) {
		if (content.indexOf("案件受理费") == 0 ||content.indexOf("本案受理费") == 0 ||content.indexOf("本案案件受理费") == 0 ||content.indexOf("本案一审诉讼费") == 0 
				||(content.indexOf("案件受理费")==2)||content.indexOf("减半收取") >-1||content.indexOf("案件诉讼费") == 0||content.indexOf("诉讼费") == 0||content.indexOf("本案诉讼费") == 0||content.indexOf("本诉案件受理费") == 0||content.indexOf("本案件受理费") == 0
				||content.indexOf("一审案件受理费") == 0||content.indexOf("案件一审受理费") == 0||content.indexOf("本案一审案件受理费") == 0||
				content.indexOf("一、二审案件受理费") > -1||content.indexOf("一审案件诉讼费") > -1||content.indexOf("一、二审案件诉讼费") > -1
			     ||content.indexOf("二审案件受理费") == 0 ||content.indexOf("案件二审受理费") == 0||content.indexOf("本案二审案件受理费") == 0
			    		 ||content.indexOf("本案二审受理费") == 0 ||content.indexOf("本案第二审案件受理费") == 0||content.indexOf("第二审案件受理费") == 0
			 ||content.indexOf("二审受理费") == 0||content.indexOf("上诉案件受理费") == 0||content.indexOf("二审诉讼费") >-1
			 ||content.indexOf("本系列案件二审案件受理费") == 0 ||content.indexOf("公告费") > -1||content.indexOf("本案收案件") > -1||content.indexOf("本案减半征收案件") > -1){
			return true;
		}else
			return false;
	}
	public static boolean startWithNumber(String content){
		if(content.indexOf("一") == 0 || content.indexOf("二") == 0
				|| content.indexOf("三") == 0 || content.indexOf("四") == 0
				|| content.indexOf("五") == 0 || content.indexOf("六") == 0
				|| content.indexOf("七") == 0 || content.indexOf("八") == 0
				|| content.indexOf("九") == 0 || content.indexOf("十") == 0
				|| content.indexOf("1") == 0 || content.indexOf("2") == 0
				|| content.indexOf("3") == 0 || content.indexOf("4") == 0
				|| content.indexOf("5") == 0 || content.indexOf("6") == 0 || content.indexOf("7") == 0
				|| content.indexOf("8") == 0 || content.indexOf("9") == 0 || content.indexOf("10") == 0){
			return true;
			
		}else{
			return false;
		}
	}
}

