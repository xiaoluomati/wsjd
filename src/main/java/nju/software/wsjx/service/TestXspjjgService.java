package nju.software.wsjx.service;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.xs.XsPjjgModel;
import org.xml.sax.SAXException;
public interface TestXspjjgService {
	/**
	 * 测试可上诉至
	 */
	public TestModel testKssz(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试上诉期限
	 */
	public TestModel testSsqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试上诉提交材料
	 */
	public TestModel testTjcl(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试提出管辖异议
	 */
	public TestModel testTcgxyy(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试结案方式
	 */
	public TestModel testJafs(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试一审刑事部分判决结果
	 */
	public TestModel testYsxsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试刑事判决结果分组诉讼参与人
	 */
	public TestModel testXspjjgfzsscyr(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                       String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-单罪判罚-罪名
	 */
	public TestModel testBsdzpfzm(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-单罪判罚-罪名
	 */
	public TestModel testBsdzpfzmdm(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-主刑罪名
	 */
	public TestModel testBsxzpfzm(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-单罪判罚-主刑类别
	 */
	public TestModel testZxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-执行判罚-主刑类别
	 */
	public TestModel testZxpfZxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-单罪判罚-主刑期限
	 */
	public TestModel testZxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-执行判罚-主刑期限
	 */
	public TestModel testZxpfZxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-单罪判罚-缓刑类别
	 */
	public TestModel testHxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-单罪判罚-缓刑期限
	 */
	public TestModel testHxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-执行判罚-缓刑类别
	 */
	public TestModel testZxHxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-执行判罚-缓刑期限
	 */
	public TestModel testZxHxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-单罪判罚-判决结果类型
	 */
	public TestModel testPjjglx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-执行判罚-判决结果类型
	 */
	public TestModel testZxPjjglx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                  String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-刑期开始日期
	 */
	public TestModel testXqksrq(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-刑期结束日期
	 */
	public TestModel testXqjsrq(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-刑期折抵办法
	 */
	public TestModel testXqzdbf(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-数罪并罚
	 */
	public TestModel testSzbf(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-免罪或无罪释放
	 */
	public TestModel testMzhwzsf(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                 String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试本审-分组-合并量刑
	 */
	public TestModel testHblx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试对附带民事赔偿的处理
	 */
	public TestModel testDfdmspcdcl(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试对附带民事裁判结果
	 */
	public TestModel testFdmsbfcpjg(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                                    String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试分组-单罪-附加刑类别
	 */
	public TestModel testFjxlb(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试分组-单罪-附加刑期限
	 */
	public TestModel testFjxqx(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试分组-单罪-附加刑金额
	 */
	public TestModel testFjxje(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试分组-单罪-附加刑币种
	 */
	public TestModel testFjxbz(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                               String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试分组-改判原因
	 */
	public TestModel testGpyy(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
	/**
	 * 测试分组-集团犯罪
	 */
	public TestModel testJtfz(TestModel testModel, XsPjjgModel pjjgModel, String inputpath,
                              String filename, String specialpath, String[] node)throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  ;
}
