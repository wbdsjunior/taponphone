package io.github.wbdsjunior.taponphone.payments.entities;

enum PaymentStatus {
      PENDING("Pending")
    , CONFIRMED("Comfirmed")
    , CANCELLED("Cancelled")
    , REFUNDED("Refunded");

    private final String value;

    private PaymentStatus(final String value) {

        this.value = value;
    }

    @Override
    public String toString() {

        return value;
    }
}
