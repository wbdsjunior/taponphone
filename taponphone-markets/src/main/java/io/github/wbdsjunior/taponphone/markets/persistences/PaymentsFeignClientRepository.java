package io.github.wbdsjunior.taponphone.markets.persistences;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="taponphone-payments", url = "${SPRING_FEIGNCLIENT_TAPONPHONE_PAYMENTS_SERVICE_URL:http://localhost:8082}")
public interface PaymentsFeignClientRepository {

    @GetMapping("payees-smartphones/{payeeSmartphoneId:[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}}/payments")
    public List<PaymentEntity> findByPayeeSmartphoneId(@PathVariable final UUID payeeSmartphoneId);
}
