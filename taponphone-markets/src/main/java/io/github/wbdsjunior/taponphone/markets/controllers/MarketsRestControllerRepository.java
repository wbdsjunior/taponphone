package io.github.wbdsjunior.taponphone.markets.controllers;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.github.wbdsjunior.taponphone.markets.entities.Market;
import io.github.wbdsjunior.taponphone.markets.entities.MarketsRepository;
import io.github.wbdsjunior.taponphone.markets.persistences.MarketEntity;
import io.github.wbdsjunior.taponphone.markets.persistences.MarketsJpaRepository;

@Repository
public class MarketsRestControllerRepository implements MarketsRepository<MarketDto, String> {

    private final MarketsJpaRepository marketsJpaRepository;

    public MarketsRestControllerRepository(final MarketsJpaRepository marketsJpaRepository) {
        this.marketsJpaRepository = marketsJpaRepository;
    }

    @Override
    public MarketDto create(Market market) {

         return MarketDto.fromMarketEntity(marketsJpaRepository.save(MarketEntity.fromMarket(market)));
    }

    @Override
    public Optional<MarketDto> selectByRegistrationNumber(String registrationNumber) {

        return marketsJpaRepository.findByRegistrationNumber(registrationNumber)
                .map(MarketDto::fromMarketEntity);
    }
}
