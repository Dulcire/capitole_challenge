package com.challenge.capitole.capitolechallenge.services.impl.v1;

import com.challenge.capitole.capitolechallenge.dtos.BrandDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.model.Brand;
import com.challenge.capitole.capitolechallenge.repositories.BrandRepository;
import com.challenge.capitole.capitolechallenge.services.impl.BrandService;
import com.challenge.capitole.capitolechallenge.util.CapitoleMapper;
import com.challenge.capitole.capitolechallenge.util.ErrorMessages;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository respository;

    private final CapitoleMapper mapper
        = Mappers.getMapper(CapitoleMapper.class);


    @Override
    public BrandDto getBrandtByCode(final String code) throws NotFoundException {
        return getBrandInfo(code);
    }

    /**
     * Method to get a Brand form the DB using the code.
     * @param code code
     * @return Brand Dto
     * @throws NotFoundException if the brand does not exist.
     */
    private BrandDto getBrandInfo(final String code) throws NotFoundException {
        final Brand brand = respository.findBrandByCode(code)
            .orElseThrow(() -> new NotFoundException(String.format(ErrorMessages.BRAND_NOT_FOUND, code)));
        return mapper.brandToBrandDto(brand);
    }
}
