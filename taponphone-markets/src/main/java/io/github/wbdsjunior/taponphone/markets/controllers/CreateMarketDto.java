package io.github.wbdsjunior.taponphone.markets.controllers;

import io.github.wbdsjunior.taponphone.markets.entities.Market;
import io.github.wbdsjunior.taponphone.markets.entities.ToMarket;

public record CreateMarketDto(
          String registrationNumber
        , String name
  ) implements ToMarket {

    @Override
    public Market toMarket() {

        return new Market(
                  registrationNumber
                , name
            );
    }
}
