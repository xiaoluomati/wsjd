package nju.software.wsjx.service.model;
/**
 * 文书测试model
 * @author super
 *
 */
public class TestModel {
	private String testNode;//节点名称
	private int njutotal;//测试文书该节点出现次数
	private int coNum;//正确个数
	public String getTestNode() {
		return testNode;
	}
	public void setTestNode(String testNode) {
		this.testNode = testNode;
	}
	public int getNjutotal() {
		return njutotal;
	}
	public void setNjutotal(int njutotal) {
		this.njutotal = njutotal;
	}
	public int getCoNum() {
		return coNum;
	}
	public void setCoNum(int coNum) {
		this.coNum = coNum;
	}
	
}
