package com.challenge.capitole.capitolechallenge.services.impl;

import com.challenge.capitole.capitolechallenge.dtos.BrandDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;

public interface BrandService {

    /**
     * Method to get a Brand form the DB using the code.
     * @param code code
     * @return Brand Dto
     * @throws NotFoundException if the brand does not exist.
     */
    BrandDto getBrandtByCode(final String code) throws NotFoundException;
}
