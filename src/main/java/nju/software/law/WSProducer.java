package nju.software.law;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

/**
 * Created by away on 2018/4/10.
 */
public class WSProducer {

    private static final String PATH = "D:\\wx\\msys";
//    private static final String PATH = "D:\\wx\\test";

    public static void main(String[] args) throws JDOMException, IOException {
        File wsdir = new File(PATH);
        if (!wsdir.exists()) {
            return;
        }

        File[] files = wsdir.listFiles();
        if (files == null) {
            return;
        }

        SAXBuilder builder = new SAXBuilder(); //实例JDOM解析器

        for (File file : files) {

            Document document = builder.build(file);//读取xml文件
            Element root = document.getRootElement();
            Element qw = root.getChild("QW");
            Attribute value = qw.getAttribute("value");
            String content = value.getValue();

            content = content.replace(" ", "\n");

            String title = qw.getChild("title").getAttribute("value").getValue();
//            String title = qw.getChild("subtitle").getAttribute("value").getValue();
//            System.out.println("title = " + title);
//            System.out.println("content = " + content);
//            System.out.println("name" + file.getName());
            if (title.equals("丁超与胡锦熬、李薇民间借贷纠纷一案")) {
                System.out.println(qw.getAttribute("value").getValue());
            }
//            WsModelFactory.getInstance(content, title+".txt");
        }


    }
}
