package nju.software.wsjx.parserule.msysFactorParseRule;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.service.model.msysFactorModel.JdcjtsgModel;
import nju.software.wsjx.service.model.msysFactorModel.MjjdModel;

public class MjjdParseRule {
	/**
	 * 抽取民间借贷的要素，返回民间借贷要素model
	 * @param wsAnalyse 包含文书各个部分的分段信息，以及每段的具体内容
	 * @param wsModel 已经抽取完成的文书model
	 * @return
	 */
	public MjjdModel getMjjdModel(WsAnalyse wsAnalyse,WsModel wsModel){
		
		MjjdModel mjjdModel=new MjjdModel();
		
		String ygscd=wsModel.getWsajjbqkModel().getYgscd();//调用原告诉称段
		List<String> cmssd=wsModel.getWsajjbqkModel().getCmssd();//调用查明事实段
		List<String> cpjgd=wsModel.getWscpjgModel().getCpjgnr();
		//List<String> cpjg=wsModel.getWscpjgModel().getPjjgList();
		//List<String> cpjgd=wsAnalyse.getCpjg();//调用裁判结果段
		
		String ygscdnr[]=ygscd.split("。");
		//List<String> ygscdlist=Arrays.asList(ygscds);//new ArrayList<String>();//存放截取之后的原告诉称段
		//System.out.println(ygscdlist);
		
		String cmssds=cmssd.toString();
		String cmssdnr[]=cmssds.split("。");
		//List<String> cmssdnr=cmssd;//new ArrayList<String>();//存放截取之后的查明事实段
		
		String cpjgs=cpjgd.toString();
		String cpjgdnr[]=cpjgs.split("；|。");
		
		
		 String dsrzjdgx="";//1、当事人之间的关系（暂不提取）
		 String htclsj="";//2、合同成立时间
		 String jkje="";//3、借款金额
		 String htkssj="";//4、合同开始时间
		 String htjssj="";//5、合同结束时间
		 List<String> bjhkqk;//6、本金还款情况
		 List<String> lxhkqk=new ArrayList<>();//7、利息还款情况
		 List<String> dbr = new ArrayList<>();//8、担保人
		 String dbhtnr="";//9、担保合同内容
		 String dbje="";//10、担保金额
		 String sfyfqgtzw="";//11、是否有夫妻共同债务
		 String myjkr="";//12、名义借款人(暂不提取)
		 
		 
		 int index=0;
		
		/**
		 * 1、当事人之间的关系（暂不提取）
		 */
		
		 
		 /**
		 * 2、合同成立时间(暂时抽的是签订合同时间)
		 */
		 //原告诉称段
		 for(int i=0;i<ygscdnr.length;i++){
			 if(ygscdnr[i].contains("年")&&ygscdnr[i].contains("月")
			    &&ygscdnr[i].contains("日")&&ygscdnr[i].contains("签订")
			    &&ygscdnr[i].contains("借款")){
				 index=i;
				 htclsj=ygscdnr[i].substring(ygscdnr[i].indexOf("2"),ygscdnr[i].indexOf("日")+1);
				 mjjdModel.setHtclsj("原告诉称段："+htclsj);
			 }
		 }
		 
		 //查明事实段
		 for(int i=0;i<cmssdnr.length;i++){
			 if(cmssdnr[i].contains("年")&&cmssdnr[i].contains("月")
			    &&cmssdnr[i].contains("日")&&cmssdnr[i].contains("签订")
			    &&cmssdnr[i].contains("借款")){
				 index=i;
				 htclsj=ygscdnr[i].substring(ygscdnr[i].indexOf("2"),ygscdnr[i].indexOf("日")+1);
				 mjjdModel.setHtclsj("查明事实段："+htclsj); 
			 } 
		 }
		 
		/**
		 * 3、借款金额（暂时抽的是判决结果里面的本金。不是原告诉称段的借款金额）
		 */
//		//原告诉称段
//		 for(int i=0;i<ygscdnr.length;i++){
//			 if(ygscdnr[i].contains("被告")&&ygscdnr[i].contains("向原告")
//			    &&ygscdnr[i].contains("借")&&ygscdnr[i].contains("万元")){
//				 index=i;
//				 jkje=ygscdnr[i].substring(ygscdnr[i].indexOf("款")+1,ygscdnr[i].indexOf("元")+1);
//				 mjjdModel.setJkje("原告诉称段："+jkje);
//			 }
//		 }
//		 
//		//查明事实段
//		 for(int i=0;i<ygscdnr.length;i++){
//			 if(ygscdnr[i].contains("被告")&&ygscdnr[i].contains("原告")
//			    &&ygscdnr[i].contains("借")&&ygscdnr[i].contains("万元")){
//				 index=i;
//				 jkje=ygscdnr[i].substring(ygscdnr[i].indexOf("款")+1,ygscdnr[i].indexOf("元")+1);
//				 mjjdModel.setJkje("查明事实段："+jkje);
//			 }
//		 }
		 //裁判结果段
		 for(int i=0;i<cpjgdnr.length;i++){
			 if(cpjgdnr[i].contains("被告")&&cpjgdnr[i].contains("原告")
			    &&cpjgdnr[i].contains("还")&&cpjgdnr[i].contains("借款")
			    &&cpjgdnr[i].contains("元")){
				 index=i;
				 jkje=cpjgdnr[i].substring(cpjgdnr[i].indexOf("款")+1,cpjgdnr[i].indexOf("元"));
				 mjjdModel.setJkje("裁判结果段:"+jkje);
			 }
			 else 
				 if(cpjgdnr[i].contains("被告")&&cpjgdnr[i].contains("原告")
					    &&cpjgdnr[i].contains("还")&&cpjgdnr[i].contains("本金")
					    &&cpjgdnr[i].contains("元")){
				      index=i;
				      jkje=cpjgdnr[i].substring(cpjgdnr[i].indexOf("款")+1,cpjgdnr[i].indexOf("元"));
				      mjjdModel.setJkje("裁判结果段:"+jkje);
			 }
		 }
		 
		/**
		 * 4、合同开始时间
		 * 5、合同结束时间
		 */
		//原告诉称段
		 for(int i=0;i<ygscdnr.length;i++){
			 if(ygscdnr[i].contains("借款期限")&&ygscdnr[i].contains("自")
			    &&ygscdnr[i].contains("年")&&ygscdnr[i].contains("月")
			    &&ygscdnr[i].contains("日")&&ygscdnr[i].contains("至")){
				 index=i;
				 htkssj=ygscdnr[i].substring(ygscdnr[i].indexOf("自")+1,ygscdnr[i].indexOf("日")+1);
				 mjjdModel.setHtkssj("原告诉称段："+htkssj);
				 htjssj=ygscdnr[i].substring(ygscdnr[i].indexOf("至")+1,ygscdnr[i].lastIndexOf("日")+1);
				 mjjdModel.setHtjssj("原告诉称段"+htjssj);
			 }
		 }
		 
		//查明事实段
		 for(int i=0;i<ygscdnr.length;i++){
			 if(ygscdnr[i].contains("借款期限")&&ygscdnr[i].contains("自")
			    &&ygscdnr[i].contains("年")&&ygscdnr[i].contains("月")
			    &&ygscdnr[i].contains("日")&&ygscdnr[i].contains("至")){
				 index=i;
				 htkssj=ygscdnr[i].substring(ygscdnr[i].indexOf("自")+1,ygscdnr[i].indexOf("日")+1);
				 mjjdModel.setHtkssj("查明事实段："+htkssj);
				 htjssj=ygscdnr[i].substring(ygscdnr[i].indexOf("至")+1,ygscdnr[i].lastIndexOf("日")+1);
				 mjjdModel.setHtjssj("查明事实段"+htjssj);
			 }
		 }
		/**
		 * 6、本金还款情况
		 */
	   //判决结果段
//		 for(int i=0;i<cpjgdnr.length;i++){
//			 if(){
//				 
//			 }
//		 }
		 
		
		/**
		 * 7、利息还款情况
		 */
	 //判决结果段
		 for(int i=0;i<cpjgdnr.length;i++){
			 if(cpjgdnr[i].contains("支付")&&cpjgdnr[i].contains("利息")&&cpjgdnr[i].contains("元")){
				 if(cpjgdnr[i].contains("减去"))
				 {
					 index=i;
					String lxhkqknr=cpjgdnr[i].substring(cpjgdnr[i].indexOf("去")+1,cpjgdnr[i].indexOf("元")+1);
					 lxhkqk.add(lxhkqknr);
					 mjjdModel.setLxhkqk(lxhkqk);
				 }
				 if(cpjgdnr[i].contains("扣除"))
				 {
					 index=i;
					String lxhkqknr=cpjgdnr[i].substring(cpjgdnr[i].indexOf("息")+1,cpjgdnr[i].indexOf("元")+1);
					 lxhkqk.add(lxhkqknr);
					 mjjdModel.setLxhkqk(lxhkqk);
				 }
			 }
		 }
		 
		/**
		 * 8、担保人
		 */
		//判决结果段
		 for(int i=0;i<cpjgdnr.length;i++){
			 if(cpjgdnr[i].contains("承担")&&cpjgdnr[i].contains("连带")&&cpjgdnr[i].contains("责任")){
				 index=i;
				 String dbrnr=cpjgdnr[i].substring(cpjgdnr[i].indexOf("告")+1,cpjgdnr[i].indexOf("对"));
				 dbr.add(dbrnr);
				 mjjdModel.setDbr(dbr);
			 }
		 }
		/**
		 * 9、合同内容
		 */
	  //原告诉称段
		 
	 //查明事实段
		/**
		 * 10、担保金额
		 */
	 //
		/**
		 * 11、是否有夫妻共同债务
		 */
	 //原告诉称段
		for(int i=0;i<ygscdnr.length;i++){
			if(ygscdnr[i].contains("夫妻关系")&&ygscdnr[i].contains("共同")
			   &&ygscdnr[i].contains("债务")){
				index=i;
				sfyfqgtzw="是";
				mjjdModel.setSfyfqgtzw(sfyfqgtzw);			
			}
			else mjjdModel.setSfyfqgtzw("否");
		}
	//查明事实段
		for(int i=0;i<cmssdnr.length;i++){
		if(cmssdnr[i].contains("夫妻关系")&&cmssdnr[i].contains("共同")
				   &&cmssdnr[i].contains("债务")){
					index=i;
					sfyfqgtzw="是";
					mjjdModel.setSfyfqgtzw(sfyfqgtzw);			
				}
				else mjjdModel.setSfyfqgtzw("否");
		}
		
		
		return mjjdModel;
		
		
}
}
