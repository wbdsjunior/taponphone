package io.github.wbdsjunior.taponphone.commons.security;

import org.springframework.security.core.GrantedAuthority;

public record JwtGrantedAuthority(String authority) implements GrantedAuthority {

    @Override
    public String getAuthority() {

        return authority();
    }
}