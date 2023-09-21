package io.github.wbdsjunior.taponphone.markets.persistences;

import java.util.UUID;

import io.github.wbdsjunior.taponphone.markets.entities.Smartphone;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "smartphone")
public class SmartphoneEntity {

    @NotNull
    @ManyToOne
    private MarketEntity market;

    @Size(
              min = 1
            , max = 32
        )
    private String phoneNumber;

    private boolean active;

    @Id
    private UUID id;

    public SmartphoneEntity(
              final UUID marketId
            , final Smartphone smartphone
        ) {

        market = new MarketEntity(marketId);
        phoneNumber = smartphone.getPhoneNumber();
    }
}
