package io.github.wbdsjunior.taponphone.markets.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.wbdsjunior.taponphone.markets.persistences.SmartphonesJpaRepository;

@RestController
@RequestMapping("smartphones/{smartphoneId:[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}}")
public class SmartphonesRestController {

    private final SmartphonesJpaRepository smartphonesJpaRepository;

    public SmartphonesRestController(final SmartphonesJpaRepository smartphonesJpaRepository) {

        this.smartphonesJpaRepository = smartphonesJpaRepository;
    }

    @GetMapping
    public SmartphoneDto find(@PathVariable final UUID smartphoneId) {

        return smartphonesJpaRepository.findById(smartphoneId)
            .map(SmartphoneDto::fromSmartphoneEntity)
            .orElseThrow(() -> new IllegalStateException("Market not found"));
    }
}
