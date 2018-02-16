package nl.martijnklene.api.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(
		@ComponentScan(basePackages = "nl.martijnklene.api")
)
@SpringBootApplication
public class ApiForMartijnKleneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiForMartijnKleneApplication.class, args);
	}
}
