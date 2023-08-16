package io.github.wbdsjunior.taponphone.markets.controllers;

import io.github.wbdsjunior.taponphone.markets.entities.Smartphone;
import io.github.wbdsjunior.taponphone.markets.entities.ToSmartphone;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateSmartphoneDto implements ToSmartphone {

    private String phoneNumber;

    @Override
    public Smartphone toSmartphone() {

        return new Smartphone(phoneNumber);
    }
}
