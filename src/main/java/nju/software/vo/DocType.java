package nju.software.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/3/31.
 */
public enum DocType {
    YS_BHQS("民事裁定书(驳回起诉用)"),
    YS_BHZJ("民事裁定书(驳回追加共同诉讼当事人申请用)"),
    YS_BCJSS("民事裁定书(不参加诉讼按撤诉处理用)"),
    YS_BZXCS("民事裁定书(不准许撤诉用)"),
    YS_DFSBYSL("民事裁定书(对反诉不予受理用)"),
    YS_DQSBYSL("民事裁定书(对起诉不予受理用)"),
    YS_HBSL("民事裁定书(合并审理用)"),
    YS_JYZPT("民事裁定书(简易程序转为普通程序用)"),
    YS_XESS("民事裁定书(小额诉讼程序驳回起诉用)"),
    YS_ZZSS("民事裁定书(中止诉讼用)"),
    YS_ZJSS("民事裁定书(终结诉讼用)"),
    YS_ZXCHFS("民事裁定书(准许撤回反诉用)"),
    YS_ZZCS("民事裁定书(准许撤诉用)"),
    ES_BCJESSS("民事裁定书(不参加二审诉讼按撤回上诉处理用)"),
    ES_ESBHQS("民事裁定书(二审驳回起诉用)"),
    ES_ESBZXCS("民事裁定书(二审不准许撤回上诉用)"),
    ES_ESFHCS("民事裁定书(二审发回重审用)"),
    ES_WCBHQSCD("民事裁定书(二审维持驳回起诉裁定用)"),
    ES_WCBYSLCD("民事裁定书(二审维持不予受理裁定用)"),
    ES_ZLLASL("民事裁定书(二审指令立案受理用)"),
    ES_ZLSL("民事裁定书(二审指令审理用)"),
    ES_ZXCHSS("民事裁定书(二审准许撤回上诉用)"),
    ES_ZXHBZX("民事裁定书(二审准许或不准许撤回起诉用)"),
    ES_WSESSLF("民事裁定书(未交二审案件受理费按撤回上诉处理用)")
    ;

    String fileName;

    DocType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public static List<String> getYS() {
        List<String> ys = new ArrayList<>();
        DocType[] values = values();
        for (DocType value : values) {
            String str = value.name();
            if (str.startsWith("YS")) {
                ys.add(str);
            }
        }
        return ys;
    }

    public static List<String> getES() {
        List<String> es = new ArrayList<>();
        DocType[] values = values();
        for (DocType value : values) {
            String str = value.name();
            if (str.startsWith("ES")) {
                es.add(str);
            }
        }
        return es;
    }


    public static DocType getType(String name) {
        for (DocType docType : values()) {
            if (docType.toString().equals(name)) {
                return docType;
            }
        }
        return null;
    }
}
