package nju.software.wsjx.scanner.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import nju.software.wsjx.scanner.WsParser;

public class DocxWsParser extends WsParser {

	public DocxWsParser(InputStream is) {
		super(is);
	}

	@Override
	public String getContent() {
		try {
			XWPFDocument xwpf = new XWPFDocument(is);
			XWPFWordExtractor extractor=new XWPFWordExtractor(xwpf);
			return extractor.getText();
		} catch (IOException e) {
//			e.printStackTrace();
		}
		return null;
	}

}
