package com.challenge.capitole.capitolechallenge.services;


import com.challenge.capitole.capitolechallenge.dtos.InfoDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.exception.ValidationException;
import com.challenge.capitole.capitolechallenge.model.Brand;
import com.challenge.capitole.capitolechallenge.model.Price;
import com.challenge.capitole.capitolechallenge.model.PriceList;
import com.challenge.capitole.capitolechallenge.model.Product;
import com.challenge.capitole.capitolechallenge.repositories.BrandRepository;
import com.challenge.capitole.capitolechallenge.repositories.PriceListRepository;
import com.challenge.capitole.capitolechallenge.repositories.PriceRepository;
import com.challenge.capitole.capitolechallenge.repositories.ProductRepository;
import com.challenge.capitole.capitolechallenge.services.impl.ResponseService;
import com.challenge.capitole.capitolechallenge.util.ErrorMessages;
import com.challenge.capitole.capitolechallenge.utils.TestGenerator;
import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_CODE_2;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_CODE_3;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_ID_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_NAME_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_NAME_2;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.ID_2;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRICE_ID_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRICE_LIST_PRICE2;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_CODE_2;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_CODE_3;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_ID_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_NAME_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_NAME_2;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.START_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResponseServiceImplTest {

    @Autowired
    private ResponseService responseServiceImpl;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private PriceRepository priceRepository;

    final Timestamp date = new Timestamp(System.currentTimeMillis());

    @BeforeAll
    void setUp() {
        //Creating Brand in DB
        final Brand brand1 = brandRepository.save(TestGenerator.brandGenerator(BRAND_ID_1, BRAND_CODE_1, BRAND_NAME_1));
        final Brand brand2 = brandRepository.save(TestGenerator.brandGenerator(ID_2, BRAND_CODE_2, BRAND_NAME_2));

        //Creating Product in DB
        final Product product1 = productRepository.save(
            TestGenerator.productGenerator(PRODUCT_ID_1,
                PRODUCT_CODE_1,
                PRODUCT_NAME_1));
        final Product product2 = productRepository.save(
            TestGenerator.productGenerator(ID_2,
                PRODUCT_CODE_2,
                PRODUCT_NAME_2));

        //Creating Price List in DB
        final PriceList priceList1 = priceListRepository.save(TestGenerator.price_ListGenerator());
        final PriceList priceList2 = priceListRepository.save(TestGenerator.price_ListGenerator(
            ID_2,
            PRICE_LIST_PRICE2));

        //Creating Price in DB
        priceRepository.save(TestGenerator.priceListGenerator(
            PRICE_ID_1, product1, brand1, priceList1, date).get(0));
        priceRepository.save(TestGenerator.priceListGenerator(
            ID_2, product2, brand2, priceList2, date).get(0));
    }

    @AfterAll
    void cleanUp() {
        priceRepository.deleteAll();
        brandRepository.deleteAll();
        productRepository.deleteAll();
        priceListRepository.deleteAll();
    }

    @Test
    void getInfoTest() throws NotFoundException {
        final List<InfoDto> expected = TestGenerator.infoDtoListGenerator(date);
        final List<InfoDto> infoDto = responseServiceImpl
            .getInfo(date, BRAND_CODE_1, PRODUCT_CODE_1);
        assertFalse(infoDto.isEmpty());
        assertEquals(expected.get(0), infoDto.get(0));
    }

    @Test
    void getInfoNotFoundProductTest() {
        Exception exception = assertThrows(NotFoundException.class, () -> responseServiceImpl
            .getInfo(date, BRAND_CODE_1, PRODUCT_CODE_2));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(
            ErrorMessages.DATA_NOT_FOUND, PRODUCT_CODE_2, BRAND_CODE_1, date)));

    }

    @Test()
    void getInfoNotFoundBrandTest() {
        Exception exception = assertThrows(NotFoundException.class, () -> responseServiceImpl
            .getInfo(date, BRAND_CODE_2, PRODUCT_CODE_1));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(
            ErrorMessages.DATA_NOT_FOUND, PRODUCT_CODE_1, BRAND_CODE_2, date)));
    }

    @Test()
    void getInfoNotFoundDateTest() {
        Exception exception = assertThrows(NotFoundException.class, () -> responseServiceImpl
            .getInfo(START_DATE, BRAND_CODE_1, PRODUCT_CODE_1));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(
            ErrorMessages.DATA_NOT_FOUND, PRODUCT_CODE_1, BRAND_CODE_1, START_DATE)));
    }

    @Test
    void getInfoValidationProductTest() {
        Exception exception = assertThrows(ValidationException.class, () -> responseServiceImpl
            .getInfo(date, BRAND_CODE_1, PRODUCT_CODE_3));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(
            ErrorMessages.PRODUCT_NOT_FOUND, PRODUCT_CODE_3)));

    }

    @Test()
    void getInfoValidationBrandTest() {
        Exception exception = assertThrows(ValidationException.class, () -> responseServiceImpl
            .getInfo(date, BRAND_CODE_3, PRODUCT_CODE_1));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(
            ErrorMessages.BRAND_NOT_FOUND, BRAND_CODE_3)));
    }


}
