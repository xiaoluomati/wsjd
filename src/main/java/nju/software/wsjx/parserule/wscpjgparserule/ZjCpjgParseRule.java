package nju.software.wsjx.parserule.wscpjgparserule;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/3/27.
 */
public class ZjCpjgParseRule extends GeneralCpjgCommonRule implements CpjgParseRule {


    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        MsysCpjgParseRule msysCpjgParseRule = new MsysCpjgParseRule();
        WscpjgModel wscpjgModel = msysCpjgParseRule.jxWscpjgModel(wsAnalyse, wssscyrModellist);
        List<String> cpjg = wsAnalyse.getCpjg();
        String reg1 = "鉴定费用(\\d*元)|(\\d*元)鉴定费";
        String reg2 = "被保全人(.*?)的";
        Pattern pattern1 = Pattern.compile(reg1);
        Pattern pattern2 = Pattern.compile(reg2);
        for (String s : cpjg) {
            Matcher matcher1 = pattern1.matcher(s);
            Matcher matcher2 = pattern2.matcher(s);
            if(matcher1.find()){
                for (int i = 0; i < matcher1.groupCount(); i++) {
                    if(matcher1.group(1+i)!=null){
                        wscpjgModel.setJdfy(matcher1.group(1+i));
                        break;
                    }
                }
            }
            if(matcher2.find()){
                wscpjgModel.setBbqr(matcher2.group(1));
            }
        }

        return wscpjgModel;
    }
}
