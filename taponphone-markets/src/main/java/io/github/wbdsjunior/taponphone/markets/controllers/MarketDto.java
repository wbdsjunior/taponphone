package io.github.wbdsjunior.taponphone.markets.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.wbdsjunior.taponphone.markets.entities.GetId;
import io.github.wbdsjunior.taponphone.markets.persistences.MarketEntity;

public record MarketDto(
          @JsonIgnore String id
        , String registrationNumber
        , String name
    )  implements GetId<String> {

    public MarketDto(final MarketEntity marketEntity) {

        this(
                  marketEntity.getId()
                        .toString()
                , marketEntity.getRegistrationNumber()
                , marketEntity.getName()
            );
    }

    @Override
    public String getId() {

        return id;
    }
}
