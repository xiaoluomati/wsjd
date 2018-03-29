package nju.software.service.impl;

import nju.software.repository.LawRepository;
import nju.software.service.LawManagerService;
import nju.software.vo.LawItemVO;
import nju.software.wsjd.model.lawModel.KuanModel;
import nju.software.wsjd.model.lawModel.LawModel;
import nju.software.wsjd.model.lawModel.TiaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by away on 2018/3/28.
 */
@Service
public class LawManagerServiceImpl implements LawManagerService {

    @Autowired
    private LawRepository lawRepository;

    @Override
    @Transactional
    public List<LawItemVO> getLaw(List<LawItemVO> lawItemVOS) {

        List<LawItemVO> results = new ArrayList<>();
        for (LawItemVO vo : lawItemVOS){
            LawModel lawModel = lawRepository.findByLawname(vo.getName());
            if (lawModel == null) {
                continue;
            }
            Map<String,String> lawmap = vo.getLawMap();
            Set<String> keys = lawmap.keySet();
            HashMap<String,TiaoModel> tiaoModels = lawModel.getContent();
            for (String key:keys){
                StringBuilder content = new StringBuilder();
                KuanModel kuanModel;
                if (key.contains("款")){
                    int tmp = key.indexOf("条")+1;
                    String tiao = key.substring(0,tmp);
                    String kuan = key.substring(tmp);
                    kuanModel = tiaoModels.get(tiao).getKuan().get(kuan);
                }else {
                    kuanModel = tiaoModels.get(key).getKuan().get("第一款");
                }
                content.append(kuanModel.getContent());
                List<String> xiang = kuanModel.getXiang();
                if (xiang!=null){
                    for (String str :xiang){
                        content.append(str);
                    }
                }
                lawmap.put(key,content.toString());
            }
            vo.setLawMap(lawmap);
            results.add(vo);
        }
//        List<LawModel> lawModels = lawRepository.findAllByLawnameIn(lawnames);

//        for (LawItemVO vo :lawItemVOS){
//            LawModel lawModel = lawModels.get()
//
//        }

        return results;
    }
}
