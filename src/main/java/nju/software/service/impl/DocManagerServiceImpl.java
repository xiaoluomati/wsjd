package nju.software.service.impl;

import nju.software.classify.BaseClassifier;
import nju.software.classify.ParseMap;
import nju.software.factory.WsModelFactory;
import nju.software.service.DocManagerService;
import nju.software.util.WordHelper;
import nju.software.vo.DocType;
import nju.software.vo.LawItemVO;
import nju.software.wsjx.business.PreWsAnalyse;
import nju.software.wsjx.business.WsAnalyse;
import nju.software.wsjx.model.wsSegmentationModel.WsModel;
import nju.software.wsjx.model.wsSegmentationModel.WswsModel;
import nju.software.wsjx.model.wsSegmentationModel.relateModel.WscpfxgcFtModel;
import nju.software.wsjx.parse.ParseSegment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
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
    public WsModel getContent(File file) {

        InputStream is= null;
        try {
            is = new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String read = WordHelper.read(is);
        WsModel wsModel = WsModelFactory.getInstance(read, file.getName());
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
            String name = law.getFlftmc();
            Map<String, String> lawMap = law.getFtMap();
            Map<String, String> lawMapEmpty= new HashMap<>();
//            System.out.println(lawMap.entrySet());

            for (String s : lawMap.keySet()) {
                String section = lawMap.get(s);
                s = "第" + s + "条";
                if (!section.equals("没有款目")) {
                    int idx = section.indexOf("款");
                    section = section.substring(0, idx+1);
                    s += section;
                }

                System.out.println("request: " + s);
                lawMapEmpty.put(s, "");
            }
            LawItemVO lawItemVO = new LawItemVO(name, lawMapEmpty);
            lawItemVOList.add(lawItemVO);
        }
        return lawItemVOList;
    }

    @Override
    @Transactional
    public DocType getDocType(File file) {
        InputStream is= null;
        try {
            is = new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = WordHelper.read(is);
        String filename = file.getName();
        WsModel wsModel = WsModelFactory.getInstance(content, filename);

        return wsModel.getDocType();
    }
}
