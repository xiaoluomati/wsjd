package nju.software.repository;


import nju.software.wsjd.model.lawModel.LawModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 69401 on 2018/3/28.
 */
@Repository
public interface LawRepository extends MongoRepository<LawModel,String> {

    LawModel findByLawname(String name);
    List<LawModel> findAllByLawnameIn(List<String> list);


}
