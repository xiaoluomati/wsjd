package nju.software.wsjx.business;

public abstract class  AbstractAnalyseWs {

	
	/**
	 * 初始化文书
	 */
	public abstract void init();
	
	/**
	 * 划分文首的内容
	 */
	public abstract  void hfWs();
	
	/**
	 * 划分诉讼参与人信息
	 */
	public abstract void hfSscyr();


	/**
	 * 划分案件基本情况
	 */
	public abstract void hfAjjbqk();
	
	/**
	 * 划分诉讼记录
	 */
	public abstract void hfSsjl();
	/**
	 * 划分裁判分析过程
	 */
	public abstract void hfCpfxgc();
	
	/**
	 * 划分裁判结果
	 */
	public abstract void hfCpjg();
	
	/**
	 * 划分文尾
	 */
	public abstract void hfWw();
	
	/**
	 * 划分附录
	 */
	public abstract void hfFl();
}
