package com.rapido.auth_service.config;

import java.util.UUID;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthorizationServerConfig {

    private final PasswordEncoder passwordEncoder;

    public AuthorizationServerConfig(
            PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http) throws Exception {

        org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration
                .OAuth2AuthorizationServerConfiguration
                .applyDefaultSecurity(http);

        http.getConfigurer(
                        org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers
                                .OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient client =
                RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("rapido-client")
                        .clientSecret(
                                passwordEncoder.encode("rapido-secret"))

                        .authorizationGrantType(
                                AuthorizationGrantType.AUTHORIZATION_CODE)

                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS)

                        .authorizationGrantType(
                                AuthorizationGrantType.REFRESH_TOKEN)

                        .redirectUri(
                                "http://127.0.0.1:8080/login/oauth2/code/rapido-client")

                        .scope("openid")
                        .scope("profile")

                        .scope("ride.read")
                        .scope("ride.write")

                        .scope("payment.read")
                        .scope("payment.write")

                        .scope("admin.read")
                        .scope("admin.write")

                        .build();

        return new InMemoryRegisteredClientRepository(client);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {

        RSAKey rsaKey = Jwks.generateRsa();

        JWKSet jwkSet = new JWKSet(rsaKey);

        return (selector, context) -> selector.select(jwkSet);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {

        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:8081")
                .build();
    }
}