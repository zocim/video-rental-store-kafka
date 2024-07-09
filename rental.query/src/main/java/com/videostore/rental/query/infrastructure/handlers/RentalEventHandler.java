/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.infrastructure.handlers;

import org.springframework.stereotype.Service;

import com.videostore.rental.common.events.FilmRentedEvent;
import com.videostore.rental.common.events.FilmReturnedEvent;
import com.videostore.rental.query.domain.enitties.Rental;
import com.videostore.rental.query.domain.enitties.Return;
import com.videostore.rental.query.domain.mappers.RentalMapper;
import com.videostore.rental.query.domain.repositories.RentalRepository;
import com.videostore.rental.query.domain.repositories.ReturnRepository;

/**
 * @author Created by Maneva.
 * @since 2.7.24.
 */
@Service
public class RentalEventHandler implements EventHandler {
    private RentalRepository rentalRepository;
    private ReturnRepository returnRepository;

    public RentalEventHandler(final RentalRepository rentalRepository, final ReturnRepository returnRepository) {
        this.rentalRepository = rentalRepository;
        this.returnRepository = returnRepository;
    }

    @Override
    public void on(FilmRentedEvent event) {
        var rental = Rental.builder()
            .id(event.getId())
            .filmTitle(event.getFilmTitle())
            .days(event.getDays())
            .price(event.getPrice())
            .build();
        rentalRepository.save(rental);
    }

    @Override
    public void on(FilmReturnedEvent event) {
        var returnF = Return.builder()
            .returnId(event.getId())
            .daysLate(event.getDaysLate())
            .lateFee(event.getLateFee())
            .rental(RentalMapper.INSTANCE.toEntity(event.getRentalDto()))
            .build();
        var rental = returnF.getRental();
        rental.setReturned(true);
        returnRepository.save(returnF);
        rentalRepository.save(rental);
    }

}
