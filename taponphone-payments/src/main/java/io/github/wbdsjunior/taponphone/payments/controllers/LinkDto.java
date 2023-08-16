package io.github.wbdsjunior.taponphone.payments.controllers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkDto {

    private final String rel;
    private final String method;
    private final String href;
}
