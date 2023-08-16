package io.github.wbdsjunior.taponphone.payments.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {

    @JsonIgnore
    private String id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String status;

    @JsonIgnore
    private PayeeSmartphoneDto payeeSmartphone;

    @JsonProperty("_links")
    public Set<LinkDto> getLinks() {

        return new HashSet<>(
                Arrays.asList(
                        LinkDto.builder()
                                .rel("this")
                                .method("GET")
                                .href("%s".formatted(id))
                                .build()
                        , LinkDto.builder()
                                .rel("payee-smartphone-payments")
                                .method("GET")
                                .href("payees-smartphones/%s/payments".formatted(payeeSmartphone.getId()))
                                .build()
                    ));
    }
}
