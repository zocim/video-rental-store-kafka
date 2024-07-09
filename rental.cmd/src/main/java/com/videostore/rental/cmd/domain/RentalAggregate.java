/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.domain;

import com.videostore.cqrs.core.domain.AggregateRoot;
import com.videostore.rental.cmd.api.commands.RentFilmCommand;
import com.videostore.rental.common.dto.RentalDto;
import com.videostore.rental.common.events.FilmRentedEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Data
@NoArgsConstructor
public class RentalAggregate extends AggregateRoot {
    private String filmTitle;
    private int days;
    private int price;
    private boolean isReturned;

    public RentalAggregate(String id) {
        this.id = id;
    }
    public RentalAggregate(RentFilmCommand command, int price) {
        raiseEvent(FilmRentedEvent.builder()
            .id(command.getId())
            .filmTitle(command.getFilmTitle())
            .days(command.getDays())
            .price(price)
            .build());
    }

    public void apply(FilmRentedEvent event) {
        this.id = event.getId();
        this.filmTitle = event.getFilmTitle();
        this.days = event.getDays();
        this.price = event.getPrice();
        this.isReturned = true;
    }

    public void returnFilm(RentalDto rentalDto) {
        raiseEvent(FilmRentedEvent.builder()
            .id(rentalDto.getId())
            .days(rentalDto.getDays())
            .price(rentalDto.getPrice())
            .isReturned(true)
            .filmTitle(rentalDto.getFilmTitle())
            .build());
    }

}
