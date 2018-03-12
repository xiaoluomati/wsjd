package nju.software.wsjx.business;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nju.software.wsjx.exception.ParseException;
import nju.software.wsjx.model.Enum.HeadEnum;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.parserule.wswsparserule.GeneralWsParseRule;
import nju.software.wsjx.parserule.wswsparserule.WsParseRule;


/**
 * 预解析文首 获取文首model
 * 
 * @author lr12
 * 
 */
public class PreWsAnalyse {
	private String wswjm;

	private String wsnr;

	List<String> wsnrList;

	/**
	 * 若某一子项为空，定义为空
	 */
	private List<String> ws;

	public PreWsAnalyse(String wswjm, String wsnr) {
		super();
		this.wswjm = wswjm;
		this.wsnr = wsnr;
		init();
		//handleWsws();
	}

	public WswsModel handleWsws() throws ParseException {
		WsParseRule wsParseRule = new GeneralWsParseRule();
		if (ws == null) {
			throw new ParseException("文首缺少首部");

		}
		try {
			WswsModel wswsModel = wsParseRule.jxWswsModel(ws);
			return wswsModel;
		} catch (ParseException e) {
			throw e;
		}
	}

	public void init() {

		if (wsnr != null) {
			// System.out.println(wsnr);
			wsnr = wsnr.replaceAll("[\\r]", "");
			wsnr = wsnr.replaceAll("[\\t]", "");
			/**
			 * 去除全角半角空格
			 */
			wsnr = wsnr.replaceAll("　", "");
			wsnr = wsnr.replaceAll(" ", "");
			String[] qfsj = wsnr.split("\n");

			wsnrList = new ArrayList<String>();
			int wsEnd = -1;
			for (int i = 0; i < qfsj.length; i++) {
				if (!qfsj[i].equals("")) {
					if (HeadEnum.HasHead(qfsj[i])) {
						wsEnd = i;
						break;
					}
				}

			}
			if (wsEnd >= 0) {
				ws = new ArrayList<String>();
			}
			for (int i = 0; i < wsEnd && i < qfsj.length; i++) {
				if (!qfsj[i].equals(""))
					ws.add(qfsj[i]);
			}

		}
	}
}
