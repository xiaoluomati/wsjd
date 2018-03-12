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
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
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
 * 民事再审裁判结果解析
 * @author LiYX
 *
 */
public class MszsCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	@Override
	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
		// TODO Auto-generated method stub
		List<String> cpjg = wsAnalyse.getCpjg();
		
//		以全角分号继续划分判决结果内容
		List<String> cpjg_r=new ArrayList<String>();
		for(int k=0;k<cpjg.size();k++){
			String[] cpjg_r1 =cpjg.get(k).split("；");
			for(int j=0;j<cpjg_r1.length;j++){
				cpjg_r.add(cpjg_r1[j]);
			}
		}
		cpjg=cpjg_r;
		
		WscpjgModel wscpjgModel=new WscpjgModel();
		List<String> cpjgnrList=new ArrayList<String>();
		List<PjjgnrModel> pjjgList =new ArrayList<PjjgnrModel>();
		String pjjgnr="";
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

//			撤诉人集合
			if(pjjgnr.contains("准")&&
					(pjjgnr.contains("撤回起诉")||pjjgnr.contains("撤诉申请")||pjjgnr.contains("撤诉")
							||(pjjgnr.contains("撤回")&&(pjjgnr.contains("诉讼")||pjjgnr.contains("起诉"))))){
				setCsrjh(wscpjgModel, wssscyrModellist, cpjg.get(i),wsAnalyse);
			}
			
			for(PjjgnrModel pm:pjjgList){//填写判决结果模型
				setPjje(pm,pjjelx);
			}
			wscpjgModel.setPjjgList(pjjgList);
		}
		
//		解析案件受理费，每个人单独承担或几个人共同承担的诉讼费
		setSsfcd(wscpjgModel, wssscyrModellist);
		
//		结案标的总额
		wscpjgModel = setJabdje(wscpjgModel,cpjg);
	
		
		
