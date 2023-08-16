package io.github.wbdsjunior.taponphone.markets.controllers;

import java.net.URI;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.wbdsjunior.taponphone.markets.persistences.MarketEntity;
import io.github.wbdsjunior.taponphone.markets.persistences.MarketsJpaRepository;
import io.github.wbdsjunior.taponphone.markets.persistences.SmartphonesJpaRepository;
import io.github.wbdsjunior.taponphone.markets.usecases.CreateMarketSmartphoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("{marketId:[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}}/smartphones")
public class MarketSmartphonesRestController {

    private final CreateMarketSmartphoneService<CreateSmartphoneDto, String> createMarketSmartphoneService;
    private final MarketsJpaRepository marketsJpaRepository;
    private final SmartphonesJpaRepository smartphonesJpaRepository;

    public MarketSmartphonesRestController(
              final CreateMarketSmartphoneService<CreateSmartphoneDto, String> createMarketSmartphoneService
            , final MarketsJpaRepository marketsJpaRepository
            , final SmartphonesJpaRepository smartphonesJpaRepository) {

        this.createMarketSmartphoneService = createMarketSmartphoneService;
        this.marketsJpaRepository = marketsJpaRepository;
        this.smartphonesJpaRepository = smartphonesJpaRepository;
    }

    @Operation(
              summary = "Create a smartphone"
            , description = "Create a smartphone and get its path"
            , parameters = @Parameter(
                      in = ParameterIn.PATH
                    , description = "Market id"
                )
            , requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                          mediaType = "application/json"
                        , schema = @Schema(implementation = CreateSmartphoneDto.class)
                    )
                )
        )
    @ApiResponses({
              @ApiResponse(
                      responseCode = "201", description = "Created smartphone path"
                    , headers = @Header(
                              name = "Location"
                            , description = "Created smartphone path"
                        )
                )
            , @ApiResponse(
                      responseCode = "404"
                    , description = "Market not found"
                    , content = @Content(
                              mediaType = "application/json"
                            , schema = @Schema(implementation = ProblemDetail.class)
                        )
                )
        })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(
              @PathVariable final UUID marketId
            , @RequestBody(required = true) final CreateSmartphoneDto createSmartphoneDto
        ) {

        return ResponseEntity.created(URI.create("/smartphones/%s".formatted(createMarketSmartphoneService.create(
                          marketsJpaRepository.findById(marketId)
                                .map(MarketEntity::getRegistrationNumber)
                                .orElseThrow(() -> new IllegalStateException("Market not found"))
                        , createSmartphoneDto
                    ))))
            .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<SmartphoneDto> smartphones(@PathVariable final UUID marketId) {

        return smartphonesJpaRepository.findByMarketRegistrationNumber(marketsJpaRepository.findById(marketId)
                        .map(MarketEntity::getRegistrationNumber)
                        .orElseThrow(() -> new IllegalStateException("Market not found")))
            .stream()
            .map(SmartphoneDto::fromSmartphoneEntity)
            .collect(Collectors.toSet());
    }
}
