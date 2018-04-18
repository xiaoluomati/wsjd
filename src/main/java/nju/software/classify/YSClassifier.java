package nju.software.classify;

import nju.software.vo.DocType;
import nju.software.wsjx.facade.impl.WsModelFacadeImpl;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.util.FileUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by away on 2018/3/31.
 */
public class YSClassifier extends BaseClassifier {

    @Override
    public DocType getType(WsModel wsModel, String ah) {
        return getType(DocType.YS_PREFIX, ah, wsModel);
//        this.ssjl = wsModel.getWsssjlSegment();
//        this.cpjg = wsModel.getWscpjgSegment();
//        List<String> ysList = DocType.getTypeList(BaseClassifier.YS_PREFIX);
//        Class<? extends YSClassifier> clz = getClass();
//        for (String ys : ysList) {
//            String methodName = getMethodName(ys);
//            try {
//                Method method = clz.getDeclaredMethod(methodName);
//                Boolean isMatch = ((boolean) method.invoke(this));
//                if (isMatch) {
//                    DocType type = DocType.getType(ys);
//                    if (type != null) {
//                        return type;
//                    }
//                }
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
    }

    // 民事裁定书(驳回追加共同诉讼当事人申请用)
    protected boolean isBHZJ() {
        String BHZJ = "驳回" + CHINESE + "追加" + CHINESE;
        return isMatch(cpjg, BHZJ);
    }

    // 民事裁定书(驳回起诉用)
    protected boolean isBHQS() {
        String BHQS = "驳回" + CHINESE + "的起诉";
        return isMatch(cpjg, BHQS);
    }

    // 民事裁定书(不参加诉讼按撤诉处理用)
    protected boolean isBCJSS() {
        String BCJSS_1 = "无正当理由拒不到庭";
        String BCJSS_2 = "未经法庭许可中途退庭";
        String BCJSS_3 = "按" + CHINESE + "撤诉处理";
        return ssjl.contains(BCJSS_1) || ssjl.contains(BCJSS_2) ||
                isMatch(cpjg, BCJSS_3);
    }

    // 民事裁定书(不准许撤诉用)
    protected boolean isBZXCS() {
        String BZXCS_1 = "不准许" + CHINESE + "撤诉";
        String BZXCS_2 = "不准许" + CHINESE + "撤回起诉";
        return isMatch(cpjg, BZXCS_1) || isMatch(cpjg, BZXCS_2);
    }

    // 民事裁定书(对反诉不予受理用)
    protected boolean isDFSBYSL() {
        String DFSBYSL = CHINESE + "的反诉，本院不予受理";
        return isMatch(cpjg, DFSBYSL);
    }

    // 民事裁定书(对起诉不予受理用)
    protected boolean isDQSBYSL() {
        String DQSBYSL = CHINESE + "的起诉，本院不予受理";
        return isMatch(cpjg, DQSBYSL);
    }

    // 民事裁定书(合并审理用)
    protected boolean isHBSL() {
        String HBSL = CHINESE + "并入" + CHINESE + "审理";
        return isMatch(cpjg, HBSL);
    }

    // 民事裁定书(简易程序转为普通程序用)
    protected boolean isJYZPT() {
        String JYZPT = CHINESE + "转" + CHINESE + "普通程序";
        return isMatch(cpjg, JYZPT) && ssjl.contains("简易程序");
    }

    // 民事裁定书(小额诉讼程序驳回起诉用)
    protected boolean isXESS() {
        String JYZPT_1 = CHINESE + "适用小额诉讼程序" + CHINESE;
        String JYZPT_2 = "驳回" + CHINESE + "的起诉";
        return isMatch(cpjg, JYZPT_1) && isMatch(cpjg, JYZPT_2);
    }

    // 民事裁定书(中止诉讼用)
    protected boolean isZZSS() {
        String ZZSS = CHINESE + "中止诉讼";
        return isMatch(cpjg, ZZSS);
    }

    // 民事裁定书(终结诉讼用)
    protected boolean isZJSS() {
        String ZJSS = CHINESE + "终结诉讼";
        return isMatch(cpjg, ZJSS);
    }

    // 民事裁定书(准许撤回反诉用)
    protected boolean isZXCHFS() {
        String TCFS = CHINESE + "提出反诉";
        String ZXCHFS = "准许" + CHINESE + "撤回反诉";
        return isMatch(ssjl, TCFS) && isMatch(cpjg, ZXCHFS);
    }


    // 民事裁定书(准许撤诉用)
    protected boolean isZZCS() {
        String CSSQ_1 = CHINESE + "撤诉申请";
        String CSSQ_2 = CHINESE + "撤回起诉";
        String ZZCS_1 = "准许" + CHINESE + "撤诉";
        String ZZCS_2 = "准许" + CHINESE + "撤回起诉";
//        System.out.println(ssjl);
//        System.out.println(cpjg);
//        System.out.println((isMatch(ssjl, CSSQ_1) || isMatch(ssjl, CSSQ_2)));
//        System.out.println(isMatch(cpjg, ZZCS_1) || isMatch(cpjg, ZZCS_2));
        return (isMatch(ssjl, CSSQ_1) || isMatch(ssjl, CSSQ_2)) && (isMatch(cpjg, ZZCS_1) || isMatch(cpjg, ZZCS_2));
    }

//    private String getMethodName(String YSName) {
//        int index = YSName.indexOf("_");
//        return "is" + YSName.substring(index+1);
//    }

    private boolean isMatch(String content, String pattern) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        return matcher.find();
    }

//    public static void main(String[] args) throws IOException {
//        File file = new File("file");
//        File[] files = file.listFiles();
//        BaseClassifier classifier = new YSClassifier();
//
//        for(File f:files) {
//            String name = f.getAbsolutePath();
//            byte[] wsnr = FileUtil.getContent(name);
//            InputStream is = new ByteArrayInputStream(wsnr);
//            WsModelFacadeImpl wsModelFacadeImpl = new WsModelFacadeImpl();
//            WsModel wsModel = wsModelFacadeImpl.jxDocument(is, name);
//
//            System.out.println(name);
//            System.out.println(classifier.getType(wsModel).getFileName());
//        }
//    }
}
