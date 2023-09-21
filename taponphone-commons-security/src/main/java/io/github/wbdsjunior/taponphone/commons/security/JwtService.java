package io.github.wbdsjunior.taponphone.commons.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtService {

    private String publicKey;
    private String privateKey;

    public JwtService(
            @Value("${X509_SHA256_RSA_PUBLIC_KEY_PEM_FILE_CONTENT:-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvpNF5Ga5OAyBw9VnLm6hW0K/AequGIBz1mEkMYfoQJ/B4KegCHf95NOTR8TuACSc0SJKSetwLDp//KZ3nz+YnbUQqWsa5y+Rgp+kHe+ebX6fyq5KtS7cPPR4Z9P62hhlRRozTnO1fEQrUOjE98sOyCL8brbrhnFkv3UFEp7LEVNKCPDFC6fWt+kIyl2dXgquuZ2XXqv/hWlSLuXiEf5ESS2AG+I8KnlG4iNwswjbJ1+hHK9H+J9jg/FkYY2fteHbxEzkGMCIMbksEfhiuBZptcfoc6d7rcnNpaa79eQh7MDTgfddJMEhfR+zLRQ1lbKKsufWuJT3FtUIvgZC7HbRWQIDAQAB-----END PUBLIC KEY-----}") String publicKey
          , @Value("${PKCS8_SHA256_RSA_PRIVATE_KEY_PEM_FILE_CONTENT:-----BEGIN PRIVATE KEY-----MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC+k0XkZrk4DIHD1WcubqFbQr8B6q4YgHPWYSQxh+hAn8Hgp6AId/3k05NHxO4AJJzRIkpJ63AsOn/8pnefP5idtRCpaxrnL5GCn6Qd755tfp/Krkq1Ltw89Hhn0/raGGVFGjNOc7V8RCtQ6MT3yw7IIvxutuuGcWS/dQUSnssRU0oI8MULp9a36QjKXZ1eCq65nZdeq/+FaVIu5eIR/kRJLYAb4jwqeUbiI3CzCNsnX6Ecr0f4n2OD8WRhjZ+14dvETOQYwIgxuSwR+GK4Fmm1x+hzp3utyc2lprv15CHswNOB910kwSF9H7MtFDWVsoqy59a4lPcW1Qi+BkLsdtFZAgMBAAECggEAPUFHIM/nzt0uRIzuWl1S3euJS4tsJBH9qbWVlZoSi9sNzTDz3mT7JbbfyxzARyl9gjccW8AiUQUIqGO1ZWlim6ZcUahMgGyov6yiasDfUmB16ga1mqESR0FpBi57nehjfJ+cSCYm+mdK6Pm4fdE10hx0uP1wnMkL0gJB/CcwLEPkLmjG8qHV1MeOrP8VenPB6dZBS7HILUFo+PO/qi75w/ldZ9KgBEdPp8YFIBG6upSx4KoWnjm+cGEAQ5G7wCMJ475TD9sckAYy4MAm49Xw0UBBm9XDFQlS09fvHyEY/ZTzP9aB/AqkFvnbwx6E7FMM2dXWcnR/MQh9yzFpl9AjVQKBgQDhJHrTVC4CvIKOJjdeHkfaOyP0MhXrbqmlnYsUwlI08GAMzfJrzWCGLHpTrBXPNu27DUQcIInGxtltyPaLtwMQWq8gYDsbDUXoZS/WIFkrgJNzGUkpONv2OD8kDlM9C/A2D4iJf9zJioOtam8r/TWoi2nkbUVsXEKJsU50BCkQPwKBgQDYsfEY0dNwB4lKnlffotDvYY+6zMjIIg5P/rvcAT3ubObbgDNciTIIEfcsJOuM/7Qpo06lI1GBcRvLtr3OLdHBFdTz+kQcnapwhQTU7TyVuvuSlFt90hNoXD9qnC3b45MGwQ1/I3sPzQJXMmk+sYLJnnjmS7ZiOqF7nT/csry4ZwKBgHDmOjF/JRN6NH3T7C/mP2HQBleORi+pbqj+N6BRUaf6YjAJlmc4tg6Sf0U2Jjmi8dO680ynhG+Jh77l57xoKcEpuGgj2X/XSBchprtZvV1zPH2bUHS7bpIp4n/SLyuNsQdrAqzw0Wa/bwinNth4QVAjmRZTNV0BZtxlbVIARBdBAoGBAILgRft1jzxUcOkuZWhej85koEh0MrgdSYOrGI8x+NOt1miwL1UuT5dsMlrpGZMZRRj41yEKCcoiJGWPQ2fpaIwzwWBxwP1/hFddH6PyfOr5+cN38yI05R/cxzh+h3wNINmtc5dcOJE8grNjcQk6cQGQwvhktgzx2GIugUmscir1AoGBAMeuT4ro0atelC7uB1mBlSv3wgOyNJagi1Vcf4Ok4kKwwBq5UOrBdHpH9BRlIJqbfHAqSLFkYWzzpd99TUZYmoSpDYIrWmZGW5IlXBoDhxQzbRrOWzMkWxEaB8IrFVelzkKJ2850nzNc1DHsvurj7a4GwS/bMTE16A9yJmSxx8bo-----END PRIVATE KEY-----}")String privateKey
        ) {

        publicKey = publicKey.replace("-----BEGIN PUBLIC KEY-----", "");
        publicKey = publicKey.replace("\r\n", "");
        publicKey = publicKey.replace("-----END PUBLIC KEY-----", "");

        this.publicKey = publicKey;

        privateKey = privateKey.replace("-----BEGIN PRIVATE KEY-----", "");
        privateKey = privateKey.replace("\r\n", "");
        privateKey = privateKey.replace("-----END PRIVATE KEY-----", "");

        this.privateKey = privateKey;
    }

    public String generate(
              final Instant expiresAt
            , String[] audience
            , final UserDetails userDetails
        ) {

        final Instant issuedAt = Instant.now();

        if (       null != expiresAt
                && expiresAt.isBefore(issuedAt)
            ) {

            throw new IllegalArgumentException("Expires at instant must be in the future");
        }

        try {

            return JWT.create()
                    .withIssuedAt(issuedAt)
                    .withIssuer("taponphone")
                    .withAudience(audience)
                    .withSubject(subject(userDetails))
                    .withExpiresAt(expiresAt)
                    .withClaim(
                                "authorities"
                            , authorities(userDetails)
                        )
                    .withJWTId(UUID.randomUUID()
                            .toString())
                    .sign(algorithm());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {

            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public UserDetails extractUserDetails(String token) {

        try {

            return new JwtUserDetails(JWT.require(algorithm())
                    .build()
                    .verify(token)
                );
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {

            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


    private String subject(final UserDetails userDetails) {

        return userDetails.getUsername();
    }

    private List<String> authorities(final UserDetails userDetails) {

        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    private Algorithm algorithm() throws InvalidKeySpecException, NoSuchAlgorithmException {

        return Algorithm.RSA256(
                  (RSAPublicKey) KeyFactory.getInstance("RSA")
                        .generatePublic(new X509EncodedKeySpec(Base64.getDecoder()
                                .decode(publicKey.getBytes())))
                , (RSAPrivateKey) KeyFactory.getInstance("RSA")
                        .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder()
                                .decode(privateKey.getBytes())))
            );
    }
}

// openssl genrsa -out rsa.pem 2048
// openssl req -new -x509 -key rsa.pem -outform PEM -out x509.pem
// openssl x509 -pubkey -in x509.pem -noout -out public-key.pem
// tr -d '\n' < public-key.pem
// openssl pkcs8 -topk8 -in rsa.pem -inform PEM -outform PEM -out private-key.pem -nocrypt
// tr -d '\n' < private-key.pem
