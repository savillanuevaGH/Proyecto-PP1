package SantiagoVillanueva.orderScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "SantiagoVillanueva.orderScheduler")
@EnableJpaRepositories(basePackages = "SantiagoVillanueva.orderScheduler.repositories")
public class OrderSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderSchedulerApplication.class, args);
	}

}