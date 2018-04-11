package nju.software.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by away on 2018/3/5.
 */
public class IOHelper {

    // 有预处理
    public static List<String> read(String path) {
        List<String> content = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "utf-8"))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                s = StringUtil.trim(s);
                if (s.length() != 0) {
                    content.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // 无预处理
    public static List<String> readRaw(String path) {
        List<String> content = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "utf-8"))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
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
}
