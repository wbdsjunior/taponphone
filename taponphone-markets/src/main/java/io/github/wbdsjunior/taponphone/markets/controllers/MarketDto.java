package io.github.wbdsjunior.taponphone.markets.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.wbdsjunior.taponphone.markets.entities.GetId;
import io.github.wbdsjunior.taponphone.markets.persistences.MarketEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarketDto implements GetId<String> {

    @JsonIgnore
    private final String id;
    private final String registrationNumber;
    private final String name;

    @JsonProperty("_links")
    public Set<LinkDto> getLinks() {

        return new HashSet<>(
                Arrays.asList(
                          LinkDto.builder()
                                .rel("this")
                                .method("GET")
                                .href("/%s".formatted(null != id ? id : "*"))
                                .build()
                        , LinkDto.builder()
                                .rel("smartphones")
                                .method("POST")
                                .href("/%s/smartphones".formatted(null != id ? id : "*"))
                                .build()
                        , LinkDto.builder()
                                .rel("smartphones")
                                .method("GET")
                                .href("/%s/smartphones".formatted(null != id ? id : "*"))
                                .build()
                    )
            );
    }

    public static MarketDto fromMarketEntity(final MarketEntity marketEntity) {

        return MarketDto.builder()
                .id(
                        marketEntity.getId()
                                .toString()
                    )
                .registrationNumber(marketEntity.getRegistrationNumber())
                .name(marketEntity.getName())
                .build();
    }
}
