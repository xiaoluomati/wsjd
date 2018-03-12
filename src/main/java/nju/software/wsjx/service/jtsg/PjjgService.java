package nju.software.wsjx.service.jtsg;

import java.util.List;

import nju.software.wsjx.model.wsSegmentationModel.WssscyrModel;

public interface PjjgService {

	public boolean isPeichangIndexCorrect(String content, List<WssscyrModel> models);
}
