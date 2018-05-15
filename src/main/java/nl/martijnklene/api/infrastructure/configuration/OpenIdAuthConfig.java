package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class OpenIdAuthConfig {
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
    public OAuth2ProtectedResourceDetails openId() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(tokenUri);
        details.setUserAuthorizationUri(authorisationUri);
        details.setScope(Arrays.asList("openid", "email"));
        details.setPreEstablishedRedirectUri(redirectUri);
        details.setUseCurrentUri(false);
        return details;
    }

    @Bean
    public OAuth2RestTemplate googleOpenIdTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(openId(), clientContext);
    }
}
