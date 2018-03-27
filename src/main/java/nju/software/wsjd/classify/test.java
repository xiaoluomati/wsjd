package nju.software.wsjd.classify;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuding on 2018/3/20.
 */
public class test {

    private FsqqModel fsqqModel;//反诉请求 第一审普通程序
    private SsqqModel ssqqModel;//诉讼请求 第一审普通程序
    private ZjdsrModel zjdsrModel;//追加当事人段 第一审普通程序

    @Override
    public String toString() {
        return "test{" +
                "fsqqModel=" + fsqqModel +
                ", ssqqModel=" + ssqqModel +
                ", zjdsrModel=" + zjdsrModel +
                '}';
    }

    public static void main(String[] args) {
        String wsssjl = "一、撤销吉林省敦化市人民法院（2017）吉2403民初2405号民事判决；二、本案发回吉林省敦化市人民法院重审。上诉人关明华预交的二审案件受理费100元予以退还。";
//        String re3 = "不准许(.*)撤回上诉|上诉人(.*)预交";
        String ahreg = "[（\\(（〔]\\d{4}[）\\)〕）].*号";
        String re1= "撤销(.*人民法院)("+ahreg+")";
        Pattern p3 = Pattern.compile(re1);
        Matcher m3 = p3.matcher(wsssjl);
        if (m3.find()) {
            System.out.println(m3.group(2));
        }
    }

}
