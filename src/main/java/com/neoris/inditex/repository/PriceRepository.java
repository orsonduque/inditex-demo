package com.neoris.inditex.repository;

import com.neoris.inditex.domain.Price;
import java.time.ZonedDateTime;
import java.util.Set;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Price entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceRepository extends JpaRepository<Price, Long>, JpaSpecificationExecutor<Price> {
    Set<Price> findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
        Long brandId,
        Long productId,
        ZonedDateTime dateA,
        ZonedDateTime dateB
    );
}
