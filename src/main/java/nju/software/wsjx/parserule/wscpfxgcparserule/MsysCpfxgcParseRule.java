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
 * 民事一审裁判分析过程解析
 * @author wangzh
 *
 */
public class MsysCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule{
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
		List<String> cpfxgc = wsAnalyse.getCpfxgc();
		WscpfxgcModel wscpfxgcModel = new WscpfxgcModel();
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(delUrl(cpfxgc
				.get(cpfxgc.size() - 1)));
		wscpfxgcModel.setFtModellist(new WsFtParse(wsAnalyse).getFtModelList());
		System.out.println(new WsFtParse(wsAnalyse).getFtModelList());
		// 结案方式类型
		String lastContent = contentlist.get(contentlist.size() - 1);
		if (lastContent != null) {
			int jaindex = lastContent.indexOf("如下");
			if (jaindex != -1) {
				String jafslx = lastContent.substring(jaindex - 2, jaindex);
				wscpfxgcModel.setJafslx(jafslx);
			}
		}
		// 解析民事一审案件来源
		String ysajly = null;
		for (int i = 0; i < contentlist.size(); i++) {
			if (contentlist.get(i).contains("又起诉")
					|| contentlist.get(i).contains("再次起诉")
					|| contentlist.get(i).contains("再行起诉")
					|| contentlist.get(i).contains("再次诉讼")
					|| contentlist.get(i).contains("曾诉")) {
				ysajly = "重新起诉";
			}
		}
		wscpfxgcModel.setYsajly(ysajly);
		return wscpfxgcModel;
	}


}
