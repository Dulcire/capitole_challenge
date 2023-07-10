package com.challenge.capitole.capitolechallenge.services;

import static com.challenge.capitole.capitolechallenge.util.ErrorMessages.DATA_NOT_FOUND;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.capitole.capitolechallenge.dtos.PriceDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.model.Price;
import com.challenge.capitole.capitolechallenge.repositories.PriceRepository;
import com.challenge.capitole.capitolechallenge.util.CapitoleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;



@Service
public class PriceService {

    @Autowired
    private PriceRepository respository;


    private final CapitoleMapper mapper
        = Mappers.getMapper(CapitoleMapper.class);

    public List<PriceDto> getPrice(final Timestamp startDate, final String brandCode, final String productCode)
        throws NotFoundException {
        return getPriceInfo(startDate, brandCode, productCode);
    }

    private List<PriceDto> getPriceInfo(final Timestamp startDate, final String brandCode, final String productCode)
        throws NotFoundException {
        final List<Price> priceList = respository
            .findByStartDateAndBrandId_CodeAndProductId_Code(startDate, brandCode, productCode);
        if (CollectionUtils.isEmpty(priceList)) {
            throw new NotFoundException(String.format(DATA_NOT_FOUND,productCode,brandCode,startDate));
        }
        return priceList.stream().map(mapper::priceToPriceDto).collect(Collectors.toList());
    }


}
