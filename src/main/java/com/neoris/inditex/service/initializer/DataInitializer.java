package com.neoris.inditex.service.initializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.inditex.domain.Price;
import com.neoris.inditex.repository.PriceRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final PriceRepository priceRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public DataInitializer(PriceRepository priceRepository, ObjectMapper objectMapper) {
        this.priceRepository = priceRepository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void initData() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/data.json");
        List<Price> prices = objectMapper.readValue(inputStream, new TypeReference<List<Price>>() {});
        priceRepository.saveAll(prices);
    }
}
