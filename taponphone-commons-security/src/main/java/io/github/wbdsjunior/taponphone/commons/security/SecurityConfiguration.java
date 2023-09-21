package io.github.wbdsjunior.taponphone.commons.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(final DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
              final HttpSecurity httpSecurity
            , final JwtOncePerRequestFilterComponent jwtOncePerRequestFilterComponent
        ) throws Exception {

        return httpSecurity.csrf(disable())
                .sessionManagement(statelessSessionCreationPolicy())
                .authorizeHttpRequests(anyRequestAuthenticated())
                .addFilterBefore(jwtOncePerRequestFilterComponent, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private Customizer<CsrfConfigurer<HttpSecurity>> disable() {
        return csrf -> csrf.disable();
    }

    private Customizer<SessionManagementConfigurer<HttpSecurity>> statelessSessionCreationPolicy() {

        return sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> anyRequestAuthenticated() {
        return authorizeHttpRequests -> authorizeHttpRequests.requestMatchers(
                          HttpMethod.GET
                        , "/api-docs/v3"
                        , "/api-docs/v3/swagger-config"
                        , "/swagger-ui/**"
                    )
                .permitAll()
                .anyRequest()
                //.authenticated();
                .permitAll();
    }
}
