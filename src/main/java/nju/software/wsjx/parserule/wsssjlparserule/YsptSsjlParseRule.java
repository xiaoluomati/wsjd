package nju.software.wsjx.parserule.wsssjlparserule;

import nju.software.wsjd.model.ysptWsModel.ssjl.FsModel;
import nju.software.wsjd.model.ysptWsModel.ssjl.SsrqydsrModel;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;

import java.util.List;

/**
 * Created by zhuding on 2018/3/23.
 */
public class YsptSsjlParseRule extends GeneralSsjlCommonRule implements SsjlParseRule{

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) throws ParseException {
        SsjlParseRule msysSsjlParseRule = new MsysSsjlParseRule();
        WsssjlModel wsssjlModel = msysSsjlParseRule.jxWsssjlModel(wssscyrModellist, wsssjl);
        String[] contentArray = wsssjl.split("，|,|\\.|。|、|;|；");
        //原告和被告
        String ay = wsssjlModel.getAy();
        for (String s : contentArray) {
            if (s.contains("原告") && s.contains("被告") && s.contains("一案")){
                int index = s.indexOf("与");
                if (index == -1) {
                    index = s.indexOf("诉");
                }
                wsssjlModel.setYg(s.substring(s.indexOf("原告") + 2, index));
                wsssjlModel.setBg(s.substring(s.indexOf("被告") + 2, s.indexOf(ay)));
                break;
            }
        }

        contentArray = wsssjl.split("\\.|。");
        for (String s : contentArray) {
            SsrqydsrModel ssrqydsrModel = new SsrqydsrModel();
            String[] detail = s.split("，|,|、|;|；");
            if(s.contains("反诉状")){
                ssrqydsrModel.setSf("反诉人");
                for (String s1 : detail) {
                    if(ssrqydsrModel.getDate() ==null && s1.contains("年") && s1.contains("月")){
                        ssrqydsrModel.setDate(s1);
                    }else if(ssrqydsrModel.getName() ==null&&s1.contains("反诉状")){
                        ssrqydsrModel.setName(s1.substring(s1.indexOf("收到")+2, s1.indexOf("的反诉状")));
                    }
                }
                wsssjlModel.setFsz(ssrqydsrModel);
            }else if(s.contains("起诉状")){
                ssrqydsrModel.setSf("起诉人");
                for (String s1 : detail) {
                    if(ssrqydsrModel.getDate() ==null && s1.contains("年") && s1.contains("月")){
                        ssrqydsrModel.setDate(s1);
                    }else if(ssrqydsrModel.getName() ==null&&s1.contains("起诉状")){
                        ssrqydsrModel.setName(s1.substring(s1.indexOf("收到")+2, s1.indexOf("的起诉状")));
                    }
                }
                wsssjlModel.setQsz(ssrqydsrModel);
            }else if(s.contains("提出反诉")){
                FsModel fsModel = new FsModel();
                for (String s1 : detail) {
                    if(fsModel.getFsrq() == null && s1.contains("年") && s1.contains("月")){
                        fsModel.setFsrq(s1);
                    }else if(fsModel.getBg() == null && fsModel.getBg() == null && s.contains("原告") && s.contains("被告")){
                        fsModel.setBg(s.substring(s.indexOf("被告") + 2, s.indexOf("对")));
                        fsModel.setYg(s.substring(s.indexOf("原告") + 2, s.indexOf("提出反诉")));
                    }
                }
                wsssjlModel.setFs(fsModel);
            }else if(s.contains("拒不到庭")||s.contains("中途退庭")){
                ssrqydsrModel.setSf("当事人");
                for (String s1 : detail) {
                    if (ssrqydsrModel.getDate() == null && s1.contains("年") && s1.contains("月")) {
                        ssrqydsrModel.setDate(s1);
                    }else if(ssrqydsrModel.getName() ==null&&(s1.contains("经传唤")||s1.contains("经传票传唤"))){
                        ssrqydsrModel.setName(s1.substring(0, s1.indexOf("经传票传唤")));
                    }
                }
                wsssjlModel.setJjct(ssrqydsrModel);
            }else if(s.contains("撤诉申请")||s.contains("撤回")){
                ssrqydsrModel.setSf("撤诉人");
                for (String s1 : detail) {
                    if (ssrqydsrModel.getDate() == null && s1.contains("年") && s1.contains("日")&&!s1.contains(ay)) {
                        ssrqydsrModel.setDate(s1.substring(s1.indexOf("年")-4, s1.indexOf("日")+1));
                        s1 = s1.substring(0, s1.indexOf("于"));
                    }
                    if(s1.contains("以")&&s1.contains("为由")){
                        s1 = s1.substring(0, s1.indexOf("以"));

                    }
                    if(ssrqydsrModel.getName() ==null&&s1.contains("原告")&&!s1.contains(ay)){
                        ssrqydsrModel.setName(s1.substring(s1.indexOf("原告") + 2));

                        if(ssrqydsrModel.getName().equals("")){
                            ssrqydsrModel.setName("*未写出");
                        }
                    }


                }
                wsssjlModel.setCs(ssrqydsrModel);
            }
        }
        return wsssjlModel;
    }
}
