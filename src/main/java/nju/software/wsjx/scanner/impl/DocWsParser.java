package nju.software.wsjx.scanner.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import nju.software.wsjx.scanner.WsParser;

public class DocWsParser extends WsParser{

	public DocWsParser(InputStream is) {
		super(is);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getContent() {
		try {
			if(is==null){
				return "";
			}
			WordExtractor extractor=new WordExtractor(is);
			return extractor.getText();
		} catch (OfficeXmlFileException e) {
			try {
				is.reset();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			WsParser wsParser=new DocxWsParser(is);

			return wsParser.getContent();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
