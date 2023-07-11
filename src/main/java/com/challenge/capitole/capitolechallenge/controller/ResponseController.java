package com.challenge.capitole.capitolechallenge.controller;

import java.sql.Timestamp;

import com.challenge.capitole.capitolechallenge.dtos.ErrorDetails;
import com.challenge.capitole.capitolechallenge.dtos.ResponseDto;
import com.challenge.capitole.capitolechallenge.services.impl.v1.ResponseServiceImpl;
import com.challenge.capitole.capitolechallenge.util.CapitoleConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CapitoleConstants.BASE_URL)
@RequiredArgsConstructor
@Validated
public class ResponseController {

    @Autowired
    public ResponseServiceImpl service;

    @Operation(summary = "Get Response", description = "Get Information about a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful Response",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ErrorDetails.class))),
        @ApiResponse(responseCode = "404", description = "Not Found",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ErrorDetails.class))),
        @ApiResponse(responseCode = "500", description = "Service Error",
            content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ErrorDetails.class)))
    })
    @GetMapping(CapitoleConstants.PRODUCT_PATH)
    public ResponseDto getResponse(
        @RequestParam @NotNull @NotBlank final Timestamp applicationDate,
        @RequestParam @NotNull @NotBlank final String productCode,
        @RequestParam @NotNull @NotBlank final String brandCode) throws Exception {
        return ResponseDto.builder().response(service.getInfo(applicationDate, brandCode, productCode)).build();
    }
}
