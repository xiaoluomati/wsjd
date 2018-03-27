package nju.software.util;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by away on 2018/3/27.
 */
public class WordHelper {

    public static String read(MultipartFile file) {
        String text= "";
        try {
            InputStream is = file.getInputStream();
            text = read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String read(InputStream is) {
        String text= "";
        try {
            is = FileMagic.prepareToCheckMagic(is);
            if (FileMagic.valueOf(is) == FileMagic.OLE2) {
                WordExtractor ex = new WordExtractor(is);
                text = ex.getText();
                ex.close();
            } else if(FileMagic.valueOf(is) == FileMagic.OOXML) {
                XWPFDocument doc = new XWPFDocument(is);
                XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
                text = extractor.getText();
                extractor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    private static String getSuffix(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int index = fileName.indexOf(".");
        return fileName.substring(index);
    }
}
