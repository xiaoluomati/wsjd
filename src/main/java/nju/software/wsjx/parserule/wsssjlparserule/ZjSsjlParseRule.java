package nju.software.wsjx.parserule.wsssjlparserule;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/3/27.
 */
public class ZjSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule {

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) throws ParseException {
        MsysSsjlParseRule msysSsjlParseRule = new MsysSsjlParseRule();
        WsssjlModel wsssjlModel = msysSsjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);

        String ahreg = "[（\\(（〔]\\d{4}[）\\)〕）].*\\d{2,4}号";
        String reg1 = "[，,\\.。、;；](.*人民法院)于(.*?)作出("+ahreg+")民事裁定：(.*)";
        Matcher matcher1 = Pattern.compile(reg1).matcher(wsssjl);
        if(matcher1.find()){
            wsssjlModel.setCdfy(matcher1.group(1));
            wsssjlModel.setCdrq(matcher1.group(2));
            wsssjlModel.setCdah(matcher1.group(3));
            wsssjlModel.setCdzw(matcher1.group(4));
        }


        String reg2 = "申请人(.*)向本院提出书面申请";
        Matcher matcher2 = Pattern.compile(reg2).matcher(wsssjl);
        if(matcher2.find()){
            wsssjlModel.setSqr(matcher2.group(1));
        }
        String reg3 = "责令(.*)提交(.*?)[，,\\.。、;；]";
        matcher2 = Pattern.compile(reg3).matcher(wsssjl);
        if (matcher2.find()){
            wsssjlModel.setTjr(matcher2.group(1));
            wsssjlModel.setZjmc(matcher2.group(2));
        }
        String reg4 = "以证明(.*?)[\\.。;；]";
        matcher2 = Pattern.compile(reg4).matcher(wsssjl);
        if (matcher2.find()){
            wsssjlModel.setTjr(matcher2.group(1));
        }

        String reg5 = "申请人(.*?)于(.*)向本院申请.*保全证据[，,]请求(.*)[，,\\.。;；]";
        Matcher matcher3 = Pattern.compile(reg5).matcher(wsssjl);
        if(matcher3.find()){
            wsssjlModel.setSqr(matcher3.group(1));
            wsssjlModel.setSqrq(matcher3.group(2));
            wsssjlModel.setZjbqcs(matcher3.group(3));
        }

        String reg6 = "以(.*?)的(.*)提供担保。";
        matcher3 = Pattern.compile(reg6).matcher(wsssjl);
        if(matcher3.find()){
            String group1 = matcher3.group(1);
            if(group1.contains("担保人")){
                wsssjlModel.setSqr(group1.substring(3+group1.indexOf("担保人")));
            }else if(group1.contains("本人")){
                wsssjlModel.setSqr(group1);
            }
            wsssjlModel.setDbcc(matcher3.group(2));
        }
        return wsssjlModel;
    }
}
