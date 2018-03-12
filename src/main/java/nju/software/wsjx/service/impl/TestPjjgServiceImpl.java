package nju.software.wsjx.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;





import org.xml.sax.SAXException;

import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjeModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.PjjgnrModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WsCpjgssfModel;
import nju.software.wsjx.service.TestPjjgService;
import nju.software.wsjx.service.model.TestModel;
import nju.software.wsjx.service.model.WsCpjgssfjeModel;
import nju.software.wsjx.service.model.WscpjgssfcdModel;
import nju.software.wsjx.service.model.WsfdModel;
import nju.software.wsjx.util.FileUtil;
import nju.software.wsjx.util.StringUtil;
import nju.software.wsjx.util.XMLReader;

public class TestPjjgServiceImpl implements TestPjjgService{
	private XMLReader xr;
	private TestMethodServiceImpl ts;

	/**
	 * 测试判决结果
	 */
		@Override
		public TestModel testPjjg(TestModel testModel, WsfdModel wsfdModel,
				String inputpath, String filename, String specialpath, String[] node)
				throws XPathExpressionException, ParserConfigurationException,
				SAXException, IOException {
			// TODO Auto-generated method stub
			XMLReader xr=new XMLReader();
			TestMethodServiceImpl ts=new TestMethodServiceImpl();
			String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
	        if(wsfdModel!=null){
	        	String cpjg = wsfdModel.getCpjg();
	        	if(StringUtil.equalsIgnoreWhitespace(content, cpjg)){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else{
	        		System.out.println("测试："+cpjg);	
	        		System.out.println("华宇："+content);
//	    			FileUtil.fileCopy(inputpath, filename, specialpath+"Monthpecial", filename);
	        	}
	        }
			return testModel;
		}

	@Override
	public TestModel testJafs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		if(wscpjgModel!=null){
//        	String jafs = wscpjgModel.getMsesjafs();//1.民事二审结案方式
			String jafs = wscpjgModel.getJafs();//2.民事一审结案方式
//			String jafs = wscpjgModel.getXzysjafs();//3.行政一审结案方式
//        	if(judegMsesjafs(jafs, content)){
//			if(judegMsysjafs(jafs, content)){//2.民事一审结案方式、行政一审结案方式
			if(judegMsesjafs(jafs, content)){//2.民事二审结案方式、行政二审结案方式
        		testModel.setCoNum(testModel.getCoNum()+1);
        	}else{
        		System.out.println("测试："+jafs);	
        		System.out.println("华宇："+content);
    			FileUtil.fileCopy(inputpath, filename, specialpath+"esjafsSpecial", filename);
        	}
        }
		return testModel;
	}
	
	public  static boolean judegMsesjafs(String jafs,String content){
		boolean eq = false;
		if(StringUtil.equals(jafs, content)){
			eq = true;
		}else if(StringUtil.equals("撤诉", content) && (StringUtil.equals("准予撤回起诉并撤销一审判决", jafs)||StringUtil.equals("准予撤回上诉", jafs)||StringUtil.equals("按撤回上诉处理", jafs))){
			eq = true;
		}else if(StringUtil.equals("按撤诉处理", content)&&StringUtil.equals("按撤回上诉处理", jafs)){
			eq = true;
		}else if(StringUtil.equals("撤销变更原裁定", content)&&(StringUtil.equals("撤销原裁定并指令审理", jafs)||StringUtil.equals("撤销原裁定并指令受理", jafs))){
			eq = true;
		}else if(StringUtil.equals("驳回起诉", content)&&StringUtil.equals("撤销原判并驳回起诉", jafs)){
			eq = true;
		}else if(StringUtil.equals("维持原裁定", content)&&StringUtil.equals("维持", jafs)){
			eq = true;
		}
		return eq;
	}
	
	public  static boolean judegMsysjafs(String jafs,String content){
		boolean eq = false;
		if(StringUtil.equals(jafs, content)){
			eq = true;
		}else if(StringUtil.equals("撤诉", content) && StringUtil.equals("准予撤诉", jafs)){
			eq = true;
		}else if(StringUtil.equals("不予受理", content) && StringUtil.equals("不予立案", jafs)){
			eq = true;
		}
		return eq;
	}

