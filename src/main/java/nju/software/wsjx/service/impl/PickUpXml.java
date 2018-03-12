package nju.software.wsjx.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PickUpXml {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\DTLXY\\Desktop\\xzys");
		String filename[];
		filename = file.list();
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for (int i = 0; i < filename.length; i++) {
//			System.out.println(filename[i]);
			ArrayList<String> result = PickUpXml.getXMLNodeMap("C:\\Users\\DTLXY\\Desktop\\xzys"
							+ "\\" + filename[i], "//FLFTFZ//MC/@value");
			for(int j=0;j<result.size();j++){
				String content = result.get(j);
				if(map.containsKey(content)){
					int val = map.get(content) + 1;
					map.put(content, val);
				}else{
					map.put(content, 1);
				}
			}
		}
		for (Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toString();
			System.out.println(key + "  " + value);
		}
	}

	public static ArrayList<String> getXMLNodeMap(String path,String x) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		 ArrayList<String> result = new ArrayList<String>();
		  DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();			  
		  DocumentBuilder builder = dbf.newDocumentBuilder(); 
		  File f = new File(path);
//		  System.out.println(path +"--- "+x);
		  Document doc=builder.parse(f); //编译xml
		  XPathFactory factory = XPathFactory.newInstance();
		  XPath xpath = factory.newXPath();
		  XPathExpression expr = xpath.compile(x); //x是xml中的节点，例如“YGSCD”
		  NodeList nodes=(NodeList)expr.evaluate(doc, XPathConstants.NODESET);// doc中读取expr节点内容
		  if (nodes.getLength()>0){
			  for (int i=0;i<nodes.getLength();i++){
				  result.add(nodes.item(i).getNodeValue());// 满足正则的XML中的那段文字复制到result中
			  }
		  }
		  
	return result;

	}
}
