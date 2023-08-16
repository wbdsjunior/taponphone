package io.github.wbdsjunior.taponphone.payments.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class PaymentRestController {

    @Operation(
              summary = "Get a payment"
            , description = "Get a payment"
            , requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    mediaType = "application/json"
                    , schema = @Schema(implementation = CreatePaymentDto.class))
                )
        )
    @ApiResponses({
              @ApiResponse(
                      responseCode = "204"
                    , description = "Payment"
                    , headers = @Header(
                              name = "Location"
                            , description = "Payment path"
                        )
                )
            , @ApiResponse(
                      responseCode = "404"
                    , description = "Payment not found"
                    , content = @Content(
                              mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                        )
                )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void post(@RequestBody(required = true) final CreatePaymentDto createPaymentDto) {

        throw new UnsupportedOperationException("Not implemented yet");
    }
}
