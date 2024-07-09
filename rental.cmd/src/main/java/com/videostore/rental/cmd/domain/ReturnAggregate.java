/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.domain;

import com.videostore.cqrs.core.domain.AggregateRoot;
import com.videostore.rental.cmd.api.commands.ReturnFilmCommand;
import com.videostore.rental.common.dto.RentalDto;
import com.videostore.rental.common.events.FilmReturnedEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by Maneva.
 * @since 7.7.24.
 */
@Data
@NoArgsConstructor
public class ReturnAggregate extends AggregateRoot {
    private String filmTitle;
    private int daysLate;
    private int lateFee;
    private RentalDto rentalDto;

    public ReturnAggregate(String id) {
        this.id = id;
    }
    public ReturnAggregate(ReturnFilmCommand command, int lateFee, RentalDto rentalDto) {
        rentalDto.setReturned(true);
        raiseEvent(FilmReturnedEvent.builder()
            .id(command.getId())
            .filmTitle(command.getFilmTitle())
            .daysLate(command.getDaysLate())
            .lateFee(lateFee)
            .rentalDto(rentalDto)
            .rentalDto(rentalDto)
            .build());
    }

    public void apply(FilmReturnedEvent event) {
        this.id = event.getId();
        this.filmTitle = event.getFilmTitle();
        this.daysLate = event.getDaysLate();
        this.lateFee = event.getLateFee();
        this.rentalDto = event.getRentalDto();
    }
}
