package com.challenge.capitole.capitolechallenge.services.impl;

import com.challenge.capitole.capitolechallenge.dtos.ProductDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;

public interface ProductService {

    /**
     * Method to get a product from DB.
     * @param code product code.
     * @return a product information.
     * @throws NotFoundException if the product does not exist in DB.
     */
    ProductDto getProductByCode(final String code) throws NotFoundException;
}
