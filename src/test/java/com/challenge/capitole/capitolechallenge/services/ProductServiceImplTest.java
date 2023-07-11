package com.challenge.capitole.capitolechallenge.services;

import com.challenge.capitole.capitolechallenge.dtos.ProductDto;
import com.challenge.capitole.capitolechallenge.exception.NotFoundException;
import com.challenge.capitole.capitolechallenge.repositories.ProductRepository;
import com.challenge.capitole.capitolechallenge.services.impl.ProductService;
import com.challenge.capitole.capitolechallenge.util.ErrorMessages;
import com.challenge.capitole.capitolechallenge.utils.TestGenerator;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_CODE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductServiceImplTest {

    @MockBean
    private ProductRepository repository;

    @Autowired
    private ProductService service;

    @Test
    void getBrandByCodeTest() throws NotFoundException {
        Mockito.when(repository.findProductByCode(PRODUCT_CODE_1)).thenReturn(Optional.of(TestGenerator.productGenerator()));
        final ProductDto actual = service.getProductByCode(PRODUCT_CODE_1);
        final ProductDto expected = TestGenerator.productDtoGenerator();

        assertEquals(actual.getCode(), expected.getCode());
        assertEquals(actual.getName(), expected.getName());
    }

    @Test()
    void getBrandByCodeExceptionTest() {
        Exception exception = assertThrows(NotFoundException.class, () -> service
            .getProductByCode(PRODUCT_CODE_1));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(String.format(ErrorMessages.PRODUCT_NOT_FOUND, PRODUCT_CODE_1)));
    }
}