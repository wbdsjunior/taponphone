package io.github.wbdsjunior.taponphone.commons.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtOncePerRequestFilterComponent extends OncePerRequestFilter {

    private static final String BEARER_AUTHENTICATION_SCHEME = "Bearer ";

    private final JwtService jwtService;

    public JwtOncePerRequestFilterComponent(final JwtService jwtService) {

        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
              final HttpServletRequest httpServletRequest
            , final HttpServletResponse httpServletResponse
            , final FilterChain filterChain
        ) throws ServletException, IOException {

        SecurityContextHolder.setContext(securityContext(httpServletRequest));
        filterChain.doFilter(
                  httpServletRequest
                , httpServletResponse
            );
    }

    private SecurityContext securityContext(final HttpServletRequest httpServletRequest) {

        var securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(usernamePasswordAuthenticationToken(
                  userDetails(bearerToken(authorizationHeader(httpServletRequest)))
                , httpServletRequest));
        return securityContext;
    }

    private String authorizationHeader(final HttpServletRequest httpServletRequest) {
        var authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (!httpServletRequestHasAuthorizationHeader(authorizationHeader)) {

            throw new IllegalArgumentException("Authorization header cannot be blank");
        }
        return authorizationHeader;
    }

    private boolean httpServletRequestHasAuthorizationHeader(String authorizationHeader) {

        return
                   null != authorizationHeader
                && authorizationHeader.trim()
                        .isEmpty();
    }

    private String bearerToken(String authorizationHeader) {

        if (!authorizationHeaderHasBearerAuthenticationScheme(authorizationHeader)) {

            throw new IllegalArgumentException("Authorization header must starts with bearer authentication scheme");
        }
        var bearerToken = authorizationHeader.replace(
                  JwtOncePerRequestFilterComponent.BEARER_AUTHENTICATION_SCHEME
                , ""
            )
            .trim();

        if (null == bearerToken) {

            throw new IllegalArgumentException("Authorization header must be a bearer token");
        }
        return bearerToken;
    }

    private boolean authorizationHeaderHasBearerAuthenticationScheme(String authorizationHeader) {

        return authorizationHeader.startsWith(JwtOncePerRequestFilterComponent.BEARER_AUTHENTICATION_SCHEME);
    }

    private UserDetails userDetails(String bearerToken) {

        return jwtService.extractUserDetails(bearerToken);
    }

    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(
              final UserDetails userDetails
            , final HttpServletRequest httpServletRequest
        ) {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                      userDetails
                    , null
                    , userDetails.getAuthorities()
                );
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(httpServletRequest));
        return usernamePasswordAuthenticationToken;
    }
}
