package nju.software.wsjx.service.impl.jtsg;

import java.util.List;

import nju.software.wsjx.service.jtsg.CltbqkService;

public class CltbqkServiceImpl implements CltbqkService {

	@Override
	public boolean isToubao(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if (cmss.contains("未投保")) {
				b = false;
				break;
			}
		}
		return b;
	}

	@Override
	public String touBaoXZ(List<String> cmsslist) {
		String touBaoXZ = null;
		boolean hasJqx = false;
		boolean hasSybx = false;
		for (String cmss : cmsslist) {
			if (!(cmss.contains("未") || cmss.contains("没有"))) {
				if (cmss.contains("交强险") || cmss.contains("交通事故责任强制保险")) {
					hasJqx = true;
					break;
				}
			}
		}
		for (String cmss : cmsslist) {
			if (!(cmss.contains("未") || cmss.contains("没有"))) {
				if (cmss.contains("商业保险")||cmss.contains("商业三者险")||cmss.contains("第三者商业责任险")) {
					hasSybx = true;
					break;
				}
			}
		}
		if (hasJqx) {
			if (hasSybx) {
				touBaoXZ = "交通事故责任强制保险,第三者责任商业保险";
			} else {
				touBaoXZ = "交通事故责任强制保险";
			}
		} else if (hasSybx) {
			touBaoXZ = "第三者责任商业保险";
		} 
		return touBaoXZ;
	}

	@Override
	public boolean isValid(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if (cmss.contains("保险")
					&& (cmss.contains("已过期") || cmss.contains("已过期") || cmss.contains("不在保险期") )) {
				b = false;
			}
		}
		return b;
	}

	@Override
	public boolean isPay(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if (cmss.contains("先期赔付")) {
				return b;
			}
		}
		return b;
	}

	@Override
	public boolean identifyIsTrue(List<String> cmsslist) {
		boolean b = true;
		for (String cmss : cmsslist) {
			if(cmss.contains("鉴定")&&(cmss.contains("不可信")||cmss.contains("无效"))){
				b = false;
			}
		}
		return b;
	}

	@Override
	public String getShangQing(List<String> cmsslist) {
		String str1 = "诊断";
		String shangQing = null;
		for(String cmss : cmsslist){
			if(cmss.contains(str1)){
				String str = cmss.substring(cmss.indexOf(str1));
				shangQing = str.substring(0, str.indexOf("。")+1);
			}
		}
		return shangQing;
	}

	@Override
	public String getRealPay(List<String> cmsslist) {
		String realPay = null;
		for(String cmss : cmsslist){
			if(cmss.contains("共计")||cmss.contains("总计")){
				if(cmss.contains("共计")){
					String str1 = cmss.substring(cmss.indexOf("共计")+2);
					str1 = str1.substring(0, str1.indexOf("元")+1);
					if(str1.indexOf("：")==0){
						str1 = str1.substring(1);
					}
					if(str1.indexOf("人民币")==0){
						str1 = str1.substring(3);
					}
					realPay = str1;
					break;
				}
				else if(cmss.contains("总计")){
					String str = cmss.substring(cmss.indexOf("总计")+2);
					str = str.substring(0, str.indexOf("元")+1);
					if(str.indexOf("：")==0){
						str = str.substring(1);
					}
					if(str.indexOf("人民币")==0){
						str = str.substring(3);
					}
					realPay = str;
					break;
				}
			}
		}
		return realPay;
	}

	@Override
	public String getIdentifyContent(List<String> cmsslist) {
		String str1 = "鉴定意见：";
		String str2 = "鉴定意见为：";
		String str3 = "鉴定意见为“";
		String identifyContent = null;
		for(String cmss : cmsslist){
			if(cmss.contains(str1)||cmss.contains(str2)||cmss.contains(str3)){
				if(cmss.contains(str1)){
					String str = cmss.substring(cmss.indexOf(str1)+5);
					identifyContent = str.substring(0, str.indexOf("。")+1);
				}
				if(cmss.contains(str2)){
					String str = cmss.substring(cmss.indexOf(str2)+6);
					identifyContent = str.substring(0, str.indexOf("。")+1);
				}
				if(cmss.contains(str3)){
					String str = cmss.substring(cmss.indexOf(str3)+6);
					identifyContent = str.substring(0, str.indexOf("。")+1);
				}
			}
		}
		return identifyContent;
	}
}
