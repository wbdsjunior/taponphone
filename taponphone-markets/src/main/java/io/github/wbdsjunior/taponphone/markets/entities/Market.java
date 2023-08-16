package io.github.wbdsjunior.taponphone.markets.entities;

import java.util.Objects;

public class Market {

    private final String registrationNumber;
    private final String name;

    public Market(
              final String registrationNumber
            , final String name
        ) {

        if (
                      null == registrationNumber
                    || registrationNumber.trim()
                            .isEmpty()
                ) {

            throw new IllegalStateException("Market registration number cannot be null or empty");
        }
        this.registrationNumber = registrationNumber.trim();

        if (
                       null == name
                    || name.trim()
                            .isEmpty()
                ) {

            throw new IllegalStateException("Market name cannot be null or empty");
        }
        this.name = name;
    }

    public String getRegistrationNumber() {

        return registrationNumber;
    }

    public String getName() {

        return name;
    }

    @Override
    public int hashCode() {

        return Objects.hash(registrationNumber);
    }

    @Override
    public boolean equals(Object market) {

        if (this == market) {

            return true;
        }

        if (!(market instanceof Market)) {

            return false;
        }
        return Objects.equals(
                  registrationNumber
                , ((Market) market).registrationNumber
            );
    }
}
