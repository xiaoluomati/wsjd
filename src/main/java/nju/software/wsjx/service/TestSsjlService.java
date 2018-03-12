package nju.software.wsjx.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.service.model.TestModel;

import org.xml.sax.SAXException;

public interface TestSsjlService {
	/**
	 * 
	 */
	public TestModel testAy(TestModel testModel, WsssjlModel wsssjlModel,
                            String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testWzay(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testAydm(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testAjly(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testAjsj(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testKtsl(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testKtrq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testQsah(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testQsfy(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testKtslxx(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testBgkslyy(TestModel testModel, WsssjlModel wsssjlModel,
                                 String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testLarq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testYsajsycx(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试简易转普通
	 */
	public TestModel testJyzpt(TestModel testModel, WsssjlModel wsssjlModel,
                               String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试一审案件来源
	 */
	public TestModel testYsajly(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试受理日期
	 */
	public TestModel testSlrq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试审判组织
	 */
	public TestModel testSpzz(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试独任审判
	 */
	public TestModel testDrsp(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试申请撤诉日期
	 */
	public TestModel testSqcsrq(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试缺席人信息
	 */
	public TestModel testQxrxx(TestModel testModel, WsssjlModel wsssjlModel,
                               String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试出庭人信息
	 */
	public TestModel testCtrxx(TestModel testModel, WsssjlModel wsssjlModel,
                               String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试被告主要领导出庭
	 */
	public TestModel testBgzyldct(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试行政行为种类
	 */
	public TestModel testXzxwzl(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试行政侵权行为种类
	 */
	public TestModel testXzqqxwzl(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试起诉日期
	 */
	public TestModel testQsrq(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试诉讼性质
	 */
	public TestModel testSsxz(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试完整罪名
	 */
	public TestModel testWzzm(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试罪名代码
	 */
	public TestModel testZmdm(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试公诉机关
	 */
	public TestModel testGsjg(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试公诉案号
	 */
	public TestModel testGsah(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试附带民事部分继续审理
	 */
	public TestModel testMsbfjxsl(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 测试检察院建议延期审理
	 */
	public TestModel testJcyjyyqsl(TestModel testModel,
                                   WsssjlModel wsssjlModel, String inputpath, String filename,
                                   String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException;

	public TestModel testQsland(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsfyjc(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQswszl(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQssj(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsajyl(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	/**
	 * 
	 */
	public TestModel testXzesqsah(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testSshksfw(TestModel testModel, WsssjlModel wsssjlModel,
                                 String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsjafs(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQspj(TestModel testModel, WsssjlModel wsssjlModel,
                              String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testXsesqsah(TestModel testModel, WsssjlModel wsssjlModel,
                                  String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQscpsj(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testQsgsjg(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

	public TestModel testBzfymc(TestModel testModel, WsssjlModel wsssjlModel,
                                String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException;

}
