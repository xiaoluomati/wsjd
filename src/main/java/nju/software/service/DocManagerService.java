package nju.software.service;

import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by away on 2018/3/26.
 */
public interface DocManagerService {

    WsModel getContent(MultipartFile file);
}
