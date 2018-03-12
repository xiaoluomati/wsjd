package nju.software.wsjx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {
	public ArrayList<Double> getJc() {
		return jc;
	}

	public void setJc(ArrayList<Double> jc) {
		this.jc = jc;
	}

	private int k;// 分成多少簇
	private int m;// 迭代次数
	private int dataSetLength;// 数据集元素个数，即数据集的长度
	private ArrayList<double[]> dataSet;// 数据集链表
	private ArrayList<double[]> center;// 中心链表
	private ArrayList<ArrayList<double[]>> cluster; // 簇
	private ArrayList<Double> jc;// 误差平方和，k越接近dataSetLength，误差越小
	private Random random;
	private ArrayList<ArrayList<Double>> xsdjh; // 相似度集合
	private double minCos;// 最小相似度
	private double avgCos;
	
	
	public double getAvgCos() {
		return avgCos;
	}

	/**
	 * 向量的维度
	 */
	private int wd;

	private double maxjl;// 距离中心点最大的距离

	public double getMaxjl() {
		return maxjl;
	}

	private double averageDistance;

	public double getAverageDistance() {
		return averageDistance;
	}

	/**
	 * 设置需分组的原始数据集
	 * 
	 * @param dataSet
	 */

	public void setDataSet(ArrayList<double[]> dataSet) {
		this.dataSet = dataSet;

	}

	/**
	 * 获取结果分组
	 * 
	 * @return 结果集
	 */

	public ArrayList<ArrayList<double[]>> getCluster() {
		return cluster;
	}

	/**
	 * 构造函数，传入需要分成的簇数量
	 * 
	 * @param k
	 *            簇数量,若k<=0时，设置为1，若k大于数据源的长度时，置为数据源的长度
	 */
	public Kmeans(int k, int wd) {
		if (k <= 0) {
			k = 1;
		}
		this.k = k;
		if (wd <= 0) {
			wd = 1;
		}
		this.wd = wd;
		minCos = 100;// 最小相似度

	}

	/**
	 * 初始化
	 */
	private void init() {
		maxjl = 0;
		m = 0;
		random = new Random();
		if (dataSet == null || dataSet.size() == 0) {
			initDataSet();
		}
		dataSetLength = dataSet.size();
		if (k > dataSetLength) {
			k = dataSetLength;
		}
		center = initCenters();
		cluster = initCluster();
		jc = new ArrayList<Double>();
	}

	/**
	 * 如果调用者未初始化数据集，则采用内部测试数据集
	 */
	private void initDataSet() {
		dataSet = new ArrayList<double[]>();
		// 其中{6,3}是一样的，所以长度为15的数据集分成14簇和15簇的误差都为0
		double[][] dataSetArray = new double[][] { { 8, 2 }, { 3, 4 },
				{ 2, 5 }, { 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 },
				{ 5, 3 }, { 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 },
				{ 8, 6 } };

		for (int i = 0; i < dataSetArray.length; i++) {
			dataSet.add(dataSetArray[i]);
		}
	}

	/**
	 * 初始化中心数据链表，分成多少簇就有多少个中心点 使用先随机选取一个点，然后不断取距离这个点集最远的点，依次迭代，直到选取到第k个
	 * 
	 * @return 中心点集
	 */
	private ArrayList<double[]> initCenters() {
		ArrayList<double[]> center = new ArrayList<double[]>();
		int[] randoms = new int[k];
		int temp = random.nextInt(dataSetLength);
		randoms[0] = temp;
		for (int i = 1; i < k; i++) {
			randoms[i] = sx(randoms, i);
		}

		// 测试随机数生成情况
		/*
		 * for (int i = 0; i < k; i++) { System.out.println("test1:randoms[" + i
		 * + "]=" + randoms[i]); }
		 */

		// System.out.println();
		for (int i = 0; i < k; i++) {
			center.add(dataSet.get(randoms[i]));// 生成初始化中心链表
		}
		return center;
	}

	/**
	 * 获取到randoms最远的位置
	 * 
	 * @param randoms
	 * @return
	 */
	private int sx(int[] randoms, int count) {
		int place = 0;
		int maxDistance = 0;
		for (int i = 0; i < dataSet.size(); i++) {
			int distance = 0;
			if (isSx(randoms, count, i))
				continue;
			for (int j = 0; j < count; j++) {
				distance += Math.sqrt(errorSquare(dataSet.get(randoms[j]),
						dataSet.get(i)));
			}
			/* System.out.println(distance+"******************"+i); */
			if (distance >= maxDistance) {

				if (distance == maxDistance && random.nextInt(10) < 5) {
					continue;
				}
				maxDistance = distance;
				place = i;
			}
		}
		return place;
	}

	/**
	 * 计算位置index是否被选取过
	 * 
	 * @param randoms
	 * @param index
	 * @return
	 */
	private boolean isSx(int[] randoms, int count, int index) {
		for (int i = 0; i < count; i++)
			if (index == randoms[i]) {
				return true;
			}
		return false;
	}

	/**
	 * 初始化簇集合
	 * 
	 * @return 一个分为k簇的空数据的簇集合
	 */
	private ArrayList<ArrayList<double[]>> initCluster() {
		ArrayList<ArrayList<double[]>> cluster = new ArrayList<ArrayList<double[]>>();
		for (int i = 0; i < k; i++) {
			cluster.add(new ArrayList<double[]>());
		}

		return cluster;
	}

	/**
	 * 计算两个点之间的距离
	 * 
	 * @param element
	 *            点1
	 * @param center
	 *            点2
	 * @return 距离
	 */
	private double distance(double[] element, double[] center) {
		double distance = 0.0f;
		double z = 0;
		// System.out.println(center.length + ":" + element.length);
		for (int i = 0; i < wd; i++) {
			z = z + (element[i] - center[i]) * (element[i] - center[i]);
		}
		distance = (double) Math.sqrt(z);

		return distance;
	}

	/**
	 * 获取距离集合中最小距离的位置
	 * 
	 * @param distance
	 *            距离数组
	 * @return 最小距离在距离数组中的位置
	 */
	private int minDistance(double[] distance) {
		double minDistance = distance[0];
		int minLocation = 0;
		for (int i = 1; i < distance.length; i++) {
			if (distance[i] < minDistance) {
				minDistance = distance[i];
				minLocation = i;
			} else if (distance[i] == minDistance) // 如果相等，随机返回一个位置
			{
				if (random.nextInt(10) < 5) {
					minLocation = i;
				}
			}
		}

		return minLocation;
	}

	/**
	 * 核心，将当前元素放到最小距离中心相关的簇中
	 */
	private void clusterSet() {
		double[] distance = new double[k];
		for (int i = 0; i < dataSetLength; i++) {
			for (int j = 0; j < k; j++) {
				distance[j] = distance(dataSet.get(i), center.get(j));
				// System.out.println("test2:"+"dataSet["+i+"],center["+j+"],distance="+distance[j]);

			}
			int minLocation = minDistance(distance);
			/*
			 * System.out.println("test3:" + "dataSet[" + i + "],minLocation=" +
			 * minLocation);
			 */
			// System.out.println();

			cluster.get(minLocation).add(dataSet.get(i));// 核心，将当前元素放到最小距离中心相关的簇中

		}
	}

	/**
	 * 求两点误差平方的方法
	 * 
	 * @param element
	 *            点1
	 * @param center
	 *            点2
	 * @return 误差平方
	 */
	private double errorSquare(double[] element, double[] center) {
		int length = wd;
		double errSquare = 0;
		for (int i = 0; i < length; i++) {
			errSquare = errSquare + (element[i] - center[i])
					* (element[i] - center[i]);
		}

		return errSquare;
	}

	/**
	 * 计算误差平方和准则函数方法
	 */
	private void countRule() {
		double jcF = 0;
		maxjl = 0;
		double total = 0;
		for (int i = 0; i < cluster.size(); i++) {
			for (int j = 0; j < cluster.get(i).size(); j++) {
				double xldistance = errorSquare(cluster.get(i).get(j),
						center.get(i));
				jcF += xldistance;
				xldistance = Math.sqrt(xldistance);
				total = total + xldistance;
				if (xldistance > maxjl) {
					maxjl = xldistance;
				}
			}
		}
		averageDistance = total / dataSetLength;
		jc.add(jcF);
	}

	/**
	 * 设置新的簇中心方法
	 */
	private void setNewCenter() {

		for (int i = 0; i < k; i++) {
			int n = cluster.get(i).size();
			if (n != 0) {
				double[] newCenter = new double[wd];
				for (int mm = 0; mm < wd; mm++) {
					for (int j = 0; j < n; j++) {
						newCenter[mm] += cluster.get(i).get(j)[mm];
					}
					newCenter[mm] = newCenter[mm] / n;
				}
				center.set(i, newCenter);
			}
		}
	}

	/**
	 * 打印数据，测试用
	 * 
	 * @param dataArray
	 *            数据集
	 * @param dataArrayName
	 *            数据集名称
	 */
	public void printDataArray(ArrayList<double[]> dataArray,
			String dataArrayName) {
		for (int i = 0; i < dataArray.size(); i++) {

			for (int j = 0; j < wd; j++)
				if (j == 0)
					System.out.print(dataArrayName + "[" + i + "]={"
							+ dataArray.get(i)[j] + ",");
				else {
					System.out.print(+dataArray.get(i)[j] + ",");
				}
			System.out.println();
		}
		System.out.println("===================================");
	}

	private double getCos(double x1[], double x2[]) {
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
			return (fz / fm) * 100;
		return 0;

	}

	/**
	 * Kmeans算法核心过程方法
	 */
	private void kmeans() {
		init();
		/*
		 * printDataArray(dataSet, "initDataSet"); printDataArray(center,
		 * "initCenter");
		 */

		// 循环分组，直到误差不变为止
		while (true) {
			clusterSet();
			// System.out.println("m:"+m);
			// for(int i=0;i<cluster.size();i++)
			// {
			// printDataArray(cluster.get(i),"cluster["+i+"]");
			// }

			countRule();

			// System.out.println("count:" + "jc[" + m + "]=" + jc.get(m));

			// System.out.println();
			// 误差不变了，分组完成
			if (m != 0) {
				if (jc.get(m) - jc.get(m - 1) == 0) {
					break;
				}
			}

			setNewCenter();
			// printDataArray(center, "newCenter");
			m++;
			// System.out.println("m:"+m);
			cluster.clear();
			cluster = initCluster();
		}

		xsdjh = new ArrayList<ArrayList<Double>>();
		double sum=0;
		int num=0;
		for (int i = 0; i < cluster.size(); i++) {
			ArrayList<Double> result = new ArrayList<Double>();

			if (cluster.get(i).size() == 0)
				xsdjh.add(result);
			else {

				
				for (int j = 0; j < cluster.get(i).size(); j++) {
					double cos = getCos(center.get(i), cluster.get(i).get(j));
					// printDataArray(cluster.get(i), "cluster");
					// System.out.println(cos+"::cos");
					if(cos<100){
					sum+=cos;
					num++;
					}
					if (cos < minCos)
						minCos = cos;
					result.add(cos);
				}
				xsdjh.add(result);
			}

		}
        avgCos=sum/num;
		// System.out.println(minCos);
		// System.out.println("note:the times of repeat:m="+m);//输出迭代次数
	}

	/**
	 * 执行算法
	 */
	public void execute() {
		/*
		 * long startTime = System.currentTimeMillis();
		 * System.out.println("kmeans begins");
		 */
		kmeans();
		/*
		 * long endTime = System.currentTimeMillis();
		 * System.out.println("kmeans running time=" + (endTime - startTime) +
		 * "ms"); System.out.println("kmeans ends"); System.out.println();
		 */
	}

	public ArrayList<ArrayList<Double>> getXsdjh() {
		return xsdjh;
	}

	public double getMinCos() {
		return minCos;
	}

}
