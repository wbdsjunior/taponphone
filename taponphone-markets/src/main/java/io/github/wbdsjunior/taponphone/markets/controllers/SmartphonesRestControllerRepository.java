package io.github.wbdsjunior.taponphone.markets.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.github.wbdsjunior.taponphone.markets.entities.Smartphone;
import io.github.wbdsjunior.taponphone.markets.entities.SmartphonesRepository;
import io.github.wbdsjunior.taponphone.markets.persistences.MarketEntity;
import io.github.wbdsjunior.taponphone.markets.persistences.MarketsJpaRepository;
import io.github.wbdsjunior.taponphone.markets.persistences.SmartphoneEntity;
import io.github.wbdsjunior.taponphone.markets.persistences.SmartphonesJpaRepository;

@Repository
public class SmartphonesRestControllerRepository implements SmartphonesRepository<SmartphoneDto, String> {

    private final SmartphonesJpaRepository smartphonesJpaRepository;
    private final MarketsJpaRepository marketsJpaRepository;

    public SmartphonesRestControllerRepository(
              final SmartphonesJpaRepository smartphonesJpaRepository
            , final MarketsJpaRepository marketsJpaRepository
        ) {

        this.smartphonesJpaRepository = smartphonesJpaRepository;
        this.marketsJpaRepository = marketsJpaRepository;
    }

    @Override
    public SmartphoneDto insert(
              final String marketRegistrationNumber
            , final Smartphone smartphone
        ) {

        return new SmartphoneDto(smartphonesJpaRepository.save(new SmartphoneEntity(
                  marketsJpaRepository.findByRegistrationNumber(marketRegistrationNumber)
                        .map(MarketEntity::getId)
                        .orElseThrow(() -> new IllegalStateException("Market not found"))
                , smartphone
            )));
    }

    @Override
    public Optional<SmartphoneDto> selectByPhoneNumber(final String phoneNumber) {

        return smartphonesJpaRepository.findByPhoneNumber(phoneNumber)
                .map(SmartphoneDto::new);
    }

    @Override
    public List<SmartphoneDto> selectByMarketRegistrationNumber(final String marketRegistrationNumber) {

        return smartphonesJpaRepository.findByMarketRegistrationNumber(marketRegistrationNumber)
                .stream()
                .map(SmartphoneDto::new)
                .toList();
    }
}
