package nju.software.wsjx.util;

/**
 * Created by Hufk on 2017/07/26.
 */
public class FileNameUtil {
    /**
     * 获得文件扩展名
     * @param fileName 带扩展名的文件全名
     * @return 文件扩展名
     */
    public static String getExt(String fileName){
        String ext = null;
        int index = fileName.lastIndexOf(".");
        ext = fileName.substring(index+1);
        return ext;
    }

    /**
     * 获得不带扩展名的文件名
     * @param fileName 带扩展名的文件全名
     * @return 不带扩展名的文件名
     */
    public static String getFileName(String fileName){
        String filename = null;
        int index = fileName.lastIndexOf(".");
        filename = fileName.substring(0,index);
        return filename;
    }

    /**
     * 组装文件的全称
     * @param fileName 文件名
     * @param AH 案号
     * @return 带后缀的文件全名
     */
    public static String getFullName(String fileName,String AH,int type){
        return AH + "-" + type + "." + getExt(fileName);
    }

    /**
     * 组装文件全称
     * @param fileName
     * @param ah
     * @param ysah
     * @return
     */
    public static String getFullName(String fileName, String ah, String ysah){
        return ah + "-" + ysah + "." + getExt(fileName);
    }

    //public static String parse
}
