import nju.software.repository.LawRepository;
import nju.software.service.impl.LawManagerServiceImpl;
import nju.software.vo.LawItemVO;
import nju.software.wsjd.model.lawModel.LawModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 69401 on 2018/3/29.
 */
@SpringBootApplication(scanBasePackages = "nju.software")
@EnableMongoRepositories(basePackages = "nju.software.repository")
public class DBtest implements CommandLineRunner{

    @Autowired
    private LawRepository lawRepository;
    @Autowired
    private LawManagerServiceImpl lawManagerService;

    public static void main(String[] args) {
        SpringApplication.run(DBtest.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {

//        LawModel lawModel = lawRepository.findByLawname("中华人民共和国慈善法");
//        System.out.println(lawModel);

        List<LawItemVO> lawItemVOS = new ArrayList<>();

        LawItemVO lawItemVO = new LawItemVO();
        lawItemVO.setName("中华人民共和国慈善法");
        Map<String,String> lawmap = new HashMap<>();
        lawmap.put("第八条第二款",null);
        lawItemVO.setLawMap(lawmap);
        lawItemVOS.add(lawItemVO);
        lawItemVOS = lawManagerService.getLaw(lawItemVOS);
        System.out.println(lawItemVOS.get(0).getName());
        System.out.println(lawItemVOS.get(0).getLawMap().get("第八条第二款"));

    }
}
