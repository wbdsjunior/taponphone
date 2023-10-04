package io.github.wbdsjunior.taponphone.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "io.github.wbdsjunior.taponphone" })
public class TaponphoneApiGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                  TaponphoneApiGatewayApplication.class
                , args
            );
    }
}
