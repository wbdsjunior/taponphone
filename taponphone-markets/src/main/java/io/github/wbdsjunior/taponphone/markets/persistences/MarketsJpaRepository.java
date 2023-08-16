package io.github.wbdsjunior.taponphone.markets.persistences;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketsJpaRepository extends JpaRepository<MarketEntity, UUID> {

    Optional<MarketEntity> findByRegistrationNumber(final String registrationNumber);
}
