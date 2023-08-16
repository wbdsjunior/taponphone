package io.github.wbdsjunior.taponphone.markets.persistences;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaymentEntity {

    private UUID id;
    private LocalDateTime date;
    private BigDecimal amount;
    private PaymentStatusEntity status;

    @JsonProperty("_links")
    private List<LinkEntity> links;
}
