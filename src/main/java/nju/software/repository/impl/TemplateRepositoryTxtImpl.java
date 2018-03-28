package nju.software.repository.impl;

import nju.software.repository.TemplateRepository;
import nju.software.util.IOHelper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by away on 2018/3/28.
 */
@Repository
public class TemplateRepositoryTxtImpl implements TemplateRepository {

    private static final String PATH = "json";
    private static final String SUFFIX= ".json";

    @Override
    public String getJson(String name) {
        name = name + SUFFIX;
        File file = new File(PATH);
        if (!file.exists()) {
            return "";
        }
        File[] files = file.listFiles();
        if (files == null) {
            return "";
        }
        for (File eachFile : files) {
            if (name.equals(eachFile.getName())) {
                return read(eachFile);
            }
        }
        return "";
    }

    private String read(File file) {
        try {
//            System.out.println(file.getCanonicalPath());
            List<String> readList = IOHelper.read(file.getCanonicalPath());
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : readList) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static void main(String[] args) {
//        System.out.println(new TemplateRepositoryTxtImpl().getJson("民事判决书(二审改判用)"));
//    }
}
