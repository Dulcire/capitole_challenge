package com.challenge.capitole.capitolechallenge.services;

import com.challenge.capitole.capitolechallenge.dtos.ProductDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.model.Product;
import com.challenge.capitole.capitolechallenge.repositories.ProductRepository;
import com.challenge.capitole.capitolechallenge.util.CapitoleMapper;
import com.challenge.capitole.capitolechallenge.util.ErrorMessages;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository respository;

    private final CapitoleMapper mapper
        = Mappers.getMapper(CapitoleMapper.class);

    public ProductDto getProductByCode(final String code) throws NotFoundException {
        return getProductInfo(code);
    }

    private ProductDto getProductInfo(final String code) throws NotFoundException {
        final Product product = respository.findProductByCode(code)
            .orElseThrow(() -> {
                return new NotFoundException(String.format(ErrorMessages.PRODUCT_NOT_FOUND, code));
            });
        return mapper.productToProductDto(product);
    }
}
