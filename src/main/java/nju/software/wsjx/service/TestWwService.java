package nju.software.wsjx.service;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.service.model.TestModel;
import org.xml.sax.SAXException;
public interface TestWwService {
	/**
	 * 测试审判人员姓名
	 */
	public TestModel testSpryxm(TestModel testModel, WswwModel wswwModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试审判人员角色
	 */
	public TestModel testSpryjs(TestModel testModel, WswwModel wswwModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试裁判时间
	 */
	public TestModel testCpsj(TestModel testModel, WswwModel wswwModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试结案年度
	 */
	public TestModel testJand(TestModel testModel, WswwModel wswwModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试结案年月日
	 */
	public TestModel testJanyr(TestModel testModel, WswwModel wswwModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试结案年月
	 */
	public TestModel testJany(TestModel testModel, WswwModel wswwModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试结案月份
	 */
	public TestModel testJay(TestModel testModel, WswwModel wswwModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
