package nju.software.wsjx.service.impl;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;






import nju.software.wsjx.service.WsService;
import nju.software.wsjx.service.model.FzModel;

import nju.software.wsjx.util.FcUtil;
import nju.software.wsjx.util.Kmeans;
import nju.software.wsjx.util.NumberUtil;
import nju.software.wsjx.util.POIUtil;


public class WsServiceImpl implements WsService {

	


	@Override
	public double getXsd(String word1, String word2) {
		List<String> doc1 = FcUtil.getWholeToken(word1);
		List<String> doc2 = FcUtil.getWholeToken(word2);
		int dotProduct = 0;
		double denominator;

		for (int i = 0; i < doc1.size(); i++) {
			String word = doc1.get(i);
			for (int j = 0; j < doc2.size(); j++) {
				if (word.equals(doc2.get(j))) {
					dotProduct += 1;
					break;
				}
			}
		}
		int dotProduct2 = 0;
		for (int i = 0; i < doc2.size(); i++) {
			String word = doc2.get(i);
			for (int j = 0; j < doc1.size(); j++) {
				if (word.equals(doc1.get(j))) {
					dotProduct2 += 1;
					break;
				}
			}
		}
		dotProduct = (int) (Math.sqrt(dotProduct) * Math.sqrt(dotProduct2));
		denominator = Math.sqrt(doc1.size()) * Math.sqrt(doc2.size());
		System.out.println(doc1.size() + "[[[[]]]]" + doc2.size());
		double ratio = 0;
		if (denominator != 0)
			ratio = dotProduct / denominator;

		return Math.round(ratio * 100);
	}

	@Override
	public double calculateXsd(String word1, String word2) {
		List<String> doc1 = FcUtil.getWholeToken(word1);
		List<String> doc2 = FcUtil.getWholeToken(word2);
		Set<String> bjSet = new HashSet<String>();
		bjSet.addAll(doc1);
		bjSet.addAll(doc2);
		int[] xl1 = new int[bjSet.size()];
		int[] xl2 = new int[bjSet.size()];
		int index = 0;
		for (String word : bjSet) {
			xl1[index] = getTotalHits(word, doc1);
			xl2[index] = getTotalHits(word, doc2);
			index++;
		}
		double value = 100*getYx(xl1, xl2);
		return Double.parseDouble(NumberUtil.changeNumber(value, 2));
	}

	private int getTotalHits(String word, List<String> list) {
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			if (word.equals(list.get(i)))
				total++;
		}
		return total;
	}

	private double getYx(int[] x1, int[] x2) {
		double fz = 0;
		int length = x1.length;
		for (int i = 0; i < length; i++) {
			fz = fz + x1[i] * x2[i];
		}
		double fm = 0;
		for (int i = 0; i < length; i++) {
			fm = fm + x1[i] * x1[i];
		}
		double fm2 = 0;
		for (int i = 0; i < length; i++) {
			fm2 = fm2 + x2[i] * x2[i];
		}
		fm = Math.sqrt(fm2) * Math.sqrt(fm);
		if (fm != 0)
			return fz / fm;
		return 0;
	}

	public List<FzModel> fz(List<String> words, List<String> filename) {
		Set<String> totalSet = new HashSet<String>();
		List<List<String>> docs = new ArrayList<List<String>>();
		for (int i = 0; i < words.size(); i++) {
			docs.add(FcUtil.getWholeToken(words.get(i)));
			totalSet.addAll(docs.get(i));
		}
		ArrayList<double[]> dataSet = new ArrayList<double[]>();

		HashMap<double[], String> hashMap = new HashMap<double[], String>();
		for (int i = 0; i < docs.size(); i++) {
			double[] xl = getXl(docs.get(i), totalSet);
			dataSet.add(xl);
			hashMap.put(xl, filename.get(i));
		}
		Kmeans resultKmeans = null;
		Kmeans k;

		boolean isXs = false;
		int maxbound = (int) Math.ceil(Math.sqrt(words.size()));
		double minjl = Integer.MAX_VALUE;
		System.out.println("总共有"+words.size()+"个样本");
		
		int left=maxbound,right=words.size();
		int zs=maxbound;
		while( left<right) {
			int i=(left+right)/2;
			System.out.println("==============================");
			System.out.println("分成第"+i+"组");
			k = new Kmeans(i, totalSet.size());
			// 设置原始数据集
			k.setDataSet(dataSet);
			// 执行算法
			k.execute();
		//	System.out.println(k.getMaxjl() + ":maxjl::::pjjl" + (k.getAverageDistance()));
			//设定阈值
			System.out.println("最小相似度阈值"+k.getMinCos());
			if (Math.abs(k.getMinCos()-95)<=0.2) {
				//System.out.println(i + "i");
				resultKmeans = k;
				isXs = true;
				zs=i;
				break;
			}
			if(k.getAvgCos()>95.2){
				right=i-1;
			}
			if(k.getAvgCos()<94.8){
				left=i+1;
			}
			//和之前的分组进行对比
			if(k.getAverageDistance()<minjl||(k.getAverageDistance()==minjl&&k.getMaxjl()<resultKmeans.getMaxjl())){
				resultKmeans=k;
				minjl=k.getAverageDistance();
			}
		}
		
		if(!isXs){
			zs=right;
		}
		for (int i = 0; i < 10; i++) {
			k = new Kmeans(zs, totalSet.size());
			k.setDataSet(dataSet);
			k.execute();
			if(k.getAverageDistance()<minjl||(k.getAverageDistance()==minjl&&k.getMaxjl()<resultKmeans.getMaxjl())){
				resultKmeans=k;
				minjl=k.getAverageDistance();
			}
		}
//		if (!isXs) {
//			double minjl = Integer.MAX_VALUE;
//			for (int i = 0; i < 30; i++) {
//				k = new Kmeans(maxbound, totalSet.size());
//				k.setDataSet(dataSet);
//				k.execute();
//				if(k.getAverageDistance()<minjl||(k.getAverageDistance()==minjl&&k.getMaxjl()<resultKmeans.getMaxjl())){
//					resultKmeans=k;
//					minjl=k.getAverageDistance();
//				}
//			}
//		}

		// 得到聚类结果
		ArrayList<ArrayList<double[]>> cluster = resultKmeans.getCluster();
		List<FzModel> result = new ArrayList<FzModel>();
		// 查看结果
		System.out.println(cluster.size() + ":::size");
		int m=0;
		ArrayList<ArrayList<Double>> xsdjhArrayList=resultKmeans.getXsdjh();
		for (int i = 0; i < cluster.size(); i++) {
			m++;
			if (cluster.get(i).size() == 0)
				continue;
			FzModel fzModel=new FzModel();
			fzModel.setZh(m);
			List<String> list=new ArrayList<String>();
			for (int j = 0; j < cluster.get(i).size(); j++) {
				String name = hashMap.get(cluster.get(i).get(j));
				String[] split=name.split("_");
				list.add( split[0]+"\t"+split[1]+"\t"+split[2] +"\t"+NumberUtil.changeNumber(xsdjhArrayList.get(i).get(j),2));
			}
			fzModel.setCluster(list);
			result.add(fzModel);
			/* k.printDataArray(cluster.get(i), "cluster[" + i + "]"); */
		}
		return result;
	}

	/**
	 * 计算一个文档的向量
	 * 
	 * @param list
	 * @param total
	 * @return
	 */
	private double[] getXl(List<String> doc, Set<String> total) {
		double[] xl = new double[total.size()];
		int i = 0;
		for (String str : total) {
			xl[i] = getTotalHits(str, doc);
			i++;
		}
		return xl;
	}
}
