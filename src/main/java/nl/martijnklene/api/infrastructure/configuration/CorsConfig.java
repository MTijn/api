package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig extends OncePerRequestFilter{

    @Value("${custom.cors.allowOrigin:*}")
    private String allowOrigin;

    @Value("${custom.cors.allowMethods:GET, POST, PUT, DELETE, OPTIONS}")
    private String allowMethods;

    @Value("${custom.cors.allowHeaders:Content-Type}")
    private String allowHeaders;

    @Value("${custom.cors.allowCredentials:true}")
    private String allowCredentials;

    @Value("${custom.cors.maxAge:3600}")
    private String maxAge;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", allowOrigin);
        response.addHeader("Access-Control-Allow-Methods", allowMethods);

        filterChain.doFilter(request, response);
    }
}