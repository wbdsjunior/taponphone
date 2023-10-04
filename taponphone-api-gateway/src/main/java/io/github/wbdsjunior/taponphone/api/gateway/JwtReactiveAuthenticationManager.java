package io.github.wbdsjunior.taponphone.api.gateway;

import java.util.Objects;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;

    public JwtReactiveAuthenticationManager(final JwtService jwtService) {

        this.jwtService = jwtService;
    }

    @Override
    public Mono<Authentication> authenticate(final Authentication authentication) {

        return Mono.justOrEmpty(authentication.getCredentials())
            .filter(Objects::nonNull)
            .flatMap(this::userDetails)
            .flatMap(this::usernamePasswordAuthenticationToken);
    }

    private Mono<UserDetails> userDetails(final Object token) {

        return Mono.just(jwtService.extractUserDetails((String) token));
    }

    private Mono<UsernamePasswordAuthenticationToken> usernamePasswordAuthenticationToken(final UserDetails userDetails) {

        return Mono.just(new UsernamePasswordAuthenticationToken(
                  userDetails.getUsername()
                , null
                , userDetails.getAuthorities()
            ));
    }
}
