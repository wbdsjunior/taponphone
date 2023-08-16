package io.github.wbdsjunior.taponphone.markets.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.wbdsjunior.taponphone.markets.entities.GetId;
import io.github.wbdsjunior.taponphone.markets.persistences.SmartphoneEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmartphoneDto implements GetId<String> {

    @JsonIgnore
    private final String id;
    private final String phoneNumber;

    @JsonIgnore
    private final MarketDto market;

    @JsonProperty("_links")
    public Set<LinkDto> getLinks() {

        return new HashSet<>(
                Arrays.asList(
                          LinkDto.builder()
                                .rel("this")
                                .method("GET")
                                .href("smartphones/%s".formatted(null != id ? id : "*"))
                                .build()
                        , LinkDto.builder()
                                .rel("market")
                                .method("GET")
                                .href("/%s".formatted(null != market && null != market.getId() ? market.getId() : "*"))
                                .build()
                    )
            );
    }

    public static SmartphoneDto fromSmartphoneEntity(final SmartphoneEntity smartphoneEntity) {

        return SmartphoneDto.builder()
                .id(
                        smartphoneEntity.getId()
                                .toString()
                    )
                .phoneNumber(smartphoneEntity.getPhoneNumber())
                .market(MarketDto.fromMarketEntity(smartphoneEntity.getMarket()))
                .build();
    }
}
