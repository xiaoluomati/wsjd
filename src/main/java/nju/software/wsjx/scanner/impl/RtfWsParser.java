package nju.software.wsjx.scanner.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

import nju.software.wsjx.scanner.WsParser;

public class RtfWsParser extends WsParser {

	public RtfWsParser(InputStream is) {
		super(is);
	}

	@Override
	public String getContent() {
		RTFEditorKit rtfKit=new RTFEditorKit();
		DefaultStyledDocument dsd=new DefaultStyledDocument();
		try {
			rtfKit.read(is, dsd, 0);
			String tmp=dsd.getText(0, dsd.getLength());
			return new String(tmp.getBytes("iso-8859-1"));
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (BadLocationException e) {
//			e.printStackTrace();
		}
		return null;
	}

}
