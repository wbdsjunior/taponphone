package io.github.wbdsjunior.taponphone.markets.usecases;

import io.github.wbdsjunior.taponphone.markets.entities.MarketsRepository;
import io.github.wbdsjunior.taponphone.markets.entities.ToMarket;

public class CreateMarketService<M extends ToMarket, I> {

    private final MarketsRepository<?, I> marketsRepository;

    public CreateMarketService(final MarketsRepository<?, I> marketsRepository) {

        this.marketsRepository = marketsRepository;
    }

    public I create(final M market) {

        if (null == market) {

            throw new IllegalStateException("Market cannot be null");
        }
        return marketsRepository.create(market.toMarket())
                .getId();
    }
}
