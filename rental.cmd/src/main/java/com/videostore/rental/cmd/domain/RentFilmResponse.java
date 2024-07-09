/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.domain;

import java.util.List;

import com.videostore.rental.common.dto.BaseResponse;

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
public class RentFilmResponse extends BaseResponse {
    private List<String> rentalIds;
    private int totalPrice;
    private List<String> errors;

    public RentFilmResponse(String message, List<String> rentalIds, int totalPrice, List<String> errors) {
        super(message);
        this.rentalIds = rentalIds;
        this.totalPrice = totalPrice;
        this.errors = errors;
    }

}
