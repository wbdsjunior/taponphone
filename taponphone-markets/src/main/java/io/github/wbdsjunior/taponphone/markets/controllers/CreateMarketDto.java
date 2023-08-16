package io.github.wbdsjunior.taponphone.markets.controllers;

import io.github.wbdsjunior.taponphone.markets.entities.Market;
import io.github.wbdsjunior.taponphone.markets.entities.ToMarket;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateMarketDto implements ToMarket {

    private String registrationNumber;
    private String name;

    @Override
    public Market toMarket() {

        return new Market(registrationNumber, name);
    }
}
