package nju.software.wsjx.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import org.xml.sax.SAXException;
public interface TestPjjgService {
	/**
	 * 测试判决结果
	 */
	public TestModel testPjjg(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试结案方式
	 */
	public TestModel testJafs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试是否发回重审
	 */
	public TestModel testSffhcs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试发回重审原因
	 */
	public TestModel testMsesffcsyy(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试案件审理费
	 */
	public TestModel testAjslf(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试结案标的金额
	 */
	public TestModel testJabdje(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * 测试权利人
	 */
	public TestModel testQlr(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试义务人
	 */
	public TestModel testYwr(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试判决执行期限
	 */
	public TestModel testPjzxqx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试上诉相关
	 */
	public TestModel testSsxx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试撤诉人集合
	 */
	public TestModel testCsrjh(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试撤诉类型
	 */
	public TestModel testCslx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试诉讼费承担人
	 */
	public TestModel testCdr(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * 测试诉讼费承担方式
	 */
	public TestModel testCdfs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试诉讼费金额
	 */
	public TestModel testSsfje(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试是否支持原告诉讼请求
	 */
	public TestModel testSfzcygssqq(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试判决责任承担方式
	 */
	public TestModel testPjzrcdfs(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试判决金额
	 */
	public TestModel testPjje(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试判决金额类型
	 */
	public TestModel testPjjelx(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试提出管辖异议
	 */
	public TestModel testGxyy(TestModel testModel, WscpjgModel wscpjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}

