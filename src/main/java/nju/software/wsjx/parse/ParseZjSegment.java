package nju.software.wsjx.parse;

import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.*;

import java.util.List;

/**
 * Created by zhuding on 2018/3/27.
 */
public class ParseZjSegment extends DefaultParseSegment {

    @Override
    public WsajjbqkModel jxWsajjbqkModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        return null;
    }

    @Override
    public WscpfxgcModel jxWscpfxgcModel(WsAnalyse wsAnalyse) {
        return null;
    }

    @Override
    public WscpjgModel jxWscpjgModel(WsAnalyse wsAnalyse, List<WssscyrModel> wssscyrModellist) {
        return null;
    }

    @Override
    public List<WssscyrModel> jxWssscyrModel(WsAnalyse wsAnalyse) {
        return null;
    }

    @Override
    public WsssjlModel jxWsssjlModel(List<WssscyrModel> wssscyrModellist, String wsssjl) {
        return null;
    }
}
