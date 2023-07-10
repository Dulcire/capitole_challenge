package com.challenge.capitole.capitolechallenge.utils;

import com.challenge.capitole.capitolechallenge.dtos.BrandDto;
import com.challenge.capitole.capitolechallenge.dtos.InfoDto;
import com.challenge.capitole.capitolechallenge.dtos.PriceDto;
import com.challenge.capitole.capitolechallenge.dtos.PriceListDto;
import com.challenge.capitole.capitolechallenge.dtos.ProductDto;
import com.challenge.capitole.capitolechallenge.enums.Currency;
import com.challenge.capitole.capitolechallenge.enums.Priority;
import com.challenge.capitole.capitolechallenge.model.Brand;
import com.challenge.capitole.capitolechallenge.model.Price;
import com.challenge.capitole.capitolechallenge.model.PriceList;
import com.challenge.capitole.capitolechallenge.model.Product;
import java.sql.Timestamp;
import java.util.List;

import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_ID_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_NAME_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.END_DATE;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRICE_ID_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRICE_LIST_ID_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRICE_LIST_PRICE;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRICE_PRICE;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_NAME_1;

public class TestGenerator {

    public static Brand brandGenerator() {
        return brandGenerator(BRAND_ID_1, BRAND_CODE_1, BRAND_NAME_1);
    }

    public static Brand brandGenerator(final Long id, final String code, final String name) {
        return Brand.builder()
            .id(id)
            .code(code)
            .name(name)
            .build();
    }

    public static BrandDto brandDtoGenerator() {
        return BrandDto.builder()
            .code(BRAND_CODE_1)
            .name(BRAND_NAME_1)
            .build();
    }

    public static Product productGenerator() {
        return productGenerator(PRICE_ID_1, PRODUCT_CODE_1, PRODUCT_NAME_1);
    }

    public static Product productGenerator(final Long id, final String code, final String name) {
        return Product.builder()
            .id(id)
            .code(code)
            .name(name)
            .build();
    }

    public static ProductDto productDtoGenerator() {
        return ProductDto.builder()
            .code(PRODUCT_CODE_1)
            .name(PRODUCT_NAME_1)
            .build();
    }

    public static PriceListDto priceListDtoGenerator() {
        return PriceListDto.builder()
            .price(PRICE_LIST_PRICE)
            .build();
    }

    public static PriceList price_ListGenerator() {
        return price_ListGenerator(PRICE_LIST_ID_1, PRICE_LIST_PRICE);
    }

    public static PriceList price_ListGenerator(final Long id, final Double price) {
        return PriceList.builder()
            .id(id)
            .price(price)
            .build();
    }

    public static List<Price> priceListGenerator(final Timestamp startDate) {
        return priceListGenerator(PRICE_ID_1,
            productGenerator(),
            brandGenerator(),
            PRICE_PRICE,
            price_ListGenerator(),
            Currency.EUR,
            Priority.LOW,
            startDate,
            END_DATE);
    }

    public static List<Price> priceListGenerator(final Long id,
                                                 final Product product,
                                                 final Brand brand,
                                                 final PriceList priceList,
                                                 final Timestamp startDate
    ) {
        return priceListGenerator(id,
            product,
            brand,
            PRICE_PRICE,
            priceList,
            Currency.EUR,
            Priority.LOW,
            startDate,
            END_DATE);

    }

    public static List<Price> priceListGenerator(final Long id,
                                                 final Product product,
                                                 final Brand brand,
                                                 final Double price,
                                                 final PriceList priceList,
                                                 final Currency curr,
                                                 final Priority priority,
                                                 final Timestamp startDate,
                                                 final Timestamp endDate) {
        return List.of(Price.builder()
            .id(id)
            .productId(product)
            .brandId(brand)
            .price(price)
            .priceList(priceList)
            .curr(curr)
            .startDate(startDate)
            .priority(priority)
            .endDate(endDate)
            .build());
    }

    public static List<PriceDto> priceDtoListGenerator(final Timestamp startDate) {
        return List.of(PriceDto.builder()
            .priority(Priority.LOW)
            .productId(productDtoGenerator())
            .brandId(brandDtoGenerator())
            .price(PRICE_PRICE)
            .priceList(priceListDtoGenerator())
            .curr(Currency.EUR)
            .startDate(startDate)
            .endDate(END_DATE)
            .build());
    }

    public static List<InfoDto> infoDtoListGenerator(final Timestamp date) {
        return List.of(InfoDto.builder()
            .brandCode(BRAND_CODE_1)
            .date(date)
            .productCode(PRODUCT_CODE_1)
            .pvp(PRICE_PRICE)
            .priceList(PRICE_LIST_PRICE)
            .build());
    }
}
