package com.neoris.inditex.web.rest;

import static com.neoris.inditex.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.neoris.inditex.IntegrationTest;
import com.neoris.inditex.domain.Price;
import com.neoris.inditex.domain.enumeration.ISOCurrencyCode;
import com.neoris.inditex.repository.PriceRepository;
import com.neoris.inditex.service.PriceQueryService;
import com.neoris.inditex.service.PriceService;
import com.neoris.inditex.service.criteria.PriceCriteria;
import com.neoris.inditex.service.dto.PriceDTO;
import com.neoris.inditex.service.dto.PriceOutputDTO;
import com.neoris.inditex.service.mapper.PriceMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PriceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PriceResourceIT {

    private static final String ENTITY_API_URL = "/api/prices";

    @Autowired
    private PriceService priceService;

    @Autowired
    private MockMvc restPriceMockMvc;

    @ParameterizedTest
    @CsvSource(
        {
            "2020-06-14T10:00:00+02:00, 1, 35455", // Test 1
            "2020-06-14T16:00:00+02:00, 1, 35455", // Test 2
            "2020-06-14T21:00:00+02:00, 1, 35455", // Test 3
            "2020-06-15T10:00:00+02:00, 1, 35455", // Test 4
            "2020-06-16T21:00:00+02:00, 1, 35455", // Test 5
        }
    )
    void testGetPrice(ZonedDateTime dateTime, Long brandId, Long productId) throws Exception {
        Optional<PriceOutputDTO> optionalPriceOutputDTO = priceService.findOneByDateAndBrandIdAndProductId(dateTime, brandId, productId);
        PriceOutputDTO priceOutputDTO = optionalPriceOutputDTO.orElseThrow(() -> new AssertionError("priceOutput is null"));

        restPriceMockMvc
            .perform(get(ENTITY_API_URL + "/brand/{brandId}/product/{productId}/date/{date}", brandId, productId, dateTime))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.brandId").value(priceOutputDTO.getBrandId()))
            .andExpect(jsonPath("$.productId").value(priceOutputDTO.getProductId()))
            .andExpect(jsonPath("$.priceList").value(priceOutputDTO.getPriceList()))
            .andExpect(jsonPath("$.priceValue").value(priceOutputDTO.getPriceValue()));
    }
}
