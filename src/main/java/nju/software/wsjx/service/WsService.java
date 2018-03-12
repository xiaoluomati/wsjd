package nju.software.wsjx.service;


import java.util.List;



import nju.software.wsjx.service.model.FzModel;


public interface WsService {
	
	
	
	
	/**
	 * 计算两篇文档的相似度
	 * 
	 */
     public double getXsd(String word1, String word2);
     
     /**
      * 使用余弦值来计算相似度
      * @param word1
      * @param word2
      * @return
      */
     public double calculateXsd(String word1, String word2);

     /**
      * 聚类算法 
      * @param words 文书的内容
      * @param filename 文书文件名
      * @return
      */
     public List<FzModel> fz(List<String> words, List<String> filename);
     
}

