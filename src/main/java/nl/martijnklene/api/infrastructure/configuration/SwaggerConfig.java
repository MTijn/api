package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        SecurityReference securityReference = SecurityReference.builder()
                .scopes(new AuthorizationScope[0])
                .reference("operator_auth")
                .build();

        ArrayList<SecurityContext> securityContexts = newArrayList(
                SecurityContext.builder()
                        .securityReferences(newArrayList(securityReference)
                        ).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(newArrayList(securitySchema()))
                .securityContexts(securityContexts)
                .select()
                .apis(RequestHandlerSelectors.basePackage("nl.martijnklene.api"))
                .build();
    }

    private OAuth securitySchema() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("");
        return new OAuth("operator_auth", newArrayList(), newArrayList(grantType));
    }
}
