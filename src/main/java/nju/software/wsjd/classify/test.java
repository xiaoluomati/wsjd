package nju.software.wsjd.classify;

import nju.software.wsjd.model.ysptWsModel.ajjbqk.FsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.SsqqModel;
import nju.software.wsjd.model.ysptWsModel.ajjbqk.ZjdsrModel;

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
        ParseMap parseMap = ParseMap.getInstance();
        Iterable<String> namelist = parseMap.namelist();
        for (String s : namelist) {
            System.out.println(s);
        }

    }

}
