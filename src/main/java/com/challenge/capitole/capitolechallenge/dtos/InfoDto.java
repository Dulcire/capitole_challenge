package com.challenge.capitole.capitolechallenge.dtos;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoDto {
    private String productCode;
    private String brandCode;
    private Double priceList;
    private Timestamp date;
    private Double pvp;
}
