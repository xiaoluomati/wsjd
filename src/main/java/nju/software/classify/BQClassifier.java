package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.io.File;

/**
 * Created by zhuding on 2018/4/17.
 */
public class BQClassifier extends BaseClassifier {


//    public static void main(String[] args) {
//        File file = new File("template/保全和先予执行");
//        File[] files = file.listFiles();
//
//        for(File f:files){
//            System.out.println("BQ_(\"" + f.getName().substring(0,f.getName().indexOf("."))+"\")");
//        }
//    }

    //"),
//    BQ_"),
//    BQ_CDFY("民事裁定书(保全或者先予执行裁定复议用)"),
//    BQ_XYZX("民事裁定书(先予执行用)"),
//    BQ_BGBQ("民事裁定书(变更保全用)"),
//    BQ_ZXQBQ("民事裁定书(执行前保全用)"),
//    BQ_JCBQ("民事裁定书(解除保全用)"),
//    BQ_SQXWBQ("民事裁定书(诉前行为保全用)"),
//    BQ_SQCCBQ("民事裁定书(诉前财产保全用)"),
//    BQ_SSXWBQ("民事裁定书(诉讼行为保全用)"),
//    BQ_SSCCBQ("民事裁定书(诉讼财产保全用)"),
//    BQ_BHSQ("民事裁定书(驳回保全或者先予执行申请用)")
//    /**
//     *
//     * @return
//     */
//    private boolean is(){
//
//    }


    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.BQ_PREFIX, ah, wsModel);
    }


    /**
     * 民事裁定书(仲裁中财产保全用)
     * @return
     */
    private boolean isZCZCCBQ(){
        String p1 = "向"+CHINESE+"仲裁委员会申请财产保全";
        String p2 = "(查封|扣押|冻结)"+CHINESE+"的";
        return matchSsjl(p1) && matchCpjg(p2);
    }

    /**
     * 民事裁定书(依职权诉讼保全用)
     * @return
     */
    private boolean isYZQSSBQ(){
        String p1 = "依职权";
        String p2 = "诉讼保全";
        return matchCpgc(p1) && matchCpgc(p2) || matchCpjg(p1) && matchCpjg(p2);
    }

    /**
     *
     * @return
     */
    private boolean is(){
        return false;
    }

}
