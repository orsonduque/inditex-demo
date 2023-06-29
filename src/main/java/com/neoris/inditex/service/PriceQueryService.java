package com.neoris.inditex.service;

import com.neoris.inditex.domain.*; // for static metamodels
import com.neoris.inditex.domain.Price;
import com.neoris.inditex.repository.PriceRepository;
import com.neoris.inditex.service.criteria.PriceCriteria;
import com.neoris.inditex.service.dto.PriceDTO;
import com.neoris.inditex.service.mapper.PriceMapper;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Price} entities in the database.
 * The main input is a {@link PriceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PriceDTO} or a {@link Page} of {@link PriceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PriceQueryService extends QueryService<Price> {

    private static final String CRITERIA_REGEX = "[\n\r\t]";

    private final Logger log = LoggerFactory.getLogger(PriceQueryService.class);

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    public PriceQueryService(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    /**
     * Return a {@link List} of {@link PriceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PriceDTO> findByCriteria(PriceCriteria criteria) {
        String criteriaString = criteria.toString().replaceAll(CRITERIA_REGEX, "_");
        log.debug("find by criteria : {}", criteriaString);
        final Specification<Price> specification = createSpecification(criteria);
        return priceMapper.toDto(priceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PriceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PriceDTO> findByCriteria(@Valid PriceCriteria criteria, Pageable page) {
        String criteriaString = criteria.toString().replaceAll(CRITERIA_REGEX, "_");
        log.debug("find by criteria: {}, page: {}", criteriaString, page);
        final Specification<Price> specification = createSpecification(criteria);
        return priceRepository.findAll(specification, page).map(priceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PriceCriteria criteria) {
        String criteriaString = criteria.toString().replaceAll(CRITERIA_REGEX, "_");
        log.debug("count by criteria : {}", criteriaString);
        final Specification<Price> specification = createSpecification(criteria);
        return priceRepository.count(specification);
    }

    /**
     * Function to convert {@link PriceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Price> createSpecification(PriceCriteria criteria) {
        Specification<Price> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Price_.id));
            }
            if (criteria.getBrandId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBrandId(), Price_.brandId));
            }
            if (criteria.getPriceList() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceList(), Price_.priceList));
            }
            if (criteria.getProductId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProductId(), Price_.productId));
            }
            if (criteria.getPriceValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceValue(), Price_.priceValue));
            }
        }
        return specification;
    }
}
