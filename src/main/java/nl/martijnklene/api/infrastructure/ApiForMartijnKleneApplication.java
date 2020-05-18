package nl.martijnklene.api.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableOAuth2Sso
@ComponentScans(
        @ComponentScan(basePackages = "nl.martijnklene.api")
)
@SpringBootApplication
@EntityScan(basePackages = {"nl.martijnklene.api", "org.axonframework.eventhandling.saga.repository.jpa", "org.axonframework.eventsourcing.eventstore.jpa"})
@PropertySources({
        @PropertySource(value = "classpath:application.properties"),
        @PropertySource(value = "file:/run/secrets/blog_api_config", ignoreResourceNotFound = true)
})
public class ApiForMartijnKleneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiForMartijnKleneApplication.class, args);
    }
}
