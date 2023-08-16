package io.github.wbdsjunior.taponphone.markets.persistences;

public enum PaymentStatusEntity {

      PENDING("Pending")
    , CONFIRMED("Comfirmed")
    , CANCELLED("Cancelled")
    , REFUNDED("Refunded");

    private final String value;

    private PaymentStatusEntity(final String value) {

        this.value = value;
    }

    @Override
    public String toString() {

        return value;
    }
}
