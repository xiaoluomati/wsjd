package nju.software.preProcess;

import javafx.util.Pair;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcess {

    private static final String ResourcePath = new ClassPathResource("src/main/resources/LawResource").getPath();

    private static final String stopwordsPath = ResourcePath+"/stopwords.txt";

    private static final String ProcessModelPath = ResourcePath+"/process_new.zip";

    public List<String> getRecommend(String content,LabeledSentenceProcess labeledSentenceProcess) throws UnsupportedEncodingException {
        System.out.println(content);
        NLPIR nlpir = new NLPIR();
        nlpir.init();
        String nlpircontent = getNLPIRresult(nlpir,content,getStopWords());
        System.out.println(nlpircontent);
        nlpir.unInit();
        List<String> results = new ArrayList<>();
        List<Pair<String,Double>> knnresult = labeledSentenceProcess.mulKnnresult(nlpircontent,10,10);
        for (Pair<String,Double> tmp : knnresult){
            results.add(tmp.getKey());
            System.out.println(tmp.getKey());
        }
        return results;
    }

    public static LabeledSentenceProcess readModel(){

        LabeledSentenceProcess labeledSentenceProcess = null;

        try {
            ObjectInputStream ooi=new ObjectInputStream(new FileInputStream(ProcessModelPath));
            try {
                Object obj=ooi.readObject();
                labeledSentenceProcess =(LabeledSentenceProcess)obj;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ooi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return labeledSentenceProcess;
    }

    private Set getStopWords(){
        String system_charset = "utf-8";

        BufferedReader bufferedReader;
        Set stopWordSet = new HashSet<String>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(stopwordsPath)),system_charset));
            String stopword = null;
            for (;(stopword=bufferedReader.readLine())!=null;){
                stopword = unicodeToUtf8(stopword);
                stopWordSet.add(stopword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopWordSet;
    }

    private String getNLPIRresult(NLPIR nlpir,String sInput,Set stopWordSet) throws UnsupportedEncodingException {
        String nativeBytes = "";
        nativeBytes = nlpir.parseSeq(sInput);
        String[] resultArray =nativeBytes.split(" ");
        for(int i=0;i<resultArray.length;++i){
            if (resultArray[i].contains("/"))
                resultArray[i] = resultArray[i].substring(0,resultArray[i].indexOf("/"));
            if (stopWordSet.contains(resultArray[i]))
                resultArray[i] = null;
        }
        StringBuilder result = new StringBuilder();
        for (String tmp : resultArray){
            if (tmp!=null){
                if (!isContainNumber(tmp)){
                    result.append(tmp).append(" ");
                }
            }
        }
        return result.toString();
    }

    private String unicodeToUtf8 (String s) throws UnsupportedEncodingException {
        return new String( s.getBytes("GBK") , "GBK");
    }

    private static boolean isContainNumber(String company) {

        Pattern p = Pattern.compile("[0-9]|[a-zA-Z]");
        Matcher m = p.matcher(company);
        if (m.find())
            return true;
        else
            return (company.contains("原告")||company.contains("被告"));

    }


}
