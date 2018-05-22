package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().and()
                .httpBasic().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
        ;
    }
}
