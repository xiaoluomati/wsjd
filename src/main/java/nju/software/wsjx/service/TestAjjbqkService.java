package nju.software.wsjx.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.service.model.TestModel;



import org.xml.sax.SAXException;

public interface TestAjjbqkService {
	/**
	 * ≤‚ ‘«∞…Û∂Œ¬‰
	 */
	public TestModel testQsdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±æ…Û∂Œ¬‰
	 */
	public TestModel testBsdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘«∞…Û≈–æˆ∂Œ
	 */
	public TestModel testQspjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘«∞…Û‘≠∏ÊÀﬂ≥∆∂Œ
	 */
	public TestModel testQsygscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘«∞…Û±ª∏Ê±Á≥∆∂Œ
	 */
	public TestModel testQsbgbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘«∞…Û∑¥ÀﬂÀﬂ≥∆∂Œ
	 */
	public TestModel testQsfsscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘«∞…Û÷§æ›∂Œ
	 */
	public TestModel testQszjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * ≤‚ ‘…œÀﬂ»ÀÀﬂ≥∆∂Œ
	 */
	public TestModel testSsrscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±ª…œÀﬂ»À±Á≥∆∂Œ
	 */
	public TestModel testBssrbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±æ…Ûµ⁄»˝»À“‚º˚∂Œ
	 */
	public TestModel testBsdsryjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * ≤‚ ‘±æ…Û…Û¿Ì∂Œ
	 */
	public TestModel testBssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±æ…Û÷§æ›∂Œ
	 */
	public TestModel testBszjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	
	/**
	 * ≤‚ ‘«∞…Û…Û¿Ì∂Œ
	 */
	public TestModel testQssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘‘≠∏ÊÀﬂ≥∆∂Œ
	 */
	public TestModel testYgscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±ª∏Ê±Á≥∆∂Œ
	 */
	public TestModel testBgbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘≤È√˜ ¬ µ∂Œ
	 */
	public TestModel testCmssd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘÷§æ›∂Œ
	 */
	public TestModel testZjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘µ⁄»˝»À“‚º˚∂Œ
	 */
	public TestModel testDsryjd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘∑¥ÀﬂÀﬂ≥∆∂Œ
	 */
	public TestModel testFsscd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘∑¥Àﬂ±Á≥∆∂Œ
	 */
	public TestModel testFsbcd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘––’˛ÀﬂÀœ’˘“È∂Œ
	 */
	public TestModel testXzsszyd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘÷∏øÿ∂Œ¬‰
	 */
	public TestModel testZkdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±Áª§∂Œ¬‰
	 */
	public TestModel testBhdl(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘∏Ω¥¯√Ò ¬ÀﬂÀœ«Î«Û∂Œ
	 */
	public TestModel testFdmsssqqd(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                   String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘–Ã ¬±æ…Û…Û¿Ì∂Œ
	 */
	public TestModel testXsbssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘÷∏øÿ ¬ µ
	 */
	public TestModel testZkss(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘÷∏øÿ÷§æ›
	 */
	public TestModel testZkzj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘÷∏øÿ“‚º˚
	 */
	public TestModel testZkyj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±ª∏Ê»À±Á≥∆
	 */
	public TestModel testBgrbc(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘±Áª§»À±Áª§
	 */
	public TestModel testBhrbh(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘–Ã ¬«∞…Û…Û¿Ì∂Œ
	 */
	public TestModel testXsqssld(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘…œÀﬂ…ÍÀﬂ±Áª§“‚º˚
	 */
	public TestModel testSsssbhyj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘π´Àﬂª˙πÿ≥ˆÕ•“‚º˚
	 */
	public TestModel testGsjgctyj(TestModel testModel, WsajjbqkModel wsajjbqkModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;




}
