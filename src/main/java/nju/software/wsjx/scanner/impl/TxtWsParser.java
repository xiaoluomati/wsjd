package nju.software.wsjx.scanner.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nju.software.wsjx.scanner.WsParser;

public class TxtWsParser extends WsParser {

	public TxtWsParser(InputStream is) {
		super(is);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getContent() {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String s;
		try {
			while ((s = br.readLine())!= null) {
				stringBuilder.append(s + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

}
