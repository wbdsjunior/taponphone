package io.github.wbdsjunior.taponphone.markets.entities;

import java.util.Objects;

public class Smartphone {

    private final String phoneNumber;
    // private final Market market;

    public Smartphone(final String phoneNumber /* , final Market market */) {

        if (
                       null == phoneNumber
                    || phoneNumber.trim()
                            .isEmpty()
                ) {

            throw new IllegalStateException("Smartphone phone number cannot be blank");
        }
        this.phoneNumber = phoneNumber;

        // if (null == market) {

        //     throw new IllegalStateException("Smartphone market cannot be null");
        // }
        // this.market = market;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    // public Market getMarket() {

    //     return market;
    // }

    @Override
    public int hashCode() {

        return Objects.hash(phoneNumber);
    }

    @Override
    public boolean equals(Object smartphone) {

        if (this == smartphone) {

            return true;
        }

        if (!(smartphone instanceof Smartphone)) {

            return false;
        }
        return Objects.equals(
                  phoneNumber
                , ((Smartphone) smartphone).phoneNumber
            );
    }
}
