package nju.software.wsjx.service;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import org.xml.sax.SAXException;
public interface TestfdService {
	/**
	 * ≤‚ ‘Œƒ ◊∂Œ
	 */
	public TestModel testWs(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘ÀﬂÀœ≤Œ”Î»À∂Œ
	 */
	public TestModel testSscyr(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘ÀﬂÀœº«¬º∂Œ
	 */
	public TestModel testSsjl(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘∞∏º˛ª˘±æ«Èøˆ∂Œ
	 */
	public TestModel testAjjbqk(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘≤√≈–∑÷Œˆπ˝≥Ã∂Œ
	 */
	public TestModel testCpfxgc(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘≤√≈–Ω·π˚∂Œ
	 */
	public TestModel testCpjg(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘ŒƒŒ≤∂Œ
	 */
	public TestModel testWw(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * ≤‚ ‘∏Ω¬º∂Œ
	 */
	public TestModel testFl(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
