import nju.software.repository.LawRepository;
import nju.software.wsjd.model.lawModel.LawModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by 69401 on 2018/3/29.
 */
@SpringBootApplication(scanBasePackages = "nju.software")
@EnableMongoRepositories(basePackages = "nju.software.repository")
public class DBtest implements CommandLineRunner{

    @Autowired
    private LawRepository lawRepository;

    public static void main(String[] args) {
        SpringApplication.run(DBtest.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        LawModel lawModel = lawRepository.findByLawname("中华人民共和国慈善法");
        System.out.println(lawModel);
    }
}
