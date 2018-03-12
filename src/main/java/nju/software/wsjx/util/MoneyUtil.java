package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;


public class MoneyUtil {
	public static List<String> getJe(String content){
		List<String> moneyList = new ArrayList<String>();
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
    		if(!StringUtil.isBlank(je_toAdd)){
	        	if(je_toAdd.endsWith("万")){
		    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
		    		try{
		    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                        je_toAdd=je_addDou+"";
                    }catch(Exception e){
                    	//金额是汉字
                    	int intvalue = NumberConverter.convertFromChinese(je_toAdd)*10000;
                    	if (intvalue!=1 && !je_toAdd.equals("一万")) {
                    		je_toAdd = intvalue+"";
						}
		    		}
	        	}
	        	if(!StringUtil.isBlank(je_toAdd)){
	        		 moneyList.add(je_toAdd+"元");
	        	}
	        }
	    }
		return moneyList;
	}
	public static String getJestr(String content){
		String reg = "元";
    	 int index=-1;
    	 char[] chars = content.toCharArray();
    	 for(int j=chars.length-1;j>-1;j--){
    		 if(!(Character.isDigit(content.charAt(j))||content.charAt(j)=='．'||content.charAt(j)=='万'||content.charAt(j)=='，')){
	    		index=j;
	    		break;
	    	}
	    }
    	String je_toAdd = StringUtil.ToDBC(content.substring(index+1, content.length()));
    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	排除共计
		if(!StringUtil.isBlank(je_toAdd)){
        	if(je_toAdd.endsWith("万")){
	    		je_toAdd = je_toAdd.substring(0, je_toAdd.length()-1);
	    		try{
	    			Double je_addDou = Double.parseDouble(je_toAdd)*10000;
                    je_toAdd=je_addDou+"";
                }catch(Exception e){
                	//金额是汉字
                	int intvalue = NumberConverter.convertFromChinese(je_toAdd)*10000;
                	if (intvalue!=1 && !je_toAdd.equals("一万")) {
                		je_toAdd = intvalue+"";
					}
	    		}
        	}
        	return je_toAdd+"元";
        }
		return null;
	}
	
	/**
	 * 抽取文本中以元结尾的金额原文
	 * @param content
	 * @return
	 */
	public static List<String> getMoeny(String content){
		
		List<String> moneyList = new ArrayList<String>();
		String reg = "元";
		String[] nums = content.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))
	    				 ||nums[i].charAt(j)=='．'||nums[i].charAt(j)=='.'
	    				 ||nums[i].charAt(j)=='万'||nums[i].charAt(j)=='千'
	    				 ||nums[i].charAt(j)=='，'||nums[i].charAt(j)==',' || charIsNum(nums[i].charAt(j)))){
		    		index=j;
		    		break;
		    	}
		    }
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	排除共计
    		if(!StringUtil.isBlank(je_toAdd)){
    			moneyList.add(je_toAdd+"元");
	        }
	    }
		return moneyList;
	}
	/**
	 * 抽取文本中以元结尾的金额原文以及其金额类型
	 * @param content
	 * @param flag 是否需要抽取计算方法
	 * @return
	 */
	public static List<PjjeModel> getJeModels(String content,boolean flag){
		List<PjjeModel> moneyList = new ArrayList<PjjeModel>();
		List<String> jelsx = getPjjeLx();
		String reg = "元";
		String temp = StringUtil.removeBreak(content);
		String[] nums = temp.split(reg);
	    for (int i = 0; i < nums.length; i++){
	    	 int index=-1;
	    	 char[] chars = nums[i].toCharArray();
	    	 for(int j=chars.length-1;j>-1;j--){
	    		 if(!(Character.isDigit(nums[i].charAt(j))
	    				 ||nums[i].charAt(j)=='．'||nums[i].charAt(j)=='.'
	    				 ||nums[i].charAt(j)=='万'||nums[i].charAt(j)=='千'
	    				 ||nums[i].charAt(j)=='，'||nums[i].charAt(j)==',' || charIsNum(nums[i].charAt(j)))){
		    		index=j;
		    		break;
		    	}
		    }
	    	String je_toAdd = StringUtil.ToDBC(nums[i].substring(index+1, nums[i].length()));
	    	je_toAdd = je_toAdd.replaceAll(",", "");
//	    	排除共计
    		if(!StringUtil.isBlank(je_toAdd)){
    			PjjeModel model = new PjjeModel();
    			model.setValue(je_toAdd+"元");
    			int indexOfCat = -1;
    			 for(String s:jelsx){
	 					if(StringUtil.contains(nums[i], s) && nums[i].indexOf(s)+s.length() == nums[i].indexOf(je_toAdd)){
	 						if(model.getCategorys()==null){
	 							model.setCategorys(new ArrayList<String>());
	 						}
	 						model.getCategorys().add(s);
	 					}
	 				}
//    			 解析计算方法、开始结束日期
    			 if(flag){
    				 if(i<nums.length-1 && content.indexOf(nums[i])>-1 && content.indexOf(nums[i])<content.indexOf(nums[i+1]))  {
    					 setJsfsAndKsjsrq(content.substring(content.indexOf(nums[i]), content.indexOf(nums[i+1])), je_toAdd+"元", model); 
    				 }else if(i==nums.length-1 && content.indexOf(nums[i])>-1){
    					 setJsfsAndKsjsrq(content.substring(content.indexOf(nums[i])), je_toAdd+"元", model); 
    				 }
    			 }
    			 
    			 moneyList.add(model);
	        }
	    }
		return moneyList;
	}
	
	public static void setJsfsAndKsjsrq(String content,String je,PjjeModel model){
		int indexOfJe = content.indexOf(je)+je.length();
		int indexRight = content.indexOf("(")!=-1 ?content.indexOf("(") :content.indexOf("（") ;
		if(indexRight==indexOfJe){
			int indexLeft = content.indexOf(")")!=-1 ?content.indexOf(")") :content.indexOf("）") ;
			if(indexLeft>-1){
//				取括号内的内容
				String fs = content.substring(indexRight+1, indexLeft-1);
				int indexOfYear = content.indexOf("年");
				int indexOfDay = content.indexOf("日至")!=-1?content.indexOf("日至"):content.indexOf("日-");
				if(indexOfYear>-1 && indexOfDay>-1 && indexOfDay>indexOfYear){
					String ksrq = content.substring(indexOfYear-4,indexOfDay+1);
					String temp = content.substring(indexOfDay+1);
					indexOfYear = temp.indexOf("年");
					indexOfDay = temp.indexOf("日");
					model.setKssj(ksrq);
//					System.out.println("start"+ksrq);
					if(indexOfYear>-1 && indexOfDay>-1 && indexOfDay>indexOfYear){
						ksrq = temp.substring(indexOfYear-4,indexOfDay+1);
					    model.setJssj(ksrq);
//						System.out.println("end"+ksrq);
					}
				}else{
					model.setJsfs(fs);
//					System.out.println("all"+fs);
				}
			}
		}
	}
	public static boolean charIsNum(char content){
		if(content=='一' || content=='二' ||content=='三' ||content=='四' ||content=='五' ||content=='六' ||
				content=='七' ||content=='八' ||content=='九' ||content=='十' ){
			return true;
		}
		return false;
	}
	
	public static List<String> getPjjeLx(){
		String lxs = "[无, 存车费, 交强险, 损害赔偿金, 价款, 借款, 利息, 罚息, 债权, 律师费, 医疗费, 营养费, 住院伙食补助费, 残疾赔偿金, 交通费, "
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
				+ " 取证费, 衣服损失费, 合同款, 房屋使用费, 查档费, 人工费, 生活帮助费, 照相费, 模具款, 死亡赔偿金, 丧葬费, 精神抚慰金, 经济补偿, 人身损害赔偿金, 出让价款, 停工费, 头盔, 电动车款]";
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
	
}
