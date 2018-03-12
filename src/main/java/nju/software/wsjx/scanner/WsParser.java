package nju.software.wsjx.scanner;

import java.io.InputStream;

/**
 * 解析的抽象类
 * @author lr12
 *
 */
public abstract class WsParser {
	protected InputStream is;
	public WsParser(InputStream is){
		this.is=is;
	}
	
	public abstract String getContent();
}
