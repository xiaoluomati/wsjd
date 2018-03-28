package nju.software.repository;


import nju.software.wsjd.model.lawModel.LawModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by 69401 on 2018/3/28.
 */
public interface LawRepository extends MongoRepository<LawModel,String> {

    public LawModel findByLawname(String name);
    public List<LawModel> findAllByLawnameIn(List<String> list);


}
