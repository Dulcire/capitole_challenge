package com.challenge.capitole.capitolechallenge.util;

import com.challenge.capitole.capitolechallenge.dtos.BrandDto;
import com.challenge.capitole.capitolechallenge.dtos.InfoDto;
import com.challenge.capitole.capitolechallenge.dtos.PriceDto;
import com.challenge.capitole.capitolechallenge.dtos.PriceListDto;
import com.challenge.capitole.capitolechallenge.dtos.ProductDto;
import com.challenge.capitole.capitolechallenge.model.Brand;
import com.challenge.capitole.capitolechallenge.model.Price;
import com.challenge.capitole.capitolechallenge.model.PriceList;
import com.challenge.capitole.capitolechallenge.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapitoleMapper {
    PriceDto priceToPriceDto(Price source);

    BrandDto brandToBrandDto(Brand source);

    Brand brandDtoToBrand(BrandDto destination);

    ProductDto productToProductDto(Product source);

    Product productDtoToProduct(ProductDto destination);

    PriceListDto priceListToPriceListDto(PriceList source);

    PriceList priceListDtoToPriceList(PriceListDto destination);


    @Mapping(source = "price", target = "pvp")
    @Mapping(source = "brandId.code", target = "brandCode")
    @Mapping(source = "productId.code", target = "productCode")
    @Mapping(source = "priceList.price", target = "priceList")
    @Mapping(source = "startDate", target = "date")
    InfoDto mapToInfoDto(PriceDto priceDto);
}
