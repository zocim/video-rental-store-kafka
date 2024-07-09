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

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnFilmResponse extends BaseResponse {
    private List<String> returnIds;
    private int lateFee;
    private List<String> errors;

    public ReturnFilmResponse(String message, List<String> returnIds,
                              int lateFee, List<String> errors) {
        super(message);
        this.returnIds = returnIds;
        this.lateFee = lateFee;
        this.errors = errors;
    }

}
