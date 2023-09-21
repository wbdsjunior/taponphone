package io.github.wbdsjunior.taponphone.markets.controllers;

import io.github.wbdsjunior.taponphone.markets.entities.Smartphone;
import io.github.wbdsjunior.taponphone.markets.entities.ToSmartphone;

public record CreateSmartphoneDto(String phoneNumber) implements ToSmartphone {

    @Override
    public Smartphone toSmartphone() {

        return new Smartphone(phoneNumber);
    }
}
