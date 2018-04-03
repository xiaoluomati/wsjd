package nju.software.factory;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.util.ListToString;

/**
 * Created by away on 2018/3/28.
 */
// 同一份文书只存在一篇
public class WsModelFactory {

    private static volatile String name;
    private static volatile WsModel wsModel;
    private WsModelFactory() {}

    public static WsModel getInstance(String content, String filename) {
        // 文书改变或第一次初始化文书时生成
        if (!filename.equals(name) || wsModel == null) {
            synchronized (WsModel.class) {
                if (!filename.equals(name) || wsModel == null) {
//                    WsModelFacadeImpl wsModelFacadeImpl = new WsModelFacadeImpl();
//                    wsModel = wsModelFacadeImpl.jxDocument(is, filename);
                    WsAnalyse wsAnalyse = new WsAnalyse(filename, content);
                    wsModel = fillWsModelSegment(wsAnalyse);
                    name = filename;
                }
            }
        }
        return wsModel;
    }

    public static void setWsModel(WsModel wsModel) {
        WsModelFactory.wsModel = wsModel;
    }

    private static  WsModel  fillWsModelSegment(WsAnalyse wsAnalyse) {
        WsModel wsModel = new WsModel();
        wsModel.setWswsSegment(ListToString.List2String(wsAnalyse.getWs()));
        wsModel.setWssscyrSegment(wsAnalyse.getSscyr());
        wsModel.setWsssjlSegment(wsAnalyse.getSsjl());
        wsModel.setWsajjbqSegment(ListToString.List2String(wsAnalyse.getAjjbqk()));
        wsModel.setWscpfxgcSegment(ListToString.List2String(wsAnalyse.getCpfxgc()));
        wsModel.setWscpjgSegment(ListToString.List2String(wsAnalyse.getCpjg()));
        wsModel.setWswwSegment(ListToString.List2String(wsAnalyse.getWw()));
        wsModel.setWsqw(wsAnalyse.getWsnr());
        wsModel.setWsfl(ListToString.List2String(wsAnalyse.getFl()));
        return wsModel;
    }
}
