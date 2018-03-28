package nju.software.service.impl;

import nju.software.factory.WsModelFactory;
import nju.software.service.DocManagerService;
import nju.software.vo.LawItemVO;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    @Transactional
    public List<LawItemVO> getLaw(List<WscpfxgcFtModel> lawList) {
        List<LawItemVO> lawItemVOList = new ArrayList<>();
        for (WscpfxgcFtModel law : lawList) {
            String name = "《" + law.getFlftmc() + "》";
            Map<String, String> lawMap = law.getFtMap();
            Map<String, String> lawMapEmpty= new HashMap<>();
            for (String s : lawMap.keySet()) {
                lawMapEmpty.put("第" + s + "条", "");
            }
            LawItemVO lawItemVO = new LawItemVO(name, lawMapEmpty);
            lawItemVOList.add(lawItemVO);
        }
        return lawItemVOList;
    }
}
