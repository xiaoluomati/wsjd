package nju.software.preProcess;

import nju.software.classify.ParseMap;
import nju.software.wsjx.business.PreWsAnalyse;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/10.
 */
public class WsPrePro {

    private String wsContent;

    private String wsName;

    private WswsModel wswsModel;

    private WsFtParse wsFtParse;

    private WsAnalyse wsAnalyse;

    public WsPrePro(String wsContent, String wsName) {
        this.wsContent = wsContent;
        this.wsName = wsName;
        init();
    }

    private void init(){
        PreWsAnalyse preWsAnalyse=new PreWsAnalyse(wsName,  wsContent);
        this.wswsModel=preWsAnalyse.handleWsws();
        this.wsAnalyse = new WsAnalyse(wsName, wsContent);
        this.wsFtParse = new WsFtParse(this.wsAnalyse);
    }

    public WsFtParse getWsFtParse() {
        return wsFtParse;
    }

    public WsAnalyse getWsAnalyse() {
        return wsAnalyse;
    }

    public String getAH(){
        return this.wswsModel.getAh();
    }

    public String getPossibleType(){
        String classnameByAH = getClassnameByAH();
        if(!classnameByAH.equals(ParseMap.NOT_DETERMINED)){
            return classnameByAH;
        }
        String classnameByLaws = getClassnameByLaws();
        if(!classnameByLaws.equals(ParseMap.NOT_DETERMINED)){
            System.out.println("classnameByLaws = " + classnameByLaws);
            return classnameByLaws;
        }
        String classnameByKeyword = getClassnameByKeyword();
        if(!classnameByKeyword.equals(ParseMap.NOT_DETERMINED)){
            System.out.println("classnameByKeyword = " + classnameByKeyword);
            return classnameByKeyword;
        }
        return ParseMap.DEFAULT_PARSECLASS;
    }

    private String getClassnameByAH(){
        final String ah = wswsModel.getAh();
        return PreClassifyAH.getPossibleClass(ah);
    }

    private String getClassnameByKeyword(){
        return PreClassifyKeyword.getPossibleType(wsContent);
    }

    private String getClassnameByLaws(){
        return PreClassifyLaws.getPossibleType(wsFtParse.getFtModelList());
    }
}
