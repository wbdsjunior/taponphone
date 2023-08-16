package io.github.wbdsjunior.taponphone.payments.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("{paymentId:[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}}")
public class PaymentsRestController {

    @Operation(
              summary = "Get a payment"
            , description = "Get a payment by its id"
            , parameters = @Parameter(
                      in = ParameterIn.PATH
                    , description = "Payment id"
                )
        )
    @ApiResponses({
            @ApiResponse(
                      responseCode = "200"
                    , description = "The payment"
                    , content = @Content(
                              mediaType = "application/json"
                            , schema = @Schema(implementation = PaymentDto.class)
                        )
                ),
            @ApiResponse(
                      responseCode = "404"
                    , description = "Payment not found"
                    , content = @Content(
                              mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                        )
                )
        })
    @GetMapping
    public PaymentDto get(@PathVariable final UUID paymentId) {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(
              summary = "Confirm a payment"
            , description = "Blá blá blá"
            , parameters = @Parameter(
                      in = ParameterIn.PATH
                    , description = "Payment id"))
    @ApiResponses({
            @ApiResponse(
                      responseCode = "204"
                    , description = "Payment comfirmed"
                ),
            @ApiResponse(
                      responseCode = "404"
                    , description = "Payment not found"
                    , content = @Content(
                              mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                        )
                )
        })
    @PatchMapping("statuses/comfirmed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable final UUID paymentId) {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(
              summary = "Cancel a payment"
            , description = "Blá blá blá"
            , parameters = @Parameter(
                      in = ParameterIn.PATH
                    , description = "Payment id"))
    @ApiResponses({
            @ApiResponse(
                      responseCode = "204"
                    , description = "Payment canceled"
                ),
            @ApiResponse(
                      responseCode = "404"
                    , description = "Payment not found"
                    , content = @Content(
                              mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                        )
                )
        })
    @PatchMapping("statuses/cancelled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable final UUID paymentId) {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Operation(
              summary = "Refaund a payment"
            , description = "Blá blá blá"
            , parameters = @Parameter(
                      in = ParameterIn.PATH
                    , description = "Payment id"))
    @ApiResponses({
            @ApiResponse(
                      responseCode = "204"
                    , description = "Payment refunded"
                ),
            @ApiResponse(
                      responseCode = "404"
                    , description = "Payment not found"
                    , content = @Content(
                              mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                        )
                )
        })
    @PatchMapping("statuses/refunded")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void refund(@PathVariable final UUID paymentId) {

        throw new UnsupportedOperationException("Not implemented yet");
    }
}
