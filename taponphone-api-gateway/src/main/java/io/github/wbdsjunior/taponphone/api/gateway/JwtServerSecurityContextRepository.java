package io.github.wbdsjunior.taponphone.api.gateway;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtServerSecurityContextRepository implements ServerSecurityContextRepository {

    private static final String AUTHORIZATION_BEARER_SCHEMA = "Bearer ";
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;

    public JwtServerSecurityContextRepository(final ReactiveAuthenticationManager reactiveAuthenticationManager) {

        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
    }

    @Override
    public Mono<Void> save(
              final ServerWebExchange exchange
            , final SecurityContext context
        ) {

        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(final ServerWebExchange exchange) {

        return Mono.justOrEmpty(exchange)
                .flatMap(this::authorizationHeader)
                .filter(this::bearer)
                .flatMap(this::token)
                .flatMap(this::usernamePasswordAuthenticationToken)
                .flatMap(reactiveAuthenticationManager::authenticate)
                .map(SecurityContextImpl::new);
    }

        private Mono<String> authorizationHeader(final ServerWebExchange serverWebExchange) {

        return Mono.justOrEmpty(serverWebExchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION));
    }

    private boolean bearer(String authorizationHeader) {

        return authorizationHeader.length() > AUTHORIZATION_BEARER_SCHEMA.length();
    }


    private Mono<String> token(String authorizationHeader) {

        return Mono.justOrEmpty(authorizationHeader.substring(AUTHORIZATION_BEARER_SCHEMA.length()));
    }


    private Mono<Authentication> usernamePasswordAuthenticationToken(final String token) {

        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(
                  token
                , token
            ));
    }
}
