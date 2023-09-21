package io.github.wbdsjunior.taponphone.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
          title = "Payments API"
        , summary = "Wbdsjunior's Tap on Phone Payments API"
        , description = "Pay with a smartphone, list payments made with a smartphone, look up payment details, and change payment status"
    ))
@SpringBootApplication
@ComponentScan(basePackages = { "io.github.wbdsjunior.taponphone" })
public class TaponphonePaymentsApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                  TaponphonePaymentsApplication.class
                , args
            );
    }
}
