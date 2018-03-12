package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

/**
 * 行政一审裁判分析过程解析
 * @author wangzh
 *
 */
public class XzysCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule{
	
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		List<String> wscpfxgc = wsAnalyse.getCpfxgc();
		WscpfxgcModel wscpfxgcModel = new WscpfxgcModel();
		if(wscpfxgc == null || wscpfxgc.size() == 0)
			return wscpfxgcModel;
		String wscpgcString = wscpfxgc.get(wscpfxgc.size() - 1);
		wscpgcString = delUrl(wscpgcString);		
	//	System.out.println(wscpgcString);
		
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(wscpgcString);
		int index = 0;	
		boolean isFind = true;		
		for (int i = contentlist.size() - 1; i > 0; i--) {
			if (contentlist.get(i).contains("综上")) {
				index = i;
				break;
			} else if (contentlist.get(i).contains("依照")
					|| contentlist.get(i).contains("依据")
					|| contentlist.get(i).contains("根据")) {
				index = i;				
				isFind = false;
				break;
			}
			if(i == (contentlist.size() - 9)){
				index = i;
				break;
			}
		}
		String flftList[] = {
				"中华人民共和国行政诉讼法",
				"中华人民共和国民事诉讼法",
				"中华人民共和国行政复议法",
				"最高人民法院关于适用〈中华人民共和国行政诉讼法〉若干问题的解释",
				"最高人民法院关于适用<中华人民共和国行政诉讼法>若干问题的解释",
				"最高人民法院关于适用＜中华人民共和国行政诉讼法＞若干问题的解释",
				"最高人民法院关于审理政府信息公开行政案件若干问题的规定",
				"最高人民法院关于审理行政赔偿案件若干问题的规定",
				"最高人民法院关于执行〈中华人民共和国行政诉讼法〉若干问题的解释",
				"最高人民法院关于执行<中华人民共和国行政诉讼法>若干问题的解释",
				"最高人民法院关于执行＜中华人民共和国行政诉讼法＞若干问题的解释",
				"最高人民法院关于行政诉讼证据若干问题的规定",
				"最高人民法院关于审理工伤保险行政案件若干问题的规定",
				"最高人民法院关于审理房屋登记案件若干问题的规定",
				"最高人民法院关于审理涉及农村集体土地行政案件若干问题的规定",
				"最高人民法院关于审理行政许可案件若干问题的规定"
		};
		String flftjcList[] = {
				"行政诉讼法",
				"民事诉讼法",
				"行政复议法",
				"适用〈中华人民共和国行政诉讼法〉若干问题的解释",
				"适用<中华人民共和国行政诉讼法>若干问题的解释",
				"适用＜中华人民共和国行政诉讼法＞若干问题的解释",
				"关于审理政府信息公开行政案件若干问题的规定",
				"关于审理行政赔偿案件若干问题的规定",
				"关于执行〈中华人民共和国行政诉讼法〉若干问题的解释",
				"关于执行<中华人民共和国行政诉讼法>若干问题的解释",
				"关于执行＜中华人民共和国行政诉讼法＞若干问题的解释",
				"关于行政诉讼证据若干问题的规定",
				"关于审理工伤保险行政案件若干问题的规定",
				"最高人民法院关于审理房屋登记案件若干问题的规定",
				"关于审理涉及农村集体土地行政案件若干问题的规定",
				"最高人民法院关于审理行政许可案件若干问题的规定"
		};
		// 解析法条名称
		String ftString = "";// 法条
		for (int j = index; j < contentlist.size(); j++) {
			ftString += contentlist.get(j);
		}				
	//	System.out.println(ftString);												
		String[] ftfz = ftString.split("《");
		//删除数组最后一个里面的规定等字符
		ftfz[ftfz.length-1] = delGd(ftfz[ftfz.length-1]);		
		ArrayList<WscpfxgcFtModel> ftModellist = new ArrayList<WscpfxgcFtModel>();
		wscpfxgcModel.setFtModellist(ftModellist);
		for (int j = 0; j < ftfz.length; j++) {
			String content = ftfz[j];
		//	System.out.println(content);
			if (content.indexOf("》") != -1) {
				WscpfxgcFtModel ftModel = new WscpfxgcFtModel();
				String flftmc = content.substring(0, content.indexOf("》"));		
				
				//如果需要从数组里面查找
				if(isFind == true){
					int m = 0;
					for(m = 0; m < flftList.length; m++){
						if(flftList[m].equals(flftmc)){
							break;							
						}
						else {
							if(flftjcList[m].equals(flftmc)){
								flftmc = flftList[m];
								break;
							}
						}
					}
					//没找到法条名称
					if(m == flftList.length)
						continue;
				}								
				ftModel.setFlftmc(flftmc);								
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
		// 解析是否存在行政赔偿
		String xzpc = "否";
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("行政赔偿")
					|| contentlist.get(i).contains("国家赔偿法")) {
				xzpc = "是";
			} 
		}
		wscpfxgcModel.setXzpc(xzpc);
		//解析是否经过行政复议
		String sfjgxzfy = "否";
		for(int i=0;i<contentlist.size();i++){
			if(contentlist.get(i).contains("行政复议")||contentlist.get(i).contains("复决字")){
				sfjgxzfy = "是";
			}
		}
		wscpfxgcModel.setSfjgxzfy(sfjgxzfy);
		
		//解析行政行为违法补救
		String xzxwwfbj = "无";
		for(int i=0;i<contentlist.size();i++){
			if(contentlist.get(i).contains("责令")){
				for(int j=0;j<contentlist.size();j++){
					if(contentlist.get(i).contains("补救")){
						xzxwwfbj = "责令被告采取相应的补救措施";
					}else{
						xzxwwfbj = "责令重新作出具体行政行为";
					}
				}
			}
		}
		wscpfxgcModel.setXzxwwfbj(xzxwwfbj);
		return wscpfxgcModel;
	}


}
