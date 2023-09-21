package io.github.wbdsjunior.taponphone.authentications;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.wbdsjunior.taponphone.commons.security.JwtService;
import jakarta.validation.Valid;

@RestController
public class AuthenticationsController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final long minutesUntilExpire;

    public AuthenticationsController(
              final AuthenticationManager authenticationManager
            , final JwtService jwtService
            , final @Value("${JWT_EAT_MINUTES_UNTIL_EXPIRE:15}") long minutesUntilExpire
        ) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.minutesUntilExpire = minutesUntilExpire;
    }

    @PostMapping
    @PreAuthorize("permitAll")
    public String authenticate(@RequestBody @Valid final AuthenticateUserDto authenticateUserDto) {

        return jwtService.generate(
                  Instant.now()
                        .plusSeconds(minutesUntilExpire * 60L)
                , null
                , (UserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                  authenticateUserDto.username()
                                , authenticateUserDto.password()
                            ))
                        .getPrincipal()
            );
    }
}
