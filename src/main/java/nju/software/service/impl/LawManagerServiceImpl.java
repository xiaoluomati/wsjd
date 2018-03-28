package nju.software.service.impl;

import nju.software.repository.LawRepository;
import nju.software.service.LawManagerService;
import nju.software.vo.LawItemVO;
import nju.software.wsjd.model.lawModel.KuanModel;
import nju.software.wsjd.model.lawModel.LawModel;
import nju.software.wsjd.model.lawModel.TiaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by away on 2018/3/28.
 */
@Service
public class LawManagerServiceImpl implements LawManagerService {

    @Autowired
    private LawRepository lawRepository;

    @Override
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
                if (key.contains("¿î")){
                    int tmp = key.indexOf("Ìõ")+1;
                    String tiao = key.substring(0,tmp);
                    String kuan = key.substring(tmp);
                    KuanModel kuanModel = tiaoModels.get(tiao).getKuan().get(kuan);
                    content.append(kuanModel.getContent());
                    List<String> xiang = kuanModel.getXiang();
                    for (String str :xiang){
                        content.append(str);
                    }

                }else {
                    KuanModel kuanModel = tiaoModels.get(key).getKuan().get("¿î1");
                    content.append(kuanModel.getContent());
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
