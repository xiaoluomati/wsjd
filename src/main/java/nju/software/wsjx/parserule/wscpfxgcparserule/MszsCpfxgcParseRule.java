package nju.software.wsjx.parserule.wscpfxgcparserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.software.preProcess.WsFtParse;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

public class MszsCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule{

	@Override
	public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse)
			throws ParseException {
		// TODO Auto-generated method stub
		List<String> cpfxgc = wsAnalyse.getCpfxgc();
		WscpfxgcModel wscpfxgcModel = new WscpfxgcModel();
		if(cpfxgc==null||cpfxgc.size()==0){
			return wscpfxgcModel;
		}
		ArrayList<String> contentlist = WsAnalyse.getWholeContent(cpfxgc
				.get(cpfxgc.size() - 1));
		wscpfxgcModel.setFtModellist(new WsFtParse(wsAnalyse).getFtModelList());
		// 结案方式类型
		String lastContent = contentlist.get(contentlist.size() - 1);
		if (lastContent != null) {
			int jaindex = lastContent.indexOf("如下");
			if (jaindex != -1) {
				String jafslx = lastContent.substring(jaindex - 2, jaindex);
				wscpfxgcModel.setJafslx(jafslx);
			}
		}
		return wscpfxgcModel;
	}

}
