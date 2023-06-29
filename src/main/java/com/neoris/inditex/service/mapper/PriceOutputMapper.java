package com.neoris.inditex.service.mapper;

import com.neoris.inditex.domain.Price;
import com.neoris.inditex.service.dto.PriceOutputDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Price} and its DTO {@link PriceOutputDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceOutputMapper extends EntityMapper<PriceOutputDTO, Price> {}
