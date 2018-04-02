package nju.software.service;

import nju.software.vo.DocType;
import nju.software.vo.LawItemVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by away on 2018/3/26.
 */
public interface DocManagerService {

    WsModel getContent(MultipartFile file);

    List<LawItemVO> getLaw(List<WscpfxgcFtModel> lawList);

    DocType getDocType(MultipartFile file);
}
