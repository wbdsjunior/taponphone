package io.github.wbdsjunior.taponphone.commons.security;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
public class JwtOncePerRequestFilterComponent extends OncePerRequestFilter {

    private static final String BEARER_AUTHENTICATION_SCHEME = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtOncePerRequestFilterComponent(
              final JwtService jwtService
            , final UserDetailsService userDetailsService
        ) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
              final HttpServletRequest httpServletRequest
            , final HttpServletResponse httpServletResponse
            , final FilterChain filterChain
        ) throws ServletException, IOException {

        var requestAuthorizationHeader = httpServletRequest.getHeader("Authorization");

        if (!requestHasAuthorizationHeader(requestAuthorizationHeader)) {

            throw new IllegalArgumentException("Authorization header cannot be blank");
        }

        if (!authorizationHeaderHasBearerToken(requestAuthorizationHeader)) {

            throw new IllegalArgumentException("Authorization header must be a bearer token");
        }
        var bearerToken = extractBearerToken(requestAuthorizationHeader);

        try {
            if (!bearerTokenIsValidJwt(bearerToken)) {

                throw new IllegalArgumentException("Bearer must be a valid JWT");
            }
            var jwtSubject = extractSubject(bearerToken);

            if (jwtSubjectIsValidUser(jwtSubject)) {

                throw new IllegalArgumentException("JWT subject must be a valid User");
            }
            var userDetails = userDetailsService.loadUserByUsername(jwtSubject);
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails
                    , null
                    , userDetails.getAuthorities()
                );
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                    .buildDetails(httpServletRequest));
            var securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(usernamePasswordAuthenticationToken);
            SecurityContextHolder.setContext(securityContext);
            filterChain.doFilter(
                    httpServletRequest
                    , httpServletResponse
                );
        } catch (
                  InvalidKeySpecException
                | NoSuchAlgorithmException e
            ) {

            // TODO Auto-generated catch block
            new RuntimeException(e);
        }
    }
    private boolean requestHasAuthorizationHeader(String requestAuthorizationHeader) {

        return
                   null != requestAuthorizationHeader
                && requestAuthorizationHeader.trim()
                        .isEmpty();
    }

    private boolean authorizationHeaderHasBearerToken(String requestAuthorizationHeader) {

        return
                   requestAuthorizationHeader.startsWith(JwtOncePerRequestFilterComponent.BEARER_AUTHENTICATION_SCHEME)
                && extractBearerToken(requestAuthorizationHeader)
                        .isEmpty();
    }

    private String extractBearerToken(String requestAuthorizationHeader) {

        return requestAuthorizationHeader.replace(JwtOncePerRequestFilterComponent.BEARER_AUTHENTICATION_SCHEME, "")
                .trim();
    }

    private boolean bearerTokenIsValidJwt(String bearerToken) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        return null != extractSubject(bearerToken);
    }

    private String extractSubject(String bearerToken) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        return jwtService.extractSubject(bearerToken);
    }

    private boolean jwtSubjectIsValidUser(String jwtSubject) {

        return null != userDetailsService.loadUserByUsername(jwtSubject);
    }
}
