package io.github.wbdsjunior.taponphone.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Payments API", summary = "Wbdsjunior' Tap On Phone Payments API", description = "Pay with a smartphone, list payments made with a smartphone, look up payment details, and change payment status"))
@SpringBootApplication
public class TaponphonePaymentsApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaponphonePaymentsApplication.class, args);
    }
}
