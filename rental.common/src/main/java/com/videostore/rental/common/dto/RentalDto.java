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
 * @since 6.7.24.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    private String id;
    private String filmTitle;
    private int days;
    private int price;
    private boolean returned;
}
