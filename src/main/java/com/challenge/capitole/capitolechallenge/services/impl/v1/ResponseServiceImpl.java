package com.challenge.capitole.capitolechallenge.services.impl.v1;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.capitole.capitolechallenge.services.impl.BrandService;
import com.challenge.capitole.capitolechallenge.services.impl.PriceService;
import com.challenge.capitole.capitolechallenge.services.impl.ProductService;
import com.challenge.capitole.capitolechallenge.services.impl.ResponseService;
import com.challenge.capitole.capitolechallenge.dtos.InfoDto;
import com.challenge.capitole.capitolechallenge.dtos.PriceDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.exception.ValidationException;
import com.challenge.capitole.capitolechallenge.util.CapitoleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {


    @Autowired
    private PriceService priceServiceImpl;

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private BrandService brandServiceImpl;

    private final CapitoleMapper mapper
        = Mappers.getMapper(CapitoleMapper.class);

    @Override
    public List<InfoDto> getInfo(final Timestamp startDate, final String brandCode, final String productCode) throws NotFoundException {
        try {
            brandServiceImpl.getBrandtByCode(brandCode);
            productServiceImpl.getProductByCode(productCode);
        } catch (final NotFoundException exception) {
            throw new ValidationException(exception.getMessage());
        }
        return getInfo(priceServiceImpl.getPrice(startDate, brandCode, productCode));
    }

    /**
     * Method to set the information obtained from a list of prices.
     * @param prices list of prices from DB.
     * @return List of information to send to a user.
     */
    private List<InfoDto> getInfo(final List<PriceDto> prices) {
        return prices.stream().map(mapper::mapToInfoDto).collect(Collectors.toList());
    }


}
