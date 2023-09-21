package io.github.wbdsjunior.taponphone.markets.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.wbdsjunior.taponphone.markets.persistences.PaymentEntity;

public record PaymentDto(
          @JsonIgnore String id
        , LocalDateTime date
        , BigDecimal amount
        , String status
        , SmartphoneDto smartphone
    ) {

    public PaymentDto(
              final UUID smartphoneId
            , final PaymentEntity paymentEntity
        ) {

        this(
                  null
                , paymentEntity.date()
                , paymentEntity.amount()
                , paymentEntity.status()
                        .toString()
                , new SmartphoneDto(smartphoneId.toString())
            );
    }
}
