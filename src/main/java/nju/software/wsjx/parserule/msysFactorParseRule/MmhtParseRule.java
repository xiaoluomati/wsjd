package nju.software.wsjx.parserule.msysFactorParseRule;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.service.model.msysFactorModel.MmhtModel;

public class MmhtParseRule {
	public MmhtModel getMmhtModel(WsAnalyse wsAnalyse,WsModel wsModel){
		MmhtModel mmhtModel = new MmhtModel();
		
		List<String> cmssd = wsModel.getWsajjbqkModel().getCmssd();
		List<String> zjd = wsModel.getWsajjbqkModel().getZjd();
		String ygscd = wsModel.getWsajjbqkModel().getYgscd();
		String cpfxgc = wsModel.getWscpfxgcSegment();
		String cmssnr = "";
		String zjnr = "";
		String[] cmssList;
		String[] cmssList2;
		String[] cmssList3;
		String[] cpfxgcList;
		String[] zjdList;
        for(String cmss:cmssd){
        	if(cmss.contains("经审理查明")||cmss.contains("本院认定事实如下")
        			||cmss.contains("本院查明以下事实")||cmss.contains("本院根据上述认证查明")){
        		cmssnr = cmss;
        	}
        }
        if(zjd!=null){
        	 for(String zj:zjd){
             	if(zj.contains("合同签订后")||zj.contains("合同订立后")){
             		zjnr = zj;
             	}
             }
        }    
		cmssList = cmssnr.split("；|。");
		cmssList2 = cmssnr.split("；|。|，");
		cmssList3 = cmssnr.split("。|，|、|；");
		cpfxgcList = cpfxgc.split("。");
		zjdList = zjnr.split("，|。");
		
		//解析签订时间
		String qdsj = "";
		for(String list:cmssList){
			if(list.contains("订")&&list.contains("合同")&&list.contains("年")
					&&list.contains("月")&&list.contains("日")){
				qdsj = list.substring(list.indexOf("年")-4,list.indexOf("日")+1);
				if(qdsj!=""){
					mmhtModel.setHtqdsj(qdsj);
					break;
				}
			}
		}
				
		//解析物品名称、数量
		String wmc = "";
		String sl ="";
		for(String list:cmssList2){
			if(list.contains("《")&&list.contains("》")&&list.contains("签订")&&list.contains("合同")
					&&(list.indexOf("合同")-list.indexOf("签订")!=3)){
				wmc = list.substring(list.indexOf("《")+1,list.indexOf("》")-4);
			}
			if(list.contains("原告")&&list.contains("被告")&&list.contains("供应")&&list.contains("向")){
				if(isNumeric(list)!=0){
					sl = list.substring(isNumeric(list));
                    wmc = list.substring(list.indexOf("供应")+2,isNumeric(list));
				}else{
					wmc = list.substring(list.indexOf("供应")+2);
					}	
			}
			if(list.contains("原告")&&list.contains("订购")&&list.contains("向")||
					list.contains("被告")&&list.contains("订购")&&list.contains("向")){
				wmc = list.substring(list.indexOf("订购")+2);
				if(wmc.contains("总金额")&&isNumeric(wmc)>0){
					if(wmc.indexOf("元")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("总金额"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("元")+1);
					}								
				}
				if(wmc.contains("价值")&&isNumeric(wmc)>0){
					if(wmc.indexOf("元")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("总金额"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("元")+1);
					}	
				}
			}
			if(list.contains("原告")&&list.contains("购买")&&list.contains("向")||
					list.contains("被告")&&list.contains("购买")&&list.contains("向")){
				wmc = list.substring(list.indexOf("购买")+2);
				if(wmc.contains("总金额")&&isNumeric(wmc)>0){
					if(wmc.indexOf("元")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("总金额"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("元")+1);
					}								
				}
				if(wmc.contains("价值")&&isNumeric(wmc)>0){
					if(wmc.indexOf("元")+1==wmc.length()){
						wmc = wmc.substring(wmc.indexOf(wmc),wmc.indexOf("总金额"));	
					}else{
						wmc = wmc.substring(wmc.indexOf("元")+1);
					}	
				}
			}
			if(list.contains("数量")&&isNumeric(list)>0){
				sl = list.substring(list.indexOf("数量")+2);
			}
		}
		if(wmc!=""){
			mmhtModel.setWmc(wmc);
		}
		if(wmc==""){
		    String[]  ygscList = ygscd.split("，|。");
			for(String list:ygscList){
				if(list.contains("原告")&&list.contains("提供")&&list.contains("被告")){
					wmc = list.substring(list.indexOf("提供")+2);
					if(wmc!=""){
						mmhtModel.setWmc(wmc);
					}
				}else if(list.contains("原告")&&list.contains("供应")&&list.contains("被告")){
					wmc = list.substring(list.indexOf("供应")+2);
					if(wmc!=""){
						mmhtModel.setWmc(wmc);
					}
				}
			}
		}
		if(sl!=""){
			mmhtModel.setYdsl(sl);
		}else{
			mmhtModel.setYdsl("未提及");
		}
		
		//当提供的物品不是一种类型时合并名称和数量
		List<String> wmcsl = new ArrayList<String>(); 
		for(String list:cmssList3){
			if(list.indexOf("年")==-1&&list.indexOf("月")==-1&&list.indexOf("日")==-1&&list.indexOf("元")==-1
					&&isNumeric(list)>0&&list.indexOf("%")==-1&&list.indexOf("％")==-1&&list.indexOf("天")==-1){
				wmcsl.add(list);
			}
		}
		if(wmcsl.size()!=0){
			mmhtModel.setWmcsl(StringUtils.strip(wmcsl.toString(),"[]"));
		}else{
			mmhtModel.setWmcsl("无");
		}
			
		//解析合同签订地点
		String qddd = "";
		for(String list:cmssList2){
			if(list.contains("签订地点")){
				qddd = list.substring(list.indexOf("签订地点")+4);
				if(qddd.contains("：")){
					qddd=qddd.replaceFirst("：", "");
					mmhtModel.setHtqddd(qddd);
					break;
				}else{
					mmhtModel.setHtqddd(qddd);
					break;
				}
			}
		}
		if(qddd==""){
			mmhtModel.setHtqddd("未提及");
		}
		
		//解析合同约定总价款(后来变更)
		String zje = "";
		for(String list:cmssList2){
			if((list.contains("总金额")||list.contains("总价")||list.contains("合计")||list.contains("货款共计"))
					&&list.indexOf("%")==-1&&isNumeric(list)>0){
				zje = list.substring(isNumeric(list));
					mmhtModel.setYdjk(zje);
			}
			if(list.contains("变更")&&list.contains("总金额")){
				zje = list.substring(isNumeric(list));
				mmhtModel.setYdjk(zje);
			}
		}
		if(zje==""){
			mmhtModel.setYdjk("未提及");
		}
		
		//合同约定价款交付时间
		String ydjfsj = "";
		for(String list:cmssList2){
			if(list.contains("应付时间")&&list.contains("年")&&list.contains("月")&&list.contains("日")){
				ydjfsj = list.substring(list.indexOf("应付时间为")+5,list.indexOf("日")+1);
			}else if(list.contains("应付时间")&&list.contains("年")&&list.contains("月")){
				ydjfsj = list.substring(list.indexOf("应付时间为")+5,list.indexOf("月")+1);
			}
		}
		if(ydjfsj!=""){
			mmhtModel.setYdjkjfdd(ydjfsj);
		}else{
			mmhtModel.setYdjkjfdd("未提及");
		}
		
		//合同约定价款给付方式
		String jkjffs = "";
		for(String list:cmssList2){
			if(list.contains("原告")&&list.contains("被告")&&list.contains("指定")&&list.contains("方式")){
				jkjffs = list;
			}
		}
		if(jkjffs!=""){
			mmhtModel.setYdjkjffs(jkjffs);
		}else{
			mmhtModel.setYdjkjffs("未提及");
		}
		
		//解析合同约定物交付时间(若有多种货物且交货时间不一致，只能提取到最先提到的交货时间)
		String jhsj = "";
		for(String list:cmssList2){
			if(list.contains("交（提）货时间")){
				jhsj = list.substring(list.indexOf("交（提）货时间为")+8);
			}else if(list.contains("交货时间")){
				jhsj = list.substring(list.indexOf("交货时间为")+5);
			}
			if(list.contains("货物")&&list.contains("到达")&&list.contains("日")){
				jhsj = list;
			}
		}
		if(jhsj!=""){
			mmhtModel.setYdwjfsj(jhsj);
		}else{
			mmhtModel.setYdwjfsj("未提及");
		}
		
		//合同约定物交付地点、实际交付地点
		String wydjfdd = "";
		String wsjjfdd = "";
		for(String list:cmssList2){
			if(list.contains("原告")&&list.contains("被告")&&list.contains("到达")&&list.contains("地点")){
				wsjjfdd = list.substring(list.indexOf("到达")+2);
				mmhtModel.setWsjjfdd(wsjjfdd);
			}else if(list.contains("原告")&&list.contains("被告")&&list.contains("送到")){
				wsjjfdd = list.substring(list.indexOf("送到")+2);
				mmhtModel.setWsjjfdd(wsjjfdd);
			}else if(list.contains("交货地点为")){
				wydjfdd = list.substring(list.indexOf("交货地点为")+5);
				mmhtModel.setWydjfdd(wydjfdd);
			}else if(list.contains("应")&&list.contains("到达")&&list.contains("指定")){
				wydjfdd = list.substring(list.indexOf("到达")+2);
				mmhtModel.setWydjfdd(wydjfdd);
			}else if(list.contains("交货")&&list.contains("地点")){
				wydjfdd = list.substring(list.indexOf("地点"));
				mmhtModel.setWydjfdd(wydjfdd);
			}else if(list.contains("送至")){
				wydjfdd = list.substring(list.indexOf("送至")+2);
				mmhtModel.setWydjfdd(wydjfdd);
			}
		}
		if(wydjfdd==""){
			mmhtModel.setWydjfdd("未提及");
		}
		if(wsjjfdd==""&&zjdList.length!=0){
			for(String list:zjdList){
				if(list.contains("送至")){
					wsjjfdd = list.substring(list.indexOf("送至")+2);
					mmhtModel.setWsjjfdd(wsjjfdd);
				}
			}
		}else{
			mmhtModel.setWsjjfdd("未提及");
		}
		
		//合同约定物实际交付时间
		String wsjjfsj = "";
		for(String list:cmssList){
			if(list.contains("原告")&&list.contains("送")&&list.contains("年")&&list.contains("月")&&list.contains("日")
					||list.contains("被告")&&list.contains("送")&&list.contains("年")&&list.contains("月")&&list.contains("日")
					||list.contains("买方")&&list.contains("卖方")&&list.contains("送")&&list.contains("年")&&list.contains("月")&&list.contains("日")){
				if(list.contains("自")&&list.contains("至")){
					if((list.indexOf("月", list.indexOf("月")+1)-list.indexOf("月"))>0){
						wsjjfsj = list.substring(list.indexOf("自")+1,list.indexOf("月", list.indexOf("月")+1)+1);
					}else if((list.indexOf("日", list.indexOf("日")+1)-list.indexOf("日"))>0){
						wsjjfsj = list.substring(list.indexOf("自")+1,list.indexOf("日", list.indexOf("日")+1)+1);
					}					
				}else{
					wsjjfsj = list.substring(list.indexOf("年")-4,list.indexOf("日")+1);
				} 
			}
		}
		if(wsjjfsj ==""&&zjdList.length!=0){
			for(String list:zjdList){
				if(list.contains("送至")&&list.contains("年")&&list.contains("月")&&list.contains("日")){
					wsjjfsj = list.substring(list.indexOf("年")-4,list.indexOf("日")+1);
					mmhtModel.setWsjjfsj(wsjjfsj);
				}
			}
		}
		if(wsjjfsj!=""){
			mmhtModel.setWsjjfsj(wsjjfsj);
		}else{
			mmhtModel.setWsjjfsj("未提及");
		}
				
		//解析合同约定物交付方式
		String jhfs = "";
		for(String list:cmssList2){
			if(list.contains("交（提）货方式")){
				jhfs = list.substring(list.indexOf("交（提）货方式为")+8);
			}else if(list.contains("交货方式")){
				jhfs = list.substring(list.indexOf("交货方式"));
			}
		}
		if(jhfs!=""){
			mmhtModel.setYdwjffs(jhfs);
		}else{
			mmhtModel.setYdwjffs("未提及");
		}
		
		//解析合同约定结算方式
		List<String> jsfs = new ArrayList<String>();
		for(String list:cmssList3){
			if(list.contains("结算方式")||list.contains("付款方式")){
				 jsfs.add(list);
			}
			if(list.indexOf("结")==-1&&list.contains("％")&&list.contains("付")&&list.indexOf("违约金")==-1||
					list.indexOf("结")==-1&&list.contains("%")&&list.contains("付")&&list.indexOf("违约金")==-1){
				if(list.contains("付清")){}
				else{
					 jsfs.add(list);
				}
			}if(list.contains("付清")){
				jsfs.add(list);
			}else if(list.contains("按")&&list.contains("结算")){
				jsfs.add(list);
			}			
		}
		if(jsfs.size()!=0){			
			mmhtModel.setYdjkjsfs(StringUtils.strip(jsfs.toString(),"[]"));
		}else{
			mmhtModel.setYdjkjsfs("未提及");
		}
		
		//解析约定物验收时间（约定还是实际？）
		String yssj = "";
		for(String list:cmssList){
			if(list.contains("年")&&list.contains("月")&&list.contains("日")&&list.contains("出具安装验收单")){
				yssj = list.substring(list.indexOf("年")-4,list.indexOf("日")+1);
				if(yssj!=""){
					mmhtModel.setYssj(yssj);
					break;
				}
			}
		}
		if(yssj==""){
			mmhtModel.setYssj("未提及");
		}
				
		//解析合同约定违约责任及承担方式
		List<String> wyzr = new ArrayList<String>();
		for(String list:cmssList){
			if(list.contains("承担违约责任")||list.contains("违约责任约定")){
				wyzr.add(list);
			}
		}
		if(wyzr.size()!=0){
			mmhtModel.setYdwyzr(wyzr);
		}else{
			wyzr.add("未提及");
			mmhtModel.setYdwyzr(wyzr);
		}
			
		//解析违约金计算公式（原告诉称和裁判结果不一致提取哪一个？）
		String cpjgd = wsModel.getWscpjgSegment();
		String[] cpjgnr = cpjgd.split("。");
		String wyjjs = "";
		for(String list:cpjgnr){
			if(list.contains("违约金")&&(list.indexOf("（")-list.indexOf("金"))==1){
				wyjjs = list.substring(list.indexOf("（"),list.indexOf("）"));
			}else if(list.contains("违约金")&&(list.indexOf("[")-list.indexOf("金"))==1){
				wyjjs = list.substring(list.indexOf("["),list.indexOf("]"));
			}else if(list.contains("违约金")&&isNumeric(list)>0&&list.contains("元")){
				wyjjs = list.substring(list.indexOf("违约金")+3,list.indexOf("元")+1);
			}
		}
		if(wyjjs!=""){
			mmhtModel.setWyjjsgs(wyjjs);
		}else{
			mmhtModel.setWyjjsgs("未提及");
		}
		
		//解析合同约定物实际交付数量
		String wsjjfsl = "";
		for(String list: cmssList){
			String[] str = list.split("，|。");
			if(list.contains("后")&&list.contains("原告")&&list.contains("提供")
					||list.contains("后")&&list.contains("被告")&&list.contains("提供")){				
				for(String list2:str){
					if(list2.contains("提供")&&isNumeric(list2)>0){
						wsjjfsl = list2.substring(isNumeric(list2));
					}
				}				
			}else if(list.contains("后")&&list.contains("原告")&&list.contains("运送")
					||list.contains("后")&&list.contains("被告")&&list.contains("运送")){
				for(String list2:str){
					if(list2.contains("运送")&&isNumeric(list2)>0){
						wsjjfsl = list2.substring(isNumeric(list2));
					}
				}
			}else if(list.contains("后")&&list.contains("原告")&&list.contains("提供")
					||list.contains("后")&&list.contains("被告")&&list.contains("提供")){
				for(String list2:str){
					if(list2.contains("提供")&&isNumeric(list2)>0){
						wsjjfsl = list2.substring(isNumeric(list2));
					}
				}
			}
		}
		if(wsjjfsl!=""){
			mmhtModel.setWsjjfsl(wsjjfsl);
		}else{
			mmhtModel.setWsjjfsl("未提及");
		}
		
		//解析合同价款实际支付情况
		List<String> sjzf = new ArrayList<String>();
		for(String list:cmssList){
			if(list.contains("后")&&list.contains("原告")&&list.contains("支付")&&isNumeric(list)>0
					&&list.indexOf("%")==-1&&list.indexOf("％")==-1
					||list.contains("后")&&list.contains("被告")&&list.contains("支付")&&isNumeric(list)>0
					&&list.indexOf("%")==-1&&list.indexOf("％")==-1){
				sjzf.add(list);
			}
			if(list.contains("原告")&&list.contains("尚欠")&&isNumeric(list)>0
				||list.contains("被告")&&list.contains("尚欠")&&isNumeric(list)>0){
					sjzf.add(list);
			}
			if(list.contains("已经支付")||list.contains("剩余未付")){
				sjzf.add(list);
			}
		}
		if(sjzf.size()!=0){			
			mmhtModel.setJksjzf(StringUtils.strip(sjzf.toString(),"[]"));
		}
		if(sjzf.size()==0&&zjdList.length!=0){
			for(String list:zjdList){
				if(list.contains("原告")&&list.contains("付")&&isNumeric(list)>0
						||list.contains("被告")&&list.contains("付")&&isNumeric(list)>0){
					sjzf.add(list);
				}
				if(list.contains("尚欠")&&isNumeric(list)>0||list.contains("至今未付")){
					sjzf.add(list);
				}
				if(sjzf.size()!=0){			
					mmhtModel.setJksjzf(StringUtils.strip(sjzf.toString(),"[]"));
				}
			}
		}else{
			mmhtModel.setJksjzf("未提及");
		}
		
		//对合同约定违约金标准有无异议
		String ywyy = "";
		for(String list:cpfxgcList){
			if(list.contains("提出")&&list.contains("违约金")&&list.contains("请求法院予以调整")){
				ywyy = "有";
				mmhtModel.setDwyjbzywyy(ywyy);
				break;
			}else if(list.contains("原告")&&list.contains("认为")&&list.contains("高")
					||list.contains("被告")&&list.contains("认为")&&list.contains("高")){
				ywyy = "有";
				mmhtModel.setDwyjbzywyy(ywyy);
				break;
			}
		}
        if(ywyy!=""){}
        else{
        	mmhtModel.setDwyjbzywyy("无");
        }
		
		//解析质保金
        String zbj = "";
        for(String list:cmssList2){
        	if(list.contains("为质保金")){
        		zbj= list;
        	}
        	if(zbj!=""){
        		mmhtModel.setYdzbj(zbj);
        		break;
        	}
        }
        if(zbj!=""){}
        else{
        	mmhtModel.setYdzbj("未提及");
        }
        
        //解析质保期
        String zbq = "";
        for(String list:cmssList2){
        	if(list.contains("质保期")&&list.contains("自")){
        		zbq = list;
        		if(zbq!=""){
        			mmhtModel.setYdzbq(zbq);
        			break;
        		}
        	}
        }
        if(zbq==""){
        	mmhtModel.setYdzbq("未提及");
        }
        
        //解析是否造成损失、损失具体数额
        boolean flag = false;
        String ss = "";
        String ssse = "";
	    String[]  ygscList = ygscd.split("，");
        if(ygscd.contains("赔偿")&&ygscd.contains("损失")){
        	for(String list:cpfxgcList){
        		if(list.contains("损失")&&list.contains("对此本院予以照准")){
        			flag = true;
        			ss = "是";
        			mmhtModel.setSfzcss(ss);
        			break;
        		}
        	}
            for(String list:ygscList){
            	if(list.contains("赔偿")&&list.contains("损失")&&isNumeric(list)>0){
            		ssse = list.substring(list.indexOf("损失")+2);
            		mmhtModel.setSsse(ssse);
            		break;
            	}
            }
        }
        if(ss==""){
        	mmhtModel.setSfzcss("否");
        	mmhtModel.setSsse("无");
        }
        
        //解析合同物有无质量争议
        String ywzlzy = "";
        for(String list:cmssd){
        	if(list.contains("原告")&&list.contains("承认")&&list.contains("存在问题")
        			||list.contains("被告")&&list.contains("承认")&&list.contains("存在问题")){
        		ywzlzy = "有";
        		mmhtModel.setWywzlzy(ywzlzy);
        		break;
        	}else if(list.contains("不能正常使用")||list.contains("无法正常使用")){
        		ywzlzy = "有";
        		mmhtModel.setWywzlzy(ywzlzy);
        		break;
        	}
        }
        if(ywzlzy==""){
        	mmhtModel.setWywzlzy("无");
        }
        
        //解析原告诉讼及证据段、被告辩称及证据段
        List<String> ygsszj = new ArrayList<String>();
        List<String> bgbczj = new ArrayList<String>();
        ygsszj.add(wsModel.getWsajjbqkModel().getYgscd());
        if(wsModel.getWsajjbqkModel().getBgbcd()!=""){
        	bgbczj.add(wsModel.getWsajjbqkModel().getBgbcd());
        }else{
        	bgbczj.add("无被告辩称");
        }
        if(zjd!=null){
        	for(String list:zjd){
            	if(list.contains("原告提交的证据为")||list.contains("原告向本院提交以下证据")){
            		ygsszj.add(list);
            	}else if(list.contains("被告提交的证据为")||list.contains("被告向本院提交以下证据")){
            		bgbczj.add(list);
            	}else{
            		ygsszj.add(list);
            		bgbczj.add(list);
            	}
            }
        }       
        mmhtModel.setYgssqqjzj(ygsszj);
        mmhtModel.setBgkblyjzj(bgbczj);
		
		return mmhtModel;
	}
	
	//返回字符串中出现的第一个数字的位置
    public int isNumeric(String str) {
        for (int i = 0;i<str.length();i++) {
            if (Character.isDigit(str.charAt(i))) {
                return i;
            }
        }
		return 0;
    }
         
}
