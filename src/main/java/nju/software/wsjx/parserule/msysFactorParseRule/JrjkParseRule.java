package nju.software.wsjx.parserule.msysFactorParseRule;
import java.util.*;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.msysFactorModel.JrjkModel;
public class JrjkParseRule {
	/**
	 * 抽取金融借款的要素，返回金融借款要素model
	 * @param wsAnalyse 包含文书各个部分的分段信息，以及每段的具体内容
	 * @param wsModel 已经抽取完成的文书model
	 * @return
	 */
	public JrjkModel getJrjkModel(WsAnalyse wsAnalyse,WsModel wsModel){
		JrjkModel jrjkModel=new JrjkModel();
		//String ajjbqkseg = wsModel.getWsajjbqSegment();//提取事件基本情况段落
		//String[] ajjbqksegs = ajjbqkseg.split("。");//对事件基本情况段落分段
		//String ygscd=wsModel.getWsajjbqkModel().getYgscd();//调用原告诉称段
		List<String> zjd=wsModel.getWsajjbqkModel().getZjd();//调用证据段
		List<String> cpjgd=wsModel.getWscpjgModel().getCpjgnr();//调用裁判结果段
		List<String> cmsss = wsModel.getWsajjbqkModel().getCmssd();//提取查明事实段内容
		//String cpjgs=cpjgd.toString();
		//String cpjgdnr[]=cpjgs.split("；|。");
		String cmssList="";//只截取事实段
		String [] cmssSplit;//存放截取之后的查明事实段内容
		String [] cmssSplit2;
		for (String cmss: cmsss) {
			if (cmss.contains("本院认定事实如下")||cmss.contains("经审理查明")||cmss.contains("本院查明以下事实")||cmss.contains("事实与理由")) {
				cmssList=cmss;
					}
				}
		//System.out.println(cmssList);
         cmssSplit=cmssList.split("。|；");//截取之后的查明事实段内容按。；分段
		cmssSplit2=cmssList.split("，|。|；|、|：");
		//for(String list:cmssSplit2) {
			//System.out.println(list);
		//}
        /*
        *共有要素的提取 
        */
		//合同计数
		/*int htjs=0;
		for(String list:zjd) {
		if(list.contains("借款合同")||list.contains("贷款合同")||list.contains("授信合同")||list.contains("授信协议")){
				htjs++;
			}
			}*/
		//System.out.println("合同计数："+htjs);
		/*解析签约时间
		 * cmssList段可能含有多段时间
		 */
		String sj="未提及";
		for(String list:cmssSplit){
			if(list.contains("年")&&list.contains("月")&&list.contains("日")){
		         sj=list.substring(list.indexOf("年")-4,list.indexOf("日")+1);
		       break;
		   }}
		//System.out.println("签约时间："+sj);
		jrjkModel.setSj(sj);
		
		//解析签字人
		List<String> qzr=new ArrayList<String>();//签字人
		List<WssscyrModel> wssscyrModellist=wsModel.getWssscyrModels();
		for(int i=0;i<wssscyrModellist.size();i++) {
			WssscyrModel sscyr=(WssscyrModel) wssscyrModellist.get(i);
			if(sscyr.getSssf().equals("原告")||sscyr.getSssf().equals("被告")) {
				qzr.add(sscyr.getSscyr());
			}
		}
		if(qzr!=null) {
			jrjkModel.setQzr(qzr);
		}
		/*for(String list:qzr) {
			System.out.println("签字人："+list);
		}*/
		
		//解析借款金额
	    String jkje="未提及";
	    for (String list : cmssSplit2) {
				if ((list.contains("借款")||list.contains("贷款"))&&list.contains("元")) {
					jkje=list.substring(list.indexOf("款")+1, list.length());
				}else if(list.contains("本金")&&list.contains("元")) {
					jkje=list.substring(list.indexOf("金")+1,list.length());
				}
			}
		jrjkModel.setJkje(jkje);
		//System.out.println("借款金额："+jkje);
		/*裁判结果段抽取*/
		/*for(String it:cpjgdnr){
		 if(it.contains("被告")&&it.contains("原告")
				    &&it.contains("还")&&it.contains("借款")
				    &&it.contains("元")){
					 jkje=it.substring(it.indexOf("款")+1,it.indexOf("元"));
				 }
				 else if(it.contains("被告")&&it.contains("原告")
						    &&it.contains("还")&&it.contains("本金")
						    &&it.contains("元")){
					      jkje=it.substring(it.indexOf("款")+1,it.indexOf("元"));
					      }break;
			 }
		jrjkModel.setJkje(jkje);*/
		//System.out.println("裁判结果段借款金额："+jkje);
		//解析借款期限
		String jkqx="未提及";
		for(String list:cmssSplit2) {
			if(list.contains("借款期限")||list.contains("贷款期限")||list.contains("授信期限")) {
				jkqx=list.substring(list.indexOf("为")+1,list.length());
				break;
			}
		}
		jrjkModel.setJkqx(jkqx);
		//System.out.println("借款期限："+jkqx);
		//解析借款开始时间
	    	String jkkssj="未提及";
	    for(String list:cmssSplit2){
	    		if(list.contains("借款合同")||list.contains("贷款合同")) {
			  if(list.contains("自")&&list.contains("至")){   
			   jkkssj=list.substring(list.indexOf("自")+1,list.indexOf("至"));
			   }else if(list.contains("从")&&list.contains("起")) {
				   jkkssj=list.substring(list.indexOf("从")+1,list.indexOf("起"));
			   }
			    break;
			   }}
	   jrjkModel.setJkkssj(jkkssj);
	   //System.out.println("借款开始时间："+jkkssj);
	 
	    	//解析借款结束时间
		String jkjssj="未提及";
		for(String list:cmssSplit2){
			if(list.contains("借款期限")||list.contains("贷款期限")||list.contains("授信期限")) {
				jkjssj="未提及";
			}else if(list.contains("借款合同")||list.contains("贷款合同")) {
			  if(list.contains("至")&&list.contains("止")){   
			   jkjssj=list.substring(list.indexOf("至")+1,list.indexOf("止"));
			   }else if(list.contains("至")) {
				   jkjssj=list.substring(list.indexOf("至")+1,list.lastIndexOf("日")+1);
			   }
			    break;
			   }}
		jrjkModel.setJkjssj(jkjssj);
		//System.out.println("借款结束时间："+jkjssj);
		//解析借款利息
		String jklx="未提及";
		for(String list:cmssSplit2) {
			if(list.contains("借款利率")||list.contains("贷款利率")){
				jklx=list.substring(list.indexOf("为")+1,list.length());
			}
		}
		jrjkModel.setJklx(jklx);
		//System.out.println("借款利息："+jklx);
		
		//解析是否约定逾期还款利息
	    String sfydyqhklx="否";
		for(String list:cmssSplit2) {
			if(list.contains("逾期")) {
				if(list.contains("利息")) {
					sfydyqhklx="是";
					}
			}
		}
		jrjkModel.setSfydyqhklx(sfydyqhklx);
		//System.out.println("是否约定逾期还款利息："+sfydyqhklx);
		//解析是否约定逾期还款复利
		String sfydyqhkfl="否";
		for(String list:cmssSplit2) {
			if(list.contains("逾期")) {
				if(list.contains("复利")) {
				    sfydyqhkfl="是";
				}
			}
		}
		jrjkModel.setSfydyqhkfl(sfydyqhkfl);
		//System.out.println("是否约定逾期还款复利："+sfydyqhkfl);
		//解析是否约定逾期还款违约金
		String sfydyqhkwyj="否";
		for(String list:cmssSplit2) {
			if(list.contains("逾期")) {
				if(list.contains("违约金")) {
				    sfydyqhkwyj="是";
				}
			}
		}
		jrjkModel.setSfydyqhkwyj(sfydyqhkwyj);
		//System.out.println("是否约定逾期还款违约金："+sfydyqhkwyj);
		//解析借款后是否偿还本金
		String jkhsfchbj="未提及";
		for(String list:cmssSplit) {
			if(list.contains("本金"))
			if((list.contains("未")&&list.contains("偿还"))||list.contains("未予支付")) {
				jkhsfchbj="否";
			}else {
				jkhsfchbj="是";
			}
		}
		jrjkModel.setJkhsfchbj(jkhsfchbj);
		//System.out.println("借款后是否偿还本金："+jkhsfchbj);
		//借款后是否偿还利息
		String jkhsfchlx="未提及";
		for(String list:cmssSplit) {
			if(list.contains("利息"))
			if(list.contains("未")&&(list.contains("偿还")||list.contains("返还"))||list.contains("未予支付")) {
				jkhsfchbj="否";
			}else {
				jkhsfchbj="是";
			}
		}
		jrjkModel.setJkhsfchlx(jkhsfchlx);
		//System.out.println("借款后是否偿还利息："+jkhsfchlx);
		//解析借款后是否偿还罚息
		String jkhsfchfx="未提及";
		for(String list:cmssSplit) {
			if(list.contains("罚息"))
			if((list.contains("未")&&list.contains("偿还"))||list.contains("未予支付")) {
				jkhsfchfx="否";
			}else {
				jkhsfchfx="是";
			}
		}
		jrjkModel.setJkhsfchfx(jkhsfchfx);
		//System.out.println("借款后是否偿还罚息："+jkhsfchfx);
		//解析借款后是否偿还复利
				String jkhsfchfl="未提及";
				for(String list:cmssSplit) {
					if(list.contains("复利"))
					if((list.contains("未")&&list.contains("偿还"))||list.contains("未予支付")) {
						jkhsfchfl="否";
					}else {
						jkhsfchfl="是";
					}
				}
				jrjkModel.setJkhsfchfl(jkhsfchfl);
				//System.out.println("借款后是否偿还复利："+jkhsfchfl);
		//解析是否存在担保
		String sfczdb="未提及";
		for(String list:cmssSplit) {
			if(list.contains("保证")||list.contains("抵押")||list.contains("质押")){
			sfczdb="是";
		}else {
			sfczdb="否";
		}
			}
		jrjkModel.setSfczdb(sfczdb);
		//System.out.println("是否存在担保："+sfczdb);
		/*
		 *根据担保方式分类
		 */
		//保证
		//解析保证合同类型
		String bzhtlx="未提及";
		for(String list:cmssSplit) {
			if(list.contains("保证合同")) {
				if(list.contains("最高额")) {
					bzhtlx="最高额保证合同";
				}else {
					bzhtlx="保证合同";
				}
			}
		}
		jrjkModel.setBzhtlx(bzhtlx);
		//System.out.println("保证合同类型："+bzhtlx);
		//解析保证方式
		String bzfs="未约定或约定不明";
		//首先判断担保方式含有保证
		if(bzhtlx.contains("保证合同")) {
		for(String list:cmssSplit2) {
			if(list.contains("连带责任保证")||list.contains("连带保证责任")) {
			     bzfs="连带责任保证";
			}else {
				bzfs="一般保证";
			}	
			}
		}
		jrjkModel.setBzfs(bzfs);
		//System.out.println("保证方式："+bzfs);
		//解析保证范围
		String bzfw="未提及";
		if(bzhtlx.contains("保证合同")) {
			for(String list:cmssSplit2) {
				if(list.contains("保证范围")) {
					bzfw=list.substring(list.indexOf("围")+1,list.length());	
				}
			}
		}
		jrjkModel.setBzfw(bzfw);
		//System.out.println("保证范围："+bzfw);
		//解析保证期间
		String bzqj="未提及";
		for(String list:cmssSplit2) {
			if(list.contains("保证期间")) {
				if(list.contains("为")) {
					bzqj=list.substring(list.indexOf("为"),list.length());
				}else if(list.contains("至")) {
					bzqj=list.substring(list.indexOf("至"),list.length());
				}
			}break;
		}
		jrjkModel.setBzqj(bzqj);
		//System.out.println("保证期间："+bzqj);
		//解析保证期限内主张责任
		//解析抵押合同类型
		String dyhtlx="未提及";
		for(String list:cmssSplit) {
			if(list.contains("抵押合同")) {
				if(list.contains("最高额")) {
					dyhtlx="最高额抵押合同";
				}else {
					dyhtlx="抵押合同";
				}
			}
		}
		jrjkModel.setDyhtlx(dyhtlx);
		//System.out.println("抵押合同类型："+dyhtlx);
		//解析抵押物类型
		String dywlx="未提及";
		if(dyhtlx.contains("抵押合同")) {
		for(String list:cmssSplit) {
			if(list.contains("房产")||list.contains("土地")) {
				if(list.contains("在建")) {
					dywlx="在建不动产";
				}else {
					dywlx="不动产";
				}
			}else {
				dywlx="动产";
			}
		}}
		jrjkModel.setDywlx(dywlx);
		//System.out.println("抵押物类型："+dywlx);
		//解析抵押担保是否登记
		//解析抵押物现状
		//解析质押类型
		String zylx="未提及";
		for(String list:cmssSplit) {
			if(list.contains("质押")) {
				if(list.contains("动产质押")) {
					zylx="动产质押";
				}else if(list.contains("权利")||list.contains("股权")) {
					zylx="权利质押";
				}
			}		
				}
		jrjkModel.setZylx(zylx);
		//System.out.println("质押类型："+zylx);
		//解析质物是否交付质权人
		//解析质权人是否已行使权利质权
		//有无约定合同履行地
		//有无协议管辖约定条款
		//是否约定仲裁
		//是否约定追索债权费用的承担
		//是否有其他相关费用的约定
		String sfyqtxgfydyd="无";
		for(String list:cmssSplit2) {
			if(list.contains("其他")&&list.contains("费用")){
				sfyqtxgfydyd="有";
			}break;
		}
		jrjkModel.setSfyqtxgfydyd(sfyqtxgfydyd);
		//System.out.println("是否有其他相关费用的约定："+sfyqtxgfydyd);
		//是否签订过展期合同
		String sfqdgzqht="无";
		for(String list:cmssSplit2) {
			if(list.contains("展期")&&(list.contains("合同")||list.contains("协议"))) {
				sfqdgzqht="有";
			}break;
		}
		jrjkModel.setSfqdgzqht(sfqdgzqht);
		//System.out.println("是否签订过展期合同："+sfqdgzqht);
		//展期合同重新约定的项目
		//是否同时约定人保和物保
		//借款展期合同约定事项是否得到保证人的同意
		//涉诉债务是否存在债务人转让债务的情况
		//转让行为是否征得保证人同意
		//要求配偶承担责任的理由
		//借款人目前的婚姻状况
		//原告起诉前是否有催讨
		String ygqsqsfyct="未提及";
		for(String list:cmssSplit2) {
			if(list.contains("催讨")) {
				if(list.contains("电话")) {
					ygqsqsfyct="电话催讨";
				}else if(list.contains("书面")) {
					ygqsqsfyct="书面催讨";
				}else if(list.contains("公安")) {
					ygqsqsfyct="公安机关报案";
				}else if(list.contains("诉讼")) {
					ygqsqsfyct="其他诉讼";
				}else {
					ygqsqsfyct="其他";
				}
			}break;
		}
		jrjkModel.setYgqsqsfyct(ygqsqsfyct);
		//System.out.println("原告起诉前是否有催讨："+ygqsqsfyct);
		//目前是否可以联系到被告
		
		return jrjkModel;
	}
}
