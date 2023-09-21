package io.github.wbdsjunior.taponphone.markets.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.wbdsjunior.taponphone.markets.entities.GetId;
import io.github.wbdsjunior.taponphone.markets.persistences.SmartphoneEntity;

public record SmartphoneDto(
          @JsonIgnore String id
        , String phoneNumber
        , @JsonIgnore MarketDto market
    ) implements GetId<String> {

    public SmartphoneDto(final String id) {
        this(
              id
            , null
            , null
        );

    }
    public SmartphoneDto(final SmartphoneEntity smartphoneEntity) {

        this(
                  smartphoneEntity.getId()
                        .toString()
                ,smartphoneEntity.getPhoneNumber()
                , new MarketDto(smartphoneEntity.getMarket())
            );
    }

    @Override
    public String getId() {

        return id;
    }
}
