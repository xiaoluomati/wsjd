package nju.software.wsjx.factory;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.scanner.WsParser;
import nju.software.wsjx.scanner.impl.DocWsParser;
import nju.software.wsjx.scanner.impl.DocxWsParser;
import nju.software.wsjx.scanner.impl.RtfWsParser;
import nju.software.wsjx.scanner.impl.TxtWsParser;
import nju.software.wsjx.util.StringUtil;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 输入加载器工厂
 * @author lr12
 *
 */
public class ScannerFactory {

	/**
	 * 获取输入加载器根据文书文件名和输入流
	 * @param inputStream
	 * @param wswjm
	 * @return
	 * @throws ParseException
	 */
	public static WsParser getWsParserByWswjm(InputStream inputStream,
			String wswjm) throws ParseException {
		if (wswjm != null) {
			wswjm = wswjm.trim();//去空格
			wswjm= wswjm.toLowerCase();//统一变小写
		}
		int posb = wswjm.lastIndexOf('.');//取得最后一个点的位置
		//获取文书后缀
		String suffix = wswjm.substring(posb + 1);
		WsParser wsParser = null;
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp = new String(bytes);
		InputStream is=new ByteArrayInputStream(bytes);

		if(StringUtil.equals(temp.substring(0, 5), "{\\rtf") && !StringUtil.equals("rtf", suffix)){
			//rtf 特殊处理
			wsParser=new RtfWsParser(is);
			return wsParser;
		}
		//根据文书类型选择合适加载器
		switch (suffix) {
		case "doc":
			wsParser = new DocWsParser(is);
			break;
		case "docx":
			wsParser = new DocxWsParser(is);
			break;
		case "rtf":
			wsParser = new RtfWsParser(is);
			break;
		default:
			throw new ParseException("未定义解析该格式文书");

		}


		return wsParser;
	}
	
	/**
	 * 获取输入加载器根据文书文件名和byte数组
	 * @param bytes
	 * @param wswjm
	 * @return
	 * @throws ParseException
	 */
	public static WsParser getWsParserByWswjm(byte[] bytes,
			String wswjm) throws ParseException {
		if (wswjm != null) {
			wswjm = wswjm.trim();
			wswjm= wswjm.toLowerCase();
		}
		int posb = wswjm.lastIndexOf('.');
		//获取文书后缀
		String suffix = wswjm.substring(posb + 1);
		
		WsParser wsParser = null;	
		String temp = new String(bytes);
		InputStream is=new ByteArrayInputStream(bytes);
		
		try {
			if(StringUtil.equals(temp.substring(0, 5), "{\\rtf") && !StringUtil.equals("rtf", suffix)){
				//rtf 特殊处理
				wsParser=new RtfWsParser(is);
			}
			else if("doc".equals(suffix)){
				wsParser=new DocWsParser(is);
			}else if("docx".equals(suffix)){
				wsParser=new DocxWsParser(is);
			}else if("rtf".equals(suffix)){
				wsParser=new RtfWsParser(is);
			}
			else if("txt".equals(suffix)){
				wsParser=new TxtWsParser(is);
			}
			else throw new ParseException("未定义解析该格式文书");
			is.close();
		}catch (OfficeXmlFileException e) {
			
			/*wsParser=new DocxWsParser(new ByteArrayInputStream(bytes));*/
			return wsParser;
			//e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return wsParser;
	}

}
