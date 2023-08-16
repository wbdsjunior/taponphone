package io.github.wbdsjunior.taponphone.markets.persistences;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphonesJpaRepository extends JpaRepository<SmartphoneEntity, UUID> {

    public Optional<SmartphoneEntity> findByPhoneNumber(final String phoneNumber);

    public List<SmartphoneEntity> findByMarketRegistrationNumber(final String marketRegistrationNumber);
}
