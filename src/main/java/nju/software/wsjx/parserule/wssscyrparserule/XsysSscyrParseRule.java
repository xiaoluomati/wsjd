package nju.software.wsjx.parserule.wssscyrparserule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.Enum.MZEnum;
import nju.software.wsjx.model.Enum.WhcdEnum;
import nju.software.wsjx.model.Enum.ZWEnum;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QkqkModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.QzcsModel;
import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.StringUtil;
/**
 * 刑事一审诉讼参与人解析
 * @author wangzh
 *
 */
public class XsysSscyrParseRule extends GeneralSscyrCommonRule implements SscyrParseRule{

    public List<WssscyrModel> jxWssscyrModelList(WsAnalyse wsAnalyse){
    	List<String> sscyr = wsAnalyse.getSscyr();
    	List<String> ajjbqk = wsAnalyse.getAjjbqk();
        String ssjl = wsAnalyse.getSsjl();
        String wsnr = wsAnalyse.getWsnr();
        List<WssscyrModel> wssscyrModellist = new ArrayList<WssscyrModel>();
        String dt = "";
        String wdt = "";
        if (ssjl != null) {
            ArrayList<String> ssjlfd = WsAnalyse.getWholeContent(ssjl);
            for (int i = 0; i < ssjlfd.size(); i++) {
                if (ssjlfd.get(i).contains("未到庭")
                        || ssjlfd.get(i).contains("没有到庭")) {
                    wdt = ssjlfd.get(i);
                    break;
                } else if (ssjlfd.get(i).contains("到庭")) {
                    dt = ssjlfd.get(i);
                    break;
                }
            }
        }
        for (int i = 0; i <sscyr.size() ; i++) {
            WssscyrModel wssscyrModel = new WssscyrModel();
            ArrayList<String> contentlist=WsAnalyse.getWholeContent(sscyr.get(i));
            String sscyrallinfo="";
            for(String sscyrinfo:contentlist){
                sscyrallinfo+=sscyrinfo;
            }
            wssscyrModel.setSscyrallinfo(sscyrallinfo);
            String content=contentlist.get(0);
            //解析诉讼身份、诉讼名称
            String sssf = HeadEnum.getHead(WsAnalyse.deBracket(content));//诉讼身份
            if ((StringUtil.contains(content,"附带民事诉讼原告人")||StringUtil.contains(content,"附带民事诉讼被告人"))&&StringUtil.contains(content,"代理人")){
                if (StringUtil.contains(content,"诉讼代理人")){
                    sssf="诉讼代理人";
                }else if (StringUtil.contains(content,"委托代理人")){
                    sssf="委托代理人";
                }
            }
            if (sssf!=null){
                wssscyrModel.setSssf(sssf);
                content=WsAnalyse.deBracket(content);
                int index = content.indexOf(sssf);
                String ssmc = content.substring(index + sssf.length(),
                        content.length());
                //去除诉讼名称里的冒号
                if(ssmc.contains("：")){
                    ssmc=ssmc.replaceFirst("：", "");
                }
                wssscyrModel.setSscyr(ssmc);
            }
            //解析诉讼地位
            String ssdw=sssf;
            if (ssdw!=null){
                String[] ssdwlist={"公诉机关","自诉人","被告人","附带民事诉讼原告人","自诉人暨民事诉讼原告人","附带民事诉讼被告人"};
                for (int j = 0; j <ssdwlist.length ; j++) {
                    if (ssdw.contains(ssdwlist[j])){
                        wssscyrModel.setSsdw(ssdw);
                    }
                }
            }
            //解析当事人类别
            String dsrlb=null;
            String[] dlr={"辩护人","法定代理人","诉讼代理人","委托代理人","委托辩护人","指定辩护人","负责人","监护人"};
            if(StringUtil.equals(sssf,"公诉机关")){
                dsrlb="公诉方";
            }else if(StringUtil.equals(sssf,"被告人")||StringUtil.equals(sssf,"被告")||StringUtil.equals(sssf,"附带民事诉讼被告人")
                    ||StringUtil.equals(sssf,"被告单位")||StringUtil.equals(sssf,"附带民事诉讼被告")){
                dsrlb="应诉方";
            }else if (StringUtil.equals(sssf,"附带民事诉讼原告人")||StringUtil.equals(sssf,"自诉人")||StringUtil.equals(sssf,"自诉人暨附带民事诉讼原告人")
                    ||StringUtil.equals(sssf,"被害人")||StringUtil.equals(sssf,"被害单位")){
                dsrlb="起诉方";
            }
            if (sssf!=null){
                for(int j=0;j<dlr.length;j++){
                    if(sssf!=null&&sssf.equals(dlr[j])){
                        dsrlb="代理人";
                    }
                }
            }
            wssscyrModel.setDsrlb(dsrlb);

            //解析诉讼人后面的信息
            for (int j = 0; j < contentlist.size(); j++) {
                String zjlx=null;//证件类型
                String zjhm = null;
                // 解析证件类型、证件号
                if (contentlist.get(j).indexOf("身份") != -1) {
                    zjlx="身份证";
                    Pattern pattern = Pattern.compile("\\d{18}|\\d{17}(\\d|X|x)");
                    Matcher match = pattern.matcher(contentlist.get(j));
                    while (match.find()){
                        zjhm=match.group();
                    }
                }else if(contentlist.get(j).indexOf("执业证")!=-1){
                    zjlx="执业证";
                    ArrayList<String> zjxx = (ArrayList<String>) FcUtil
                            .getWholeToken(contentlist.get(j));
                    Pattern pattern = Pattern.compile("\\d");
                    for (int k = 0; k < zjxx.size(); k++) {
                        int count = 0;
                        Matcher match = pattern.matcher(zjxx.get(k));
                        while (match.find())
                            count++;
                        if (count >= 14)
                            zjhm = zjxx.get(k);
                    }
                }else if (contentlist.get(j).indexOf("护照")!=-1){
                    zjlx="护照";
                    Pattern pattern = Pattern.compile("[a-zA-Z0-9]{5,17}");
                    Matcher match = pattern.matcher(contentlist.get(j));
                    while (match.find()){
                        zjhm=match.group();
                    }

                }
                if (zjlx!=null){
                    wssscyrModel.setZjlx(zjlx);
                }
                // 解析地址
                String dsrdz = null;
                if (contentlist.get(j).indexOf("住所地") != -1
                        && contentlist.get(j).indexOf("住所地") < 3) {
                    dsrdz = contentlist.get(j).substring(
                            contentlist.get(j).indexOf("住所地") + 3,
                            contentlist.get(j).length());

                } else if (contentlist.get(j).indexOf("住") != -1
                        && contentlist.get(j).indexOf("住") < 3) {
                    dsrdz = contentlist.get(j).substring(
                            contentlist.get(j).indexOf("住") + 1,
                            contentlist.get(j).length());

                }
                //解析组织机构代码
                String zzjgdm=null;
                String contentinfo=contentlist.get(j);
                int zzindex=contentinfo.indexOf("组织机构代码");
                if(zzindex!=-1){
                    zzjgdm=contentinfo.substring(zzindex, contentinfo.length());
                    String regex="[A-Za-z0-9]{8}-[A-Za-z0-9]{1}|[A-Za-z0-9]{9}|[A-Za-z0-9]{9}|[A-Za-z0-9]{8}－[A-Za-z0-9]{1}";
                    Pattern pattern=Pattern.compile(regex);
                    Matcher matcher=pattern.matcher(zzjgdm);
                    while(matcher.find()){
                        zzjgdm=matcher.group();
                    }
                }
                if(zzjgdm!=null){
                    wssscyrModel.setZzjgdm(zzjgdm);
                }
                // 解析性别
                String xb = null;
                if (contentlist.get(j).indexOf("男") != -1
                        && contentlist.get(j).length() < 4) {
                    xb = "男";
                } else if (contentlist.get(j).indexOf("女") != -1
                        && contentlist.get(j).length() < 4) {
                    xb = "女";
                }
                // 解析出生日期
                String csrq = null;
                int rq = 0;
                if (contentlist.get(j).indexOf("年") != -1)
                    rq++;
                if (contentlist.get(j).indexOf("月") != -1)
                    rq++;
                if (contentlist.get(j).indexOf("日") != -1)
                    rq++;
                if (contentlist.get(j).indexOf("生") != -1)
                    rq++;
                if (rq == 4&&(contentlist.get(j).indexOf("年") - 3)>0) {
//					XX年XX月XX日
                    if(contentlist.get(j).indexOf("年")>3){
                        csrq = contentlist.get(j).substring(
                                contentlist.get(j).indexOf("年") - 4,
                                contentlist.get(j).indexOf("日") + 1);
                    }

                }
                // 解析详细出生日期
                String year = null;
                String month = null;
                String day = null;
                if (csrq != null) {
                    year = csrq.substring(csrq.indexOf("年") - 4,
                            csrq.indexOf("年"));
                    month = csrq.substring(csrq.indexOf("年") + 1,
                            csrq.indexOf("月"));
                    day = csrq.substring(csrq.indexOf("月") + 1,
                            csrq.indexOf("日"));
                }
                // 解析民族
                String mz = null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    mz = MZEnum.getMZ(contentlist.get(j));
                }

                // 解析职务
                String dsrzw = null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    dsrzw = ZWEnum.getZW(contentlist.get(j));
                } else {
                    dsrzw = ZWEnum.getZW(contentlist.get(j));
                }

                // 解析文化程度
                String dsrwhcd = null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    dsrwhcd = WhcdEnum.getWhcd(contentlist.get(j));
                }
                //解析学位
                String dsrxw = null;
                if (dsrwhcd!=null){
                    if (StringUtil.contains(dsrwhcd,"大学")){
                        dsrxw="学士";
                    }else if (StringUtil.contains(dsrwhcd,"硕士")){
                        dsrxw="硕士";
                    }else{
                        dsrxw="其他";
                    }
                }
                if (dsrxw!=null){
                    wssscyrModel.setDsrxw(dsrxw);
                }
                //解析政治面貌
                String zzmm=null;
                if (FcUtil.getWholeToken(contentlist.get(j)).size() < 3) {
                    if (StringUtil.equals(contentlist.get(j),"群众")){
                        zzmm="群众";
                    }else if (StringUtil.equals(contentlist.get(j),"中共党员")){
                        zzmm="中国党员";
                    }

                }
                //解析户籍所在地
                String hjd=null;
                if (StringUtil.contains(contentlist.get(j),"户籍地")){
                    hjd=contentlist.get(j).substring(contentlist.get(j).indexOf("户籍地")+3,contentlist.get(j).length());
                }else if (StringUtil.contains(contentlist.get(j),"户籍所在地为")){
                    hjd=contentlist.get(j).substring(contentlist.get(j).indexOf("户籍所在地为")+6,contentlist.get(j).length());
                }else if (StringUtil.contains(contentlist.get(j),"户籍所在地")) {
                    hjd = contentlist.get(j).substring(contentlist.get(j).indexOf("户籍所在地") + 5, contentlist.get(j).length());
                }else if (StringUtil.contains(contentlist.get(j),"户籍地址：")) {
                    hjd = contentlist.get(j).substring(contentlist.get(j).indexOf("户籍地址：") + 5, contentlist.get(j).length());
                }
                // 将解析过的内容放入model中
                if (dsrdz != null) {
                    //去除地址中的冒号
                    if(dsrdz.contains("：")){
                        dsrdz=dsrdz.replaceFirst("：", "");
                    }
                    wssscyrModel.setDsrdz(dsrdz);
                }
                if (xb != null) {
                    wssscyrModel.setXb(xb);
                }
                if (csrq != null) {
                    wssscyrModel.setCsrq(csrq);
                }
                if (zjhm != null) {
                    wssscyrModel.setZjhm(zjhm);
                }
                if (mz != null) {
                    wssscyrModel.setMz(mz);
                }
                if (dsrzw != null&&(!dsrzw.equals("农民")&&!dsrzw.equals("无业")&&!dsrzw.equals("无固定职业")&&!dsrzw.equals("无职业")&&!dsrzw.equals("务工")&&!dsrzw
                        .equals("个体"))) {
                    wssscyrModel.setDsrzw(dsrzw);
                }
                if (year != null && month != null && day != null) {
                    wssscyrModel.setYear(year);
                    wssscyrModel.setMonth(month);
                    wssscyrModel.setDay(day);

                }
                if (dsrwhcd!=null){
                    wssscyrModel.setDsrwhcd(dsrwhcd);
                }
                if (zzmm!=null){
                    wssscyrModel.setZzmm(zzmm);
                }
                if (hjd!=null){
                    wssscyrModel.setHjd(hjd);
                }
            }
            //解析当事人国籍
            setDsrgj(wssscyrModel);
            //解析自然人身份
            setZrrsf(wssscyrModel,contentlist);
            //解析是否被害人
            if (StringUtil.equals(wssscyrModel.getDsrlb(),"起诉方")||StringUtil.equals(wssscyrModel.getDsrlb(),"应诉方")){
                setIsBhr(wssscyrModel,ajjbqk);
            }
            //解析附带民事诉讼原告人类型
            if (StringUtil.equals(wssscyrModel.getSssf(),"附带民事诉讼原告人")||StringUtil.equals(wssscyrModel.getSssf(),"自诉人暨民事诉讼原告人")||StringUtil.equals(wssscyrModel.getSssf(),"自诉人暨附带民事诉讼原告")){
                setMsssygrlx(wssscyrModel);
            }
            //解析刑事责任能力、缓刑考验期内犯罪、假释考验期内犯罪
            if (StringUtil.equals(wssscyrModel.getDsrlb(),"应诉方")){
                setXszrablity(wssscyrModel,wsnr);
                setHxkyqfz(wssscyrModel,contentlist);
                setJskyqfz(wssscyrModel,contentlist);
            }

            //解析羁押场所
            setJycs(wssscyrModel,contentlist);
            //解析强制措施
            setQzcs(wssscyrModel,sscyr.get(i),wsnr);
            //解析前科情况
            setQkqk(wssscyrModel,sscyr.get(i),wsnr);
            wssscyrModellist.add(wssscyrModel);
        }


        return  wssscyrModellist;
    }

    private void setQkqk(WssscyrModel wssscyrModel, String sscyrinfo,String wsnr) {
        List<QkqkModel> qkqkModelList = new ArrayList<QkqkModel>();
        ArrayList<String> contentlist=new ArrayList<String>();
        ArrayList<String> qkqk = new ArrayList<String>();//前科情况
        String[] jhsplit=sscyrinfo.split("。");
        for (int i = 0; i <jhsplit.length ; i++) {
            String fhcontent=jhsplit[i];
            String[] fhsplit=fhcontent.split("；");
            for(int k=0;k<fhsplit.length;k++){
                if(fhsplit[k].length()>0){
                    contentlist.add(fhsplit[k]);
                }
            }
        }
        if (contentlist.size()>1){
            for (int i = 0; i <contentlist.size() ; i++) {
                if (contentlist.get(i).contains("判处")||contentlist.get(i).contains("行政拘留")||contentlist.get(i).contains("戒毒")||contentlist.get(i).contains("劳动教养")||
                        contentlist.get(i).contains("罚款")||contentlist.get(i).contains("罚金")||contentlist.get(i).contains("管制")||contentlist.get(i).contains("警告")
                        ||contentlist.get(i).contains("暂扣驾驶证")){
                    qkqk.add(contentlist.get(i));
                }
            }
        }
        for (int i = 0; i <qkqk.size() ; i++) {
//            System.out.println(qkqk.get(i));
            QkqkModel qkqkModel = new QkqkModel();
            //解析罪名
            String zm=null;
            Pattern pattern = Pattern.compile("因.+?罪");
            Matcher matcher = pattern.matcher(qkqk.get(i));
            while (matcher.find()){
                zm=matcher.group();
                //System.out.println("原因："+matcher.group());
            }

            //解析判处法院
            String pcfy=null;
            if (StringUtil.contains(qkqk.get(i),"院")&&StringUtil.contains(qkqk.get(i),"被")) {
                if (qkqk.get(i).indexOf("被") < qkqk.get(i).indexOf("院"))
                    pcfy = qkqk.get(i).substring(qkqk.get(i).indexOf("被") + 1, qkqk.get(i).indexOf("院") + 1);
            }
            //解析前科类别
            String[] xzqklblist={"行政拘留","戒毒","劳动教养","罚款"};
            boolean isXzqk=false;
            for (String qklb: xzqklblist) {
                if (qkqk.get(i).contains(qklb)){
                    isXzqk=true;
                }
            }
            if (isXzqk){
                qkqkModel.setQklb("行政前科");
            }else{
                qkqkModel.setQklb("刑事前科");
            }
            //解析处罚时间、刑满释放日期
            List<String> timelist=new ArrayList<String>();
            Pattern patterntime = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日|\\d{4}年\\d{1,2}月|同年\\d{1,2}月\\d{1,2}日");
            Matcher matchertime = patterntime.matcher(qkqk.get(i));
            while (matchertime.find()){
                timelist.add(matchertime.group());
            }
            for (int j=0;j<timelist.size();j++){
                int pcindex=qkqk.get(i).indexOf("判处");
                int xmsfIndex=qkqk.get(i).indexOf("释放");
                int cfTimeIndex=qkqk.get(i).indexOf(timelist.get(j));
                if (pcindex!=-1&&pcindex>=cfTimeIndex){
                    qkqkModel.setCfTime(timelist.get(j));
                }
                for (String xzqklb:xzqklblist){
                    int xzqkindex=qkqk.get(i).indexOf(xzqklb);
                    if (xzqkindex!=-1&&xzqkindex>=cfTimeIndex){
                        qkqkModel.setCfTime(timelist.get(j));
                    }
                }
                if (xmsfIndex!=-1&&cfTimeIndex<=xmsfIndex&&cfTimeIndex>=pcindex){
                    qkqkModel.setXmsfTime(timelist.get(j));
                }
            }
            //解析处罚原因
            List<String> cfReason=new ArrayList<String>();
            Pattern patterncfReason = Pattern.compile("因涉嫌犯.{1,8}？罪所得罪|因.+?罪");
            Matcher matchercfReason = patterncfReason.matcher(qkqk.get(i));
            while (matchercfReason.find()){
                String cfyy=matchercfReason.group();
                cfyy=cfyy.substring(1,cfyy.length());
                cfReason.add(cfyy);
            }
//            if (StringUtil.contains(qzcscontent,"因本案")){
//                qzcsReason.add("本案");
//            }
            String[] cfzmlist={"组织卖淫","吸毒成瘾","运输毒品罪","盗伐林木","妨害公务罪","重大劳动安全事故罪","骗取贷款","扰乱公共场所秩序","犯贩卖毒品罪","非法行医罪","信用卡诈骗罪","容留他人吸毒","抢劫","非法狩猎罪","掩饰隐瞒犯罪所得","放火罪","虚开增值税专用发票犯罪","掩饰犯罪所得罪","贩卖毒品","可能判处管制、拘役","破坏电力设备","寻衅滋事","非法收购、出售珍贵、濒危野生动物制品罪","抢夺罪","盗伐林木罪","滥阀林木罪","销售有毒、有害食品罪","信用卡诈骗","伪证罪","盗窃再次","非法持有枪支","销售假药罪","交通肇事罪","犯赌博罪","贪污罪","掩饰、隐瞒犯罪所得","故意毁坏财物罪","非法持有毒品罪","非法拘禁","抢夺","非法制造注册商标标识嫌疑","非法制造、销售非法制造的注册商标标识嫌疑","犯故意伤害罪","销售非法制造的注册商标标识嫌疑","非法经营罪","组织、领导传销活动","非法制造、出售非法制造的发票罪","危险驾驶犯罪","故意杀人罪","单位行贿罪","犯开设赌场罪","敲诈勒索罪","受贿","以危险方法危害公共安全罪","容留他人吸毒犯罪","非法制造、销售非法制造的注册商标标识罪","贩卖毒品罪","盗窃罪","容留他人吸毒罪","职务侵占罪","掩饰、隐瞒犯罪所得罪","销售伪劣产品","合同诈骗","拒不执行判决、裁定","玩忽职守罪","传播淫秽物品","失火罪","非法吸收公众存款罪","拐卖儿童","滥用职权罪","过失致人死亡罪","破坏公用电信设施","玩忽职守犯罪","非法拘禁罪","生产、销售有毒、有害食品","生产销售有毒有害食品","生产、销售有毒有害食品","犯盗窃罪","赌博","销售伪劣产品罪","开设赌场","盗窃嫌疑","诈骗","寻衅滋事罪","破坏易燃易爆设备","犯危险驾驶罪","殴打他人","重大责任事故罪","开设赌场罪","盗窃","伪造国家机关证件","不报、谎报安全事故罪","买卖国家机关证件","非法占用农用地","非法制造爆炸物罪","生产、销售有毒有害食品罪","买卖国家机关证件罪","伪造、变造、买卖国家机关公文、证件、印章罪","引诱、容留、介绍卖淫罪","抢劫罪","生产、销售不符合安全标准的食品","强迫交易","盗窃、掩饰、隐瞒犯罪所得罪","非法持有毒品","敲诈勒索","滥伐林木","故意杀人","故意伤害嫌疑","交通肇事","单位受贿","故意伤害罪","嫌疑非法经营","交通肇事案","非法采矿罪","贩卖毒品嫌疑","故意伤害","包庇","生产、销售不符合安全标准的食品罪","危险驾驶","赌博罪","容留、介绍卖淫罪","出售非法制造的发票罪","掩饰隐瞒犯罪所得罪","涉嫌盗窃罪","贪污","破坏公用电信设施罪","故意伤害犯罪","虚开增值税专用发票","妨害公务","行贿罪","本案盗窃","受贿嫌疑","危险驾驶罪","非法采伐国家重点保护植物罪","违反规定","销售假药","掩饰、隐瞒犯罪所得、犯罪所得收益罪","抢劫、寻衅滋事","诈骗罪","掩饰、隐瞒犯罪所得犯罪","非法持有枪支罪","滥伐林木罪","职务侵占","生产、销售伪劣产品","犯妨害公务罪","失火","不报安全事故罪","盗窃犯罪","过失致人重伤","故意毁坏财物","教唆他人吸毒罪","侵犯著作权罪","运输毒品","犯非法持有毒品罪","盗窃、掩饰、隐瞒犯罪所得犯罪","生产、销售不符合安全标准的食品犯罪","假冒注册商标罪"};
            for (String cfzm:cfzmlist){
                if (StringUtil.contains(qkqk.get(i),cfzm)&&cfReason.size()==0){
                    cfReason.add(cfzm);
                }
            }
            if (cfReason.size()>0){
                qkqkModel.setCfReason(cfReason);
            }
            for (String cfyyprint:cfReason){
                //System.out.println(cfyyprint);
            }
            //解析处罚单位
            String cfdw=null;
            Pattern patterndw=Pattern.compile("[经被由].{1,13}?分局|[经被由].{1,18}?派出所|[经被由].{1,15}?局|[经被由].{1,18}?院|本院|被公安机关|[经被由].{1,15}?公安处|被指定居所");
            Matcher matcherdw=patterndw.matcher(qkqk.get(i));
            while (matcherdw.find()){
                cfdw=matcherdw.group();
                if(!cfdw.equals("本院"))
                    cfdw=cfdw.substring(1,cfdw.length());
            }
            if (StringUtil.equals(cfdw,"本院")||StringUtil.equals(cfdw,"我院")){
                String ws=wsnr.substring(0,30);
                ws=ws.replaceAll("\n","");
                if (ws.indexOf("法院")!=-1){
                    cfdw=ws.substring(0,ws.indexOf("法院")+2);
                }
            }
            if (cfdw!=null){
                qkqkModel.setCfdw(cfdw);
                //System.out.println("单位："+cfdw);
            }
            //解析处罚形式
            String[] cfxslist={"没收违法所得、没收非法财物","暂扣驾驶证","无期徒刑","行政拘留","拘役","死刑","有期徒刑","强制隔离戒毒","社区戒毒","管制","警告","罚款","劳动教养"};
            String cfxs=null;
            for (String cfxsitem:cfxslist){
                if (StringUtil.contains(qkqk.get(i),cfxsitem)){
                    cfxs=cfxsitem;
                }
                if (cfxs==null&&StringUtil.contains(qkqk.get(i),"罚金")){
                    cfxs="单处附加刑";
                }
            }
            if (cfxs!=null){
                qkqkModel.setCfxs(cfxs);
                //System.out.println(cfxs);
            }

            //解析刑期
            List<String> xq=new ArrayList<String>();
            int pcindex =qkqk.get(i).indexOf("判处");
            pcindex=pcindex==-1?0:pcindex;
            String timeinfo=qkqk.get(i).substring(pcindex,qkqk.get(i).length());
            Pattern  pattern1 = Pattern.compile("([一二三四五六七八九十]{1,2}个?月)|([一二三四五六七八九十]{1,2}年[一二三四五六七八九十]{1,2}个?月)|([一二三四五六七八九十]{1,2}年)|[一二三四五六七八九十]{1,2}日");
            Matcher matcher1 = pattern1.matcher(timeinfo);
            while (matcher1.find()){
                xq.add(matcher1.group());
                //System.out.println(matcher1.group());
            }
            if (xq.size()==2){
                if (StringUtil.contains(timeinfo,"缓刑")&&StringUtil.contains(timeinfo,"有期徒刑")){
                    xq.set(0,"有期徒刑刑期"+xq.get(0));
                    xq.set(1,"缓刑"+xq.get(1));
                }else if (StringUtil.contains(timeinfo,"剥夺政治权利")&&StringUtil.contains(timeinfo,"有期徒刑")){
                    xq.set(0,"有期徒刑刑期"+xq.get(0));
                    xq.set(1,"剥夺政治权利"+xq.get(1));
                }
            }
            if (xq.size()>0){
                qkqkModel.setCfxq(xq);
            }
            qkqkModelList.add(qkqkModel);
        }
        wssscyrModel.setQkqkList(qkqkModelList);
    }

    private void setQzcs(WssscyrModel wssscyrModel, String sscyrinfo,String wsnr) {
        List<QzcsModel> qzcsModellist = new ArrayList<QzcsModel>();
        String[] qzcslxlist={"逮捕","监视居住","取保候审","取保侯审","拘传","限制出境","拘留","抓获","羁押","捉获","批捕","释放"};
        ArrayList<String> contentlist=new ArrayList<String>();
        String[] jhsplit=sscyrinfo.split("。");
        for (int i = 0; i <jhsplit.length ; i++) {
            String fhcontent=jhsplit[i];
            String[] fhsplit=fhcontent.split("；");
            for(int k=0;k<fhsplit.length;k++){
                if(fhsplit[k].length()>0){
                    contentlist.add(fhsplit[k]);
                }
            }
        }
        ArrayList<String> qzcscontents=new ArrayList<String>();
        if (contentlist.size()>1){
            for (int i = 0; i <contentlist.size() ; i++) {
                //获得每句话中有关于强制措施词的位置
                Set<Integer> qzcsindexSet=new TreeSet<>();
                for (int j = 0; j <qzcslxlist.length ; j++) {
                    if (contentlist.get(i).indexOf(qzcslxlist[j])!=-1){
                        int temp=0;
                        while(true){
                            int index=contentlist.get(i).indexOf(qzcslxlist[j],temp);
                            if (index==-1){
                                break;
                            }
                            qzcsindexSet.add(index+qzcslxlist[j].length());
                            temp=index+qzcslxlist[j].length();
                        }
                    }
                }
                Integer[] qzcsindexs=qzcsindexSet.toArray(new Integer[0]);
                if (qzcsindexs.length>=1){
                    qzcscontents.add(contentlist.get(i).substring(0,qzcsindexs[0]));
                    for (int j=0; j<qzcsindexSet.size()-1;j++){
                        qzcscontents.add(contentlist.get(i).substring(qzcsindexs[j],qzcsindexs[j+1]));
                    }
                }
            }
            for (int i = 0; i < qzcscontents.size(); i++) {
                if (StringUtil.contains(qzcscontents.get(i),"现羁押")||StringUtil.contains(qzcscontents.get(i),"判处")||StringUtil.contains(qzcscontents.get(i),"刑满释放")||StringUtil.contains(qzcscontents.get(i),"行政拘留")){
                    qzcscontents.remove(i);
                }
            }
            String qzcsTimeCompletion = null;
            String year = null;
            String month = null;
            String day = null;
            for (String qzcscontent:qzcscontents) {
                //System.out.println(qzcscontent);
                QzcsModel qzcsModel = new QzcsModel();
                //解析强制措施种类
                String qzcsCategory=null;
                for (String qzcxlx:qzcslxlist){
                    if (StringUtil.contains(qzcscontent,qzcxlx)){
                        qzcsCategory=qzcxlx;
                    }
                }
                if (StringUtil.equals(qzcsCategory,"抓获")||StringUtil.equals(qzcsCategory,"捉获")){
                    qzcsCategory="羁押";
                }
                if (StringUtil.contains(qzcscontent,"不批准逮捕")){
                    qzcsCategory="不批准逮捕";
                }
                if (StringUtil.contains(qzcscontent,"解除取保候审")){
                    qzcsCategory="解除";
                }
                if (StringUtil.contains(qzcscontent,"批捕")){
                    qzcsCategory="逮捕";
                }
                if (StringUtil.contains(qzcscontent,"行政拘留")){
                    qzcsCategory="羁押";
                }
                if (StringUtil.contains(qzcscontent,"释放")){
                    qzcsCategory="解除";
                }
                if (StringUtil.contains(qzcscontent,"取保侯审")){
                    qzcsCategory="取保候审";
                }
                //System.out.println("类型："+qzcsCategory);
                qzcsModel.setQzcsCategory(qzcsCategory);
                //解析强制措施执行时间
                String qzcsTime=null;
                ArrayList<String> qzcsTimelist = new ArrayList<String>();
                Pattern patterntime = Pattern.compile("\\d{4}同年\\d{1,2}月\\d{1,2}日|\\d{4}年\\d{1,2}月\\d{1,2}日|同年\\d{1,2}月\\d{1,2}日|同月\\d{1,2}日|\\d{1,2}月\\d{1,2}日|\\d{4}年\\d{1,2}月\\d{1,2}");
                Matcher matchertime = patterntime.matcher(qzcscontent);
                while (matchertime.find()){
                    qzcsTimelist.add(matchertime.group());
                    //System.out.println(matchertime.group());
                }
                //区分每条强制措施只存在一个日期，和存在多个日期的情况
                if (qzcsTimelist.size()==1){
                    qzcsTime=qzcsTimelist.get(0);
                    if (!StringUtil.contains(qzcsTime,"日")){
                        qzcsTime=qzcsTime+"日";
                    }
                    if (Pattern.compile("\\d{4}同年\\d{1,2}月\\d{1,2}日").matcher(qzcsTime).find()){
                        qzcsTime=qzcsTime.substring(0,4)+"年"+qzcsTime.substring(6,qzcsTime.length());
                    }
                    //判断是否是完整日期、并取出年月日
                    if (qzcsTime!=null){
                        Pattern patterntimecomple = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
                        Matcher matchertimecomple = patterntimecomple.matcher(qzcsTime);
                        if (matchertimecomple.find()){
                            qzcsTimeCompletion = qzcsTime;
                            year=qzcsTimeCompletion.substring(0,4);
                            month=qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("年")+1,qzcsTimeCompletion.indexOf("月"));
                            day = qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("月")+1,qzcsTimeCompletion.indexOf("日"));
                        }
                    }
                    //判断同年的情况
                    if (StringUtil.contains(qzcsTime,"同年")){
                        if (year!=null)
                            qzcsTime=year+qzcsTime.substring(1,qzcsTime.length());
                        if (StringUtil.contains(qzcsTime,"月")&&StringUtil.contains(qzcsTime,"日")){
                            month = qzcsTime.substring(qzcsTime.indexOf("年")+1,qzcsTime.indexOf("月"));
                            day = qzcsTime.substring(qzcsTime.indexOf("月")+1,qzcsTime.indexOf("日"));
                        }
                    }
                    //判断同月的情况
                    if (StringUtil.contains(qzcsTime,"同月")){
                        if (year!=null&&month!=null)
                            qzcsTime=year+"年"+month+"月"+qzcsTime.substring(qzcsTime.indexOf("月")+1,qzcsTime.length());
                        day=qzcsTime.substring(qzcsTime.indexOf("月")+1,qzcsTime.indexOf("日"));
                    }
                    //判断只出现月和日的情况
                    if (!StringUtil.contains(qzcsTime,"年")&&StringUtil.contains(qzcsTime,"月")&&StringUtil.contains(qzcsTime,"日")){
                        if (year!=null)
                            qzcsTime=year+"年"+qzcsTime;
                    }
                    //出现次日
                    if (qzcsTime!=null&&qzcscontent.contains("次日")){
                        int today = Integer.parseInt(qzcsTime.substring(qzcsTime.indexOf("月")+1,qzcsTime.indexOf("日")));
                        //需要考虑日期进位问题
                        if (day!=null&&month!=null&&year!=null){
                            int tomorrow1 = today+1;
                            int[] bigMonths = {1,3,5,7,8,10,12};
                            int[] smallMonths = {4,6,9,11};
                            for (int bigMonth:bigMonths){
                                if (Integer.parseInt(month)==bigMonth&&tomorrow1>31){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow1=1;
                                    if (Integer.parseInt(month)>12){
                                        year=(Integer.parseInt(year)+1)+"";
                                        month="1";
                                        tomorrow1=1;
                                    }
                                }
                            }
                            for (int smallMonth:smallMonths){
                                if (Integer.parseInt(month)==smallMonth&&tomorrow1>30){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow1=1;
                                }
                            }
                            if (Integer.parseInt(month)==2){
                                if (Integer.parseInt(year)%4==0&&Integer.parseInt(year)%100!=0||
                                        Integer.parseInt(year)%400==0){
                                    if (tomorrow1>29){
                                        month=3+"";
                                        tomorrow1=1;
                                    }
                                }else {
                                    if (tomorrow1>28){
                                        month=3+"";
                                        tomorrow1=1;
                                    }
                                }
                            }
                            qzcsTime = year+"年"+month+"月"+tomorrow1+"日";
                        }
                    }

                }else if (qzcsTimelist.size()<1){
                    if (qzcsTime==null&&qzcscontent.contains("次日")){
                        if (day!=null&&month!=null&&year!=null) {
                            int tomorrow2 = Integer.parseInt(day) + 1;
                            int[] bigMonths = {1,3,5,7,8,10,12};
                            int[] smallMonths = {4,6,9,11};
                            for (int bigMonth:bigMonths){
                                if (Integer.parseInt(month)==bigMonth&&tomorrow2>31){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow2=1;
                                    if (Integer.parseInt(month)>12){
                                        year=(Integer.parseInt(year)+1)+"";
                                        month="1";
                                        tomorrow2=1;
                                    }
                                }
                            }
                            for (int smallMonth:smallMonths){
                                if (Integer.parseInt(month)==smallMonth&&tomorrow2>30){
                                    month=(Integer.parseInt(month)+1)+"";
                                    tomorrow2=1;
                                }
                            }
                            if (Integer.parseInt(month)==2){
                                if (Integer.parseInt(year)%4==0&&Integer.parseInt(year)%100!=0||
                                        Integer.parseInt(year)%400==0){
                                    if (tomorrow2>29){
                                        month=3+"";
                                        tomorrow2=1;
                                    }
                                }else {
                                    if (tomorrow2>28){
                                        month=3+"";
                                        tomorrow2=1;
                                    }
                                }
                            }
                            qzcsTime = year + "年" + month + "月" + tomorrow2 + "日";
                        }
                    }
                }else if (qzcsTimelist.size()>1){
                    for (int i = 0; i < qzcsTimelist.size(); i++) {
                        //判断是否是完整日期、并取出年月日
                        if (qzcsTimelist.get(i)!=null){
                            Pattern patterntimecomple = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
                            Matcher matchertimecomple = patterntimecomple.matcher(qzcsTimelist.get(i));

                            if (matchertimecomple.find()){
                                qzcsTimeCompletion = qzcsTimelist.get(i);
                                year=qzcsTimeCompletion.substring(0,4);
                                month=qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("年")+1,qzcsTimeCompletion.indexOf("月"));
                                day = qzcsTimeCompletion.substring(qzcsTimeCompletion.indexOf("月")+1,qzcsTimeCompletion.indexOf("日"));
                            }

                        }
                        //判断同年的情况
                        if (StringUtil.contains(qzcsTimelist.get(i),"同年")){
                            qzcsTime=year+qzcsTimelist.get(i).substring(1,qzcsTimelist.get(i).length());
                        }
                        //判断同月的情况
                        if (StringUtil.contains(qzcsTimelist.get(i),"同月")){
                            qzcsTime=year+"年"+month+"月"+qzcsTimelist.get(i).substring(qzcsTimelist.get(i).indexOf("月")+1,qzcsTimelist.get(i).length());
                        }

                    }
                    if (qzcsTime==null){
                        qzcsTime=qzcsTimelist.get(qzcsTimelist.size()-1);
                    }
                }

                if (qzcsTime!=null){
                    qzcsModel.setQzcsTime(qzcsTime);
                    //System.out.println("强制措施日期："+qzcsTime);
                }
                //解析强制措施单位
                String qzcsdw=null;
                Pattern patterndw=Pattern.compile("[经被由].{1,13}?分局|[经被由].{1,18}?派出所|[经被由].{1,15}?局|[经被由].{1,18}?院|本院|被公安机关|[经被由].{1,15}?公安处|被指定居所");
                Matcher matcherdw=patterndw.matcher(qzcscontent);
                while (matcherdw.find()){
                    qzcsdw=matcherdw.group();
                    if(!qzcsdw.equals("本院"))
                        qzcsdw=qzcsdw.substring(1,qzcsdw.length());
                }
                if (StringUtil.equals(qzcsdw,"本院")||StringUtil.equals(qzcsdw,"我院")){
                    String ws=wsnr.substring(0,30);
                    ws=ws.replaceAll("\n","");
                    if (ws.indexOf("法院")!=-1){
                        qzcsdw=ws.substring(0,ws.indexOf("法院")+2);
                    }
                }
                if (qzcsdw!=null){
                    qzcsModel.setQzcsDw(qzcsdw);
                    //System.out.println("单位："+qzcsdw);
                }
                //解析强制措施原因
                List<String> qzcsReason=new ArrayList<String>();
                Pattern pattern = Pattern.compile("因涉嫌犯.{1,8}？罪所得罪|因.+?罪");
                Matcher matcher = pattern.matcher(qzcscontent);
                while (matcher.find()){
                    String qzcsyy=matcher.group();
                    qzcsyy=qzcsyy.substring(1,qzcsyy.length());
                    qzcsReason.add(qzcsyy);
                    //System.out.println("原因："+qzcsyy);
                }
                if (StringUtil.contains(qzcscontent,"因本案")){
                    qzcsReason.add("本案");
                }
                String[] qzcszmlist={"组织卖淫","运输毒品罪","盗伐林木","妨害公务罪","重大劳动安全事故罪","骗取贷款","扰乱公共场所秩序","犯贩卖毒品罪","非法行医罪","信用卡诈骗罪","容留他人吸毒","抢劫","非法狩猎罪","掩饰隐瞒犯罪所得","放火罪","虚开增值税专用发票犯罪","掩饰犯罪所得罪","贩卖毒品","可能判处管制、拘役","破坏电力设备","寻衅滋事","非法收购、出售珍贵、濒危野生动物制品罪","抢夺罪","盗伐林木罪","滥阀林木罪","销售有毒、有害食品罪","信用卡诈骗","伪证罪","盗窃再次","非法持有枪支","销售假药罪","交通肇事罪","犯赌博罪","贪污罪","掩饰、隐瞒犯罪所得","故意毁坏财物罪","非法持有毒品罪","非法拘禁","抢夺","非法制造注册商标标识嫌疑","非法制造、销售非法制造的注册商标标识嫌疑","犯故意伤害罪","销售非法制造的注册商标标识嫌疑","非法经营罪","组织、领导传销活动","非法制造、出售非法制造的发票罪","危险驾驶犯罪","故意杀人罪","单位行贿罪","犯开设赌场罪","敲诈勒索罪","受贿","以危险方法危害公共安全罪","容留他人吸毒犯罪","非法制造、销售非法制造的注册商标标识罪","贩卖毒品罪","盗窃罪","容留他人吸毒罪","职务侵占罪","掩饰、隐瞒犯罪所得罪","销售伪劣产品","合同诈骗","拒不执行判决、裁定","玩忽职守罪","传播淫秽物品","失火罪","非法吸收公众存款罪","拐卖儿童","滥用职权罪","过失致人死亡罪","破坏公用电信设施","玩忽职守犯罪","非法拘禁罪","生产、销售有毒、有害食品","生产销售有毒有害食品","生产、销售有毒有害食品","犯盗窃罪","赌博","销售伪劣产品罪","开设赌场","盗窃嫌疑","诈骗","寻衅滋事罪","破坏易燃易爆设备","犯危险驾驶罪","殴打他人","重大责任事故罪","开设赌场罪","盗窃","伪造国家机关证件","不报、谎报安全事故罪","买卖国家机关证件","非法占用农用地","非法制造爆炸物罪","生产、销售有毒有害食品罪","买卖国家机关证件罪","伪造、变造、买卖国家机关公文、证件、印章罪","引诱、容留、介绍卖淫罪","抢劫罪","生产、销售不符合安全标准的食品","强迫交易","盗窃、掩饰、隐瞒犯罪所得罪","非法持有毒品","敲诈勒索","滥伐林木","故意杀人","故意伤害嫌疑","交通肇事","单位受贿","故意伤害罪","嫌疑非法经营","交通肇事案","非法采矿罪","贩卖毒品嫌疑","故意伤害","包庇","生产、销售不符合安全标准的食品罪","危险驾驶","赌博罪","容留、介绍卖淫罪","出售非法制造的发票罪","掩饰隐瞒犯罪所得罪","涉嫌盗窃罪","贪污","破坏公用电信设施罪","故意伤害犯罪","虚开增值税专用发票","妨害公务","行贿罪","本案盗窃","受贿嫌疑","危险驾驶罪","非法采伐国家重点保护植物罪","违反规定","销售假药","掩饰、隐瞒犯罪所得、犯罪所得收益罪","抢劫、寻衅滋事","诈骗罪","掩饰、隐瞒犯罪所得犯罪","非法持有枪支罪","滥伐林木罪","职务侵占","生产、销售伪劣产品","犯妨害公务罪","失火","不报安全事故罪","盗窃犯罪","过失致人重伤","故意毁坏财物","教唆他人吸毒罪","侵犯著作权罪","运输毒品","犯非法持有毒品罪","盗窃、掩饰、隐瞒犯罪所得犯罪","生产、销售不符合安全标准的食品犯罪","假冒注册商标罪"};
                for (String qzcszm:qzcszmlist){
                    if (StringUtil.contains(qzcscontent,qzcszm)&&qzcsReason.size()==0){
                        qzcsReason.add(qzcszm);
                    }
                }
                if (qzcsReason.size()>0){
                    qzcsModel.setQscsReason(qzcsReason);
                }
                //解析是否逮捕和逮捕日期
                if (StringUtil.equals(qzcsCategory,"逮捕")){
                    qzcsModel.setIsDB("是");
                    if (qzcsModel.getQzcsTime()!=null){
                        qzcsModel.setDBTime(qzcsModel.getQzcsTime());
                    }
                }else{
                    qzcsModel.setIsDB("否");
                }
                qzcsModellist.add(qzcsModel);
            }
        }



        wssscyrModel.setQzcsList(qzcsModellist);
    }

    private void setJycs(WssscyrModel wssscyrModel, ArrayList<String> contentlist) {
        String jycs=null;//羁押场所
        for (int i = 0; i <contentlist.size() ; i++) {
            String[] yjcswords={"现被羁押于","现羁押于","现羁押在","现押于","现羁押","现押"};
            for (String yjcsword:yjcswords){
                if (contentlist.get(i).indexOf(yjcsword)!=-1){
                    jycs=contentlist.get(i).substring(contentlist.get(i).indexOf(yjcsword)+yjcsword.length(),contentlist.get(i).length());
                    break;
                }
            }
            if (contentlist.get(i).indexOf("现在")!=-1&&contentlist.get(i).indexOf("服刑")!=-1){
                jycs=contentlist.get(i).substring(contentlist.get(i).indexOf("现在")+2,contentlist.get(i).indexOf("服刑"));
            }
        }
        if (jycs!=null){
            jycs=WsAnalyse.deBracket(jycs);
            wssscyrModel.setXjycs(jycs);
        }
    }

    private void setJskyqfz(WssscyrModel wssscyrModel, ArrayList<String> contentlist) {
        String jskyqfz="否";
        for (int i = 0; i <contentlist.size() ; i++) {
            if (contentlist.get(i).contains("假释")){
                jskyqfz="是";
            }
        }
        wssscyrModel.setJskyqfz(jskyqfz);
    }

    private void setHxkyqfz(WssscyrModel wssscyrModel,ArrayList<String> contentlist) {
        String hxkyqfz="否";
        for (int i = 0; i <contentlist.size() ; i++) {
            if (contentlist.get(i).contains("缓刑")||contentlist.get(i).contains("缓期")&&contentlist.get(i).contains("执行")){
                hxkyqfz="是";
            }
        }
        wssscyrModel.setHxkyqfz(hxkyqfz);
    }

    private void setXszrablity(WssscyrModel wssscyrModel, String wsnr) {
        String xszrablity="完全刑事责任能力";
        String name=wssscyrModel.getSscyr();
        String[] xzxszr={"限制刑事责任能力","限定刑事责任能力","部分刑事责任能力","限定责任能力","限定（部分）刑事责任能力"};
        ArrayList<String> wsnrlist = new ArrayList<String>();
        String xszrcontent;
        wsnrlist=WsAnalyse.getWholeContent(wsnr);
        for (int i = 0; i <wsnrlist.size() ; i++) {
            for (String xzxszritem:xzxszr) {
                if (StringUtil.contains(wsnrlist.get(i),xzxszritem)){
                    xszrcontent=wsnrlist.get(i-4)+wsnrlist.get(i-3)+wsnrlist.get(i-2)+wsnrlist.get(i-1)+wsnrlist.get(i);
                    if (StringUtil.contains(xszrcontent,name)){
                        xszrablity="限制刑事责任能力";
                    }
                }
            }
        }
//        if (StringUtil.contains(wsnr,"无刑事责任能力")&&!StringUtil.contains(wsnr,"完全刑事责任能力")&&!StringUtil.equals(wsnr,"限定刑事责任能力")){
//            xszrablity="无刑事责任能力";
//        }
        wssscyrModel.setXszrablity(xszrablity);

    }

    private void setMsssygrlx(WssscyrModel wssscyrModel) {
        String msssygrlx=null;
        String allinfo=wssscyrModel.getSscyrallinfo();
        String[] namelist={"父","母","子","女","兄","弟","妻","夫","继"};
        if (StringUtil.equals(wssscyrModel.getSssf(),"自诉人暨民事诉讼原告人")){
            msssygrlx="自诉人";
        }else if (StringUtil.contains(allinfo,"系")||StringUtil.contains(allinfo,"之")){
            for (String name:namelist){
                if (StringUtil.contains(allinfo,name)){
                    msssygrlx="其他";
                }
            }
            if (msssygrlx==null){
                msssygrlx="公诉案件被害人";
            }
        }else {
            msssygrlx="公诉案件被害人";
        }
        if (msssygrlx!=null){
            wssscyrModel.setMsssygrlx(msssygrlx);
        }
    }

    private void setIsBhr(WssscyrModel wssscyrModel,List<String> ajjbqk) {
        String sssf=wssscyrModel.getSssf();//诉讼身份
        String name=wssscyrModel.getSscyr();//诉讼名称
        String allinfo=wssscyrModel.getSscyrallinfo();
        if (StringUtil.contains(sssf,"被害人")||StringUtil.equals(sssf,"附带民事诉讼原告人")||StringUtil.equals(sssf,"自诉人")){
            wssscyrModel.setIsBhr("是");
        }else if (StringUtil.contains(allinfo,"系本案被害人")||StringUtil.contains(allinfo,"系被害人")){
            wssscyrModel.setIsBhr("是");
        }
        if (ajjbqk!=null){
            for (String ajjbqkitem:ajjbqk){
                int bhrindex=ajjbqkitem.indexOf("被害人");
                int nameindex=ajjbqkitem.indexOf(name);
                if (bhrindex!=-1&&nameindex!=-1&&(nameindex-bhrindex)>0&&(nameindex-bhrindex)<4){
                    wssscyrModel.setIsBhr("是");
                }
            }
        }
        if (wssscyrModel.getIsBhr()==null) {
            wssscyrModel.setIsBhr("否");
        }
    }

    private void setZrrsf(WssscyrModel wssscyrModel,ArrayList<String> contentlist) {
        String zrrsf=null;
        String dsrzw=null;
        for (int i = 0; i <contentlist.size() ; i++) {
            if (FcUtil.getWholeToken(contentlist.get(i)).size() < 3) {
                dsrzw = ZWEnum.getZW(contentlist.get(i));
            } else {
                dsrzw = ZWEnum.getZW(contentlist.get(i));
            }
            if (dsrzw!=null){
                break;
            }
        }
        if(StringUtil.equals(dsrzw,"无业")||StringUtil.equals(dsrzw,"无固定职业")||StringUtil.equals(dsrzw,"无职业")){
            zrrsf="无业人员";
        }else if (StringUtil.equals(dsrzw,"农民")){
            zrrsf="农民";
        }else if (StringUtil.contains(dsrzw,"律师")){
            zrrsf="职员";
        }else if (StringUtil.equals(dsrzw,"务工")){
            zrrsf="务工人员";

        } else if (StringUtil.equals(dsrzw,"个体")){
            zrrsf="私营企业主、个体劳动者";

        } else if (wssscyrModel.getSscyr()!=null&&wssscyrModel.getSscyr().length()<5){
            zrrsf = "其他";
        }
        if (zrrsf!=null){
            wssscyrModel.setZrrsf(zrrsf);
        }
    }

}
