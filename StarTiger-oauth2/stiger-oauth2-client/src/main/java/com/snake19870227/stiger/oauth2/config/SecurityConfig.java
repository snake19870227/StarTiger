package com.snake19870227.stiger.oauth2.config;

import java.net.URI;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.PkceParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

/**
 * @author Bu HuaYang
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        tokenResponseClient.setRequestEntityConverter(new OAuth2AuthorizationCodeGrantRequestEntityConverter() {
            @Override
            public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
                ClientRegistration clientRegistration = authorizationCodeGrantRequest.getClientRegistration();

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
                final MediaType contentType = MediaType.valueOf(APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
                headers.setContentType(contentType);
                headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

                OAuth2AuthorizationExchange authorizationExchange = authorizationCodeGrantRequest.getAuthorizationExchange();
                MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();
                formParameters.add(OAuth2ParameterNames.GRANT_TYPE, authorizationCodeGrantRequest.getGrantType().getValue());
                formParameters.add(OAuth2ParameterNames.CODE, authorizationExchange.getAuthorizationResponse().getCode());
                String redirectUri = authorizationExchange.getAuthorizationRequest().getRedirectUri();
                String codeVerifier = authorizationExchange.getAuthorizationRequest().getAttribute(PkceParameterNames.CODE_VERIFIER);
                if (redirectUri != null) {
                    formParameters.add(OAuth2ParameterNames.REDIRECT_URI, redirectUri);
                }
                if (!ClientAuthenticationMethod.BASIC.equals(clientRegistration.getClientAuthenticationMethod())) {
                    formParameters.add(OAuth2ParameterNames.CLIENT_ID, clientRegistration.getClientId());
                }
                if (ClientAuthenticationMethod.POST.equals(clientRegistration.getClientAuthenticationMethod())) {
                    formParameters.add(OAuth2ParameterNames.CLIENT_SECRET, clientRegistration.getClientSecret());
                }
                if (codeVerifier != null) {
                    formParameters.add(PkceParameterNames.CODE_VERIFIER, codeVerifier);
                }
                URI uri = UriComponentsBuilder.fromUriString(clientRegistration.getProviderDetails().getTokenUri())
                        .build()
                        .toUri();

                return new RequestEntity<>(formParameters, headers, HttpMethod.POST, uri);
            }
        });
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/error").permitAll()
                    .anyRequest().authenticated()
                .and()
                .oauth2Login(configurer -> configurer.tokenEndpoint(
                        tokenEndpointConfig -> tokenEndpointConfig.accessTokenResponseClient(tokenResponseClient)
                ));
    }
}
