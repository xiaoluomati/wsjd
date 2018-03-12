package nju.software.wsjx.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.service.model.TestModel;

import org.xml.sax.SAXException;

public interface TestWsService {
	/**
	 * 测试文书制作单位
	 */
	public TestModel testWszzdw(TestModel testModel, WswsModel wswsmodel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试经办法院
	 */
	public TestModel testJbfy(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试案号
	 */
	public TestModel testAh(TestModel testModel, WswsModel wswsmodel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试文书名称
	 */
	public TestModel testWsmc(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试立案年度
	 */
	public TestModel testLand(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试法院级别
	 */
	public TestModel testFyjb(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试行政区划（省）
	 */
	public TestModel testXzqhProv(TestModel testModel, WswsModel wswsmodel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试行政区划（市）
	 */
	public TestModel testXzqhCity(TestModel testModel, WswsModel wswsmodel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试案件性质
	 */
	public TestModel testAjxz(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试文书种类
	 */
	public TestModel testWszl(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试审判程序
	 */
	public TestModel testSpcx(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试案件类型
	 */
	public TestModel testAjlx(TestModel testModel, WswsModel wswsmodel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
