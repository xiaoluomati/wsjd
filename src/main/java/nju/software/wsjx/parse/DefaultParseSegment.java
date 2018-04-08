package nju.software.wsjx.parse;

import nju.software.wsjx.model.wsSegmentationModel.*;

import java.util.List;

/**
 * Created by zhuding on 2018/3/25.
 */
public  abstract class DefaultParseSegment extends ParseSegment implements ParseflexibleSegment {

    @Override
    public WsModel transformToWsModel() {
        WswsModel wswsModel = jxWswsModel(wsAnalyse.getWs());
        List<WssscyrModel> wssscyrModels = jxWssscyrModel(wsAnalyse);
        WsssjlModel wsssjlModel = jxWsssjlModel(wssscyrModels,wsAnalyse.getSsjl());
        WsajjbqkModel wsajjbqkModel = jxWsajjbqkModel(wsAnalyse,wssscyrModels);
        WscpfxgcModel wscpfxgcModel = jxWscpfxgcModel(wsAnalyse);
        WscpjgModel wscpjgModel = jxWscpjgModel(wsAnalyse,wssscyrModels);
        WswwModel wswwModel = jxWswwModel(wsAnalyse.getWw());
        WsModel wsModel = new WsModel(wswsModel, wssscyrModels, wsajjbqkModel, wsssjlModel, wscpfxgcModel, wscpjgModel, wswwModel);

        return wsModel;
    }
}
