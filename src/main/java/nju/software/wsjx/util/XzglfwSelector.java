package nju.software.wsjx.util;

import java.util.HashMap;

/**
 * Created by zhx on 2016/12/5.
 */
public class XzglfwSelector {
    public static String  selectXzglfw(String dwmc) {
        String xzglfw = null;
        HashMap<String, String[]> xzglfwmap = new HashMap();//定义一个HashMap放行政管理范围和行政管理范围对应判别的条件

        //财政行政管理(财政)
        String[] czxzgl ={"财政"};
        xzglfwmap.put("财政行政管理(财政)", czxzgl);
        //草原行政管理(草原)
        String[] cyxzgl = {"草原监理站"};
        xzglfwmap.put("草原行政管理(草原)",cyxzgl);
        //城市规划管理(规划)
        String[] csghgl={"规划"};
        xzglfwmap.put("城市规划管理(规划)",csghgl);
        //城市综合行政管理(城管)
        String[] cszhxz = {"城市管理","市容管理"};
        xzglfwmap.put("城市综合行政管理(城管)",cszhxz);
        //城乡建设行政管理
        String[] cxjsxz = {"城乡建设","建设"};
        xzglfwmap.put("城乡建设行政管理",cxjsxz);
        //畜牧行政管理(畜牧)
        String[] xmxzgl={"农牧","畜牧"};
        xzglfwmap.put("畜牧行政管理(畜牧)",xmxzgl);
        //道路交通管理(道路)
        String[] dljtgl={"交警","交巡警","交通警察","交通巡逻"};
        xzglfwmap.put("道路交通管理(道路)",dljtgl);
        //房屋登记管理(房屋登记)
        String[] fwdjgl={"房产管理","房地产管理","房屋管理","房产"};
        xzglfwmap.put("房屋登记管理(房屋登记)",fwdjgl);
        //地质矿产行政管理(地矿)
        String[] dzkcxz={"地质","矿产"};
        xzglfwmap.put("地质矿产行政管理(地矿)",dzkcxz);
        //房屋拆迁管理(拆迁)
        String[] fwcqgl={"拆迁"};
        xzglfwmap.put("房屋拆迁管理(拆迁)",fwcqgl);
        //工商行政管理(工商)
        String[] gsxzgl={"工商行政","工商"};
        xzglfwmap.put("工商行政管理(工商)",gsxzgl);
        //公安行政管理
        String[] gaxzgl={"公安"};
        xzglfwmap.put("公安行政管理",gaxzgl);
        //公路交通行政管理(公路)
        String[] gljtxz={"汽车管理","客运管理","公共交通","公路管理","公路运输"};
        xzglfwmap.put("公路交通行政管理(公路)",gljtxz);
        //广播电视电影行政管理(广电)
        String[] gbdsdy={"广电","广播","电视","电影"};
        xzglfwmap.put("广播电视电影行政管理(广电)",gbdsdy);
        //国有资产行政管理(国资)
        String[] gyzcgl={"国有资产"};
        xzglfwmap.put("国有资产行政管理(国资)",gyzcgl);
        //海关行政管理(海关)
        String[] hgxzgl={"海关"};
        xzglfwmap.put("海关行政管理(海关)",hgxzgl);
        //环境保护行政管理(环保)
        String[] hjbhxz={"环境保护"};
        xzglfwmap.put("环境保护行政管理(环保)",hjbhxz);
        //计划生育行政管理(计划生育)
        String[] jhsygl={"计划生育"};
        xzglfwmap.put("计划生育行政管理(计划生育)",jhsygl);
        //检疫行政管理(检疫)
        String[] jyxzgl={"检疫"};
        xzglfwmap.put("检疫行政管理(检疫)",jyxzgl);
        //交通运输行政管理(交通)
        String[] ytysgl={"道路运输","交通运输","交通行政"};
        xzglfwmap.put("交通运输行政管理(交通)",ytysgl);
        //教育行政管理(教育)
        String[] eduxzgl={"教育","学校","大学","学院"};
        xzglfwmap.put("教育行政管理(教育)",eduxzgl);
        //金融行政管理(金融)
        String[] jrxzgl={"保险","基金","银行"};
        xzglfwmap.put("金融行政管理(金融)",jrxzgl);
        //经贸行政管理(内贸、外贸)
        String[] jmxzgl={"贸易","商务","经济管理","经济贸易"};
        xzglfwmap.put("经贸行政管理(内贸、外贸)",jmxzgl);
        //劳动和社会保障行政管理(劳动、社会保障)
        String[] ldandsh={"人力资源和社会保障","劳动人事","人事","社会保险","社会保障","就业服务"};
        xzglfwmap.put("劳动和社会保障行政管理(劳动、社会保障)",ldandsh);
        //林业行政管理(林业)
        String[] lyxzgl={"林业"};
        xzglfwmap.put("林业行政管理(林业)",lyxzgl);
        //旅游行政管理(旅游)
        String[] travelrxzgl={"旅游","景区"};
        xzglfwmap.put("旅游行政管理(旅游)",travelrxzgl);
        //民政行政管理(民政)
        String[] mzxzgl={"民政局","移民"};
        xzglfwmap.put("民政行政管理(民政)",mzxzgl);
        //能源行政管理(能源管理)
        String[] nyxzgl={"天然气","石油","煤矿"};
        xzglfwmap.put("能源行政管理(能源管理)",nyxzgl);
        //农业行政管理(农业)
        String[] farmxz={"农业"};
        xzglfwmap.put("农业行政管理(农业)",farmxz);
        //商标行政管理(商标)
        String[] sbxzgl={"商标"};
        xzglfwmap.put("商标行政管理(商标)",sbxzgl);
        //审计行政管理(审计)
        String[] sjxzgl={"审计"};
        xzglfwmap.put("审计行政管理(审计)",sjxzgl);
        //食品药品安全行政管理(食品、药品)
        String[] spypaq={"食品药品"};
        xzglfwmap.put("食品药品安全行政管理(食品、药品)",spypaq);
        //水利行政管理(水利)
        String[] slxzgl={"水利","水库"};
        xzglfwmap.put("水利行政管理(水利)",slxzgl);
        //税务行政管理(税务)
        String[] swxzgl={"税务"};
        xzglfwmap.put("税务行政管理(税务)",swxzgl);
        //司法行政管理(司法行政)
        String[] sfxzgl={"司法"};
        xzglfwmap.put("司法行政管理(司法行政)",sfxzgl);
        //铁路行政管理(铁路)
        String[] tlxzgl={"铁路"};
        xzglfwmap.put("铁路行政管理(铁路)",tlxzgl);
        //统计行政管理(统计)
        String[] tjxzgl={"统计"};
        xzglfwmap.put("统计行政管理(统计)",tjxzgl);
        //土地行政管理(土地)
        String[] tdxzgl={"国土资源","土地管理"};
        xzglfwmap.put("土地行政管理(土地)",tdxzgl);
        //土地行政管理(土地)；房屋登记管理(房屋登记)
        String[] tdfw={"国土资源和房屋"};
        xzglfwmap.put("土地行政管理(土地)；房屋登记管理(房屋登记)",tdfw);
        //卫生行政管理(卫生)
        String[] wsxzgl={"卫生","医疗保险","医疗"};
        xzglfwmap.put("卫生行政管理(卫生)",wsxzgl);
        //文化行政管理(文化)
        String[] whxzgl={"文化"};
        xzglfwmap.put("文化行政管理(文化)",whxzgl);
        //物价行政管理(物价)
        String[] wjxzgl={"物价"};
        xzglfwmap.put("物价行政管理(物价)",wjxzgl);
        //消防管理(消防)
        String[] xfgl={"消防"};
        xzglfwmap.put("消防管理(消防)",xfgl);
        //新闻出版行政管理(新闻、出版)
        String[] xwcbxz={"新闻出版"};
        xzglfwmap.put("新闻出版行政管理(新闻、出版)",xfgl);
        //信息电讯行政管理(信息、电讯)
        String[] xxdxxz={"信息化"};
        xzglfwmap.put("信息电讯行政管理(信息、电讯)",xxdxxz);
        //行政监察(监察)
        String[] xzjc={"行政执法监察","监察"};
        xzglfwmap.put("行政监察(监察)",xzjc);
        //烟草专卖行政管理(烟草专卖)
        String[] yczm={"烟草专卖"};
        xzglfwmap.put("烟草专卖行政管理(烟草专卖)",yczm);
        //盐业行政管理(盐业)
        String[] yyxzgl={"盐务"};
        xzglfwmap.put("盐业行政管理(盐业)",yyxzgl);
        //渔业行政管理(渔业)
        String[] fishxzgl={"渔业","渔政","水产"};
        xzglfwmap.put("渔业行政管理(渔业)",fishxzgl);
        //政府
        String[] zf={"政府"};
        xzglfwmap.put("政府",zf);
        //治安管理(治安)
        String[] zagl={"派出所"};
        xzglfwmap.put("治安管理(治安)",zagl);
        //质量监督检验检疫行政管理
        String[] zljdyj={"监督管理"};
        xzglfwmap.put("质量监督检验检疫行政管理",zljdyj);
        //质量监督行政管理(质量监督)
        String[] zljdxz={"质量技术监督","监督管理"};
        xzglfwmap.put("质量监督行政管理(质量监督)",zljdxz);
        //专利行政管理(专利)
        String[] zlxzgl={"知识产权"};
        xzglfwmap.put("专利行政管理(专利)",zlxzgl);
        //资源行政管理
        String[] zyxzgl={"人力和社会资源","国土资源环境"};
        xzglfwmap.put("资源行政管理",zyxzgl);
        if (dwmc!=null){
            for (String key : xzglfwmap.keySet()) {
                for (String item : xzglfwmap.get(key)) {
                    if (dwmc.contains(item)) {
                        xzglfw = key;
                    }
                }
            }
            //特殊情况
            if (dwmc.contains("公安")) {
                for (String item:dljtgl) {
                    if (dwmc.contains(item)){
                        xzglfw="道路交通管理(道路)";
                    }
                }
                if(dwmc.contains("消防")){
                    xzglfw="消防管理(消防)";
                }
                if(dwmc.contains("派出所")){
                    xzglfw="治安管理(治安)";
                }
            }
            if (dwmc.contains("规划")){
                for(String item:tdxzgl) {
                    if (dwmc.contains(item)){
                        xzglfw="土地行政管理(土地)";
                    }
                }
                if(dwmc.contains("监察")){
                    xzglfw="城市规划管理(规划)";
                }
            }
            if(dwmc.contains("规划")&&dwmc.contains("建设")){
                xzglfw="城市规划管理(规划)";
            }
            if(dwmc.contains("工商行政")&&dwmc.contains("商标")){
                xzglfw="商标行政管理(商标)";
            }
            if (dwmc.contains("保险")&&dwmc.contains("医疗")){
                xzglfw="卫生行政管理(卫生)";
            }
            if (dwmc.contains("卫生")&&dwmc.contains("计划生育")){
                xzglfw="计划生育行政管理(计划生育)";
            }
        }

        return xzglfw;
    }
}
