/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.videostore.rental.common.dto.RentalDto;
import com.videostore.rental.query.domain.enitties.Film;
import com.videostore.rental.query.domain.mappers.RentalMapper;
import com.videostore.rental.query.domain.repositories.FilmRepository;
import com.videostore.rental.query.domain.repositories.RentalRepository;
import com.videostore.rental.common.enums.FilmType;

/**
 * @author Created by Maneva.
 * @since 5.7.24.
 */
@Service
public class RentalQueryService {

    private RentalRepository rentalRepository;
    private FilmRepository filmRepository;

    public RentalQueryService(RentalRepository rentalRepository, FilmRepository filmRepository) {
        this.rentalRepository = rentalRepository;
        this.filmRepository = filmRepository;
    }

    public Optional<FilmType> findFilmTypeByTitle(String filmTitle) {
        Optional<Film> filmOptional = filmRepository.findByTitle(filmTitle);
        return filmOptional.map(Film::getFilmType);
    }

    public Optional<RentalDto> findRentalByIdAndAndReturnedFalse(String rentalId) {
        return rentalRepository.findByIdAndReturnedFalse(rentalId)
            .map(rental -> RentalMapper.INSTANCE.toDto(rental));
    }
    
    
}
