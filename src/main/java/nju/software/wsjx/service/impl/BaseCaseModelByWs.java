package nju.software.wsjx.service.impl;
import java.util.ArrayList;
import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.WsajjbqkModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpfxgcModel;
import nju.software.wsjx.model.wsSegmentationModel.WscpjgModel;
import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswwModel;
import nju.software.wsjx.service.CaseModelByWs;
import nju.software.wsjx.service.model.WsfdModel;
public class BaseCaseModelByWs implements CaseModelByWs {

	@Override
	public boolean isWzws() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WsfdModel jxWsfdModel(String wsnr, List<String> ws,
			List<String> sscyr,String ssjl, List<String> ajjbqk, List<String> cpfxgc,
			List<String> cpjg, List<String> ww, List<String> fl) {
		WsfdModel wsfdModel = new WsfdModel();
		// 截取文首内容
		if (ws!=null) {
			String wenshou = "";
			for (int i = 0; i < ws.size(); i++) {
				if(i==ws.size()-1)
					wenshou += ws.get(i);
				else
					wenshou += ws.get(i) + " ";
			}

			wsfdModel.setWs(wenshou);
		}
		// 截取诉讼参与人内容
		if (sscyr!=null) {
			String sscyren = "";
			for (int i = 0; i < sscyr.size() ; i++) {
				if(i==sscyr.size()-1)
					sscyren += sscyr.get(i);
				else
					sscyren += sscyr.get(i) + " ";
			}
			wsfdModel.setSscyr(sscyren);
		}
		// 截取诉讼记录内容
		if (ssjl!=null) {
			wsfdModel.setSsjl(ssjl);
		}
		// 截取案件基本情况
		if (ajjbqk!=null) {
			String anjjbqk = "";
			for (int i = 0; i < ajjbqk.size(); i++) {
				if(i==ajjbqk.size()-1)
					anjjbqk += ajjbqk.get(i);
				else
					anjjbqk += ajjbqk.get(i) + " ";
			}
			wsfdModel.setAjjbqk(anjjbqk);
		}
		// 截取裁判分析过程
		if (cpfxgc!=null) {
			String caipfxgc = "";
			for (int i = 0; i < cpfxgc.size(); i++) {
				if(i==cpfxgc.size()-1)
					caipfxgc += cpfxgc.get(i);
				else
					caipfxgc += cpfxgc.get(i) + " ";
			}
			wsfdModel.setCpfxgc(caipfxgc);
		}
		// 截取裁判结果
		if (cpjg!=null) {
			String caipjg = "";
			for (int i = 0; i < cpjg.size(); i++) {
				if(i==cpjg.size()-1)
					caipjg += cpjg.get(i);
				else
					caipjg += cpjg.get(i) + " ";
			}
			wsfdModel.setCpjg(caipjg);
		}
		// 截取文尾
		if (ww!=null) {
			String wenw = "";
			for (int i = 0; i < ww.size(); i++) {
				if(i==ww.size()-1)
					wenw += ww.get(i);
				else
					wenw += ww.get(i) + " ";
			}
			wsfdModel.setWw(wenw);
		}
		// 截取附录
		if (fl != null) {
			String ful = "";
			for (int i = 0; i < fl.size(); i++) {
				ful += fl.get(i) + " ";
			}
			ful += fl.get(fl.size() - 1);
			wsfdModel.setFl(ful);
		}
		wsfdModel.setQw(wsnr);
		return wsfdModel;
	}

	@Override
	public WswsModel jxWswsModel(List<String> ws) {
		WswsModel wswsModel = new WswsModel();
		return wswsModel;
	}
	@Override
	public List<WssscyrModel> jxWssscyrModelList(List<String> sscyr,
			List<String> ajjbqk) {
		List<WssscyrModel> wssscyrModellist = new ArrayList<WssscyrModel>();
		return wssscyrModellist;
	}

	@Override
	public List<WsajjbqkModel> jxWsajjbqkModel(List<String> ajjbqk) {
		//		// TODO Auto-generated method stub
		List<WsajjbqkModel> wsajjbqkModellist = new ArrayList<WsajjbqkModel>();
		return wsajjbqkModellist;
	}

	@Override
	public WscpfxgcModel jxWscpfxgcModel(List<String> cpfxgc) {
		WscpfxgcModel wscpfxgcModel = new WscpfxgcModel();
		return wscpfxgcModel;
	}

	@Override
	public WscpjgModel jxWscpjgModel(List<String> cpjg) {
		// TODO Auto-generated method stub
		WscpjgModel wscpjgModel = new WscpjgModel();
		return wscpjgModel;
	}

	@Override
	public WswwModel jxWswwModel(List<String> wsww) {
		// TODO Auto-generated method stub
		WswwModel wswwModel = new WswwModel();
		return wswwModel;
	}

}
