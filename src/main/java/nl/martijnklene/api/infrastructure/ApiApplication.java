package nl.martijnklene.api.infrastructure;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"nl.martijnklene.api"})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
