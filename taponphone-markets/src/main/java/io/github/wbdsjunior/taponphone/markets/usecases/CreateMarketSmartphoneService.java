package io.github.wbdsjunior.taponphone.markets.usecases;

import io.github.wbdsjunior.taponphone.markets.entities.SmartphonesRepository;
import io.github.wbdsjunior.taponphone.markets.entities.ToSmartphone;

public class CreateMarketSmartphoneService<S extends ToSmartphone, I> {

    private final SmartphonesRepository<?, I> smartphonesRepository;

    public CreateMarketSmartphoneService(final SmartphonesRepository<?, I> smartphonesRepository) {

        this.smartphonesRepository = smartphonesRepository;
    }

    public I create(final String marketRegistrationNumber, final S smartphone) {

        if (
                       null == marketRegistrationNumber
                    || marketRegistrationNumber.trim()
                            .isEmpty()
                ) {

            throw new IllegalStateException("Market registration number be null or empty");
        }

        if (null == smartphone) {

            throw new IllegalStateException("Smartphone cannot be null");
        }
        I id = smartphonesRepository.insert(marketRegistrationNumber, smartphone.toSmartphone()).getId();

        // TODO: enviar mensagem para o broker

        return id;
    }
}
