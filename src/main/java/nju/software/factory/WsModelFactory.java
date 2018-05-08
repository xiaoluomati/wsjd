package nju.software.factory;

import nju.software.classify.BaseClassifier;
import nju.software.classify.ParseMap;
import nju.software.preProcess.WsPrePro;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.parse.ParseSegment;
import nju.software.wsjx.util.ListToString;

import java.util.List;

/**
 * Created by away on 2018/3/28.
 */
// 同一份文书只存在一篇
public class WsModelFactory {

    private static volatile String name;
    private static volatile WsModel wsModel;
    private static final String CLASSIFY_PREFIX = "nju.software.classify.";
    private static final String PARSE_PREFIX = "nju.software.wsjx.parse.";
    private static final String XML_PATH = "xml";
    private WsModelFactory() {}

    public static WsModel getInstance(String content, String filename) {
        // 文书改变或第一次初始化文书时生成
        if (!filename.equals(name) || wsModel == null) {
            synchronized (WsModel.class) {
                if (!filename.equals(name) || wsModel == null) {
                    wsModel = jxWs(content,filename);
                    name = filename;
                }
            }
        }
        return wsModel;
    }

    // wsModel 生成后拿取
    public static WsModel getInstance() {
        List<WssscyrModel> wssscyrModels = wsModel.getWssscyrModels();
        for (WssscyrModel wssscyrModel : wssscyrModels) {
            System.out.println("----------------------");
            System.out.println("wssscyrModel.getSscyr() = " + wssscyrModel.getSscyr());
            System.out.println("wssscyrModel.getXb() = " + wssscyrModel.getXb());
            System.out.println("wssscyrModel.getDsrdz() = " + wssscyrModel.getDsrdz());
            System.out.println("----------------------");
        }
        return wsModel;
    }

    private static WsModel jxWs(String content,String filename){
//        PreWsAnalyse preWsAnalyse=new PreWsAnalyse(filename,  content);
//        WswsModel wswsModel=preWsAnalyse.handleWsws();
//        WsAnalyse wsAnalyse = new WsAnalyse(filename, content);
//        final String ah = wswsModel.getAh();
//        String classifierName = null;
//        for (String s : ParseMap.classifierNameKeys()) {
//            if(ah.contains(s)){
//                classifierName = ParseMap.getInstance().getClassifierName(s);
//            }
//        }
        WsPrePro wsPrePro = new WsPrePro(content, filename);
        String className = wsPrePro.getPossibleType();
        System.out.println("className = " + className);
//        if(!className.equals(ParseMap.NOT_DETERMINED)){
        String string = CLASSIFY_PREFIX + ParseMap.getInstance().getClassifyName(className);
        try {
            System.out.println(string);
            Class<?> classifier = Class.forName(string);
            BaseClassifier baseClassifier = (BaseClassifier) classifier.newInstance();

            String parseRuleName = PARSE_PREFIX+ParseMap.getInstance().getParseName(className);
            Class<?> parseDocumentClass = Class.forName(parseRuleName);
            ParseSegment parseCaseinfo = (ParseSegment) parseDocumentClass.newInstance();
            parseCaseinfo.registerWsAnalyse(wsPrePro.getWsAnalyse());
            wsModel = parseCaseinfo.transformToWsModel();
            fillWsModelSegment(wsModel, wsPrePro.getWsAnalyse());
            wsModel.setWsType(className);
            wsModel.setPreWscpfxgcFtModels(wsPrePro.getWsFtParse().getFtModelList());
            wsModel.setDocType(baseClassifier.getType(wsModel, wsPrePro.getAH()));
            wsModel.transformToXml(XML_PATH,filename.substring(0, filename.indexOf(".")));
            return wsModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
        return null;
    }


    private static  void  fillWsModelSegment(WsModel wsModel,WsAnalyse wsAnalyse) {
        wsModel.setWswsSegment(ListToString.List2String(wsAnalyse.getWs()));
        wsModel.setWssscyrSegment(wsAnalyse.getSscyr());
        wsModel.setWsssjlSegment(wsAnalyse.getSsjl());
        wsModel.setWsajjbqSegment(ListToString.List2String(wsAnalyse.getAjjbqk()));
        wsModel.setWscpfxgcSegment(ListToString.List2String(wsAnalyse.getCpfxgc()));
        wsModel.setWscpjgSegment(ListToString.List2String(wsAnalyse.getCpjg()));
        wsModel.setWswwSegment(ListToString.List2String(wsAnalyse.getWw()));
        wsModel.setWsqw(wsAnalyse.getWsnr());
        wsModel.setWsfl(ListToString.List2String(wsAnalyse.getFl()));
    }

    public static String getName() {
        return name;
    }
}
