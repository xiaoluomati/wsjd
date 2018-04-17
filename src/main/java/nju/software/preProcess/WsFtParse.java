package nju.software.preProcess;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhuding on 2018/4/15.
 */
public class WsFtParse {

    private WsAnalyse wsAnalyse;

    public WsFtParse(WsAnalyse wsAnalyse) {
        this.wsAnalyse = wsAnalyse;
    }

    public ArrayList<WscpfxgcFtModel> getFtModelList(){
        ArrayList<String> contentlist = WsAnalyse.getWholeContent(wsAnalyse.getCpfxgc()
                .get(wsAnalyse.getCpfxgc().size() - 1));
        int index = 0;
        //获取裁判分析过程段
        for (int i = 0; i < contentlist.size(); i++) {
            if (contentlist.get(i).contains("综上")) {
                index = i;
                break;
            } else if (contentlist.get(i).contains("依照")
                    || contentlist.get(i).contains("依据")
                    || contentlist.get(i).contains("根据")) {
                index = i;
            }
        }
        // 解析法条名称
        String ftString = "";// 法条
        for (int j = index; j < contentlist.size(); j++) {
            ftString += contentlist.get(j);
        }
        String[] ftfz = ftString.split("《");
        //删除数组最后一个里面的规定等字符
        ftfz[ftfz.length-1] = delGd(ftfz[ftfz.length-1]);
        ArrayList<WscpfxgcFtModel> ftModellist = new ArrayList<WscpfxgcFtModel>();
        for (int j = 0; j < ftfz.length; j++) {
            String content = ftfz[j];
            if (content.contains("》")) {
                WscpfxgcFtModel ftModel = new WscpfxgcFtModel();
                String flftmc = content.substring(0, content.indexOf("》"));
                ftModel.setFlftmc(flftmc);
                //获取条目款目
                ftModel.setFtMap(getTmkm(content));
                ftModellist.add(ftModel);
            }
        }
        return ftModellist;
    }


    /**
     * 根据content获取条目款目，返回条目款目的map
     * @param content
     * @return HashMap<String, String>
     */
    private HashMap<String, String> getTmkm(String content){
        content = content.substring(content.indexOf("》") + 1);
        String tmString[] = content.split("条");
        //处理*条*条的字段
        if(content.lastIndexOf("条")==content.length()-1){
            String[] copy=new String[tmString.length+1];
            for(int i=0;i<tmString.length;i++){
                copy[i]=tmString[i];
            }
            copy[copy.length-1]=tmString[tmString.length-1].lastIndexOf("、")==-1?"":tmString[tmString.length-1].substring(tmString[tmString.length-1].lastIndexOf("、"));
            tmString=copy;
        }
        HashMap<String, String> ftMap = new HashMap<String, String>();// 法条map<款目，条目>
        if((tmString.length == 1)){
            if(!tmString[0].contains("、")){
                if(!tmString[0].contains("项") && !tmString[0].contains("款")){
                    //第六十九之规定
                    if(tmString[0].contains("第")){
                        ftMap.put(tmString[0].substring(tmString[0].indexOf("第") + 1,tmString[0].length()), "没有款目");
                    }
                    //六十九条之规定
                    else{
                        ftMap.put(tmString[0], "没有款目");
                    }
                }
                else{
                    //第五十六第四项之规定
                    if(tmString[0].contains("第")){
                        if(tmString[0].indexOf("第") != tmString[0].lastIndexOf("第")){
                            int d1 = tmString[0].indexOf("第");
                            String qtmString = tmString[0].substring(tmString[0].indexOf("第") + 1);
                            int d2 = qtmString.indexOf("第")+tmString[0].indexOf(qtmString);
                            String tm = tmString[0].substring(d1 + 1,d2);
                            String km = tmString[0].substring(d2,tmString[0].length());
                            ftMap.put(tm, km);
                        }
                    }
                }
            }
            else {
                //第六十九、第七十二条之规定
                if(tmString[0].indexOf("第") < tmString[0].indexOf("、")){
                    String temp = tmString[0].substring(0,tmString[0].indexOf("、"));

                    if(!temp.contains("项") && !temp.contains("款")){
                        ftMap.put(tmString[0].substring(
                                tmString[0].indexOf("第") + 1,
                                tmString[0].indexOf("、")), "没有款目");
                    }
                    String temp2 = tmString[0].substring(tmString[0].indexOf("、") + 1);
                    if(!temp2.contains("项") && !temp2.contains("款") && temp2.contains("第")){
                        ftMap.put(temp2.substring(temp2.indexOf("第") + 1), "没有款目");
                    }
                }
            }
        }

        for (int i = 0; i < tmString.length - 1; i++) {
            if (!tmString[i + 1].contains("款")
                    && !tmString[i + 1].contains("项")
                    && tmString[i].contains("第")) {
                ftMap.put(tmString[i].substring(
                        tmString[i].lastIndexOf("第") + 1,
                        tmString[i].length()), "没有款目");
                //第六十九条、第七十之规定
                if((i == tmString.length - 2) && tmString[i + 1].contains("第")){

                    ftMap.put(tmString[i+1].substring(
                            tmString[i+1].lastIndexOf("第") + 1,
                            tmString[i+1].length()), "没有款目");
                }
            } else if (tmString[i].contains("第")) {
                String tm = tmString[i].substring(
                        tmString[i].lastIndexOf("第") + 1,
                        tmString[i].length());

                String km = "";
                if (i < tmString.length - 1
                        && tmString[i + 1].contains("款")) {
                    km = tmString[i + 1].substring(0,
                            tmString[i + 1].lastIndexOf("款") + 1);
                    ftMap.put(tm, km);
                }
                if (i < tmString.length - 1
                        && tmString[i + 1].contains("项")) {
                    km = tmString[i + 1].substring(0,
                            tmString[i + 1].lastIndexOf("项") + 1);
                    if(ftMap.get(tm) != null){
                        if(!ftMap.get(tm).contains(km))
                            ftMap.put(tm, km);
                    }
                    else {
                        ftMap.put(tm, km);
                    }
                }

            }
        }
        return ftMap;
    }
    /**
     * 删除“规定”等字符串
     * @param zh
     * @return
     */
    private String delGd(String zh){
        String[] gdList = {"的规定","之规定","规定"};
        for(String gd:gdList){
            if (zh.contains("》")) {
                String shString = zh.substring(zh.indexOf("》"));
                if(shString.contains(gd)){
                    return zh.substring(0,zh.indexOf(shString) + shString.lastIndexOf(gd));
                }
            }
        }
        return zh;
    }

}
