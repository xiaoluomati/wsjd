package nju.software.service;

import nju.software.vo.CheckInfoVO;
import nju.software.vo.DocInfoVO;
import nju.software.vo.SectionTypoCheckVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuding on 2018/3/28.
 */
public interface ErrorCheckService {

    CheckInfoVO checkError(DocInfoVO docInfoVO);

    Map<String, List<SectionTypoCheckVO>> checkTypo(WsModel wsModel);

    String[] getAjjbqkPart();
}
