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
        String wsssjl = "不服内蒙古自治区高级人民法院（2012）民初字第38号民事判决";
        String reg = "[（\\(（〔]\\d{4}[）\\)〕）].+?[^鉴]字第?\\d+-?\\d+号";
        String re = "不服(.*人民法院)(" + reg + ")";
        Pattern p1 = Pattern.compile(re);
        Matcher m1 = p1.matcher(wsssjl);
        while (m1.find()) {
            System.out.println(m1.group(1));
            System.out.println(m1.group(2));
        }

    }

}