	@Override
	public TestModel testSffhcs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
			XMLReader xr = new XMLReader();
			TestMethodServiceImpl ts=new TestMethodServiceImpl();
			String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
			if(wscpjgModel!=null){
	        	String result = wscpjgModel.getSffhcs();
	        	if(content!=null && result!=null && ts.judgeNode_1(result, content)){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else if(content==null && result==null ){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else{
	        		System.out.println("测试："+result);	
	        		System.out.println("华宇："+content);
	    			FileUtil.fileCopy(inputpath, filename, specialpath+"sffhcsSpecial", filename);
	        	}
	        }
			return testModel;
	}

	@Override
	public TestModel testMsesffcsyy(TestModel testModel,
			WscpjgModel wscpjgModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
			XMLReader xr = new XMLReader();
			TestMethodServiceImpl ts=new TestMethodServiceImpl();
			String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
			if(wscpjgModel!=null){
	        	String fhcsyy = wscpjgModel.getFhcsyy();
	        	if((content!=null && fhcsyy!=null&&ts.judgeNode_2(fhcsyy, content))||
	        			(StringUtil.equals("事实不清证据不足", content)&&StringUtil.equals("原判决认定基本事实不清", fhcsyy))){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else if(((content!=null && content.equals("无"))|| content==null) && fhcsyy==null){
	        		testModel.setCoNum(testModel.getCoNum()+1);
	        	}else{
	        		System.out.println("yy测试："+fhcsyy);	
	        		System.out.println("yy华宇："+content);
	    			FileUtil.fileCopy(inputpath, filename, specialpath+"fhcsyySpecial", filename);
	        	}
	        }
			return testModel;
	}

	@Override
	public TestModel testAjslf(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		ArrayList<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		if(wscpjgModel!=null){
        	String ajslf = wscpjgModel.getAjslf();
        	boolean flag = false;
//        	存在审理费用
        	if(contents.size()!=0){
        		if(contents.size()==1 && StringUtil.equalsIgnoreWhitespace("无", contents.get(0))){
        			testModel.setCoNum(testModel.getCoNum()+1);
        			flag=true;
        		}else{
        			String contentAll = "";
	        		for(String content : contents){
	        			contentAll = contentAll+content;
	            	}
	        		if(StringUtil.contains(contentAll, ajslf)){
            			testModel.setCoNum(testModel.getCoNum()+1);
            			flag=true;
            		}
        		}
        	}
        	if(flag==false){
        		System.out.println("测试 "+ajslf);
        		if(contents.size()>0){
        			System.out.println("华宇："+contents.get(contents.size()-1));
        		}
        		
    			FileUtil.fileCopy(inputpath, filename, specialpath+"ajslfSpecial", filename);
        	}
        }
		return testModel;
	}

	@Override
	public TestModel testJabdje(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		if(wscpjgModel!=null){
			if(judgeJe(content, wscpjgModel.getJabdze())){
				testModel.setCoNum(testModel.getCoNum()+1);
			}
			else{
				System.out.println("测试"+wscpjgModel.getJabdze());
				System.out.println("华宇"+content);
				FileUtil.fileCopy(inputpath, filename, specialpath+"jabdSpecial", filename);
			}
		}
		return null;
	}
	
	public static boolean  judgeJe(String content,String test){
		if(StringUtil.equals(content,test)){
			return true;
		}
		if(content.equals("无")&&test==null){
			return true;
		}
		if(content.indexOf("元")>0&&StringUtil.indexOf(test, "元")>0){
			try{
				Double a = Double.parseDouble(content.substring(0, content.length()-1));
				Double b = Double.parseDouble(test.substring(0, test.length()-1));
				return a.doubleValue()==b.doubleValue();
			}catch(Exception e){
				return false;
			}
		}
		return false;
	}

	@Override
	public TestModel testQlr(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		HashMap<String, String> qlrMap = new HashMap<String, String>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel pjjgnrModel:pjjgnrModels){
				if(pjjgnrModel.getQlr()!=null){
					for(Map.Entry<String, String> entry:pjjgnrModel.getQlr().entrySet()){
						qlrMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		boolean flag=true;
		if(contents.size()>0&&!contents.get(0).equals("无")){
			for(String content:contents){
				if(!qlrMap.containsKey(content)){
					flag=false;
					break;
				}
			}
		}
		
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"qlrSpecial", filename);
			System.out.println("华宇权利人"+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testYwr(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		HashMap<String, String> qlrMap = new HashMap<String, String>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel pjjgnrModel:pjjgnrModels){
				if(pjjgnrModel.getYwr()!=null){
					for(Map.Entry<String, String> entry:pjjgnrModel.getYwr().entrySet()){
						qlrMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		boolean flag=true;
		if(contents.size()>0&&!contents.get(0).equals("无")){
			for(String content:contents){
				if(!qlrMap.containsKey(content)){
					flag=false;
					break;
				}
			}
		}
		
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"qlrSpecial", filename);
			System.out.println("华宇义务人："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testPjzxqx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		HashMap<String, PjjgnrModel> map = new HashMap<String, PjjgnrModel>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel model:pjjgnrModels){
				if(!StringUtil.isBlank(model.getPjzxqx())){
					map.put(model.getPjzxqx(), model);
				}
			}
		}
		boolean flag=true;
		if(contents.size()>0 && !contents.get(0).equals("无")){
			for(String content:contents){
				if(!map.containsKey(content)){
					flag=false;
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"qlrSpecial", filename);
			System.out.println("..................");
		}
		return testModel;
	}

	@Override
	public TestModel testSsxx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String ssfy = wscpjgModel.getKssz();//可上诉至
//		String ssfy = wscpjgModel.getSsqx();//上诉期限
//		String ssfy = wscpjgModel.getSstjcl();//提交上诉材料
		if(StringUtil.equals(content, ssfy) || (StringUtil.equals("无", content)&&ssfy==null)||(content.equals("无")&&!StringUtil.isBlank(ssfy))){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+ssfy);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testCsrjh(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<String> csrjh = wscpjgModel.getCsrjh();
		boolean flag = true;
		if(csrjh==null && !contents.get(0).equals("无")){
			flag = false;
		}else if(csrjh!=null&&contents.size()>0 && !contents.get(0).equals("无")){
			for(String content:contents){
				if(!csrjh.contains(content)){
					flag=false;
					break;
				}
			}
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"csrjhSpecial", filename);
			System.out.println("测试："+csrjh);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testCslx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String cslx = wscpjgModel.getCslx();
		if(StringUtil.equals(content, cslx)||(StringUtil.equals("无", content)&&cslx==null)){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("测试："+cslx);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testCdr(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
		boolean flag=true;
		List<String> cdStringList = new ArrayList<String>();
		if(ssfModel!=null && ssfModel.getSsfcdModels()!=null && !contents.get(0).equals("无")){
			for(WscpjgssfcdModel cdmodel:ssfModel.getSsfcdModels()){
//				cdStringList.add(cdmodel.getCdr());//承担人
				cdStringList.add(cdmodel.getCdje());//承担金额
			}
			for(String content:contents){
//				if(!cdStringList.contains(content)){
				if(!containsStr(content, cdStringList)){
					flag=false;
					break;
				}
			}
		}else if (!contents.get(0).equals("无")&&
				(ssfModel==null || (ssfModel!=null&&ssfModel.getSsfcdModels()==null) )){
			flag=false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("测试："+cdStringList);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}
	
	/**
	 * content 是否存在于 tests中
	 * @param content
	 * @param tests
	 * @return
	 */
	public static boolean containsStr(String content,List<String> tests){
		for(int i=0;i<tests.size();i++){
			if(StringUtil.contains(tests.get(i), content)){
				return true;
			}
		}
		return false;
	}
	
	public TestModel testCdfs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
		boolean flag=true;
		List<String> cdStringList = new ArrayList<String>();
		if(ssfModel!=null && ssfModel.getSsfcdModels()!=null && !contents.get(0).equals("无")){
			for(WscpjgssfcdModel cdmodel:ssfModel.getSsfcdModels()){
				cdStringList.add(cdmodel.getCdfs());
			}
			for(String content:contents){
//				if(!cdStringList.contains(content)){
				if(!containsStr(content, cdStringList)){
					flag=false;
					break;
				}
			}
		}else if (!contents.get(0).equals("无")&&
				(ssfModel==null || (ssfModel!=null&&ssfModel.getSsfcdModels()==null) )){
			flag=false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("测试："+cdStringList);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testSsfje(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		WsCpjgssfModel ssfModel = wscpjgModel.getSsfModel();
		boolean flag = true;
		List<String> jeList = new ArrayList<String>();
		if(ssfModel!=null && ssfModel.getSsfjeModels()!=null && !contents.get(0).equals("无")){
			for(WsCpjgssfjeModel jeModel:ssfModel.getSsfjeModels()){
//				jeList.add(jeModel.getValue());//诉讼费金额
				jeList.add(jeModel.getCategory());//诉讼费金额
			}
			for(String content:contents){
				if(!jeList.contains(content)){
					flag = false;
					break;
				}
			}
		}else if(!contents.get(0).equals("无")
				&&((ssfModel!=null && ssfModel.getSsfjeModels()==null)||ssfModel==null)){
			flag = false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+jeList);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testSfzcygssqq(TestModel testModel,
			WscpjgModel wscpjgModel, String inputpath, String filename,
			String specialpath, String[] node) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String content=xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		String sfzcygssqq = wscpjgModel.getSfzcssqq();
		if(StringUtil.equals(content, sfzcygssqq)||content.equals("无")){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			FileUtil.fileCopy(inputpath, filename, specialpath+"cslxSpecial", filename);
			System.out.println("测试："+sfzcygssqq);
			System.out.println("华宇："+content);
		}
		return testModel;
	}

	@Override
	public TestModel testPjzrcdfs(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr=new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents=xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		boolean flag = true;
		if(pjjgnrModels!=null&&!contents.get(0).equals("无")){
			List<String> cdfs = new ArrayList<String>();
			for(PjjgnrModel model:pjjgnrModels){
				if(!StringUtil.isBlank(model.getPjzrcdfs())){
					cdfs.add(model.getPjzrcdfs());
				}
			}
			for(String content:contents){
				if(!cdfs.contains(content)){
					flag=false;
					break;
				}
			}
		}else if(!contents.get(0).equals("无")
				&& (pjjgnrModels==null || (pjjgnrModels!=null &&pjjgnrModels.size()==0))){
			flag = false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
//			System.out.println("测试："+sfzcygssqq);
			System.out.println("华宇："+contents);
		}
		return null;
	}
	
	@Override
	public TestModel testPjje(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		List<String> jeList = new ArrayList<String>();
		if(pjjgnrModels!=null){
			for(PjjgnrModel model:pjjgnrModels){
				if(model!=null && model.getPjjeList()!=null){
					for(PjjeModel s:model.getPjjeList()){
						jeList.add(s.getValue());
					}
				}
			}
		}
		boolean flag = true;
		if(jeList.size()>0 && !contents.get(0).equals("无")){
			for(String content:contents){
				if(!contansJe(content, jeList)){
					flag=false;
					break;
				}
			}
		}else if(jeList.size()==0 &&  !contents.get(0).equals("无")){
			flag=false;
		}
		
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+jeList);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}
	
	public static boolean contansJe(String content,List<String> tests){
		if(tests.contains(content)){
			return true;
		}
		for(String s:tests){
			if(judgeJe(content, s)){
				return true;
			}
		}
		return false;
	}

	@Override
	public TestModel testPjjelx(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		List<String> contents = xr.getXMLNodelist(inputpath+"\\"+filename, node[1]);
		List<String> tests = new ArrayList<String>();
		List<PjjgnrModel> pjjgnrModels = wscpjgModel.getPjjgList();
		if(pjjgnrModels!=null){
			for(PjjgnrModel pjjgnrModel:pjjgnrModels){
				List<PjjeModel> pjjeModels = pjjgnrModel.getPjjeList();
				if(pjjeModels!=null){
					for(PjjeModel pjjeModel : pjjeModels){
						List<String> pjjelxList = pjjeModel.getCategorys();
						if(pjjelxList!=null){
							tests.addAll(pjjelxList);
						}
					}
				}
			}
		}
		boolean flag = true;
		if(tests.size()>0&&!contents.get(0).equals("无")){
			for(String content:contents){
				if(!tests.contains(content)){
					flag = false;
					break;
				}
			}
		}else if(tests.size()==0 && !contents.get(0).equals("无")){
			flag = false;
		}
		if(flag){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+tests);
			System.out.println("华宇："+contents);
		}
		return testModel;
	}

	@Override
	public TestModel testGxyy(TestModel testModel, WscpjgModel wscpjgModel,
			String inputpath, String filename, String specialpath, String[] node)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		XMLReader xr = new XMLReader();
		TestMethodServiceImpl ts=new TestMethodServiceImpl();
		String  content  = xr.getXMLNode(inputpath+"\\"+filename, node[1]);
		if(StringUtil.equals(content, wscpjgModel.getSftcgxyy())){
			testModel.setCoNum(testModel.getCoNum()+1);
		}else{
			System.out.println("测试："+wscpjgModel.getSftcgxyy());
			System.out.println("华宇："+content);
		}
		
		
		
		return testModel;
	}
	
}
