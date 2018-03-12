package nju.software.wsjx.service.impl;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.service.TestWsService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

public class TestWsServiceImpl implements TestWsService{
	/**
	 * 测试文书制作单位
	 */
	@Override
	public TestModel testWszzdw(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		String wszzdw = wswsmodel.getWszzdw();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (wszzdw == null || content == null) {
			System.out.println(wszzdw + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "wszzdwspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(wszzdw, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(wszzdw + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "wszzdwspecial",
					filename);
		}
		return testModel;
	}

	/**
	 * 测试经办法院
	 */
	@Override
	public TestModel testJbfy(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String jbfy = wswsmodel.getJbfy();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (jbfy == null || content == null) {
			System.out.println(jbfy + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "jbfyspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(jbfy, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(jbfy + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "jbfyspecial",
					filename);
		}
		return testModel;
	}

	/**
	 * 测试案号
	 */
	@Override
	public TestModel testAh(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String ah = wswsmodel.getAh();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (ah == null || content == null) {
			System.out.println(ah + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "ahspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(ah, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(ah + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "ahspecial",
					filename);
		}
		return testModel;

	}
	/**
	 * 测试立案年度
	 */
	@Override
	public TestModel testLand(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
	throws XPathExpressionException, ParserConfigurationException,
	SAXException, IOException {
		
		String land=wswsmodel.getLand();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (land == null || content == null) {
			System.out.println(land + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "landspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(land, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(land + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "landspecial",
					filename);
		}
		return testModel;
		
	}
	/**
	 * 测试法院级别
	 */
	@Override
	public TestModel testFyjb(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		String fyjb=wswsmodel.getFyjb();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (fyjb == null || content == null) {
			System.out.println(fyjb + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "fyjbspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(fyjb, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(fyjb + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "fyjbspecial",
					filename);
		}
		return testModel;
		
	}
	/**
	 * 测试行政区划(省)
	 */
	@Override
	public TestModel testXzqhProv(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		String xzqhProv=wswsmodel.getXzqhProv();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (xzqhProv == null || content == null) {
			System.out.println(xzqhProv + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "xzqhProvspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(xzqhProv, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(xzqhProv + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "xzqhProvspecial",
					filename);
		}
		return testModel;
	}
	/**
	 * 测试行政区划（市）
	 * 
	 */
	@Override
	public TestModel testXzqhCity(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		String xzqhCity=wswsmodel.getXzqhCity();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (xzqhCity == null || content == null) {
			System.out.println(xzqhCity + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "xzqhCityspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(xzqhCity, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(xzqhCity + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "xzqhCityspecial",
					filename);
		}
		return testModel;
	}
	
	/**
	 * 测试案件性质
	 */
	@Override
	public TestModel testAjxz(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		String ajxz=wswsmodel.getAjxz();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (ajxz == null || content == null) {
			System.out.println(ajxz + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "ajxzspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(ajxz, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(ajxz + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "ajxzspecial",
					filename);
		}
		return testModel;
	}
	
	/**
	 * 测试文书种类
	 */
	
	@Override
	public TestModel testWszl(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		String wszl=wswsmodel.getWszl();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (wszl == null || content == null) {
			System.out.println(wszl + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "wszlspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_1(wszl, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(wszl + "    " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "wszlspecial",
					filename);
		}
		return testModel;
	}

	/**
	 * 测试文书名称
	 */
	@Override
	public TestModel testWsmc(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String wsmc = wswsmodel.getWsmc();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (wsmc == null || content == null) {
			System.out.println(wsmc + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "wsmcspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_2(wsmc, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(wsmc + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "wsmcspecial",
					filename);
		}
		return testModel;
	}

	


	/**
	 * 测试审判程序
	 */
	@Override
	public TestModel testSpcx(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String spcx = wswsmodel.getSpcx();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (spcx == null || content == null) {
			System.out.println(spcx + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "spcxspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_2(spcx, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(spcx + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "spcxspecial",
					filename);
		}
		return testModel;
	}
	/**
	 * 测试案件类型
	 */
	@Override
	public TestModel testAjlx(TestModel testModel, WswsModel wswsmodel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		String ajlx = wswsmodel.getAjlx();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (ajlx == null || content == null) {
			System.out.println(ajlx + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "ajlxspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_2(ajlx, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(ajlx + "   " + content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "ajlxspecial",
					filename);
		}
		return testModel;
	}
}
