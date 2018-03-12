package nju.software.wsjx.facade.impl;

import java.io.InputStream;
import java.util.List;

import nju.software.wsjx.facade.TsblModelFacade;
import nju.software.wsjx.model.tsblModel.TsblModel;
import nju.software.wsjx.parse.tsbl.ParseTsblSegment;

public class TsblModelFacadeImpl implements TsblModelFacade{

	@Override
	public TsblModel jxDocument(InputStream inputStream, String wswjm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsblModel jxDocument(byte[] bytes, String wswjm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsblModel jxDocument(String wsnr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsblModel jxDocument(List<String> paragraps) {
		ParseTsblSegment parseTsblSegment=new ParseTsblSegment();
		TsblModel tsblModel=parseTsblSegment.tranformToTsblModel();
		return null;
	}

}
