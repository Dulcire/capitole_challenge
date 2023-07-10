package com.challenge.capitole.capitolechallenge.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.capitole.capitolechallenge.dtos.InfoDto;
import com.challenge.capitole.capitolechallenge.dtos.PriceDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.exception.ValidationException;
import com.challenge.capitole.capitolechallenge.util.CapitoleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {


    @Autowired
    private PriceService priceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    private final CapitoleMapper mapper
        = Mappers.getMapper(CapitoleMapper.class);

    /**
     * Method to get product information.
     *
     * @param startDate   application date.
     * @param brandCode   brand identification.
     * @param productCode product identification.
     * @return ResponseDto with Product information.
     */
    public List<InfoDto> getInfo(final Timestamp startDate, final String brandCode, final String productCode) throws NotFoundException {
        try {
            brandService.getBrandtByCode(brandCode);
            productService.getProductByCode(productCode);
        } catch (final NotFoundException exception) {
            throw new ValidationException(exception.getMessage());
        }
        return getInfo(priceService.getPrice(startDate, brandCode, productCode));
    }

    private List<InfoDto> getInfo(final List<PriceDto> prices) {
        return prices.stream().map(mapper::mapToInfoDto).collect(Collectors.toList());
    }


}
