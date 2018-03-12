package nju.software.wsjx.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WswsModel;

import org.xml.sax.SAXException;

/**
 * 文书测试的案件信息模型
 * @author super
 *
 */
public interface TestModelService {
	/**
	 * 测试经办法院节点
	 * @param wsnr
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws XPathExpressionException 
	 */
	public List judgeJBFY(WswsModel wswsmodel, String inputpath, String filename, List testModellist) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException;
	
}
