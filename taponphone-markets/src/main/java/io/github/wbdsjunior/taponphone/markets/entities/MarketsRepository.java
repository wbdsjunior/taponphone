package io.github.wbdsjunior.taponphone.markets.entities;

import java.util.Optional;

public interface MarketsRepository<M extends GetId<I>, I> {

    M create(final Market market);

    Optional<M> selectByRegistrationNumber(final String registrationNumber);
}
