package io.github.wbdsjunior.taponphone.authentications;

import jakarta.validation.constraints.NotBlank;

public record AuthenticateUserDto(
          @NotBlank String username
        , @NotBlank String password
    ) {
}
