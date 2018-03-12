package nju.software.wsjx.parserule.wscpjgparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.Enum.MsesFhcsyyEnum;
import nju.software.wsjx.model.Enum.SsfEnum;
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
 * 民事二审裁判结果解析
 * @author wangzh
 *
 */
public class MsesCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule{

	public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse,List<WssscyrModel> wssscyrModellist) {
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
//			解析是否支持诉讼请求
//			if (cpjg.get(i).contains("被告")&& (cpjg.get(i).contains("违法") || cpjg.get(i).contains("责令"))) {
//				wscpjgModel.setSfzcssqq("全部支持");
//			}
// 			一审案件可上诉相关
//			if(StringUtil.contains(ajlxEnum.getAjlx(), "一审")&&cpjg.get(i).indexOf("如不服本")>-1){
//				setSsqk(wscpjgModel, cpjg.get(i));
//			}
//			撤诉人集合
			if(pjjgnr.contains("准")&&
					(pjjgnr.contains("撤回起诉")||pjjgnr.contains("撤诉申请")||pjjgnr.contains("撤诉")
							||(pjjgnr.contains("撤回")&&(pjjgnr.contains("诉讼")||pjjgnr.contains("起诉"))))){
				setCsrjh(wscpjgModel, wssscyrModellist, cpjg.get(i),wsAnalyse);
			}
		}
		
//		民一、民二需要解析裁判结果内容
//		if(StringUtil.contains(ajlxEnum.getAjlx(), "民事一审")||StringUtil.contains(ajlxEnum.getAjlx(), "民事二审")){
//	     	解析判决结果内容
			for(PjjgnrModel pjjgnrmodel:pjjgList){
				setQlywr(pjjgnrmodel, wssscyrModellist);
				setPjzxqx(pjjgnrmodel);
				setPjje(pjjgnrmodel,pjjelx);
//				setPjzrcdfs(pjjgnrmodel);
			}
			wscpjgModel.setPjjgList(pjjgList);
//		}
//		解析案件受理费
		setSsfcd(wscpjgModel, wssscyrModellist);
//		结案标的金额
		wscpjgModel = setJabdje(wscpjgModel);
//		民事二审结案方式
		wscpjgModel = setMsesjafs(wscpjgModel, pjjgnr);
//		wscpjgModel = setJafsByAjlx(wscpjgModel, pjjgnr,allnr, ajlxEnum,wsAnalyse);
//		是否发回重审
		wscpjgModel.setSffhcs(StringUtil.equals(wscpjgModel.getSffhcs(), "是")?"是":"否");
//		发回重审原因
		if(StringUtil.equals(wscpjgModel.getJafs(), "发回重审")){
			List<String> cpfxgc = wsAnalyse.getCpfxgc();
			if(cpfxgc!=null){
				for(String gc:cpfxgc){
					if(gc.contains("事实不清")){
						wscpjgModel.setFhcsyy("原判决认定基本事实不清");
						break;
					}else if(MsesFhcsyyEnum.getMsesfhccyy(gc)!=null){
							wscpjgModel.setFhcsyy(MsesFhcsyyEnum.getMsesfhccyy(gc));
							break;
					}else{
						wscpjgModel.setFhcsyy("其他");
					}
				}
			}
		}
//		行政一审，提出管辖异议
//		if(StringUtil.contains(ajlxEnum.getAjlx(), "行政一审")||StringUtil.contains(ajlxEnum.getAjlx(), "民事一审")){
//			setTcgxyy(wscpjgModel, allnr);
//		}
		for (int j = 0; j < cpjgnrList.size(); j++) {
//			解析是否支持诉讼请求
			if (cpjgnrList.get(j).contains("驳回")
					&& cpjgnrList.get(j).contains("其他")
					&& cpjgnrList.get(j).contains("诉讼")) {
				wscpjgModel.setSfzcssqq("部分支持部分不支持");
			} else if (cpjgnrList.get(j).contains("驳回上诉")) {
				wscpjgModel.setSfzcssqq("全部驳回");
			} else if (cpjgnrList.get(j).contains("被告")
					&& cpjgnrList.get(j).contains("违法")) {
				wscpjgModel.setSfzcssqq("全部支持");
			}
//			解析胜败诉方
			if ((wscpjgModel.getSfzcssqq() == "部分支持部分不支持")
					|| (wscpjgModel.getSfzcssqq() == "全部支持")) {
				wscpjgModel.setSbsf("起诉方");
			} else if (wscpjgModel.getSfzcssqq() == "全部驳回") {
				wscpjgModel.setSbsf("应诉方");
			}
		}
		
