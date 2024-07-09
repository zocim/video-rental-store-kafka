/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by Maneva.
 * @since 7.7.24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnDto {
    private String id;
    private int daysLate;
    private int lateFee;
    private RentalDto rentalDto;
}
