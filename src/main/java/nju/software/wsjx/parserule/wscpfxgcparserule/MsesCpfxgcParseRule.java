package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.preProcess.WsFtParse;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
/**
 * 民事二审裁判分析过程解析
 * @author wangzh
 *
 */
public class MsesCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule{
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		List<String> cpfxgc = wsAnalyse.getCpfxgc();
		WscpfxgcModel wscpfxgcModel = new WscpfxgcModel();
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(cpfxgc
				.get(cpfxgc.size() - 1));
		ArrayList<WscpfxgcFtModel> ftModelList = new WsFtParse(wsAnalyse).getFtModelList();
		wscpfxgcModel.setFtModellist(ftModelList);
		System.out.println(ftModelList);
		// 结案方式类型
		String lastContent = contentlist.get(contentlist.size() - 1);
		if (lastContent != null) {
			int jaindex = lastContent.indexOf("如下");
			if (jaindex != -1) {
				String jafslx = lastContent.substring(jaindex - 2, jaindex);
				wscpfxgcModel.setJafslx(jafslx);
			}
		}

		for (WscpfxgcFtModel wscpfxgcFtModel : ftModelList) {
			if (wscpfxgcFtModel.getFlftmc().contains("中华人民共和国民事诉讼法")){
				wscpfxgcFtModel.setSfcxf("是程序法");
			}else {
				wscpfxgcFtModel.setSfcxf("非程序法");
			}
		}
		return wscpfxgcModel;
	}




}