//		解析是否支持行政赔偿
		for (int j = 0; j < cpjgnrList.size(); j++) {
			if (cpjgnrList.get(j).contains("被告")
					&& cpjgnrList.get(j).contains("赔偿")
					&& cpjgnrList.get(j).contains("付清")) {
				wscpjgModel.setSfzcxzpc("是");
//				解析行政赔偿金额
				if(cpjgnrList.get(j).contains("元")){
					String xzpcje = "";
					String reg = "(\\d+(\\.\\d+)?)";//整数，小数匹配正则
					Pattern p = Pattern.compile(reg);
					Matcher m = p.matcher(cpjgnrList.get(j));
					if(m.find()){
						xzpcje = m.group() + "元";
						wscpjgModel.setXzpcje(xzpcje);
					}
				}
				break;
			}else if (cpjgnrList.get(j).contains("驳回")) {
				wscpjgModel.setSfzcxzpc("否");
			}
		}
//		交通事故赔偿顺序是否正确
		PjjgService pjjgservice = new PjjgServiceImpl();
		boolean sx = pjjgservice.isPeichangIndexCorrect(pjjgnr, wssscyrModellist);
		if(sx){
			wscpjgModel.setPcsxsfzq("正确");
		}else{
			wscpjgModel.setPcsxsfzq("错误");
		}
		DsrbxgsService dsrService = new DsrbxgsServiceImpl();
		if(dsrService.jqxBg(wssscyrModellist)){
			wscpjgModel.setBsgssfbg("正确");
		}else{
			wscpjgModel.setBsgssfbg("错误");
		}
		return wscpjgModel;
	}
	
