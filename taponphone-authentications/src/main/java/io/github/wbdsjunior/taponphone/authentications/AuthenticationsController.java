package io.github.wbdsjunior.taponphone.authentications;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.wbdsjunior.taponphone.commons.security.JwtService;
import jakarta.validation.Valid;

@RestController
public class AuthenticationsController {
    private static final long MINUTES_TO_EXPIERES = 15L;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationsController(final AuthenticationManager authenticationManager, final JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    @PreAuthorize("permitAll")
    public String authenticate(@RequestBody @Valid final AuthenticateUserDto authenticateUserDto) throws InvalidKeySpecException, NoSuchAlgorithmException, AuthenticationException, IOException {

        return jwtService.generate(
                  Instant.now()
                        .plusSeconds(MINUTES_TO_EXPIERES * 60L)
                , null
                , (UserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                  authenticateUserDto.username()
                                , authenticateUserDto.password()
                            ))
                        .getPrincipal()
            );
    }
}
