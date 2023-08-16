package io.github.wbdsjunior.taponphone.markets.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.wbdsjunior.taponphone.markets.persistences.MarketsJpaRepository;
import io.github.wbdsjunior.taponphone.markets.usecases.CreateMarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class MarketsRestController {

    private final CreateMarketService<CreateMarketDto, String> createMarketService;
    private final MarketsJpaRepository marketsJpaRepository;

    public MarketsRestController(final CreateMarketService<CreateMarketDto, String> createMarketService, final MarketsJpaRepository marketsJpaRepository) {

        this.createMarketService = createMarketService;
        this.marketsJpaRepository = marketsJpaRepository;
    }

    @Operation(
              summary = "Create a market"
            , description = "Create a market and get its url"
            , parameters = @Parameter(
                      in = ParameterIn.PATH
                    , description = "Market id"
                )
            , requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                          mediaType = "application/json"
                        , schema = @Schema(implementation = CreateMarketDto.class)
                    )
                )
        )
    @ApiResponses({
              @ApiResponse(
                      responseCode = "201", description = "Created market path"
                    , headers = @Header(
                              name = "Location"
                            , description = "Created market path"
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
    public ResponseEntity<Void> create(@RequestBody(required = true) final CreateMarketDto createMarketDto) {

        return ResponseEntity.created(URI.create("/%s".formatted(createMarketService.create(createMarketDto))))
                .build();
    }

    @GetMapping("{marketId:[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}}")
    @ResponseStatus(HttpStatus.OK)
    public MarketDto find(@PathVariable final UUID marketId) {

        return marketsJpaRepository.findById(marketId)
            .map(MarketDto::fromMarketEntity)
            .orElseThrow(() -> new IllegalStateException("Market not found"));
    }
}
