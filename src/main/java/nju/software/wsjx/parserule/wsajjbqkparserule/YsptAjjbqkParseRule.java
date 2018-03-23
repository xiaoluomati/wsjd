package nju.software.wsjx.parserule.wsajjbqkparserule;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/20.
 */
public class YsptAjjbqkParseRule extends GeneralAjjbqkCommonRule implements AjjbqkParseRule {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) throws ParseException {
        MsysAjjbqkParseRule msysAjjbqkParseRule = new MsysAjjbqkParseRule();
        WsajjbqkModel wsajjbqkModel = msysAjjbqkParseRule.jxWsajjbqkModel(wsAnalyse, wssscyrModellist);
        List<String> ajjbqk = wsAnalyse.getAjjbqk();
        if(ajjbqk==null||ajjbqk.size()==0)
            return wsajjbqkModel;
        for (String s : ajjbqk) {
            if(s.contains("反诉请求")){
                FsqqModel fsqqModel = new FsqqModel();
                fsqqModel.setFsr(s.substring(s.indexOf("反诉人")+3, s.indexOf("向本院")));
                if(s.contains("反诉请求：") && s.contains("事实和理由")){
                    fsqqModel.setFsqq(s.substring(s.indexOf("反诉请求：")+5, s.indexOf("事实和理由")));
                    fsqqModel.setSshly(s.substring(s.indexOf("事实和理由：")+6));
                }
                wsajjbqkModel.setFsqqModel(fsqqModel);

            }else if(s.contains("诉讼请求")&& s.contains("事实和理由")){
                SsqqModel ssqqModel = new SsqqModel();
                ssqqModel.setSsqq(s.substring(s.indexOf("诉讼请求：")+5, s.indexOf("事实和理由")));
                ssqqModel.setSshly(s.substring(s.indexOf("事实和理由：")+6));
                String[] strings = s.split("。");
                for (String string : strings) {
                    if(string.contains("向本院提出诉讼请求")){
                        if(s.startsWith("起诉人")){
                            ssqqModel.setQsr(s.substring(s.indexOf("起诉人")+3, s.indexOf("向本院")));
                        }else{
                            ssqqModel.setQsr(s.substring(0, s.indexOf("向本院")));
                        }
                        break;
                    }
                }
                wsajjbqkModel.setSsqqModel(ssqqModel);
            }else if(s.contains("追加") && s.contains("共同")){
                ZjdsrModel zjdsrModel = new ZjdsrModel();
                String[] strings = s.split("，");
                int zjxh = 0;
                for (int i = 0; i < strings.length ;i++) {
                    String string = strings[i];
                    if(zjdsrModel.getSqsj()==null&&string.contains("年")&&string.contains("月")){
                        zjdsrModel.setSqsj(string);
                    }else if(zjdsrModel.getDsr()==null&&string.contains("申请追加")&&(string.contains("共同原告")||string.contains("共同被告"))){
                        zjdsrModel.setSqr(string.substring(0, string.indexOf("向本院申请")));
                        zjdsrModel.setDsr(string.substring(string.indexOf("追加")+2, string.indexOf("为")));
                        zjxh = i;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = zjxh; i < strings.length; i++) {
                    stringBuilder.append(strings[i]);
                    stringBuilder.append("，");
                }
                zjdsrModel.setSshly(stringBuilder.toString());
                wsajjbqkModel.setZjdsrModel(zjdsrModel);
            }
        }

        return wsajjbqkModel;
    }
}
