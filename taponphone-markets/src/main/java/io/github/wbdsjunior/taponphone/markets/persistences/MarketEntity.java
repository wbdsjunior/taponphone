package io.github.wbdsjunior.taponphone.markets.persistences;

import java.util.UUID;

import io.github.wbdsjunior.taponphone.markets.entities.Market;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "market")
public class MarketEntity {

    @Size(
              min = 1
            , max = 32
        )
    private String registrationNumber;

    @Size(
              min = 1
            , max = 256
        )
    private String name;

    @Id
    private UUID id;

    public MarketEntity(final UUID id) {

        this(
                  null
                , null
                , id
            );
    }

    public MarketEntity(final Market market) {

        this.registrationNumber = market.getRegistrationNumber();
        this.name = market.getName();
    }
}
