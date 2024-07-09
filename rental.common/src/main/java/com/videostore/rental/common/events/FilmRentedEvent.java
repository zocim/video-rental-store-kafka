/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.common.events;

import com.videostore.cqrs.core.events.BaseEvent;

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
public class FilmRentedEvent extends BaseEvent {
    private String filmTitle;
    private int days;
    private int price;
    private boolean isReturned;

}
