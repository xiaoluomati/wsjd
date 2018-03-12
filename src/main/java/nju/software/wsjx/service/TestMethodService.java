package nju.software.wsjx.service;

import java.util.ArrayList;

/**
 * 文书测试方法Service
 * @author super
 *
 */
public interface TestMethodService {
	/**
	 * 测试节点方法1
	 * 两节点完全相同返回true
	 * @param 
	 * @return
	 */
	public boolean judgeNode_1(String content1, String content2) ;
	/**
	 * 测试节点方法2
	 * 其中content1包含content2返回true
	 * @param 
	 * @return
	 */
	public boolean judgeNode_2(String content1, String content2) ;
	/**
	 * 测试节点方法3
	 * 其中content1与content2相似返回true
	 * @param 
	 * @return
	 */
	public boolean judgeNode_3(String content1, String content2) ;
	/**
	 * 测试节点方法4
	 * contentList1与contentList2相同返回true
	 * @param contentList1
	 * @param contentList2
	 * @return
	 */
	public boolean judgeNode_4(ArrayList<String> contentList1, ArrayList<String> contentList2);
}
