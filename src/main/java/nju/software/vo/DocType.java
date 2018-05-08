package nju.software.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/3/31.
 */
public enum DocType {
    YS_YSPT("民事判决书(第一审普通程序用)"),
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
    ES_ESGP("民事判决书(二审改判用)"),
    ES_BFGP("民事判决书(部分改判用)"),
    ES_WCYP("民事判决书(驳回上诉，维持原判用)"),
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
    ES_WSESSLF("民事裁定书(未交二审案件受理费按撤回上诉处理用)"),
    GX_BFGXCDSS("民事裁定书(不服管辖裁定上诉案件用)"),
    GX_GXQYY("民事裁定书(管辖权异议用)"),
    GX_SJYJXJ("民事裁定书(上级法院移交下级法院审理用)"),
    GX_SBQZDGX("民事裁定书(受移送人民法院报请指定管辖案件用)"),
    GX_XSSSGXYY("民事裁定书(小额诉讼程序管辖权异议用)"),
    GX_BQTJGX("民事裁定书(依报请提级管辖用)"),
    GX_ZQTJGX("民事裁定书(依职权提级管辖用)"),
    GX_ZQYSGX("民事裁定书(依职权移送管辖用)"),
    GX_BQZDGX("民事裁定书(因管辖权争议报请指定管辖案件用)"),
    GX_YGXQBQZD("民事裁定书(有管辖权人民法院报请指定管辖案件用)"),
    SSCJR_BGDSRY("民事裁定书(变更当事人用)"),
    SSCJR_WCJDJ("民事裁定书(未参加登记的权利人适用生效判决或裁定用)"),
    ZJ_ZCQZJBQ("民事裁定书(仲裁前证据保全用)"),
    ZJ_SQSZ("民事裁定书(申请书证提出命令用)"),
    ZJ_SQFHJDF("民事裁定书(申请返还鉴定费用用)"),
    ZJ_JCZJBQ("民事裁定书(解除证据保全用)"),
    ZJ_SQZJBQ("民事裁定书(诉前证据保全用)"),
    ZJ_SSZJBQ("民事裁定书(诉讼证据保全用)"),
    BQ_ZCZCCBQ("民事裁定书(仲裁中财产保全用)"),
    BQ_YZQSSBQ("民事裁定书(依职权诉讼保全用)"),
    BQ_CDFY("民事裁定书(保全或者先予执行裁定复议用)"),
    BQ_XYZX("民事裁定书(先予执行用)"),
    BQ_BGBQ("民事裁定书(变更保全用)"),
    BQ_ZXQBQ("民事裁定书(执行前保全用)"),
    BQ_JCBQ("民事裁定书(解除保全用)"),
    BQ_SQXWBQ("民事裁定书(诉前行为保全用)"),
    BQ_SQCCBQ("民事裁定书(诉前财产保全用)"),
    BQ_SSXWBQ("民事裁定书(诉讼行为保全用)"),
    BQ_SSCCBQ("民事裁定书(诉讼财产保全用)"),
    BQ_BHSQ("民事裁定书(驳回保全或者先予执行申请用)"),
//    GY_QFXFZQY("民事判决书(侵害消费者权益公益诉讼用)"),
//    GY_HJWR("民事判决书(环境污染或者生态破坏公益诉讼用"),
    GY_BZXCH("民事裁定书(公益诉讼不准许撤回起诉用)"),
    GY_LXTQBYSL("民事裁定书(对同一侵权行为另行提起公益诉讼不予受理用)"),
    GY_HJWRZXCH("民事裁定书(环境污染或者生态破坏公益诉讼准许撤回起诉用)"),
//    DSR_DSRCXZS("民事判决书(第三人撤销之诉用)"),
    DSR_ZZZSCX("民事裁定书(中止再审程序用)"),
    DSR_BYSL("民事裁定书(对第三人撤销之诉不予受理用)"),
    DSR_BRZSCX("民事裁定书(第三人撤销之诉并入再审程序用)"),
    SSFY_WBJACH("民事裁定书(未补交案件受理费按撤回起诉处理用)"),
    SSFY_WYJACH("民事裁定书(未预交案件受理费按撤回起诉处理用)"),
//    ZY_AWRZY("民事判决书(案外人执行异议之诉用)"),
//    ZY_SQZXRZY("民事判决书(申请执行人执行异议之诉用)")
    ;

    public static final String YS_PREFIX = "YS";

    public static final String ES_PREFIX = "ES";

    public static final String GX_PREFIX = "GX";

    public static final String SSCJR_PREFIX = "SSCJR";

    public static final String ZJ_PREFIX = "ZJ";

    public static final String BQ_PREFIX = "BQ";

    public static final String GY_PREFIX = "GY";

    public static final String DSR_PREFIX = "DSR";

    public static final String SSFY_PREFIX = "SSFY";

    public static final String ZY_PREFIX = "ZY";

    String fileName;

    DocType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public static List<String> getTypeList(String typeString) {
        List<String> ys = new ArrayList<>();
        DocType[] values = values();
        for (DocType value : values) {
            String str = value.name();
            if (str.startsWith(typeString)) {
                ys.add(str);
            }
        }
        return ys;
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
