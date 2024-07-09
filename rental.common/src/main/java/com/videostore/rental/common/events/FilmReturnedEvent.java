/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.common.events;

import com.videostore.cqrs.core.events.BaseEvent;
import com.videostore.rental.common.dto.RentalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FilmReturnedEvent extends BaseEvent {
    private String filmTitle;
    private int daysLate;
    private int lateFee;
    private RentalDto rentalDto;
}
