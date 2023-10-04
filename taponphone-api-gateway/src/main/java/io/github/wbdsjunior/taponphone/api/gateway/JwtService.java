package io.github.wbdsjunior.taponphone.api.gateway;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtService {

    private String publicKey;

    public JwtService(
            @Value("${X509_SHA256_RSA_PUBLIC_KEY_PEM_FILE_CONTENT:-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvpNF5Ga5OAyBw9VnLm6hW0K/AequGIBz1mEkMYfoQJ/B4KegCHf95NOTR8TuACSc0SJKSetwLDp//KZ3nz+YnbUQqWsa5y+Rgp+kHe+ebX6fyq5KtS7cPPR4Z9P62hhlRRozTnO1fEQrUOjE98sOyCL8brbrhnFkv3UFEp7LEVNKCPDFC6fWt+kIyl2dXgquuZ2XXqv/hWlSLuXiEf5ESS2AG+I8KnlG4iNwswjbJ1+hHK9H+J9jg/FkYY2fteHbxEzkGMCIMbksEfhiuBZptcfoc6d7rcnNpaa79eQh7MDTgfddJMEhfR+zLRQ1lbKKsufWuJT3FtUIvgZC7HbRWQIDAQAB-----END PUBLIC KEY-----}") String publicKey
        ) {

        publicKey = publicKey.replace("-----BEGIN PUBLIC KEY-----", "");
        publicKey = publicKey.replace("\r\n", "");
        publicKey = publicKey.replace("-----END PUBLIC KEY-----", "");
        this.publicKey = publicKey;
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

    private Algorithm algorithm() throws InvalidKeySpecException, NoSuchAlgorithmException {

        return Algorithm.RSA256((RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(Base64.getDecoder()
                        .decode(publicKey.getBytes())))
            );
    }
}

// openssl genrsa -out rsa.pem 2048
// openssl req -new -x509 -key rsa.pem -outform PEM -out x509.pem
// openssl x509 -pubkey -in x509.pem -noout -out public-key.pem
// tr -d '\n' < public-key.pem
// openssl pkcs8 -topk8 -in rsa.pem -inform PEM -outform PEM -out private-key.pem -nocrypt
// tr -d '\n' < private-key.pem
