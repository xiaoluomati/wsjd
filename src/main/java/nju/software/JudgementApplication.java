package nju.software;

import nju.software.util.LSPSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication()
public class JudgementApplication {

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				LSPSingleton singleton = new LSPSingleton();
			}
		}).start();

		SpringApplication.run(JudgementApplication.class, args);
	}
}
