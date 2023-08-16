package io.github.wbdsjunior.taponphone.markets.controllers;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.wbdsjunior.taponphone.markets.persistences.PaymentsFeignClientRepository;

@RestController
@RequestMapping("smartphones/{smartphoneId:[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}}/payments")
public class SmartphonePaymentsRestController {

    private final PaymentsFeignClientRepository paymentsFeignClientRepository;

    public SmartphonePaymentsRestController(final PaymentsFeignClientRepository paymentsFeignClientRepository) {

        this.paymentsFeignClientRepository = paymentsFeignClientRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<PaymentDto> find(@PathVariable final UUID smartphoneId) {

        return Optional.ofNullable(paymentsFeignClientRepository.findByPayeeSmartphoneId(smartphoneId))
                .orElse(Collections.emptyList())
                .stream()
                .map(PaymentDto::fromPaymentEntity)
                .collect(Collectors.toSet());
    }
}
