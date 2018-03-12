package nju.software.wsjx.util;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XMLReader {

public String getXMLNode(String path,String x) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
			  String result = null;
	 		  DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();			  
			  DocumentBuilder builder = dbf.newDocumentBuilder(); 
			  File f = new File(path);
			  Document doc=builder.parse(f); //编译xml
			  XPathFactory factory = XPathFactory.newInstance();
			  XPath xpath = factory.newXPath();
			  XPathExpression expr = xpath.compile(x); //x是xml中的节点，例如“YGSCD”
			  NodeList nodes=(NodeList)expr.evaluate(doc, XPathConstants.NODESET);// doc中读取expr节点内容
			  if (nodes.getLength()>0){
				 result=nodes.item(0).getNodeValue();// 满足正则的XML中的那段文字复制到result中
			  }else{
				 result="无";
			  }
		return result;
		  
}
public ArrayList<String> getXMLNodelist(String path,String x) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
	  ArrayList<String> result = new ArrayList<String>();
	  DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();			  
	  DocumentBuilder builder = dbf.newDocumentBuilder(); 
	  File f = new File(path);
	  Document doc=builder.parse(f); //编译xml
	  XPathFactory factory = XPathFactory.newInstance();
	  XPath xpath = factory.newXPath();
	  XPathExpression expr = xpath.compile(x); //x是xml中的节点，例如“YGSCD”
	  NodeList nodes=(NodeList)expr.evaluate(doc, XPathConstants.NODESET);// doc中读取expr节点内容
	  if (nodes.getLength()>0){
		  for (int i=0;i<nodes.getLength();i++){
			  result.add(nodes.item(i).getNodeValue());// 满足正则的XML中的那段文字复制到result中
		  }
		 
	  }else{
		 result.add("无");
	  }

return result;

}
}
