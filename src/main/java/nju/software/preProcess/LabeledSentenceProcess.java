package nju.software.preProcess;

import javafx.util.Pair;
import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabeledSentenceProcess implements Serializable {

    int totalcount = 0;

    Map<String,Pair<String,INDArray>> filesBylabel = new HashMap<>();//数据格式Map<label,Pair<sentence,doc2vec>>

    List<String> stringList = new ArrayList<>();

    List<String> labelList = new ArrayList<>();

    Word2Vec word2Vec;

    int word2VecSize;

    public LabeledSentenceProcess(){

    }

    public LabeledSentenceProcess(String datapath, Word2Vec word2Vec){
        this.word2Vec = word2Vec;
        this.word2VecSize = word2Vec.getWordVector(word2Vec.vocab().wordAtIndex(0)).length;

        BufferedReader buffered = null;

        try {
            buffered = new BufferedReader(new InputStreamReader(new FileInputStream(datapath)));
            String line = buffered.readLine();
            while(line != null){
                String[] lines = line.split("\t");
                String label = lines[0];
                String content = lines[1];
                if ("".equals(label)||" ".equals(label)){
                    line = buffered.readLine();
                    continue;
                }
                labelList.add(label);
                stringList.add(content);
                totalcount++;
                line = buffered.readLine();

            }
            buffered.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("TotalCount is : "+totalcount);

        for (int i=0;i<totalcount;++i){
            System.out.println("files now is:"+i);
            Map<String,Pair<Double,INDArray>> tokens = tokenizeSentence(stringList.get(i));
            filesBylabel.put(labelList.get(i),new Pair<>(stringList.get(i),Doc2vec(false,tokens)));
        }

    }

    //mulKnn算法
    public List<Pair<String,Double>> mulKnnresult(String sentence,int k,int num){

        List<Pair<String,Double>> similarityresult = calculateSimilarity(sentence);
        List<Pair<String,Double>> topKresult = gettopK(similarityresult,k);
        HashMap<String,Double> labels = getLawlabels(topKresult);
        for (Pair<String,Double> tmp : topKresult){
            Double weight = tmp.getValue();
            String lawsstr = tmp.getKey();
            String[] laws = lawsstr.split(",");
            for (String a :laws){
                String[] lawcontents = a.split(" ");
                String label = lawcontents[0];
                for (int i=1;i<lawcontents.length;++i){
                    String key= label+" "+lawcontents[i];
                    if (labels.containsKey(key)){
                        labels.put(key,labels.get(key)+weight);
                    }
                }
            }

        }
        return gettopN(labels,num);
    }

    private List<Pair<String,Double>> gettopN(HashMap<String,Double> labels,int n) {

        List<Pair<String,Double>> result = new ArrayList<>(n);

        for (int i=0;i<n;++i){
            double flag = 0;
            String label = "";
            for (Map.Entry<String,Double> entry : labels.entrySet()){
                if (flag < entry.getValue()){
                    flag = entry.getValue();
                    label = entry.getKey();
                }
            }
            Pair<String,Double> pair = new Pair<>(label,flag);
            result.add(pair);
            labels.remove(label);
        }

        return result;
    }

    private HashMap<String,Double> getLawlabels(List<Pair<String, Double>> topKresult) {

        HashMap<String,Double> result = new HashMap<>();

        for (Pair<String,Double> tmp : topKresult){
            String lawsstr = tmp.getKey();
            String[] laws = lawsstr.split(",");
            for (String a :laws){
                String[] lawcontents = a.split(" ");
                String label = lawcontents[0];
                for (int i=1;i<lawcontents.length;++i){
                    String key= label+" "+lawcontents[i];
                    if (!result.containsKey(key)){
                        result.put(key,0.0);
                    }
                }
            }
        }

        return result;
    }

    private Pair<String,Double> getfirst(List<Pair<String, Double>> result){
        double k = 0;
        int flag = 0;
        for (int i=0;i<result.size();++i){
            double a = result.get(i).getValue();
            if (a>k){
                k = a;
                flag = i;
            }
        }

        return result.get(flag);
    }

    private List<Pair<String,Double>> gettopK(List<Pair<String, Double>> similarityresult,int k) {

        List<Pair<String,Double>> result = new ArrayList<>(k);

        for (int i=0;i<k;++i){
            Pair<String,Double> tmp = getfirst(similarityresult);
            result.add(tmp);
            similarityresult.remove(tmp);
        }

        return result;
    }

    private INDArray Doc2vec(boolean isNew,Map<String,Pair<Double,INDArray>> tokens){
        INDArray doc2vec = Nd4j.create(word2Vec.getWordVector(word2Vec.vocab().wordAtIndex(0)).length);
        for (Map.Entry<String,Pair<Double,INDArray>> set : tokens.entrySet()){
            String word = set.getKey();
            int times = 0;
            int total = totalcount;
            if (isNew) {
                times = 1;
                ++total;
            }
            for (int j =0 ;j<totalcount;++j){
                String tmp = stringList.get(j);
                if (tmp.contains(word)){
                    times++;
                }
            }
            double dou = total/(times+1);
            Double idf = Math.log(dou)/Math.log(10);
            Pair<Double,INDArray> value = set.getValue();
            INDArray vec = value.getValue().mul(value.getKey()*idf);
            doc2vec.addi(vec);
        }
        doc2vec.divi(tokens.size());
        return doc2vec;
    }

    private List<Pair<String,Double>> calculateSimilarity(String sentence){
        List<Pair<String,Double>> pairList = new ArrayList<>();
        Map<String,Pair<Double,INDArray>> tokens = tokenizeSentence(sentence);
        INDArray doc2vec = Doc2vec(true,tokens);
        for (Map.Entry<String,Pair<String,INDArray>> doc : filesBylabel.entrySet()){

            String label = doc.getKey();
            Pair<String,INDArray> content = doc.getValue();
            INDArray contentvec = content.getValue();
            Double result = calculateCos(doc2vec,contentvec);
            pairList.add(new Pair<>(label,result));
        }
        return  pairList;
    }

    private double calculateCos(INDArray doc2vec,INDArray contentvec){
        double k1 = 0;
        double k2 = 0;
        double k3 = 0;
        for (int i=0;i<word2VecSize;++i){
            double x = doc2vec.getDouble(0,i);
            double y = contentvec.getDouble(0,i);
            k1 += x*y;
            k2 += x*x;
            k3 += y*y;
        }
        k2 = Math.sqrt(k2);
        k3 = Math.sqrt(k3);
        return  0.5+0.5*k1/(k2*k3);
    }

    private  Map<String,Pair<Double,INDArray>> tokenizeSentence(String sentence) {
        WeightLookupTable weightLookupTable = word2Vec.lookupTable();
        VocabCache vocabCache = weightLookupTable.getVocabCache();
        String[] t = sentence.split(" ");
        Map<String,Pair<Double,INDArray>> tokens = new HashMap<>();
        for (int i=0;i<t.length;++i){
            String word = t[i];
            if (!vocabCache.hasToken(word)){
                continue;
            }
            Double tf1 = 1.0/t.length;
            if (tokens.containsKey(word)){
                Pair old = tokens.remove(word);
                Double num = (Double) old.getKey()+tf1;
                tokens.put(word,new Pair<>(num,(INDArray) old.getValue()));
            }else {
                INDArray vector = word2Vec.getWordVectorMatrixNormalized(word);
                tokens.put(word,new Pair<>(tf1,vector));
            }

        }
        return tokens;
    }

    public int getTotalcount(){
        return this.totalcount;
    }

    public int getWord2VecSize() {
        return word2VecSize;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public void setFilesBylabel(Map<String, Pair<String, INDArray>> filesBylabel) {
        this.filesBylabel = filesBylabel;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public void setWord2Vec(Word2Vec word2Vec) {
        this.word2Vec = word2Vec;
    }

    public void setWord2VecSize(int word2VecSize) {
        this.word2VecSize = word2VecSize;
    }
}
