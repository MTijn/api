package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/",
                        "/index.html",
                        "/oauth2-redirect.html",
                        "/favicon-*.png",
                        "/webjars/springfox-swagger-ui/*",
                        "/webjars/springfox-swagger-ui/fonts",
                        "/swagger-resources/configuration/*",
                        "/swagger-ui.html/swagger-resources/configuration/security",
                        "/v2/api-docs",
                        "/swagger-*",
                        "/swagger.json"
                ).antMatchers(HttpMethod.GET)
                .antMatchers(HttpMethod.OPTIONS);
    }

    @Bean
    public OpenIdConnectFilter openIdConnectFilter() {
        return new OpenIdConnectFilter("/login", restTemplate);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterAfter(new OAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
            .addFilterAfter(openIdConnectFilter(), OAuth2ClientContextFilter.class)
            .cors().and()
            .httpBasic().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
        ;
    }
}
