package nju.software.wsjx.util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import nju.software.wsjx.scanner.WsParser;
import nju.software.wsjx.scanner.impl.DocWsParser;
import nju.software.wsjx.scanner.impl.DocxWsParser;
import nju.software.wsjx.scanner.impl.RtfWsParser;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.HWPFOldDocument;
import org.apache.poi.hwpf.OldWordFileFormatException;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * 
 * @author lr12
 *
 */
public class POIUtil {

	/**
	 * 未去除格式内容
	 * @return
	 */
	public static String getWqcGString(byte[] wsnr,String title){
		String suffix=getSuffix(title).toLowerCase();
		suffix=suffix.trim();
		InputStream is=new ByteArrayInputStream(wsnr);
		String result=null;
		String temp = new String(wsnr);
		try {
			if(StringUtil.equals(temp.substring(0, 5), "{\\rtf") && !StringUtil.equals("rtf", suffix)){
				//rtf 特殊处理
				result = getRtfContentString(is);
			}
			else if("doc".equals(suffix.trim())){
				result = getDocContentString(is);
			}else if("docx".equals(suffix)){
			    System.out.println("docx");
				result =  getDocxContentString(is);
			}else if("rtf".equals(suffix)){
				result =  getRtfContentString(is);
			}
			is.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("doc or docx error");
		} 
		return result;
	}
	
	/**
	 * get the content string of byteArray(a file binary byte array)
	 */
	public static String getContent(byte[] wsnr,String title){
		if(title!=null){
			title = title.trim();
		}
		String suffix=getSuffix(title).toLowerCase();
		InputStream is=new ByteArrayInputStream(wsnr);
		WsParser parser = null;

		String temp = new String(wsnr);
		try {
			if(StringUtil.equals(temp.substring(0, 5), "{\\rtf") && !StringUtil.equals("rtf", suffix)){
				//rtf 特殊处理
				parser=new RtfWsParser(is);
			}
			else if("doc".equals(suffix)){
				parser=new DocWsParser(is);
			}else if("docx".equals(suffix)){
				parser=new DocxWsParser(is);
			}else if("rtf".equals(suffix)){
				parser=new RtfWsParser(is);
			}
			is.close();
			if(parser==null){
				return null;
			}
			return parser.getContent();
		}catch (OfficeXmlFileException e) {
			
			parser=new DocxWsParser(new ByteArrayInputStream(wsnr));
			return parser.getContent();
			//e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * get the content of a file of which suffix belongs to .doc and .docx and .rtf;
	 * @param file
	 * @return
	 */
	public static String getContentString(File file){
		String filename=file.getName();
		String suffix=getSuffix(filename);
		try {
			FileInputStream fis=new FileInputStream(file);
			String result="";
			if("doc".equals(suffix)){
				result= getDocContentString(fis);
			}else if("docx".equals(suffix)){
				result=  getDocxContentString(fis);
			}else if("rtf".equals(suffix)){
				result=  getRtfContentString(fis);
			}
			fis.close();
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} 
		return null;
		
	}
	
	/**
	 * tranform content of *.doc to string 
	 * @throws Exception 
	 */
	public static String getDocContentString(InputStream fis) throws Exception {
		try {
			if(fis==null){
				System.out.println("wsnr is null;");
				return "";
			}
			//System.out.println("doc++++++++++++++++++++");
			WordExtractor extractor=new WordExtractor(fis);
			
			return extractor.getText();
		}catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * tranform content of *.docx to string 
	 * @throws Exception 
	 */
	public static String getDocxContentString(InputStream fis) throws Exception{
	//	System.out.println("------do nothing to docx-----");
		System.out.println("Docx;");
		try {
			if(fis==null){
				System.out.println("wsnr is null;");
				return "";
			}
			XWPFDocument xwpf = new XWPFDocument(fis);
			XWPFWordExtractor extractor=new XWPFWordExtractor(xwpf);
			return extractor.getText();
		}  catch (Exception e) {
            throw e;
		}
	}
	
	/**
	 * tranform content of *.rtf to string 
	 */
	public static String getRtfContentString  (InputStream fis) throws Exception{
		RTFEditorKit rtfKit=new RTFEditorKit();
		DefaultStyledDocument dsd=new DefaultStyledDocument();
		try {
			rtfKit.read(fis, dsd, 0);
			String tmp=dsd.getText(0, dsd.getLength());
			return new String(tmp.getBytes("iso-8859-1"),"gbk");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} 
		
		
	}
	
	public static String getSuffix(String filename){
		int posb=filename.lastIndexOf('.');
		return filename.substring(posb+1);
	}
}
