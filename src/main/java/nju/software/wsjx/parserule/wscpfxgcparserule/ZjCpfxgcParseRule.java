package nju.software.wsjx.parserule.wscpfxgcparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/3/27.
 */
public class ZjCpfxgcParseRule extends GeneralCpfxgcCommonRule implements CpfxgcParseRule {

    @Override
    public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) throws ParseException {
        MsysCpfxgcParseRule msysCpfxgcParseRule = new MsysCpfxgcParseRule();
        WscpfxgcModel wscpfxgcModel = msysCpfxgcParseRule.jxWscpfxgcModel(wsAnalyse);
        ArrayList<WscpfxgcFtModel> ftModellist = wscpfxgcModel.getFtModellist();
        for (WscpfxgcFtModel wscpfxgcFtModel : ftModellist) {
            if (wscpfxgcFtModel.getFlftmc().contains("中华人民共和国民事诉讼法")){
                wscpfxgcFtModel.setSfcxf("是程序法");
            }else {
                wscpfxgcFtModel.setSfcxf("非程序法");
            }
        }

        String reg = "申请人(.*)的申请";
        Pattern pattern = Pattern.compile(reg);

        for (String s : wsAnalyse.getCpfxgc()) {
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                wscpfxgcModel.setSqr(matcher.group(1));
            }
        }
        return wscpfxgcModel;
    }
}
