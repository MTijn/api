package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
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
    @Value("${openid.clientId}")
    private String clientId;
    @Value("${openid.clientSecret}")
    private String clientSecret;
    @Value("${openid.tokenUri}")
    private String tokenUri;
    @Value("${openid.authorisationUri}")
    private String authorisationUri;
    @Value("${openid.redirectUri}")
    private String redirectUri;

    @Bean
    public Docket api() {
        SecurityReference securityReference = SecurityReference.builder()
                .scopes(new AuthorizationScope[0])
                .reference("blog_auth")
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
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(
                this.authorisationUri,
                this.clientId,
                this.clientSecret
        );
        TokenEndpoint tokenEndpoint = new TokenEndpoint(tokenUri, "blog");
        GrantType grantType = new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);
        return new OAuth("blog_auth", newArrayList(), newArrayList(grantType));
    }
}
