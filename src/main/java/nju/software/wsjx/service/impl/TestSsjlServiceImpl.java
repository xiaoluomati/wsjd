package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import nju.software.wsjx.model.wsSegmentationModel.WsssjlModel;
import nju.software.wsjx.service.TestSsjlService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

import org.xml.sax.SAXException;

public class TestSsjlServiceImpl implements TestSsjlService {

	@Override
	public TestModel testAy(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getAy() != null) {
			String ay = wsssjlModel.getAy();
			if (ts.judgeNode_1(ay, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else if (ts.judgeNode_2(content, ay)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else if (ts.judgeNode_2(ay, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("案由" + ay + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ayspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testWzay(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getWzay() != null) {
			String wzay = wsssjlModel.getWzay();
			if (ts.judgeNode_1(wzay, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else if (ts.judgeNode_2(content, wzay)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("完整案由" + wzay + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"wzayspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testAydm(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getAydm() != null) {
			String aydm = wsssjlModel.getAydm();
			if (ts.judgeNode_1(content, aydm)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("案由代码 :" + aydm + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testAjly(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getAjly() != null) {
			String ajly = wsssjlModel.getAjly();
			if (ts.judgeNode_2(content, ajly)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("案件来源 :" + ajly + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajlyspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testAjsj(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getAjsj() != null) {
			String ajsj = wsssjlModel.getAjsj();
			if (ts.judgeNode_1(ajsj, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("案件涉及" + ajsj + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajsjspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testKtsl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getKtsl() != null) {
			String ktsl = wsssjlModel.getKtsl();
			if (ts.judgeNode_1(ktsl, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("开庭审理" + ktsl + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktslspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testKtrq(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		// String content = xr.getXMLNode(inputpath+"\\"+filename,node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getKtrq() != null) {
			ArrayList<String> ktrq = wsssjlModel.getKtrq();
			if (contentList.containsAll(ktrq) || ktrq.containsAll(contentList)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("开庭日期" + ktrq + "   ");
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktrqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getQsah() != null) {
			ArrayList<String> qsah = wsssjlModel.getQsah();
			for (int i = 0; i < qsah.size(); i++) {
				for (int j = 0; j < contentList.size(); j++) {
					if (ts.judgeNode_1(qsah.get(i), contentList.get(j))) {
						testModel.setCoNum(testModel.getCoNum() + 1);
					}
					// System.out.println("前审案号："+qsah.get(i)+"   "+contentList.get(j));
				}
			}
			// FileUtil.fileCopy(inputpath, filename, specialpath+"qsahspecial",
			// filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQsfy(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		else if (wsssjlModel.getQsfy() != null) {
			String qsfy = wsssjlModel.getQsfy();
			if (contentList.contains(qsfy)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "前审法院：" + qsfy + "   " + contentList.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"qsfyspecial", filename);
			}
		}else{
//			System.out.println(filename + "前审法院：" + "   " + contentList.get(0));
		}
		return testModel;
	}

	@Override
	public TestModel testKtslxx(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getKtslxx() != null) {
			String ktslxx = wsssjlModel.getKtslxx();
			if (ts.judgeNode_1(ktslxx, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("开庭审理信息" + ktslxx + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktslxxspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testBgkslyy(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getBgkslyy() != null) {
			String bgkslyy = wsssjlModel.getBgkslyy();
			if (ts.judgeNode_1(bgkslyy, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("不公开审理原因" + bgkslyy + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"bgkslyyspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testLarq(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getLarq() != null) {
			String larq = wsssjlModel.getLarq();
			if (ts.judgeNode_1(larq, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("立案日期" + larq + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"larqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testYsajsycx(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getYsajsycx() != null) {
			String ysajsycx = wsssjlModel.getYsajsycx();
			if (ts.judgeNode_1(ysajsycx, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("一审案件适用程序" + ysajsycx + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ysajsycxspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testJyzpt(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getJyzpt() != null) {
			String jyzpt = wsssjlModel.getJyzpt();
			if (ts.judgeNode_1(jyzpt, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("简易转普通" + jyzpt + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"jyzptspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testYsajly(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getYsajly() != null) {
			String ysajly = wsssjlModel.getYsajly();
			if (ts.judgeNode_2(content, ysajly)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("一审案件来源" + ysajly + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ysajlyspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSlrq(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getSlrq() != null) {
			String slrq = wsssjlModel.getSlrq();
			if (ts.judgeNode_1(slrq, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("受理日期" + slrq + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"slrqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSpzz(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getSpzz() != null) {
			String spzz = wsssjlModel.getSpzz();
			if (ts.judgeNode_1(spzz, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("审判组织" + filename + spzz + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"spzzspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testDrsp(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		// if(!content.contains("无")&&!content.contains("否")){
		// System.out.println(filename +"   "+ content);
		// }
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getDrsp() != null) {
			String drsp = wsssjlModel.getDrsp();
			if (ts.judgeNode_1(drsp, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				// System.out.println("独任审判"+drsp+"   "+content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"drspspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSqcsrq(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getSqcsrq() != null) {
			String sqcsrq = wsssjlModel.getSqcsrq();
			if (ts.judgeNode_1(sqcsrq, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				// System.out.println("申请撤诉日期"+sqcsrq+"   "+content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"sqcsrqspecial", filename);
			}
		} else {
			System.out.println("申请撤诉日期" + content);
		}
		return testModel;
	}

	@Override
	public TestModel testQxrxx(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (!wsssjlModel.getQxrxx().isEmpty()) {
			int index = 0;
			HashMap<String, String> map = wsssjlModel.getQxrxx();
			;
			for (Entry<String, String> entry : map.entrySet()) {
				if (contentlist.contains(entry.getKey())) {
					index++;
				} else {
					System.out.println(filename + entry.getKey());
				}
			}
			if (index == map.size()) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("缺席人信息");
			}
		}
		return testModel;
	}

	@Override
	public TestModel testCtrxx(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (!wsssjlModel.getCtrxx().isEmpty()) {
			int index = 0;
			HashMap<String, String> map = wsssjlModel.getCtrxx();
			for (Entry<String, String> entry : map.entrySet()) {
				if (contentlist.contains(entry.getKey())) {
					index++;
					testModel.setCoNum(testModel.getCoNum() + 1);
					break;
					// System.out.println(entry.getKey());
				} else {
					// System.out.println(filename+entry.getKey()+"出错---------------------出错");
				}
				if (index < map.size()) {
					// testModel.setCoNum(testModel.getCoNum() + 1);
				} else {
					System.out.println("出庭人信息" + map);
					System.out.println("出庭人信息" + contentlist);
				}
				break;
			}
		}
		return testModel;
	}

	@Override
	public TestModel testBgzyldct(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getBgzyldct() != null) {
			String bgzyldct = wsssjlModel.getBgzyldct();
			if (ts.judgeNode_1(bgzyldct, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("被告主要领导出庭" + filename + bgzyldct + "   "
						+ content);
//				 FileUtil.fileCopy(inputpath,
//				 filename,specialpath+"bgzyldctqspecial", filename);
			}
		}
		if(content.contains("是")){
			FileUtil.fileCopy(inputpath,
					 filename,specialpath+"bgzyldctqspecial", filename);
		}
		return testModel;
	}

	@Override
	public TestModel testXzxwzl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if ((content == "无") || (content.contains("其他"))) {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getXzxwzl() != null) {
			String xzxwzl = wsssjlModel.getXzxwzl();
			if (ts.judgeNode_1(xzxwzl, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("行政行为种类" + filename + xzxwzl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath,
				// filename,specialpath+"xzxwzlspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testXzqqxwzl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getXzqqxwzl() != null) {
			String xzqqxwzl = wsssjlModel.getXzqqxwzl();
			if (ts.judgeNode_1(xzqqxwzl, content)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("行政侵权行为种类" + filename + xzqqxwzl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath,
				// filename,specialpath+"xzxwzlspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsrq(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getQsrq() != null) {
			String qsrq = wsssjlModel.getQsrq();
			if (ts.judgeNode_1(content, qsrq)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println("起诉日期" + qsrq + "   ");
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ktrqspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSsxz(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			System.out.println(filename + "----" + content);
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getSsxz() != null) {
			String ssxz = wsssjlModel.getSsxz();
			if (ts.judgeNode_1(content, ssxz)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out
						.println(filename + "诉讼性质 :" + ssxz + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testWzzm(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		// else if (wsssjlModel.getWsssjlZkxx().get(0).getWsssjlZkzm() != null)
		// {
		// // HashMap<String,String> zkzm = wsssjlModel.getZkzm();
		// boolean bl = true;
		// for(int i=0;i<contentlist.size();i++){
		// // if(!zkzm.containsKey(contentlist.get(i))){
		// // bl = false;
		// // }
		// }
		// if (bl) {
		// testModel.setCoNum(testModel.getCoNum() + 1);
		// }
		else {
			System.out.println(filename + "完整罪名:" + "   " + contentlist);
			// FileUtil.fileCopy(inputpath, filename,
			// specialpath+"wzzmspecial", filename);
		}
		// }
		return testModel;
	}

	@Override
	public TestModel testZmdm(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		return testModel;
	}

	@Override
	public TestModel testGsjg(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getGsjg() != null) {
			String gsjg = wsssjlModel.getGsjg();
			if (ts.judgeNode_2(gsjg, content) || ts.judgeNode_2(content, gsjg)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out
						.println(filename + "公诉机关 :" + gsjg + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testGsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getGsah() != null) {
			String gsah = wsssjlModel.getGsah();
			if (ts.judgeNode_1(content, gsah)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out
						.println(filename + "公诉案号 :" + gsah + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testMsbfjxsl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getMsbfjxsl() != null) {
			String msbfjxsl = wsssjlModel.getMsbfjxsl();
			if (ts.judgeNode_1(content, msbfjxsl)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "附带民事部分继续审理 :" + msbfjxsl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testJcyjyyqsl(TestModel testModel,
			WsssjlModel wsssjlModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getJcyjyyqsl() != null) {
			String jcyjyyqsl = wsssjlModel.getJcyjyyqsl();
			if (ts.judgeNode_1(content, jcyjyyqsl)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "检察院建议延期审理:" + jcyjyyqsl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsland(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsland() != null) {
			String qsland = wsssjlModel.getQsland();
			if (ts.judgeNode_1(content, qsland)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "前审立案年度:" + qsland + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsfyjc(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentlist = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentlist.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsfyjc() != null) {
			String qsfyjc = wsssjlModel.getQsfyjc();
			if (contentlist.contains(qsfyjc)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "前审法院简称:" + qsfyjc + "   "+contentlist.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQswszl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQswszl() != null) {
			String qswszl = wsssjlModel.getQswszl();
			if (ts.judgeNode_1(content, qswszl)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "前审文书种类:" + qswszl + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQssj(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQssj() != null) {
			String qssj = wsssjlModel.getQssj();
			if (ts.judgeNode_1(content, qssj)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "前审审级:" + qssj + "   " + content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsajyl(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsajyl() != null) {
			String ajly = wsssjlModel.getQsajyl();
			if (contentList.contains(ajly)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "前审案件由来:" + ajly + "   "
						+ contentList.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}

		}
		return testModel;
	}

	@Override
	public TestModel testXzesqsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getXzesqsah() != null) {
			String xzesqsah = wsssjlModel.getXzesqsah();
			if (contentList.contains(xzesqsah)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "行政二审前审案号:" + xzesqsah);
				// "   " + contentList.get(j));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testSshksfw(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getSshksfw() != null) {
			String sshksfw = wsssjlModel.getSshksfw();
			if (ts.judgeNode_1(content, sshksfw)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "上诉或抗诉范围:" + sshksfw + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsjafs(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsjafs() != null) {
			String qsjafs = wsssjlModel.getQsjafs();
			if (contentList.contains(qsjafs)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "前审结案方式:" + qsjafs + "   "
						+ contentList.get(0));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQspj(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQspj() != null) {
			String qspj = wsssjlModel.getQspj();
			for (int i = 0; i < contentList.size(); i++) {
				if (ts.judgeNode_2(contentList.get(i), qspj)
						|| ts.judgeNode_2(qspj, contentList.get(i))) {
					testModel.setCoNum(testModel.getCoNum() + 1);
					break;
				}
			}
//			 else {
//				System.out.println(filename + "前审判决:" + qspj + "   " + contentList.get(0));
//				// FileUtil.fileCopy(inputpath, filename,
//				// specialpath+"ajdmspecial", filename);
//			}
		}
		return testModel;
	}

	@Override
	public TestModel testXsesqsah(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getXsesqsah() != null) {
			String xsesqsah = wsssjlModel.getXsesqsah();
			if (contentList.contains(xsesqsah)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "刑事二审前审案号:" + xsesqsah);
				// "   " + contentList.get(j));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQscpsj(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQscpsj() != null) {
			String qscpsj = wsssjlModel.getQscpsj();
			if (ts.judgeNode_1(content, qscpsj)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}else {
				System.out.println(filename + "前审裁判时间:" + qscpsj + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testQsgsjg(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if (content == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		} else if (wsssjlModel.getQsgsjg() != null) {
			String ygsjg = wsssjlModel.getQsgsjg();
			if (ts.judgeNode_1(content, ygsjg)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			}else {
				System.out.println(filename + "前审公诉机关:" + ygsjg + "   "
						+ content);
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}

	@Override
	public TestModel testBzfymc(TestModel testModel, WsssjlModel wsssjlModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr = new XMLReader();
		ArrayList<String> contentList = xr.getXMLNodelist(inputpath + "\\"
				+ filename, node[1]);
		if (contentList.get(0) == "无") {
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (wsssjlModel.getBzfymc() != null) {
			String bzfymc = wsssjlModel.getBzfymc();
			if (contentList.contains(bzfymc)) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
				System.out.println(filename + "标准法院名称:" + bzfymc);
				// "   " + contentList.get(j));
				// FileUtil.fileCopy(inputpath, filename,
				// specialpath+"ajdmspecial", filename);
			}
		}
		return testModel;
	}
}
