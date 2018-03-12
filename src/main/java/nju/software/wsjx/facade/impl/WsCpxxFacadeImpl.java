package nju.software.wsjx.facade.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.google.gson.JsonObject;





import net.sf.json.JSONObject;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.facade.WsCpxxFacade;
import nju.software.wsjx.factory.ScannerFactory;
import nju.software.wsjx.model.Enum.ParseEnum;
import nju.software.wsjx.model.facadeModel.WsxxModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.scanner.WsParser;
import nju.software.wsjx.util.JsonUtil;

public class WsCpxxFacadeImpl implements WsCpxxFacade {

	@SuppressWarnings("finally")
	@Override
	public String getCpxx(byte[] wsnr, String wswjm) {

		WsxxModel wsxxModel = new WsxxModel();

		String ly = "";
		try {
			InputStream inputStream = new ByteArrayInputStream(wsnr);
			WsParser wsParser = ScannerFactory.getWsParserByWswjm(inputStream,
					wswjm);
			// 将文档输入流转化成String
			String content = wsParser.getContent();
			// 分段
			WsAnalyse wsAnalyse = new WsAnalyse(wswjm, content);
			List<String> cpjg = wsAnalyse.getCpjg();
			wsxxModel.setCpjg(cpjg);
			List<String> cpgc = wsAnalyse.getCpfxgc();
			if (cpgc != null) {
				for (int i = 0; i < cpgc.size(); i++) {
					ly = ly + cpgc.get(i);
				}
				String cply = "";
				String cpyj = "";
				int index = 0;
				index = ly.lastIndexOf("依照");
				if (index != -1) {
					cply = ly.substring(0, index);
					int rxIndex = ly.lastIndexOf("如下");
					if (rxIndex - 3 > index)
						cpyj = ly.substring(index, ly.lastIndexOf("如下") - 3);
					cply = removeFirst(cply);

				} else {
					index = ly.lastIndexOf("根据");
					if (index != -1) {
						cply = ly.substring(0, index);
						int rxIndex = ly.lastIndexOf("如下");
						if (rxIndex - 3 > index)
							cpyj = ly
									.substring(index, ly.lastIndexOf("如下") - 3);
						cply = removeFirst(cply);
					}
				}
				cpyj = cpyj.replace("依照", "");
				cpyj = cpyj.replace("根据", "");
				wsxxModel.setCply(cply);
				wsxxModel.setCpyj(cpyj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return JsonUtil.toJson(wsxxModel);
		}
	}

	private String removeFirst(String str) {
		int dh = str.indexOf("，");
		int jh = str.indexOf("。");
		int mh = str.indexOf("：");
		int min = 0;
		// 若找不到则假设为无穷大
		if (dh == -1)
			dh = Integer.MAX_VALUE;
		if (jh == -1)
			jh = Integer.MAX_VALUE;
		if (mh == -1)
			mh = Integer.MAX_VALUE;
		min = (dh > jh ? jh : dh);
		min = mh > min ? min : mh;
		if (min == Integer.MAX_VALUE)
			min = 0;
		String content = null;
		try {
			if (min + 1 < str.length())
				content = str.substring(min + 1, str.length());

		} catch (Exception e) {
			System.out.println(str);
		}
		return content;
	}
	
	
}
