/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.videostore.rental.common.dto.RentalDto;
import com.videostore.rental.query.domain.enitties.Rental;

/**
 * @author Created by Maneva.
 * @since 6.7.24.
 */
@Mapper
public interface RentalMapper {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    RentalDto toDto(Rental rental);
    Rental toEntity(RentalDto rEntalDto);

}
