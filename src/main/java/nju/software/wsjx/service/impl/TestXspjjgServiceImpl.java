package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.XspjjgfzModel;
import nju.software.wsjx.service.TestXspjjgService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.service.model.xs.FjxModel;
import nju.software.wsjx.service.model.xs.PfModel;
import nju.software.wsjx.service.model.xs.XsPjjgModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.util.XMLReader;

public class TestXspjjgServiceImpl implements TestXspjjgService{

	@Override
	public TestModel testKssz(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getKssz();//可上诉至
		if(StringUtil.equals(content, ssfy) || content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testSsqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getSsqx();
		if(StringUtil.equals(content, ssfy) || content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testTjcl(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getSstjcl();
		if(StringUtil.equals(content, ssfy) || content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testTcgxyy(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getTcgxyy();
		if(StringUtil.equals(content, ssfy) || content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testJafs(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String jafs = pjjgModel.getJafs();
//		if(judgeXsysJafs(jafs, content)){
		if(judgeXsesJafs(jafs, content)){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+jafs);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	public boolean judgeXsysJafs(String jafs,String content){
		if(StringUtil.equals(jafs, content)||StringUtil.equals(content, "无")){
			return true;
		}else if((StringUtil.equals(jafs, "不予受理")||StringUtil.equals(jafs, "不予立案")||StringUtil.equals(jafs, "不予登记立案"))&&StringUtil.equals(content, "不予受理")){
			return true;
		}else if((StringUtil.equals(jafs, "准予撤诉")||StringUtil.equals(jafs, "按撤诉处理"))&&StringUtil.equals(content, "撤诉")){
			return true;
		}else if(StringUtil.equals(jafs, "终止审理")&&StringUtil.equals(content, "终止")){
			return true;
		}else if (StringUtil.equals(content, "其他")){
			return true;
		}
		return false;
	}

	public boolean judgeXsesJafs(String jafs,String content){
		if(StringUtil.equals(jafs, content)||StringUtil.equals(content, "无")){
			return true;
		}else if((StringUtil.contains(jafs, "撤销原裁定")||StringUtil.contains(jafs, "变更没收违法所得裁定")||StringUtil.contains(jafs, "准许撤回自诉并撤销一审裁判"))
				&&StringUtil.contains(content, "撤销变更原裁定")){
			return true;
		}else if(StringUtil.contains(jafs, "撤回")&&StringUtil.contains(content, "撤诉")){
			return true;
		}else if(StringUtil.contains(jafs, "终止审理")&&StringUtil.contains(content, "终止")){
			return true;
		}
		return false;
	}
	@Override
	public TestModel testYsxsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String jafs = pjjgModel.getYsxsbfpjjg();
		if(StringUtil.equals(content, jafs)){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("一审刑事部分裁判结果测试："+jafs);
			System.out.println("一审刑事部分裁判结果华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testXspjjgfzsscyr(TestModel testModel,
			XsPjjgModel pjjgModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getSscyr())){
					cyrList.add(fzModel.getSscyr());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("测试："+cyrList);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}
	
	/**
	 * @param content
	 * @param tests
	 * @return
	 */
	public boolean containsStr(List<String> test,List<String> contents){
		for(int i=0;i<contents.size();i++){
			if(!test.contains(contents.get(i))){
				return false;
			}
		}
		return true;
	}

	@Override
	public TestModel testBsdzpfzm(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZm()!=null && !StringUtil.isBlank(pf.getZm().getZm())){
							 zmList.add(pf.getZm().getZm());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪罪名测试："+zmList);
			System.out.println("单罪罪名华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testBsxzpfzm(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getPjzzm()!=null && !StringUtil.isBlank(fzModel.getPjzzm().getZm())){
					cyrList.add(fzModel.getPjzzm().getZm());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("测试："+cyrList);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}
	
	public TestModel testZxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZx()!=null && !StringUtil.isBlank(pf.getZx().getZxlb())){
							 zmList.add(pf.getZx().getZxlb());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪主刑类别测试："+zmList);
			System.out.println("单罪主刑类别华宇："+contents);
		}
		return testModel;
	}
	
	@Override
	public TestModel testZxpfZxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getZx()!=null){
					 if(!StringUtil.isBlank(fzModel.getZxpf().getZx().getZxlb())){
						 zmList.add(fzModel.getZxpf().getZx().getZxlb());
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("执行主刑类别测试："+zmList);
			System.out.println("执行主刑类别华宇："+contents);
		}
		return testModel;
	}
	public TestModel testZxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZx()!=null && !StringUtil.isBlank(pf.getZx().getZxxq())){
							 zmList.add(pf.getZx().getZxxq());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪主刑期限测试："+zmList);
			System.out.println("单罪主刑期限华宇："+contents);
		}
		return testModel;
	}
	
	@Override
	public TestModel testZxpfZxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getZx()!=null){
					 if(!StringUtil.isBlank(fzModel.getZxpf().getZx().getZxxq())){
						 zmList.add(fzModel.getZxpf().getZx().getZxxq());
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("执行主刑期限测试："+zmList);
			System.out.println("执行主刑期限华宇："+contents);
		}
		return testModel;
	}
	public TestModel testHxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getHx()!=null && !StringUtil.isBlank(pf.getHx().getZxlb())){
							 zmList.add(pf.getHx().getZxlb());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪缓刑类别期限测试："+zmList);
			System.out.println("单罪缓刑类别华宇："+contents);
		}
		return testModel;
	}
	public TestModel testHxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getHx()!=null && !StringUtil.isBlank(pf.getHx().getZxxq())){
							 zmList.add(pf.getHx().getZxxq());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪缓刑类别期限测试："+zmList);
			System.out.println("单罪缓刑类别华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testZxHxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getHx()!=null && !StringUtil.isBlank(fzModel.getZxpf().getHx().getZxlb())){
					zmList.add(fzModel.getZxpf().getHx().getZxlb());
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("执行缓刑类别期限测试："+zmList);
			System.out.println("执行缓刑类别华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testZxHxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && fzModel.getZxpf().getHx()!=null && !StringUtil.isBlank(fzModel.getZxpf().getHx().getZxxq())){
					zmList.add(fzModel.getZxpf().getHx().getZxxq());
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("执行判罚缓刑类别期限测试："+zmList);
			System.out.println("执行判罚缓刑类别华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testPjjglx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(!StringUtil.isBlank(pf.getPjjglx())){
							 zmList.add(pf.getPjjglx());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪判决类型期限测试："+zmList);
			System.out.println("单罪判决类型华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testZxPjjglx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getZxpf()!=null && !StringUtil.isBlank(fzModel.getZxpf().getPjjglx())){
					zmList.add(fzModel.getZxpf().getPjjglx());
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("执行判罚判决结果类型测试："+zmList);
			System.out.println("执行判罚判决结果类型华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testXqksrq(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getXqksrq())){
					cyrList.add(fzModel.getXqksrq());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("刑期开始测试："+cyrList);
			System.out.println("刑期开始华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testXqjsrq(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getXqjsrq())){
					cyrList.add(fzModel.getXqjsrq());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("刑期结束测试："+cyrList);
			System.out.println("刑期结束华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testXqzdbf(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getXqzdbf())){
					cyrList.add(fzModel.getXqzdbf());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("刑期折抵办法测试："+cyrList);
			System.out.println("刑期折抵办法开始华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testSzbf(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getSzbf())){
					cyrList.add(fzModel.getSzbf());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("数罪并罚测试："+cyrList);
			System.out.println("数罪并罚华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testMzhwzsf(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getMzhwzsf())){
					cyrList.add(fzModel.getMzhwzsf());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("免罪或无罪释放测试："+cyrList);
			System.out.println("免罪或无罪释放华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testHblx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> cyrList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(!StringUtil.isBlank(fzModel.getHblx())){
					cyrList.add(fzModel.getHblx());
				}
			}
			flag = containsStr(cyrList,contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("合并量刑测试："+cyrList);
			System.out.println("合并量刑华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testDfdmspcdcl(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getDfdmspccl();
		if(StringUtil.equals(content, ssfy) || content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testFdmsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getFdmscpjg();
		if(StringUtil.equals(content, ssfy) || content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxlb(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getLb())){
									 zmList.add(fjx.getLb()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪附加刑类别期限测试："+zmList);
			System.out.println("单罪附加刑类别华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxqx(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getQx())){
									 zmList.add(fjx.getQx()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪附加刑期限期限测试："+zmList);
			System.out.println("单罪附加刑期限华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxje(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getSe())){
									 zmList.add(fjx.getSe()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪附加刑数额期限测试："+zmList);
			System.out.println("单罪附加刑数额华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testFjxbz(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getFjxList()!=null){
							 for(FjxModel fjx:pf.getFjxList()){
								 if(fjx!=null && !StringUtil.isBlank(fjx.getBz())){
									 zmList.add(fjx.getBz()); 
								 }
							 }
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪附加刑币种期限测试："+zmList);
			System.out.println("单罪附加刑币种华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testGpyy(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
			XMLReader xr = new XMLReader();
			List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
			List<String> gpyyz = pjjgModel.getGpyy();
			boolean flag = true;
			if(!contents.get(0).equals("无") && gpyyz!=null){
				for(String content:contents){
					if(!gpyyz.contains(content)){
						flag = false;
						break;
					}
				}
			}
			
			if(flag){
				testModel.setCoNum(testModel.getCoNum()+1);
			}else{
					System.out.println("测试："+gpyyz);
				System.out.println("华宇："+contents);
			}
			return testModel;
	}

	@Override
	public TestModel testJtfz(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = pjjgModel.getJtfz();//集团犯罪
		if(StringUtil.equals(content, ssfy) || content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testBsdzpfzmdm(TestModel testModel, XsPjjgModel pjjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<XspjjgfzModel> fzmModels = pjjgModel.getPjjgfzModels();
		boolean flag=true; 
		List<String> zmList = new ArrayList<String>();
		if(fzmModels==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(fzmModels!=null && !contents.get(0).equals("无")){
			for(XspjjgfzModel fzModel:fzmModels){
				if(fzModel.getDzpf()!=null){
					 for(PfModel pf:fzModel.getDzpf()){
						 if(pf.getZm()!=null && !StringUtil.isBlank(pf.getZm().getZmdm())){
							 zmList.add(pf.getZm().getZmdm());
						 }
					 }
				}
			}
			flag = containsStr(zmList, contents);
		}
		 
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"sscyrSpecial", filename);
			System.out.println("单罪罪名测试："+zmList);
			System.out.println("单罪罪名华宇："+contents);
		}
		return testModel;
	}

}
