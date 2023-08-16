package io.github.wbdsjunior.taponphone.payments.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("payees-smartphones/{payeeSmartphoneId:[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}}/payments")
public class PayeeSmartphonePaymentsRestController {

    @Operation(
              summary = "Find payments"
            , description = "Find payee' smartphone payments"
            , parameters = @Parameter(
                      in = ParameterIn.PATH
                    , description = "Payee' smartphone ID"
                )
        )
    @ApiResponses({
              @ApiResponse(
                    responseCode = "200"
                    , description = "Payments"
                    , content = @Content(
                              mediaType = "application/json"
                            , array = @ArraySchema(schema = @Schema(implementation = PaymentDto.class))
                        )
                )
            , @ApiResponse(
                      responseCode = "404"
                    , description = "Payee' Smartphone not found"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = ProblemDetail.class))
                )
        })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> payments(@PathVariable final UUID payeeSmartphoneId) {

        return new ArrayList<>(Arrays.asList(
                 PaymentDto.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .date(LocalDateTime.now()
                                .minusHours(1))
                        .amount(BigDecimal.valueOf(100.0))
                        .status("CONFIRMED")
                        .payeeSmartphone(PayeeSmartphoneDto.builder()
                                .id(payeeSmartphoneId.toString())
                                .build())
                        .build()
                , PaymentDto.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .date(LocalDateTime.now()
                                .minusMinutes(50))
                        .amount(BigDecimal.valueOf(100.0))
                        .status("CONFIRMED")
                        .payeeSmartphone(PayeeSmartphoneDto.builder()
                                .id(payeeSmartphoneId.toString())
                                .build())
                        .build()
                , PaymentDto.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .date(LocalDateTime.now()
                                .minusMinutes(30))
                        .amount(BigDecimal.valueOf(100.0))
                        .status("CONFIRMED")
                        .payeeSmartphone(PayeeSmartphoneDto.builder()
                                .id(payeeSmartphoneId.toString())
                                .build())
                        .build()
                , PaymentDto.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .date(LocalDateTime.now()
                                .minusMinutes(5))
                        .amount(BigDecimal.valueOf(100.0))
                        .status("CANCELLED")
                        .payeeSmartphone(PayeeSmartphoneDto.builder()
                                .id(payeeSmartphoneId.toString())
                                .build())
                        .build()
                , PaymentDto.builder()
                        .id(UUID.randomUUID()
                                .toString())
                        .date(LocalDateTime.now())
                        .amount(BigDecimal.valueOf(100.0))
                        .status("PENDING")
                        .payeeSmartphone(PayeeSmartphoneDto.builder()
                                .id(payeeSmartphoneId.toString())
                                .build())
                        .build()
            ));
    }
}
