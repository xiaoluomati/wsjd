package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import nju.software.wsjx.service.TestfdService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

public class TestfdServiceImpl implements TestfdService{

	@Override
	public TestModel testWs(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String ws = wsfdModel.getWs();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (ws == null || content == null) {
//			System.out.println(ws + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
			return testModel;
		}
		if (ts.judgeNode_3(ws, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(ws + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testSscyr(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String sscyr = wsfdModel.getSscyr();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (sscyr == null || content == null) {
//			System.out.println(sscyr + "\n"+ content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "sscyrspecial",
//					filename);
			return testModel;
		}
		if (ts.judgeNode_3(sscyr, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(sscyr + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "sscyrspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testSsjl(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String ssjl = wsfdModel.getSsjl();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (ssjl == null || content == null) {
//			System.out.println(ssjl + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "ssjlspecial",
//					filename);
			return testModel;
		}
		if (ts.judgeNode_3(ssjl, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(ssjl + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "ssjlspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testAjjbqk(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String ajjbqk = wsfdModel.getAjjbqk();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "нч" ) {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ajjbqk == null ) {
//			System.out.println(ajjbqk + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "ajjbqkspecial",
//					filename);
			return testModel;
		}
		if (ts.judgeNode_3(ajjbqk, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(ajjbqk + "\n"+ content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "ajjbqkspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testCpfxgc(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String cpfxgc = wsfdModel.getCpfxgc();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (cpfxgc == null || content == null) {
//			System.out.println(cpfxgc + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "cpfxgcspecial",
//					filename);
			return testModel;
		}
		if (ts.judgeNode_3(cpfxgc, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(cpfxgc + "\n"+ content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "cpfxgcspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testCpjg(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String cpjg = wsfdModel.getCpjg();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (cpjg == null || content == null) {
//			System.out.println(cpjg + "\n"+ content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "cpjgspecial",
//					filename);
			return testModel;
		}
		if (ts.judgeNode_3(cpjg, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(cpjg +"\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "cpjgspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testWw(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String ww = wsfdModel.getWw();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (ww == null || content == null) {
//			System.out.println(ww + "\n"+ content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wwspecial",
//					filename);
			return testModel;
		}
		if (ts.judgeNode_3(ww, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
			System.out.println(ww + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wwspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testFl(TestModel testModel, WsfdModel wsfdModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String fl = wsfdModel.getFl();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (fl == null || content == null) {
			System.out.println(fl + "\n"+ content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "flspecial",
					filename);
			return testModel;
		}
		if (ts.judgeNode_3(fl, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(fl + "\n"+ content);
			FileUtil.fileCopy(inputpath, filename, specialpath + "flspecial",
					filename);
		}
		return testModel;
	}

}
