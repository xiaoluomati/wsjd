package nju.software.service.impl;

import nju.software.factory.WsModelFactory;
import nju.software.service.DocManagerService;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by away on 2018/3/26.
 */
@Service
public class DocManagerServiceImpl implements DocManagerService {

    @Override
    @Transactional
    public WsModel getContent(MultipartFile file) {

        InputStream is= null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WsModel wsModel = WsModelFactory.getInstance(is, file.getOriginalFilename());
        // 将没有的内容设为无
        if (wsModel.getWsfl().equals("")) {
            wsModel.setWsfl("无");
        }
        if (wsModel.getWsajjbqSegment().equals("")) {
            wsModel.setWsajjbqSegment("无");
        }
        return wsModel;
    }
}
