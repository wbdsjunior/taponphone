package io.github.wbdsjunior.taponphone.markets.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.wbdsjunior.taponphone.markets.persistences.PaymentEntity;
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
    private SmartphoneDto smartphone;

    @JsonProperty("_links")
    public Set<LinkDto> getLinks() {

        return new HashSet<>(
                Arrays.asList(LinkDto.builder()
                        .rel("smartphone")
                        .method("GET")
                        .href("smartphones/%s".formatted(null != smartphone && null != smartphone.getId() ? smartphone.getId() : "*"))
                        .build()
                    )
            );
    }

    public static PaymentDto fromPaymentEntity(final PaymentEntity paymentEntity) {

        return PaymentDto.builder()
                // .id(paymentEntity.getLinks()
                //                 .stream()
                //                 .filter((link) -> "this".equals(link.getRel()))
                //                 .findFirst()
                //                 .map((link) -> Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")
                //                         .matcher(link.getHref())
                //                         .group(1))
                //                 .orElseGet(null))
                .date(paymentEntity.getDate())
                .amount(paymentEntity.getAmount())
                .status(paymentEntity.getStatus()
                        .toString())
                .smartphone(SmartphoneDto.builder()
                        // .id(paymentEntity.getLinks()
                        //         .stream()
                        //         .filter((link) -> "payee-smartphone-payments".equals(link.getRel()))
                        //         .findFirst()
                        //         .map((link) -> Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")
                        //                 .matcher(link.getHref())
                        //                 .group(1))
                        //         .orElseGet(null)
                        //     )
                        .build())
                .build();
    }
}
