package io.github.wbdsjunior.taponphone.markets.persistences;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentEntity(
          UUID id
        , LocalDateTime date
        , BigDecimal amount
        , PaymentStatusEntity status
    ) {
}
