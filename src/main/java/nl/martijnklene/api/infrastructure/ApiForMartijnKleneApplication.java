package nl.martijnklene.api.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableOAuth2Sso
@ComponentScans(
		@ComponentScan(basePackages = "nl.martijnklene.api")
)
@SpringBootApplication
@EntityScan(basePackages = {"nl.martijnklene.api", "org.axonframework.eventhandling.saga.repository.jpa"})
public class ApiForMartijnKleneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiForMartijnKleneApplication.class, args);
	}
}
