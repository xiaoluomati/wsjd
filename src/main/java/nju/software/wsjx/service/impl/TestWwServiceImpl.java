package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;


import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.service.TestWwService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.XMLReader;

public class TestWwServiceImpl implements TestWwService{

	@Override
	public TestModel testSpryxm(TestModel testModel, WswwModel wswwModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
//		所有审判人员姓名list
		ArrayList<String> contentlist=xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		int index=0;
		boolean flag = true;
		if(!contentlist.get(0).equals("无")&&wswwModel!=null && wswwModel.getSpzzcyMap()!=null){
			HashMap<String , String> spzzcyMap = wswwModel.getSpzzcyMap();
			for(String content:contentlist){
				if(!spzzcyMap.containsKey(content)){
					flag = false;
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("华宇:"+contentlist);
		}
		return testModel;
	}

	@Override
	public TestModel testSpryjs(TestModel testModel, WswwModel wswwModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
//		所有审判人员姓名list
		ArrayList<String> contentlist=xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		boolean flag = true;
		String s = "";
		if(!contentlist.get(0).equals("无")&&wswwModel!=null && wswwModel.getSpzzcyMap()!=null){
			HashMap<String , String> spzzcyMap = wswwModel.getSpzzcyMap();
			for(String content:contentlist){
				if(!spzzcyMap.containsValue(content)){
					flag = false;
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("华宇:"+contentlist);
		}
		return testModel;
	}

	@Override
	public TestModel testCpsj(TestModel testModel, WswwModel wswwModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
        if(wswwModel!=null){
        	String cprq = wswwModel.getWsrq();
        	if(cprq!=null && ts.judgeNode_1(cprq, content)){
        		testModel.setCoNum(testModel.getCoNum()+1);
        	}else{
        		System.out.println("裁判时间 "+cprq+"   "+content);		
//    			FileUtil.fileCopy(inputpath, filename, specialpath+"cpsjspecial", filename);
        	}
        }
		return testModel;
	}

	@Override
	public TestModel testJand(TestModel testModel, WswwModel wswwModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
        if(wswwModel!=null){
        	String year = wswwModel.getYear();
        	if(year!=null && ts.judgeNode_1(year, content)){
        		testModel.setCoNum(testModel.getCoNum()+1);
        	}else{
        		System.out.println("结案年度 "+year+"   "+content);		
//    			FileUtil.fileCopy(inputpath, filename, specialpath+"yearspecial", filename);
        	}
        }
		return testModel;
	}

	@Override
	public TestModel testJanyr(TestModel testModel, WswwModel wswwModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
        if(wswwModel!=null){
        	String cprq = wswwModel.getWsrq();
        	if(cprq!=null && ts.judgeNode_1(cprq, content)){
        		testModel.setCoNum(testModel.getCoNum()+1);
        	}else{
        		System.out.println("结案年月日 "+cprq+"   "+content);		
//    			FileUtil.fileCopy(inputpath, filename, specialpath+"janyrspecial", filename);
        	}
        }
		return testModel;
	}

	@Override
	public TestModel testJany(TestModel testModel, WswwModel wswwModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
        if(wswwModel!=null){
        	String cprq = wswwModel.getYearAndMonth();
        	if(cprq!=null && ts.judgeNode_1(cprq, content)){
        		testModel.setCoNum(testModel.getCoNum()+1);
        	}else{
        		System.out.println("裁判时间 "+cprq+"   "+content);		
//    			FileUtil.fileCopy(inputpath, filename, specialpath+"janyspecial", filename);
        	}
        }
		return testModel;
	}

	@Override
	public TestModel testJay(TestModel testModel, WswwModel wswwModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
        if(wswwModel!=null){
        	String cprq = wswwModel.getMonth();
        	if(cprq!=null &&ts.judgeNode_1(cprq, content)){
        		testModel.setCoNum(testModel.getCoNum()+1);
        	}else{
        		System.out.println("裁判时间 "+cprq+"   "+content);		
//    			FileUtil.fileCopy(inputpath, filename, specialpath+"Monthpecial", filename);
        	}
        }
		return testModel;
	}
}
