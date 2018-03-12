package nju.software.wsjx.parserule.msysFactorParseRule;
import net.sf.json.JSON;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.Match;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.msysFactorModel.WyModel;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.service.impl.jtsg.CmssdpreServiceImpl;


public class WyParseRule {
    public WyModel getWygModel(WsAnalyse wsAnalyse, WsModel wsModel){
        WyModel wyModel=new WyModel();
        wyModel = getCommonElement(wsModel, wyModel);
        wyModel = getFwf(wsModel , wyModel);
        return wyModel;
    }

    //公共的
    public WyModel getCommonElement(WsModel wsModel ,WyModel wyModel){
        //案号
        String ah = wsModel.getWswsModel().getAh();
        wyModel.setAh(ah);

        //被告
        List<String> wssscyrSegement = wsModel.getWssscyrSegment();
        List<JSONObject> bg = new ArrayList<JSONObject>();
        for (String it : wssscyrSegement){
            JSONObject bgr = new JSONObject();
            if(it.startsWith("被告")){
                //分解被告各个属性
                bgr.put("all",it);
                bgr = getBgElement(it , bgr);
                bg.add(bgr);
            }
        }
        wyModel.setBg(bg);

        //诉讼费
        String ssf = getSsf(wsModel.getWscpjgModel().getAjslf() , wsModel);
        wyModel.setSsf(ssf);


        return wyModel;
    }

    //服务费
    public WyModel getFwf(WsModel wsModel , WyModel wyModel){
        //拖欠
        String wsajjbq = wsModel.getWsajjbqSegment();
        if(wsajjbq.equals("")){
            wsajjbq = wsModel.getWsssjlSegment();
        }
        if(wsajjbq.equals("")){
            wsajjbq = wsModel.getWscpjgSegment();
        }
        String qfqssj = "";
        String wyfwf = "";
        String strFwf = "";
        if(!wsajjbq.equals("")){
            Pattern pattern = Pattern.compile("([0-9]{4})年([0-9]{1,2})月(.{0,6})至(.{0,6})([0-9]{1,2})月(.{0,10})(服务费|物业费)(.{0,10})元");
            Matcher m = pattern.matcher(wsajjbq);
            while(m.find()) {
                strFwf = m.group();
            }
            if(!strFwf.equals("")) {
                qfqssj = strFwf.substring(0, strFwf.indexOf("至"));
                wyfwf = strFwf.substring(strFwf.lastIndexOf("费") + 1, strFwf.lastIndexOf("元") + 1);
            }
        }
        wyModel.setQfqssj(qfqssj);
        wyModel.setWyfwf(wyfwf);
        return wyModel;
    }

    //分解被告各个属性
    public JSONObject getBgElement(String it , JSONObject bgr){
        String itString = it.replace("。",",");
        itString = itString.replace("，",",");
        String[] itArray = itString.split(",");
        String bgXm;
        for(String str : itArray){
            //被告
            if(str.startsWith("被告")){
                if(str.substring(2,3).equals(":")){
                    bgXm = str.substring(3);
                }else{
                    bgXm = str.substring(2);
                }
                bgr.put("bg",bgXm);
            }
            //性别
            if(str.equals("男") | str.equals("女")){
                bgr.put("xb" , str);
            }
            //出生年月
            if(str.endsWith("出生")){
                bgr.put("cssj",str.substring(0,str.indexOf("出生")));
            }
            //民族
            if(str.endsWith("族")){
                bgr.put("mz",str.substring(0,str.indexOf("族")));
            }
            //住址
            if(str.startsWith("现住")){
                if(str.startsWith("现住址")) {
                    bgr.put("zz", str.substring(3));
                }else{
                    bgr.put("zz", str.substring(2));
                }
            }
            if(str.startsWith("住")){
                if(str.startsWith("住址地") || str.startsWith("住所地")){
                    bgr.put("zz",str.substring(3));
                }
                else if(str.startsWith("住址")||str.startsWith("住所")){
                    bgr.put("zz",str.substring(2));
                }else{
                    bgr.put("zz",str.substring(1));
                }
            }
        }
        return bgr;
    }

    //截取诉讼费
    public String getSsf(String wsSsf ,WsModel wsModel){
        String ssf = "";
        if(wsSsf != null){
            int lastIndex = wsSsf.indexOf("元")+1;
            int firsIndex = wsSsf.indexOf("费")+1;
            if(firsIndex != -1 && lastIndex != -1) {
                ssf = wsSsf.substring(firsIndex, lastIndex);
            }

        }else{
            String wscpjg = wsModel.getWscpjgSegment();
            if(wscpjg.contains("受理费")){
                ssf = wscpjg.substring( wscpjg.indexOf("费")+1,wscpjg.indexOf("元")+1);
            }
        }

        return ssf;
    }
}
