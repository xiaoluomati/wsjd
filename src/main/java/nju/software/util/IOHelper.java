package nju.software.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/3/5.
 */
public class IOHelper {

    public static List<String> read(String fileName) {
        List<String> content = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "utf-8"))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                s = preProcess(s);
                if (s.length() != 0) {
                    content.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void write(String path, String content) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))){
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 去除半角全角制表符等各类空格
    private static String preProcess(String content) {
        content = content.replace((char) 65279, ' ');
        content = content.replace((char) 12288, ' ');
        content = content.replace((char) 32, ' ');
        content = content.replace(" ", "");
        content = content.replaceAll("\\u00A0","");
        content = content.replace("\t", "");
        return content;
    }
}