//		民事再审结案方式
		wscpjgModel = setMszsjafs(wscpjgModel, pjjgnr,cpjg);
		
		
		return wscpjgModel;
	}
		

	/**
	 * 解析诉讼费承担
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	public static void setSsfcd(WscpjgModel wscpjgModel,List<WssscyrModel> wssscyrModellist){
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
	 * 设置撤诉人集合
	 * 民事一审
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	private static void setCsrjh(WscpjgModel wscpjgModel, List<WssscyrModel> wssscyrModellist, String cpjg,
			WsAnalyse wsAnalyse) {
		// TODO Auto-generated method stub
		List<String> csrjh = new ArrayList<String>();
		for(WssscyrModel sscyr:wssscyrModellist){
			if(StringUtil.contains(cpjg, sscyr.getSscyr())){
				csrjh.add(sscyr.getSscyr());
			}
		}
		if(csrjh.size()>0){
			wscpjgModel.setCsrjh(csrjh);
			if(StringUtil.contains(wsAnalyse.getSsjl(), "和解")){
				wscpjgModel.setCslx("原告与被告庭外和解后撤诉");
			}else{
				wscpjgModel.setCslx("原告主动撤诉");
			}
		}
	}

	/**
	 * 解析结案标的金额，结案标的总额
	 * 共计XX元，此处XX不属于结案标的金额
	 * @param wscpjgModel
	 * @return
	 */
	private static WscpjgModel setJabdje(WscpjgModel wscpjgModel,List<String> cpjg) {
		// TODO Auto-generated method stub
//		List<String> pjjgList = wscpjgModel.getCpjgnr();
		List<String> pjjgList = new ArrayList<String>();
		for(int i=0;i<wscpjgModel.getPjjgList().size();i++){
			pjjgList.add(wscpjgModel.getPjjgList().get(i).getPjjgnr());
		}
		List<String> jes = new ArrayList<String>();
		if(pjjgList!=null){
			for(String jg :pjjgList){
			    getJe(jg,jes,true);
			}
		}
		if(jes.size()>0){
			wscpjgModel.setJabde(jes);//结案标的额
		}
		Double zje = 0.0;
		try{
			for(String s:jes){
				zje = zje+Double.parseDouble(s);
			}
			if(zje!=0.0){
				wscpjgModel.setJabdze(zje+"元");//结案标的总额
			}
		}catch(Exception e){
			
		}
		return wscpjgModel;
	}

	/**
	 * 截取字符串末尾的数字
	 * 共计XX元，此处共计不算入
	 * @param content
	 * @param moneyList
	 * @return
	 */
	private static List<String> getJe(String content, List<String> moneyList, boolean gjFlag) {
		// TODO Auto-generated method stub
		String reg = "元";
		String[] nums = content.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='．'||nums[i].charAt(j)=='.'||nums[i].charAt(j)=='万'||nums[i].charAt(j)=='，')){
		    		index=j;
		    		break;
		    	}
		    }
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	排除共计
    		if(nums[i].indexOf("共计")!=index-1&& (!StringUtil.isBlank(je_toAdd))){
	        	if(je_toAdd.endsWith("万")){
		    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
		    		try{
		    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                        je_toAdd=je_addDou+"";
                    }catch(Exception e){
                    	je_toAdd="";
		    		}
	        	}
	        	if(!StringUtil.isBlank(je_toAdd)){
	        		 moneyList.add(je_toAdd);
	        	}
	        }
	    }
		return moneyList;
	}

	/**
	 * 是否是案件审理费
	 * @param content
	 * @return
	 */
	private static boolean isAjslf(String content) {
		// TODO Auto-generated method stub
		if (content.indexOf("案件受理费") == 0 ||content.indexOf("本案受理费") == 0 ||content.indexOf("本案案件受理费") == 0 ||content.indexOf("本案一审诉讼费") == 0 
				||(content.indexOf("案件受理费")==2)||content.indexOf("减半收取") >-1||content.indexOf("案件诉讼费") == 0||content.indexOf("诉讼费") == 0||content.indexOf("本案诉讼费") == 0||content.indexOf("本诉案件受理费") == 0||content.indexOf("本案件受理费") == 0
				||content.indexOf("一审案件受理费") > -1||content.indexOf("案件一审受理费") > -1||content.indexOf("本案一审案件受理费") > -1||
				content.indexOf("一、二审案件受理费") > -1||content.indexOf("一审案件诉讼费") > -1||content.indexOf("一、二审案件诉讼费") > -1
			     ||content.indexOf("二审案件受理费") == 0 ||content.indexOf("案件二审受理费") == 0||content.indexOf("本案二审案件受理费") == 0
			    		 ||content.indexOf("本案二审受理费") == 0 ||content.indexOf("本案第二审案件受理费") == 0||content.indexOf("第二审案件受理费") == 0
			 ||content.indexOf("二审受理费") == 0||content.indexOf("上诉案件受理费") == 0||content.indexOf("二审诉讼费") >-1
			 ||content.indexOf("本系列案件二审案件受理费") == 0 ||content.indexOf("公告费") > -1||content.indexOf("本案收案件") > -1||content.indexOf("本案减半征收案件") > -1||content.indexOf("保全费") == 0){
			return true;
		}else
			return false;
	}

	/**
	 * 解析民事再审的结案方式
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @return
	 */
	private WscpjgModel setMszsjafs(WscpjgModel wscpjgModel, String pjjgnr,List<String> cpjg) {
		// TODO Auto-generated method stub
		
//		//在案件基本情况model中寻找撤销原裁定并驳回申请依据
//		//WsajjbqkModel ajjbqk =new WsajjbqkModel();
//		WsModel wModel =new WsModel();
//		String ajjbqk=wModel.getWsajjbqSegment();
//		boolean convertFlag = false;//判断撤销原裁定并驳回申请标志
//		if (ajjbqk.contains("驳回")&&ajjbqk.contains("申请")) {
//			convertFlag=true;
//		}
		
		if (!StringUtil.equals(pjjgnr, "")) {
			if(!pjjgnr.contains("撤销")&&!pjjgnr.contains("变更")&&pjjgnr.contains("维持")){
//				 1.驳回上诉，维持原判
//				 2.维持XX项，撤销XX，属于改判
				 wscpjgModel.setJafs("维持");
			 }else if((pjjgnr.contains("准许")||pjjgnr.contains("准允"))&&pjjgnr.contains("撤回起诉")&&pjjgnr.contains("撤销")&&(pjjgnr.contains("裁定")||pjjgnr.contains("判决"))){
				 wscpjgModel.setJafs("准予撤诉并撤销原裁判");
			 }else if (pjjgnr.contains("解除")&&pjjgnr.contains("查封")) {
				wscpjgModel.setJafs("准予撤诉并撤销原裁判");
			}else if (pjjgnr.contains("撤销")&&pjjgnr.contains("对应")) {
				wscpjgModel.setJafs("撤销原裁定并驳回申请");
			}else if(pjjgnr.contains("撤销")&&pjjgnr.contains("驳回")&&pjjgnr.contains("起诉")){
				 wscpjgModel.setJafs("撤销原判并驳回起诉");
			 }else if(pjjgnr.contains("发回")&&(pjjgnr.contains("重审")||pjjgnr.contains("重新审理"))){
				 wscpjgModel.setJafs("发回重审");
				 wscpjgModel.setSffhcs("是");
			 }else if(pjjgnr.contains("撤销")&&pjjgnr.contains("裁定")&&pjjgnr.contains("受理")){
				 wscpjgModel.setJafs("撤销原裁定并指令受理");
			 }else if(pjjgnr.contains("撤销")&&pjjgnr.contains("裁定")&&pjjgnr.contains("审理")){
				 wscpjgModel.setJafs("撤销原裁定并指令审理");
			 }else if(pjjgnr.contains("调解")){
				 wscpjgModel.setJafs("调解");
			 }else if (pjjgnr.contains("调解")&&pjjgnr.contains("自愿达成")) {
				wscpjgModel.setJafs("调解");
			}else if((pjjgnr.contains("准许")||pjjgnr.contains("准允")||pjjgnr.contains("准予"))&&(pjjgnr.contains("撤回上诉")||pjjgnr.contains("撤回诉讼"))){
				 wscpjgModel.setJafs("准予撤回上诉");
			 }else if(pjjgnr.contains("按")&&(pjjgnr.contains("撤诉")||pjjgnr.contains("撤回上诉"))&&pjjgnr.contains("处理")){
				 wscpjgModel.setJafs("按撤回上诉处理");
			 }else if((pjjgnr.contains("维持")&&(pjjgnr.contains("撤销")||pjjgnr.contains("变更")))// 1.维持XX，撤销XX或者维持XX，变更XX
					 ||(pjjgnr.contains("撤销")&&pjjgnr.contains("驳回")&&pjjgnr.contains("诉讼请求"))){// 2.撤销XX判决,驳回XX诉讼请求
				 wscpjgModel.setJafs("改判");
			 }else if(pjjgnr.contains("终结诉讼")){
				 wscpjgModel.setJafs("终结");
			 } else if (pjjgnr.contains("撤销")&&pjjgnr.contains("裁定")&&pjjgnr.contains("裁判")) {
				 wscpjgModel.setJafs("撤销原裁定");
			}else if(pjjgnr.contains("中止诉讼")){
				 wscpjgModel.setJafs("其他");
			 }
			 if(StringUtil.isBlank(wscpjgModel.getJafs())){
					wscpjgModel.setJafs("其他");
			 }
		}
		for (String it : cpjg) {
			if (it.contains("调解书")) {
				wscpjgModel.setJafs("调解");
			}
		}
		return wscpjgModel ;
	}
	
	public  List<String> getPjjeLx(){
		String lxs = "[无, 交强险, 损害赔偿金, 价款, 借款, 利息, 罚息, 债权, 律师费, 医疗费, 营养费, 住院伙食补助费, 残疾赔偿金, 交通费, "
				+ "鉴定费, 护工费, 误工费, 续医费, 精神伤害补助, 工资, 加班工资, 货款, 租金, 卫生费, 本金, 日用品, 违约金, 房款, 损失, 保险赔偿, "
				+ "车损费, 施救费, 透支款, 贷款, 服务费, 电费, 使用费, 死亡补偿费, 安葬费, 补偿金, 抚养费, 遣返费, 公告费, 担保金, 财产保全费, 保全费,承揽费,"
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
	 * 找出一句话里的金额
	 * @param content
	 * @return 包含金额的List,例如“本金20元，利息10元”，返回{“20元”，“10元”}
	 */
	public static  void setPjje(PjjgnrModel pjjgnrModel,List<String> jelxList){
		List<PjjeModel> pjjeModels = new ArrayList<PjjeModel>();
		String content = pjjgnrModel.getPjjgnr();
//		List<String> moneyList = new ArrayList<String>();
		String reg = "元";
		String[] nums = content.split(reg);//以“元”分割
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='．'||nums[i].charAt(j)=='.'||nums[i].charAt(j)=='万'||nums[i].charAt(j)=='，'||nums[i].charAt(j)==',')){
		    		index=j;
		    		break;
		    	}
		    }
//	    	从结尾找到数字
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	
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
	        	if(!StringUtil.isBlank(je_toAdd)){
//	        		找到了这个金额
//	        		 moneyList.add(je_toAdd+"元");
        			 PjjeModel jeModel = new PjjeModel();
    				 jeModel.setValue(je_toAdd+"元");
//	        		 再找金额类型
	        		 for(String s:jelxList){
	 					if(StringUtil.contains(nums[i], s)){
	 						if(jeModel.getCategorys()==null){
	 							jeModel.setCategorys(new ArrayList<String>());
	 						}
	 						jeModel.getCategorys().add(s);
	 					}
	 				}
	        		 pjjeModels.add(jeModel);
	        	}
	        }
	    }
	    if(pjjeModels.size()>0){
	    	pjjgnrModel.setPjjeList(pjjeModels);
	    }
	}
}
