package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
                .authorizeRequests()
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
                        "/swagger.json")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
