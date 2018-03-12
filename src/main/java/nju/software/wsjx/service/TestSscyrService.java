package nju.software.wsjx.service;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsfdModel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface TestSscyrService {
	/**
	 * 测试当事人段
	 */
	public TestModel testSscyrqj(TestModel testModel, WsfdModel wsfdModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试诉讼参与人名称
	 */
	public TestModel testSscyrmc(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试诉讼身份
	 */
	public TestModel testSssf(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试诉讼地位
	 */
	public TestModel testSsdw(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试原审诉讼地位
	 */
	public TestModel testYsssdw(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试出生日期
	 */
	public TestModel testCsrq(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试当事人地址
	 */
	public TestModel testDsrdz(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试当事人职务
	 */
	public TestModel testDsrzw(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试性别
	 */
	public TestModel testXB(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;

	/**
	 * 测试民族
	 */
	public TestModel testMZ(TestModel testModel, List<WssscyrModel> wssscyrModellist, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试国籍
	 */
	public  TestModel testGJ(TestModel testModel, List<WssscyrModel> wssscyrModelList, String inputpath,
                             String filename, String specialpath, String[] node) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试单位性质
	 */
	public TestModel testDwxz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试当事人是否再婚
	 */
	public TestModel testDsrsfzh(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试特殊行业
	 */
	public TestModel testTshy(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试行政法律关系主体
	 */
	public TestModel testXzflgxzt(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试被告类型
	 */
	public TestModel testBglx(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试组织机构代码
	 */
	public TestModel testZzjgdm(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试行政管理范围
	 */
	public TestModel testXzglfw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试证件类型和号码
	 */
	public TestModel testZj(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试文化程度
	 */
	public TestModel testWhcd(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试学位
	 */
	public TestModel testXw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                            String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试政治面貌
	 */
	public TestModel testZzmm(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 自然人身份
	 */
	public TestModel testZrrsf(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试户籍地
	 */
	public TestModel testHjd(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试是否被害人
	 */
	public TestModel testBhr(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                             String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试附带民事诉讼原告人类型
	 */
	public TestModel testMsssygrlx(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                   String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试刑事责任能力
	 */
	public TestModel testXszrnl(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试缓刑考验期犯罪
	 */
	public TestModel testHxkyqfz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试假释考验期犯罪
	 */
	public TestModel testJskyqfz(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试羁押场所
	 */
	public TestModel testJycs(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试强制措施种类
	 */
	public TestModel testQzcszl(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试强制措施时间
	 */
	public TestModel testQzcssj(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试强制措施执行单位
	 */
	public TestModel testQzcsdw(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试强制措施原因组
	 */
	public TestModel testQzcsyy(TestModel testModel, List<WssscyrModel> wssscyrModels, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
