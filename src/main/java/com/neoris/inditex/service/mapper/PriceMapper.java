package com.neoris.inditex.service.mapper;

import com.neoris.inditex.domain.Price;
import com.neoris.inditex.service.dto.PriceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Price} and its DTO {@link PriceDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceMapper extends EntityMapper<PriceDTO, Price> {}
