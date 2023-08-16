package io.github.wbdsjunior.taponphone.payments.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

class Payment {

    private final LocalDateTime date;
    private final BigDecimal amount;
    private final PayeeSmartphone payeeSmartphone;
    private PaymentStatus status;

    Payment(final BigDecimal amount, PayeeSmartphone payeeSmartphone) {

        this(LocalDateTime.now(), amount, payeeSmartphone, PaymentStatus.PENDING);
    }

    Payment(final LocalDateTime date, final BigDecimal amount, PayeeSmartphone payeeSmartphone, PaymentStatus status) {

        if (null == date) {

            throw new IllegalStateException("Payment date cannot be null");
        }
        this.date = date;

        if (null == amount || 0 > BigDecimal.ZERO.compareTo(amount)) {

            throw new IllegalStateException("Payment amount must be greater than zero");
        }
        this.amount = amount;

        if (null == payeeSmartphone) {

            throw new IllegalStateException("Payment payee smartphone cannot be null");
        }
        this.payeeSmartphone = payeeSmartphone;

        if (null == status) {

            throw new IllegalStateException("Payment status cannot be null");
        }
        this.status = status;
    }

    LocalDateTime getDate() {

        return date;
    }

    BigDecimal getAmount() {

        return amount;
    }

    PayeeSmartphone getPayeeSmartphone() {

        return payeeSmartphone;
    }

    String getStatus() {

        return status.toString();
    }

    boolean isPending() {

        return PaymentStatus.PENDING.equals(status);
    }

    boolean isComfirmed() {

        return PaymentStatus.CONFIRMED.equals(status);
    }

    boolean isCancelled() {

        return PaymentStatus.CANCELLED.equals(status);
    }

    boolean isRefunded() {

        return PaymentStatus.REFUNDED.equals(status);
    }

    void confirm() {

        if (canConfirm()) {

            throw new IllegalStateException("You cannot confirm a %s payment".formatted(status));
        }
        status = PaymentStatus.CONFIRMED;
    }

    private boolean canConfirm() {

        return isPending();
    }

    void cancel() {

        if (canCancel()) {

            throw new IllegalStateException("You cannot cancel a %s payment".formatted(status));
        }
        status = PaymentStatus.CANCELLED;
    }

    private boolean canCancel() {

        return isPending() || isComfirmed();
    }

    void refund() {

        if (canRefund()) {

            throw new IllegalStateException("You cannot refund a %s payment".formatted(status));
        }
        status = PaymentStatus.CANCELLED;
    }

    private boolean canRefund() {

        return isComfirmed();
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, status);
    }

    @Override
    public boolean equals(Object payment) {

        if (this == payment) {

            return true;
        }

        if (!(payment instanceof Payment)) {

            return false;
        }
        Payment other = (Payment) payment;
        return Objects.equals(amount, other.amount) && status == other.status;
    }
}
