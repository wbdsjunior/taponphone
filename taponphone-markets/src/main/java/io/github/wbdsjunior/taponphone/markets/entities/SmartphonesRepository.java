package io.github.wbdsjunior.taponphone.markets.entities;

import java.util.List;
import java.util.Optional;

public interface SmartphonesRepository<S extends GetId<I>, I> {

    S insert(final String marketRegistrationNumber, final Smartphone smartphone);

    Optional<S> selectByPhoneNumber(final String phoneNumber);

    List<S> selectByMarketRegistrationNumber(final String marketRegistrationNumber);
}
