package nju.software.wsjx.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.service.model.TestModel;
public interface TestCpfxgcService {
	/**
	 * 
	 */
	public TestModel testFlft(TestModel testModel, WscpfxgcModel wscpfxgcModel, String inputpath,
                              String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	/**
	 * 
	 */
	public TestModel testSfjgxzfy(TestModel testModel, WscpfxgcModel wscpfxgcModel, String inputpath,
                                  String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	/**
	 * 
	 */
	public TestModel testXzxwwfbj(TestModel testModel, WscpfxgcModel wscpfxgcModel, String inputpath,
                                  String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	/**
	 * 
	 */
	public TestModel testXzpc(TestModel testModel, WscpfxgcModel wscpfxgcModel, String inputpath,
                              String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	/**
	 * 
	 */
	public TestModel testGtfz(TestModel testModel, WscpfxgcModel wscpfxgcModel, String inputpath,
                              String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	/**
	 * 
	 */
	public TestModel testBgrtyrzcx(TestModel testModel, WscpfxgcModel wscpfxgcModel, String inputpath,
                                   String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	/**
	 * 
	 */
	public TestModel testKtqsqchss(TestModel testModel, WscpfxgcModel wscpfxgcModel, String inputpath, String filename,
                                   String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;

}
