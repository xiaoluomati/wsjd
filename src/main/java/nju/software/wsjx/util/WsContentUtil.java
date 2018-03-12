package nju.software.wsjx.util;

/**
 * 文书内容工具类
 * Created by Hufk on 2017/09/02.
 */
public class WsContentUtil {
    /**
     * 获取.doc或.rtf文件内容
     * @param fileFullPath 文件的绝对路径
     * @return 文件内容
     */
    public static String getContent(String fileFullPath) {
        if (getSuffix(fileFullPath).equals(".txt")){
            return "";
        }
        String content = "";
        try {
            content = POIUtil.getContent(FileUtil.getContent(fileFullPath), getSuffix(fileFullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //一些特殊内容的文书将被忽略
        String tempContent = content.replaceAll("\\s","");
        if (tempContent.length()>10){
            String segment = tempContent.substring(0,10);
            if (segment.contains("询问笔录") || segment.contains("法庭审理笔录") || segment.contains("审理报告") || segment.contains("开庭笔录") || segment.contains("说明") || segment.contains("情况说明")){
                return "";
            }
        }
        return StringUtil.convertEntireSpace(content);
    }

    /**
     * 获取文件名的后缀
     * @param fileFullPath 文件的绝对路径
     * @return 返回带点的后缀名
     */
    public static String getSuffix(String fileFullPath) {
        String suffix = "";
        int index = fileFullPath.lastIndexOf(".");
        suffix = fileFullPath.substring(index);
        return suffix;
    }
}
