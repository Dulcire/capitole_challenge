package com.challenge.capitole.capitolechallenge.controller;

import com.challenge.capitole.capitolechallenge.services.ResponseService;
import com.challenge.capitole.capitolechallenge.util.CapitoleConstants;
import com.challenge.capitole.capitolechallenge.utils.TestGenerator;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.challenge.capitole.capitolechallenge.utils.TestConstant.BRAND_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.PRODUCT_CODE_1;
import static com.challenge.capitole.capitolechallenge.utils.TestConstant.START_DATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@WebMvcTest(ResponseController.class)
class ResponseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ResponseService service;

    @Test
    void getResponseTest() throws Exception {
        Mockito.when(service.getInfo(START_DATE, BRAND_CODE_1, PRODUCT_CODE_1)).thenReturn(TestGenerator.infoDtoListGenerator(START_DATE));
        MvcResult mvcResult = getResponse(mockMvc, START_DATE, PRODUCT_CODE_1, BRAND_CODE_1);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void getResponseNullProductTest() throws Exception {
        MvcResult mvcResult = getResponse(mockMvc, START_DATE, null, BRAND_CODE_1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void getResponseNullBrandTest() throws Exception {
        MvcResult mvcResult = getResponse(mockMvc, START_DATE, PRODUCT_CODE_1, null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
    }

    public MvcResult getResponse(final MockMvc mockMvc, final Timestamp date, final String productCode, final String brandCode) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(CapitoleConstants.BASE_URL + CapitoleConstants.PRODUCT_PATH)
                .param("applicationDate", date.toString())
                .param("productCode", productCode)
                .param("brandCode", brandCode))
            .andReturn();
    }
}