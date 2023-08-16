package io.github.wbdsjunior.taponphone.markets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.github.wbdsjunior.taponphone.markets.entities.MarketsRepository;
import io.github.wbdsjunior.taponphone.markets.entities.SmartphonesRepository;
import io.github.wbdsjunior.taponphone.markets.usecases.CreateMarketService;
import io.github.wbdsjunior.taponphone.markets.usecases.CreateMarketSmartphoneService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Markets API", summary = "Wbdsjunior's Tap On Phone Market API", description = "Register markets, look up market details, add smartphones to a market, look up market' smartphone details, and list payments made with a smartphone"))
@SpringBootApplication
@EnableFeignClients
public class TaponphoneMarketsApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaponphoneMarketsApplication.class, args);
    }

    @Bean
    public CreateMarketService<?, ?> createMarketService(final MarketsRepository<?, ?> marketsRepository) {

        return new CreateMarketService<>(marketsRepository);
    }

    @Bean
    public CreateMarketSmartphoneService<?, ?> createMarketSmartphoneService(final SmartphonesRepository<?, ?> smartphonesRepository) {

        return new CreateMarketSmartphoneService<>(smartphonesRepository);
    }

    // @Bean
    // public KafkaTemplate<?, ?> kafkaTemplate(@Value(value = "${spring.kafka.bootstrap-servers:localhost:9092}") final String kafkaBootstrapServers) {

    //     Map<String, Object> configs = new HashMap<>();
    //     configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
    //     configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    //     configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    //     return new KafkaTemplate<>(new DefaultKafkaProducerFactory<String, SmartphoneDto>(configs));
    // }
}
