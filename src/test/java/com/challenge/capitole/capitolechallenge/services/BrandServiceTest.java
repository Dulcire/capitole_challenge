package com.challenge.capitole.capitolechallenge.services;

import com.challenge.capitole.capitolechallenge.dtos.BrandDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.repositories.BrandRepository;
import com.challenge.capitole.capitolechallenge.util.ErrorMessages;
import com.challenge.capitole.capitolechallenge.utils.TestGenerator;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_CODE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BrandServiceTest {

    @MockBean
    private BrandRepository repository;

    @Autowired
    private BrandService service;

    @Test
    void getBrandByCodeTest() throws NotFoundException {
        Mockito.when(repository.findBrandByCode(BRAND_CODE_1)).thenReturn(Optional.of(TestGenerator.brandGenerator()));
        final BrandDto actual = service.getBrandtByCode(BRAND_CODE_1);
        final BrandDto expected = TestGenerator.brandDtoGenerator();

        assertEquals(actual.getCode(), expected.getCode());
        assertEquals(actual.getName(), expected.getName());
    }

    @Test()
    void getBrandByCodeExceptionTest() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.getBrandtByCode(BRAND_CODE_1);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(ErrorMessages.BRAND_NOT_FOUND, BRAND_CODE_1)));
    }
}