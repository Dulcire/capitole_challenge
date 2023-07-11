package com.challenge.capitole.capitolechallenge.services.impl;

import java.sql.Timestamp;
import java.util.List;

import com.challenge.capitole.capitolechallenge.dtos.InfoDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;

public interface ResponseService {

    /**
     * Method to get product information.
     *
     * @param startDate   application date.
     * @param brandCode   brand identification.
     * @param productCode product identification.
     * @return ResponseDto with Product information.
     */
    List<InfoDto> getInfo(final Timestamp startDate, final String brandCode, final String productCode)
        throws NotFoundException;
}
