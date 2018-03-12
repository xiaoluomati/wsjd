package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.service.TestAjjbqkService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

public class TestAjjbqkServiceImpl implements TestAjjbqkService{

	@Override
	public TestModel testQsdl(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String qsdl = wsajjbqkModel.getQsdl();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(qsdl, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(qsdl + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testBsdl(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String bsdl = wsajjbqkModel.getBsdl();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(bsdl, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bsdl + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQspjd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String qspjd = wsajjbqkModel.getQspjd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(qspjd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(qspjd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQsygscd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String qsygscd = wsajjbqkModel.getQsygscd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(qsygscd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(qsygscd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQsbgbcd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String qsbgbcd = wsajjbqkModel.getQsbgbcd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(qsbgbcd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(qsbgbcd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQssld(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> qssldlist = wsajjbqkModel.getQssld();
			XMLReader xr = new XMLReader();
			TestMethodServiceImpl ts = new TestMethodServiceImpl();
			List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
			if(contentlist.get(0)=="无"){
				testModel.setCoNum(testModel.getCoNum() + 1);
				return testModel;
			}
			if(qssldlist!=null){
			boolean b=true;
			int qssldco=0;

			for(int i=0;i<qssldlist.size();i++){
				String qssld=qssldlist.get(i);
				for(int j=0;j<contentlist.size();j++){
					String content=contentlist.get(j);
					if (ts.judgeNode_3(qssld, content)) {
						qssldco++;
						break;
					} 	
				}
				if(qssldco<=i){
//					System.out.println(qssld);
				}
			}
			if (qssldco==qssldlist.size()) {
				testModel.setCoNum(testModel.getCoNum() + 1);
			} else {
//				FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//						filename);
			}
		
			}else{
				testModel.setCoNum(testModel.getCoNum() + 1);
				return testModel;
			}
		return testModel;
	}

	@Override
	public TestModel testQsfsscd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String qsfsscd = wsajjbqkModel.getQsfsscd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(qsfsscd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(qsbgbcd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testSsrscd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String ssrscd = wsajjbqkModel.getSsrscd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(ssrscd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(ssrscd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testBssrbcd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> bssrbcdlist = wsajjbqkModel.getBssrbcd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(bssrbcdlist!=null){
		boolean b=true;
		int bssldco=0;

		for(int i=0;i<bssrbcdlist.size();i++){
			String qssld=bssrbcdlist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(qssld, content)) {
					bssldco++;
					break;
				} 	
			}
		}
		if (bssldco==bssrbcdlist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bssrbcdlist);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}

	@Override
	public TestModel testBssld(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> bssldlist = wsajjbqkModel.getBssld();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(bssldlist!=null){
		boolean b=true;
		int bssldco=0;

		for(int i=0;i<bssldlist.size();i++){
			String qssld=bssldlist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(qssld, content)) {
					bssldco++;
					break;
				} 	
			}
			if(bssldco<=i){
//				System.out.println(qssld);
			}
		}
		if (bssldco==bssldlist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}

	@Override
	public TestModel testYgscd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String ygscd = wsajjbqkModel.getYgscd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(ygscd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(ygscd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testBgbcd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String bgbcd = wsajjbqkModel.getBgbcd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(bgbcd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bgbcd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testCmssd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> cmssdlist = wsajjbqkModel.getCmssd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(cmssdlist!=null){
		boolean b=true;
		int cmssdco=0;

		for(int i=0;i<cmssdlist.size();i++){
			String cmssd=cmssdlist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(cmssd, content)) {
					cmssdco++;
					break;
				} 	
			}
			if(cmssdco<=i){
//				System.out.println(qssld);
			}
		}
		if (cmssdco==cmssdlist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(cmssdlist);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}

	@Override
	public TestModel testZjd(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		List<String> zjdlist = wsajjbqkModel.getZjd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(zjdlist!=null){
		boolean b=true;
		int zjdco=0;

		for(int i=0;i<zjdlist.size();i++){
			String zjd=zjdlist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(zjd, content)) {
					zjdco++;
					break;
				} 	
			}
			if(zjdco<=i){
//				System.out.println(qssld);
			}
		}
		if (zjdco==zjdlist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(zjdlist);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}

	@Override
	public TestModel testDsryjd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String dsryjd = wsajjbqkModel.getDsryjd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(dsryjd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(dsryjd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testFsscd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String fsscd = wsajjbqkModel.getFsscd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(fsscd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(fsscd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testFsbcd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String fsbcd = wsajjbqkModel.getFsbcd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(fsbcd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(fsbcd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testXzsszyd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String xzsszyd = wsajjbqkModel.getXzsszyd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(xzsszyd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(xzsszyd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testQszjd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> qszjdlist = wsajjbqkModel.getQszjd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(qszjdlist!=null){
		boolean b=true;
		int zjdco=0;

		for(int i=0;i<qszjdlist.size();i++){
			String zjd=qszjdlist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(zjd, content)) {
					zjdco++;
					break;
				} 	
			}
			if(zjdco<=i){
//				System.out.println(qssld);
			}
		}
		if (zjdco==qszjdlist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(zjdlist);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}

	@Override
	public TestModel testBsdsryjd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String bsdsryjd = wsajjbqkModel.getBsdsryjd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(bsdsryjd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bsdsryjd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testBszjd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> bszjdlist = wsajjbqkModel.getBszjd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(bszjdlist!=null){
		boolean b=true;
		int zjdco=0;

		for(int i=0;i<bszjdlist.size();i++){
			String zjd=bszjdlist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(zjd, content)) {
					zjdco++;
					break;
				} 	
			}
			if(zjdco<=i){
//				System.out.println(qssld);
			}
		}
		if (zjdco==bszjdlist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bszjdlist);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}

	@Override
	public TestModel testZkdl(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String zkdl = wsajjbqkModel.getZkdl();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(zkdl, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(zkdl + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testBhdl(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String bhdl = wsajjbqkModel.getBhdl();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(bhdl, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bhdl + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testFdmsssqqd(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String fdmsssqqd = wsajjbqkModel.getFdmsssqqd();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(fdmsssqqd, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(fdmsssqqd + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testXsbssld(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String xsbssld = wsajjbqkModel.getXsbssld();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(xsbssld, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(xsbssld + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testZkss(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String zkss = wsajjbqkModel.getZkss();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(zkss, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(zkss + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testZkzj(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String zkzj = wsajjbqkModel.getZkzj();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(zkzj, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(zkzj + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testZkyj(TestModel testModel, WsajjbqkModel wsajjbqkModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String zkyj = wsajjbqkModel.getZkyj();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(zkyj, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(zkyj + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testBgrbc(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> bgrbclist = wsajjbqkModel.getBgrbc();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(bgrbclist!=null){
		boolean b=true;
		int zjdco=0;

		for(int i=0;i<bgrbclist.size();i++){
			String zjd=bgrbclist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(zjd, content)) {
					zjdco++;
					break;
				} 	
			}
			if(zjdco<=i){
//				System.out.println(qssld);
			}
		}
		if (zjdco==bgrbclist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bgrbclist);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}

	@Override
	public TestModel testBhrbh(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		List<String> bhrbhlist = wsajjbqkModel.getBhrbh();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		List<String> contentlist = xr.getXMLNodelist(inputpath + "\\" + filename, node[1]);
		if(contentlist.get(0)=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if(bhrbhlist!=null){
		boolean b=true;
		int zjdco=0;

		for(int i=0;i<bhrbhlist.size();i++){
			String zjd=bhrbhlist.get(i);
			for(int j=0;j<contentlist.size();j++){
				String content=contentlist.get(j);
				if (ts.judgeNode_3(zjd, content)) {
					zjdco++;
					break;
				} 	
			}
			if(zjdco<=i){
//				System.out.println(qssld);
			}
		}
		if (zjdco==bhrbhlist.size()) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(bhrbhlist);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
	
		}else{
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
	return testModel;
	}


	@Override
	public TestModel testXsqssld(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String xsqssld = wsajjbqkModel.getXsqssld();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(xsqssld, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(xsqssld + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testSsssbhyj(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String ssssbhyj = wsajjbqkModel.getSsssbhyj();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(ssssbhyj, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(ssssbhyj + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}

	@Override
	public TestModel testGsjgctyj(TestModel testModel,
			WsajjbqkModel wsajjbqkModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		String gsjgctyj = wsajjbqkModel.getGsjgctyj();
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts = new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath + "\\" + filename, node[1]);
		if(content=="无"){
			testModel.setCoNum(testModel.getCoNum() + 1);
			return testModel;
		}
		if (ts.judgeNode_3(gsjgctyj, content)) {
			testModel.setCoNum(testModel.getCoNum() + 1);
		} else {
//			System.out.println(gsjgctyj + "\n" + content);
//			FileUtil.fileCopy(inputpath, filename, specialpath + "wsspecial",
//					filename);
		}
		return testModel;
	}
}
