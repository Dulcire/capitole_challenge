package com.challenge.capitole.capitolechallenge.services;

import com.challenge.capitole.capitolechallenge.dtos.PriceDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.repositories.PriceRepository;
import com.challenge.capitole.capitolechallenge.util.ErrorMessages;
import com.challenge.capitole.capitolechallenge.utils.TestGenerator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.START_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class PriceServiceTest {

    @MockBean
    private PriceRepository repository;

    @Autowired
    private PriceService service;

    @Test
    void getBrandByCodeTest() throws NotFoundException {
        Mockito.when(repository.findByStartDateAndBrandId_CodeAndProductId_Code(START_DATE, BRAND_CODE_1, PRODUCT_CODE_1))
            .thenReturn(TestGenerator.priceListGenerator(START_DATE));
        final List<PriceDto> actual = service.getPrice(START_DATE, BRAND_CODE_1, PRODUCT_CODE_1);
        final List<PriceDto> expected = TestGenerator.priceDtoListGenerator(START_DATE);

        assertEquals(expected.get(0).getBrandId(), actual.get(0).getBrandId());
        assertEquals(expected.get(0).getCurr(), actual.get(0).getCurr());
        assertEquals(expected.get(0).getPriceList(), actual.get(0).getPriceList());
        assertEquals(expected.get(0).getPrice(), actual.get(0).getPrice());
        assertEquals(expected.get(0).getStartDate(), actual.get(0).getStartDate());
        assertEquals(expected.get(0).getEndDate(), actual.get(0).getEndDate());
        assertEquals(expected.get(0).getProductId(), actual.get(0).getProductId());
        assertEquals(expected.get(0).getPriority(), actual.get(0).getPriority());

    }

    @Test()
    void getBrandByCodeNotFoundExceptionTest() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.getPrice(START_DATE, BRAND_CODE_1, PRODUCT_CODE_1);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(ErrorMessages.DATA_NOT_FOUND, PRODUCT_CODE_1, BRAND_CODE_1, START_DATE)));
    }
}