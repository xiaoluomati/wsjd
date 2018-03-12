package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.Enum.TshyEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.util.StringUtil;
/**
 * 诉讼参与人的通用方法
 * @author wangzh
 *
 */
public class GeneralSscyrCommonRule {
	public void setDsrgj(WssscyrModel wssscyrModel) {
		String dsrgj = null;
		String dsrmc = wssscyrModel.getSscyr();//当事人名称
		String allinfo = wssscyrModel.getSscyrallinfo();
		if (allinfo.contains("澳门")&&allinfo.contains("居民")){
			dsrgj="中国澳门";
		}else if(allinfo.contains("香港")&&allinfo.contains("居民")){
			dsrgj="中国香港";
		}else if (allinfo.contains("加拿大")&&allinfo.contains("国籍")){
			dsrgj="加拿大";
		}else if (allinfo.contains("美利坚合众国")&&allinfo.contains("国籍")||allinfo.contains("美国籍")||allinfo.contains("美利坚")&&allinfo.contains("公民")){
			dsrgj="美国";
		}else if (allinfo.contains("大韩民国")&&allinfo.contains("公民")){
			dsrgj="韩国";
		}else if (allinfo.contains("新西兰")&&allinfo.contains("国籍")){
			dsrgj="新西兰";
		}else if (allinfo.contains("澳大利亚")&&allinfo.contains("公民")){
			dsrgj="澳大利亚";
		}else if (dsrmc!=null&&dsrmc.length()<5){
			dsrgj = "中国";
		}
		wssscyrModel.setGj(dsrgj);
	}

}
