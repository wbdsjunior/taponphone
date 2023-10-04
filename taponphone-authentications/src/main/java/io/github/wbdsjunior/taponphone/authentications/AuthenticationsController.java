package io.github.wbdsjunior.taponphone.authentications;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class AuthenticationsController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationsController(
              final AuthenticationManager authenticationManager
            , final JwtService jwtService
        ) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public String authenticate(@RequestBody @Valid final AuthenticateUserDto authenticateUserDto) {

        return jwtService.generate((UserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                      authenticateUserDto.username()
                    , authenticateUserDto.password()
                ))
                .getPrincipal()
            );
    }
}
