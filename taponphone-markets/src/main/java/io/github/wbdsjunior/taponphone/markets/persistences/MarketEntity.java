package io.github.wbdsjunior.taponphone.markets.persistences;

import java.util.UUID;

import io.github.wbdsjunior.taponphone.markets.entities.Market;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "market")
public class MarketEntity {

    @Id
    private UUID id;

    @Column(length = 32, nullable = false, unique = true)
    private String registrationNumber;

    @Column(length = 256, nullable = false, unique = true)
    private String name;

    public static MarketEntity fromMarket(final Market market) {

        return MarketEntity.builder()
                .id(UUID.randomUUID())
                .registrationNumber(market.getRegistrationNumber())
                .name(market.getName())
                .build();
    }
}
