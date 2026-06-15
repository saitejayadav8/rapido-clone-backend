package com.rapido.auth_service.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import com.nimbusds.jose.jwk.RSAKey;

public final class Jwks {

    private Jwks() {}

    public static RSAKey generateRsa() {

        try {

            KeyPairGenerator generator =
                    KeyPairGenerator.getInstance("RSA");

            generator.initialize(2048);

            KeyPair keyPair =
                    generator.generateKeyPair();

            return new RSAKey.Builder(
                    (java.security.interfaces.RSAPublicKey)
                            keyPair.getPublic())
                    .privateKey(
                            (java.security.interfaces.RSAPrivateKey)
                                    keyPair.getPrivate())
                    .keyID("rapido-key")
                    .build();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}