/*	*//**
	 * 解析结案方式
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @param ajlxEnum
	 * @return
	 *//*
	public static WscpjgModel setJafsByAjlx(WscpjgModel wscpjgModel,String pjjgnr,String allPjjg,AjlxEnum ajlxEnum,WsAnalyse wsAnalyse){

			return setMsesjafs(wscpjgModel, pjjgnr);
	}
*/	
	/**
	 * 解析民事二审的结案方式
	 * @param wscpjgModel
	 * @param pjjgnr
	 * @return
	 */
	public static WscpjgModel setMsesjafs(WscpjgModel wscpjgModel,String pjjgnr){
		if(!StringUtil.equals(pjjgnr, "")){
			 if(!pjjgnr.contains("撤销")&&!pjjgnr.contains("变更")&&pjjgnr.contains("维持")){
//				 1.驳回上诉，维持原判
//				 2.维持XX项，撤销XX，属于改判
				 wscpjgModel.setJafs("维持");
			 }
			 else if(pjjgnr.contains("撤销")&&pjjgnr.contains("驳回")&&pjjgnr.contains("起诉")){
				 wscpjgModel.setJafs("撤销原判并驳回起诉");
			 } 
			 else if(pjjgnr.contains("发回")&&(pjjgnr.contains("重审")||pjjgnr.contains("重新审理"))){
				 wscpjgModel.setJafs("发回重审");
				 wscpjgModel.setSffhcs("是");
			 }else if(pjjgnr.contains("撤销")&&pjjgnr.contains("裁定")&&pjjgnr.contains("受理")){
				 wscpjgModel.setJafs("撤销原裁定并指令受理");
			 }else if(pjjgnr.contains("撤销")&&pjjgnr.contains("裁定")&&pjjgnr.contains("审理")){
				 wscpjgModel.setJafs("撤销原裁定并指令审理");
			 }else if(pjjgnr.contains("调解")){
				 wscpjgModel.setJafs("调解");
			 }else if(pjjgnr.contains("撤销")&&pjjgnr.contains("判决")&&(pjjgnr.contains("准许")||pjjgnr.contains("准允"))&&pjjgnr.contains("撤回起诉")){
				 wscpjgModel.setJafs("准予撤回起诉并撤销一审判决");
			 }else if((pjjgnr.contains("准许")||pjjgnr.contains("准允")||pjjgnr.contains("准予"))&&(pjjgnr.contains("撤回上诉")||pjjgnr.contains("撤回诉讼"))){
				 wscpjgModel.setJafs("准予撤回上诉");
			 }else if(pjjgnr.contains("按")&&(pjjgnr.contains("撤诉")||pjjgnr.contains("撤回上诉"))&&pjjgnr.contains("处理")){
				 wscpjgModel.setJafs("按撤回上诉处理");
			 }else if((pjjgnr.contains("维持")&&(pjjgnr.contains("撤销")||pjjgnr.contains("变更")))// 1.维持XX，撤销XX或者维持XX，变更XX
					 ||(pjjgnr.contains("撤销")&&pjjgnr.contains("驳回")&&pjjgnr.contains("诉讼请求"))){// 2.撤销XX判决,驳回XX诉讼请求
				 wscpjgModel.setJafs("改判");
			 }else if(pjjgnr.contains("终结诉讼")){
				 wscpjgModel.setJafs("终结");
			 } else if(pjjgnr.contains("中止诉讼")){
				 wscpjgModel.setJafs("其他");
			 }
			 if(StringUtil.isBlank(wscpjgModel.getJafs())){
					wscpjgModel.setJafs("其他");
			 }
		}
		return wscpjgModel;
	}
	/**
	 * 解析结案标的金额，结案标的总额
	 * 共计XX元，此处XX不属于结案标的金额
	 * @param wscpjgModel
	 * @return
	 */
	public static WscpjgModel setJabdje(WscpjgModel wscpjgModel){
		List<String> pjjgList = wscpjgModel.getCpjgnr();
		List<String> jes = new ArrayList<String>();
		if(pjjgList!=null){
			for(String jg :pjjgList){
			    getJe(jg,jes,true);
			}
		}
		if(jes.size()>0){
			wscpjgModel.setJabde(jes);
		}
		Double zje = 0.0;
		try{
			for(String s:jes){
				zje = zje+Double.parseDouble(s);
			}
			if(zje!=0.0){
				wscpjgModel.setJabdze(zje+"元");
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
	public static List<String> getJe(String content,List<String> moneyList,boolean gjFlag){
		String reg = "元";
		String[] nums = content.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='．'||nums[i].charAt(j)=='万'||nums[i].charAt(j)=='，')){
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
	public static boolean isAjslf(String content){
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
	
	/**
	 * 解析权利人、义务人
	 * @param model
	 */
	public static void setQlywr(PjjgnrModel pjjgnrModel,List<WssscyrModel> wssscyrModellist){
		String pjjg = pjjgnrModel.getPjjgnr();

		HashMap<String,String> qlr = new HashMap<String, String>();
		HashMap<String,String> ywr = new HashMap<String, String>();
//		所有姓名的index
		int firstIndex = getMin(pjjg);//最前动词
		
		HashMap<String, Integer> nameIndexMap = new HashMap<String, Integer>();
		for(WssscyrModel cyr:wssscyrModellist){
			if(!StringUtil.isBlank(cyr.getSscyr())){
				String name  = cyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				nameIndexMap.put(name, nameIndex);
			}
		}
//		如果存在名字在动词之后的，则属于前后情况，flag=false
		boolean flag=true;
		if(firstIndex>-1&&firstIndex!=pjjg.length()){
			for(Map.Entry<String, Integer> entry:nameIndexMap.entrySet()){
				if(entry.getValue()>firstIndex){
					flag=false;
					break;
				}
			}
		}
		
//		第一个关于支付的动词，之前为义务人，之后为权利人
//		如果所有的姓名都在动词之前，否则使用前后方法
		if(!flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				int nameIndex = pjjg.indexOf(name);
				if(nameIndex>-1 &&nameIndex<firstIndex ){
					//义务人是主语
					ywr.put(name, sscyr.getSssf());
				}else if(nameIndex>-1 &&nameIndex>firstIndex){
					qlr.put(name, sscyr.getSssf());
				}
			}
			pjjgnrModel.setQlr(qlr);
			pjjgnrModel.setYwr(ywr);
		}else if(flag&&firstIndex>-1&&firstIndex!=pjjg.length()){
//			所有名字在动词之前
			int qlIndex=-1;
			for( WssscyrModel  sscyr:wssscyrModellist){
				String name = sscyr.getSscyr(); 
				if(!StringUtil.isBlank(name)){
					String ssdwname = sscyr.getSssf()+sscyr.getSscyr(); 
//					如果向当事人存在，那么这个当事人是第一个权利人
					if(nameIndexMap.get(name)>-1 &&(pjjg.indexOf("向"+name)>0||(pjjg.indexOf("向"+ssdwname)>0))){
						qlr.put(name, sscyr.getSssf());
						qlIndex=nameIndexMap.get(name);
						break;
					}
				}
			}
			if(qlIndex!=-1){
				for( WssscyrModel  sscyr:wssscyrModellist){
					String name = sscyr.getSscyr(); 
					if(!StringUtil.isBlank(name)){
						if(nameIndexMap.get(name)>-1 && nameIndexMap.get(name)<qlIndex){
							ywr.put(name, sscyr.getSssf());
						}
					}
					
				}
			}
			pjjgnrModel.setQlr(qlr);
			pjjgnrModel.setYwr(ywr);
		}
	}
	
	/**
	 * 获得权利人义务人判断的中介点，即第一个关于支付的动词位置
	 * @param content
	 * @return
	 */
	public static int getMin(String content){
		List<Integer> indexList = new ArrayList<Integer>();
		indexList.add(content.indexOf("赔偿"));
		indexList.add(content.indexOf("支付"));
		indexList.add(content.indexOf("承担"));
		indexList.add(content.indexOf("负担"));
		indexList.add(content.indexOf("给付"));
		indexList.add(content.indexOf("返还"));
		indexList.add(content.indexOf("偿还"));
		indexList.add(content.indexOf("赔付"));
		indexList.add(content.indexOf("补偿"));
		int result=content.length();
		for(Integer index:indexList){
			if(index!=-1 && index<result){
				result = index;
			}
		}
		return result;
	}
	
	/**
	 * 解析判决执行期限
	 * @param pjjgnrModel
	 */
	public static void setPjzxqx(PjjgnrModel pjjgnrModel){
		if(pjjgnrModel!=null && !StringUtil.isBlank(pjjgnrModel.getPjjgnr())){
			String pjjgnr = pjjgnrModel.getPjjgnr();
			int indexOfStart=-1;
			String start="";
			String[] starts={"本判决生效之日起","于判决生效后","限判决生效后","本判决发生法律效力后","本判决发生法律效力之日起","限判决生效之日起","于判决生效之日起","本判决生效后"};
			for(int i=0;i<starts.length;i++){
				indexOfStart = pjjgnr.indexOf(starts[i]);
				if(indexOfStart>-1){
					start = starts[i];
					break;
				}
			}
			if(indexOfStart>-1){
				try{
					int timeStart = indexOfStart+start.length();
					start = pjjgnr.substring(timeStart, pjjgnr.length());
					int endIndex = start.indexOf("内");
					start = start.substring(0, endIndex);
					pjjgnrModel.setPjzxqx(start);
				}catch(Exception e){
				}
			}
		}
	}
	
	public static void setPjzrcdfs(PjjgnrModel pjjgnrModel){
		if(pjjgnrModel!=null && !StringUtil.isBlank(pjjgnrModel.getPjjgnr())){
			pjjgnrModel.setPjzrcdfs("继续履行");
		}
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
	    		 if(!(Character.isDigit(nums[i].charAt(j))||nums[i].charAt(j)=='．'||nums[i].charAt(j)=='万'||nums[i].charAt(j)=='，')){
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
	/**
	 * 设置可上诉至、上诉材料、上诉期限
	 * 民事一审
	 * @param wscpjgModel
	 */
	public static void setSsqk(WscpjgModel wscpjgModel,String cpjg){
//		 可上诉至：上诉于XX法院
		 int indexStart = cpjg.indexOf("上诉于");
		 if(indexStart==-1){
			 indexStart = cpjg.indexOf("上诉至");
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
//       向本院递交
	     indexStart = cpjg.indexOf("院递交");
	     fyIndex  = getMinforSsqk(cpjg);
	     if(indexStart>-1 && fyIndex>-1 && indexStart<fyIndex){
	    	 String sscl = cpjg.substring(indexStart+3, fyIndex).trim();
	    	 if(sscl.endsWith("，")){
	    		 sscl=sscl.substring(0, sscl.length()-1);
	    	 }
	    	 wscpjgModel.setSstjcl(sscl);
	     }
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
	 * 设置撤诉人集合
	 * 民事一审
	 * @param wscpjgModel
	 * @param wssscyrModellist
	 */
	public static void setCsrjh(WscpjgModel wscpjgModel,List<WssscyrModel> wssscyrModellist,String cpjg,WsAnalyse wsAnalyse){
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
//		String ssfcdjl = wscpjgModel.getAjslf();
//		WsCpjgssfModel ssfModel = new WsCpjgssfModel();
//		if(!StringUtil.isBlank(ssfcdjl)){
////			-----------诉讼费记录--------------
//			ssfModel.setSsfjl(ssfcdjl);
////			------遍历诉讼费种类，解析诉讼费金额-----
//			char[] chars = ssfcdjl.toCharArray();
//			String ssfzl,ssfje;
//			int indexOfSsf=-1,indexOfYuan=-1,lengthOfSsfName=-1;
//			List<WsCpjgssfjeModel> jeModels = new ArrayList<WsCpjgssfjeModel>();
//			int ssfzje=0;
//			
//			//按照元区分
//			String[] ssfstrList = ssfcdjl.split("元");
////			for(String ssfstr:ssfstrList){
//			for(int i=0;i<ssfstrList.length-1;i++){
//				String ssfstr = ssfstrList[i];
////				对于每一个诉讼费
//				for(SsfEnum ssfEnum:SsfEnum.values()){
//					if(StringUtil.contains(ssfstr, ssfEnum.getSsfName())){
//						ssfstr=StringUtil.trim(ssfstr);
//						String je = ssfstr.substring(ssfEnum.getSsfName().length()+ssfstr.indexOf(ssfEnum.getSsfName()), ssfstr.length());//+"元";
//						je = je.replace('．', '.');
//						je=je.replaceAll("，", "");
//						if(je.contains("减半收取")){
//							je = je.substring(je.indexOf("减半收取")+4);
//						}
//						if(je.contains("（减半收取）")){
//							je = je.substring(6);
//						}
//						if(StringUtil.contains(je,"共计")){
//							je = je.substring(2);
//						}else if(StringUtil.contains(je,"计")||StringUtil.contains(je,"各")||StringUtil.contains(je,"为")||StringUtil.contains(je,"即")){
//							je = je.substring(1);
//						}
////						je是中文数字
//						if(!StringUtil.isNum(je)&&!StringUtil.contains(je, "人民币")){
//							int jeInt = NumberConverter.convertFromChinese(je);
//							if((jeInt>10 && je.length()>1)||jeInt==10){
//								je=jeInt+"";
//							} 
//						}
//						if(ssfEnum.equals(SsfEnum.JBSQ)&&jeModels.size()>0){
//							jeModels.get(jeModels.size()-1).setValue(je+"元");
//						}else{
//							WsCpjgssfjeModel jeModel = new WsCpjgssfjeModel(je+"元", ssfEnum.getSsfName());
//							jeModels.add(jeModel);
//							try{
//								ssfzje+=Integer.parseInt(ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan));
//							}catch(Exception e){
//								
//							}
//						}
//						break;
//					}
//					
//				}
//			}
////			遍历诉讼费种类
////			for(SsfEnum ssfEnum:SsfEnum.values()){
////				ssfzl = ssfEnum.getSsfName();//诉讼费种类名称
////				lengthOfSsfName = ssfzl.length();//诉讼费种类名称长度
////				indexOfSsf = ssfcdjl.indexOf(ssfzl);//诉讼费位置
//////					从诉讼费位置开始找到第一个元，例如 需缴纳审理费100元，
////				for(int j=indexOfSsf+lengthOfSsfName;j<chars.length;j++){
////		   	    	 if(chars[j] == '元'){
////		   	    		indexOfYuan = j;
////		   	    		 break;
////		   	    	 }
////			    }
////				if(indexOfSsf!=-1 &&indexOfYuan!=-1 ){
//////						计算诉讼费总金额
////					try{	
////						ssfje = ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan+1);
////						if(ssfEnum.equals(SsfEnum.JBSQ)){
////							if(jeModels.get(jeModels.size()-1)!=null){
////								jeModels.get(jeModels.size()-1).setValue(ssfje);
////							}
////						}else{
////							WsCpjgssfjeModel jeModel = new WsCpjgssfjeModel(ssfje, ssfzl);
////							jeModels.add(jeModel);
////							ssfzje+=Integer.parseInt(ssfcdjl.substring(indexOfSsf+lengthOfSsfName, indexOfYuan));
////						}
////					}catch(Exception e){
////						ssfzje=0;
////					}
////				}
////			}
//			
//			if(jeModels.size()>0){
//				ssfModel.setSsfjeModels(jeModels);
//				ssfModel.setZje(ssfzje+"元");
//			}
////			--------解析诉讼费承担-----------
//			List<WscpjgssfcdModel> cdModels = new ArrayList<WscpjgssfcdModel>();
//			HashMap<String, String> sscyrMap = new HashMap<String, String>();
//			for(WssscyrModel sscyr:wssscyrModellist){
//				sscyrMap.put(sscyr.getSscyr(), sscyr.getSsdw());
//			}
//		    String[] ssfs = ssfcdjl.split("，");
//		    int count=0;
//			for(String s:ssfs){
//				String cdje = getNumberFromString(s);
//				List<WscpjgssfcdModel> tempcdModels = new ArrayList<WscpjgssfcdModel>();
//				if(StringUtil.contains(s, "负担")||StringUtil.contains(s, "承担")){
//					count++;
//					for(Map.Entry<String, String> entry:sscyrMap.entrySet()){
//						if(StringUtil.contains(s, entry.getKey())){
//							WscpjgssfcdModel cdmodel = new WscpjgssfcdModel();
//							cdmodel.setCdr(entry.getKey());
//							cdmodel.setCdrdw(entry.getValue());
////							if(!StringUtil.equals(cdje, "0元")){
////								cdmodel.setCdje(cdje);
////							}
//							cdmodel.setCdfs("与他人共同承担该金额");
//							tempcdModels.add(cdmodel);
//						}
//					}
////					设置承担金额和承担方式
//					if(tempcdModels.size()==1){
//						tempcdModels.get(0).setCdfs("个人独立承担该金额");
//					}
//					cdModels.addAll(tempcdModels);
//				}
//			}
////			只有一方承担，承担总金额
//			if(count==1){
//				for(WscpjgssfcdModel cdmodel:cdModels){
//					cdmodel.setCdje(ssfModel.getZje());
//				}
//			}
//			if(cdModels.size()>0){
//				ssfModel.setSsfcdModels(cdModels);
//			}
//			wscpjgModel.setSsfModel(ssfModel);
//		}
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
	
	public  List<String> getPjjeLx(){
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
	 * 解析是否提出管辖权异议
	 * @param wscpjgModel
	 */
	public static void setTcgxyy(WscpjgModel wscpjgModel,String allNr){
		if(StringUtil.contains(allNr, "管辖异议")){
			wscpjgModel.setSftcgxyy("是");
		}else{
			wscpjgModel.setSftcgxyy("否");
		}
	}
}