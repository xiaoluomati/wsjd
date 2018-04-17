package nju.software.factory;

import nju.software.check.facade.GeneralChecker;
import nju.software.classify.ParseMap;
import nju.software.util.JsonParserUtil;
import nju.software.util.XmlParserUtil;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by away on 2018/4/18.
 */
public class CheckerFactory {

    private static final String PACKAGE_PATH = "nju.software.check.facade.";

    private CheckerFactory() {}

    private static GeneralChecker generalChecker;

    public static GeneralChecker getInstance(JsonParserUtil jsonParserUtil, XmlParserUtil xmlParserUtil, WsModel wsModel) {
        if (generalChecker == null) {
            synchronized (GeneralChecker.class) {
                if (generalChecker == null) {
                    String checkerName = ParseMap.getInstance().getCheckerName(wsModel.getWsType());
                    try {
                        Class<?> checkerClass = Class.forName(PACKAGE_PATH + checkerName);
                        Constructor<?> checkerConstructor = checkerClass.getDeclaredConstructor(JsonParserUtil.class, XmlParserUtil.class, WsModel.class);
                        checkerConstructor.setAccessible(true);
                        generalChecker = (GeneralChecker)checkerConstructor.newInstance(jsonParserUtil, xmlParserUtil, wsModel);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return generalChecker;
    }
}
