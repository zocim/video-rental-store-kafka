/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.api.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videostore.rental.common.dto.RentalDto;
import com.videostore.rental.query.api.services.RentalQueryService;

/**
 * @author Created by Maneva.
 * @since 6.7.24.
 */
@RestController
@RequestMapping("/rentals")
public class RentalQueryController {
    private RentalQueryService rentalQueryService;

    public RentalQueryController(final RentalQueryService rentalQueryService) {
        this.rentalQueryService = rentalQueryService;
    }

    @GetMapping("/{rentalId}")
    public Optional<RentalDto> findNotReturnedRentalById(@PathVariable("rentalId") String rentalId) {
        return rentalQueryService.findRentalByIdAndAndReturnedFalse(rentalId);
    }
}
