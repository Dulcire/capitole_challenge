package com.challenge.capitole.capitolechallenge.services.impl.v1;

import com.challenge.capitole.capitolechallenge.dtos.ProductDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.model.Product;
import com.challenge.capitole.capitolechallenge.repositories.ProductRepository;
import com.challenge.capitole.capitolechallenge.services.impl.ProductService;
import com.challenge.capitole.capitolechallenge.util.CapitoleMapper;
import com.challenge.capitole.capitolechallenge.util.ErrorMessages;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository respository;

    private final CapitoleMapper mapper
        = Mappers.getMapper(CapitoleMapper.class);

    @Override
    public ProductDto getProductByCode(final String code) throws NotFoundException {
        return getProductInfo(code);
    }

    /**
     * Method to get a product from DB.
     * @param code product code.
     * @return a product information.
     * @throws NotFoundException if the product does not exist in DB.
     */
    private ProductDto getProductInfo(final String code) throws NotFoundException {
        final Product product = respository.findProductByCode(code)
            .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.PRODUCT_NOT_FOUND, code)));
        return mapper.productToProductDto(product);
    }
}
