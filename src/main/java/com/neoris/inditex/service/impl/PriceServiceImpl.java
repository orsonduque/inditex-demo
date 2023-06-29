package com.neoris.inditex.service.impl;

import com.neoris.inditex.domain.Price;
import com.neoris.inditex.repository.PriceRepository;
import com.neoris.inditex.service.PriceService;
import com.neoris.inditex.service.dto.PriceDTO;
import com.neoris.inditex.service.dto.PriceOutputDTO;
import com.neoris.inditex.service.mapper.PriceMapper;
import com.neoris.inditex.service.mapper.PriceOutputMapper;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Price}.
 */
@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    private final PriceOutputMapper priceOutputMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper, PriceOutputMapper priceOutputMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
        this.priceOutputMapper = priceOutputMapper;
    }

    @Override
    public PriceDTO save(PriceDTO priceDTO) {
        log.debug("Request to save Price : {}", priceDTO);
        Price price = priceMapper.toEntity(priceDTO);
        price = priceRepository.save(price);
        return priceMapper.toDto(price);
    }

    @Override
    public Optional<PriceDTO> partialUpdate(PriceDTO priceDTO) {
        log.debug("Request to partially update Price : {}", priceDTO);

        return priceRepository
            .findById(priceDTO.getId())
            .map(existingPrice -> {
                priceMapper.partialUpdate(existingPrice, priceDTO);

                return existingPrice;
            })
            .map(priceRepository::save)
            .map(priceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PriceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Prices");
        return priceRepository.findAll(pageable).map(priceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PriceDTO> findOne(Long id) {
        log.debug("Request to get Price : {}", id);
        return priceRepository.findById(id).map(priceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PriceOutputDTO> findOneByDateAndBrandIdAndProductId(ZonedDateTime date, Long brandId, Long productId) {
        log.debug("Request to get Price by date: {}, brandId: {} and productId: {} ", date, brandId, productId);
        Optional<Price> price = priceRepository
            .findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, date, date)
            .stream()
            .findFirst();
        log.debug("Request to get Price : {}", price);
        return price.map(priceOutputMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Price : {}", id);
        priceRepository.deleteById(id);
    }
}
