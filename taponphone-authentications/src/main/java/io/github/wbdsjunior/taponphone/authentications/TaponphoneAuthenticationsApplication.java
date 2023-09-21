package io.github.wbdsjunior.taponphone.authentications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
          title = "Authentications API"
        , summary = "Wbdsjunior's Tap on Phone Authentications API"
        , description = "Authenticate users"
    ))
@SpringBootApplication
@ComponentScan(basePackages = { "io.github.wbdsjunior.taponphone" })
public class TaponphoneAuthenticationsApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                  TaponphoneAuthenticationsApplication.class
                , args
            );
    }
}
