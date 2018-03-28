package nju.software.factory;

import nju.software.wsjx.facade.impl.WsModelFacadeImpl;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.InputStream;

/**
 * Created by away on 2018/3/28.
 */
// 同一份文书只存在一篇
public class WsModelFactory {

    private static volatile String name;
    private static volatile WsModel wsModel;
    private WsModelFactory() {}

    public static WsModel getInstance(InputStream is, String filename) {
        // 文书改变或第一次初始化文书时生成
        if (!filename.equals(name) || wsModel == null) {
            synchronized (WsModel.class) {
                if (!filename.equals(name) || wsModel == null) {
                    WsModelFacadeImpl wsModelFacadeImpl = new WsModelFacadeImpl();
                    wsModel = wsModelFacadeImpl.jxDocument(is, filename);
                    name = filename;
                }
            }
        }
        return wsModel;
    }
}
