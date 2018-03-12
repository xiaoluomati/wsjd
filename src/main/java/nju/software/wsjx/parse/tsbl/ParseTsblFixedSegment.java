package nju.software.wsjx.parse.tsbl;

import java.util.List;
import java.util.Map;

/**
 * ½âÎöÍ¥Éó±ÊÂ¼¹Ì¶¨Âß¼­¶ÎÂä
 * @author lr12
 *
 */
public interface ParseTsblFixedSegment {

	//½âÎö¹Ì¶¨µÄÂß¼­
	public Map<String, String> parseFixedSegment(List<String> paragraphs);
	
}
