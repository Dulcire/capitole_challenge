package com.challenge.capitole.capitolechallenge.services.impl;

import java.sql.Timestamp;
import java.util.List;

import com.challenge.capitole.capitolechallenge.dtos.PriceDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;

public interface PriceService {

    /**
     * Method to get a list of prices which match with the data request from DB.
     * @param startDate start Date.
     * @param brandCode code of the brand.
     * @param productCode code of the product.
     * @return list of prices
     * @throws NotFoundException if there are no registers with the brand, product and date.
     */
    List<PriceDto> getPrice(final Timestamp startDate, final String brandCode, final String productCode)
        throws NotFoundException;
}
