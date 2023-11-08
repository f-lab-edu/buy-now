package flab.buynow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BuyNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyNowApplication.class, args);
	}

}
