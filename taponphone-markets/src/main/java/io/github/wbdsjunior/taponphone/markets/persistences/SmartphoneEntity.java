package io.github.wbdsjunior.taponphone.markets.persistences;

import java.util.UUID;

import io.github.wbdsjunior.taponphone.markets.entities.Smartphone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "smartphone")
public class SmartphoneEntity {

    @Id
    private UUID id;

    @Column(length = 16, nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MarketEntity market;

    public static SmartphoneEntity fromSmarphone(final UUID marketId, final Smartphone smartphone) {

        return SmartphoneEntity.builder()
                .id(UUID.randomUUID())
                .phoneNumber(smartphone.getPhoneNumber())
                .market(
                        MarketEntity.builder()
                                .id(marketId)
                                .build())
                .build();
    }
}
