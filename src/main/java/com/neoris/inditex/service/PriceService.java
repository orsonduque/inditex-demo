package com.neoris.inditex.service;

import com.neoris.inditex.service.dto.PriceDTO;
import com.neoris.inditex.service.dto.PriceOutputDTO;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.neoris.inditex.domain.Price}.
 */
public interface PriceService {
    /**
     * Save a price.
     *
     * @param priceDTO the entity to save.
     * @return the persisted entity.
     */
    PriceDTO save(PriceDTO priceDTO);

    /**
     * Partially updates a price.
     *
     * @param priceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PriceDTO> partialUpdate(PriceDTO priceDTO);

    /**
     * Get all the prices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PriceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" price.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PriceDTO> findOne(Long id);

    /**
     * Get the "date" price.
     *
     * @param date the date to filter.
     * @param brandId the brand id to filter.
     * @param productId the productId id to filter.
     * @return the entity.
     */
    Optional<PriceOutputDTO> findOneByDateAndBrandIdAndProductId(ZonedDateTime date, Long brandId, Long productId);

    /**
     * Delete the "id" price.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
