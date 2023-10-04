package io.github.wbdsjunior.taponphone.api.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
              final ServerHttpSecurity serverHttpSecurity
            , final ServerSecurityContextRepository serverSecurityContextRepository
        ) {

        return serverHttpSecurity.csrf(CsrfSpec::disable)
                .formLogin(FormLoginSpec::disable)
                .httpBasic(HttpBasicSpec::disable)
                .securityContextRepository(serverSecurityContextRepository)
                .authorizeExchange(authorizeExchange -> authorizeExchange.pathMatchers(
                                  HttpMethod.POST
                                , "/authentications"
                                , "/authentications/"
                            )
                        .permitAll()
                        .pathMatchers(
                                  HttpMethod.GET
                                , "/actuator"
                                , "/actuator/**"
                                , "/authentications/actuator"
                                , "/authentications/actuator/**"
                                , "/markets/actuator"
                                , "/markets/actuator/**"
                                , "/payments/actuator"
                                , "/payments/actuator/**"
                            )
                        .permitAll()
                        .anyExchange()
                        .authenticated())
                .build();
    }
}
