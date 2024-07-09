/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.api.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.videostore.rental.common.enums.FilmType;
import com.videostore.rental.query.api.services.RentalQueryService;

/**
 * @author Created by Maneva.
 * @since 6.7.24.
 */

@RestController
@RequestMapping("/films")
public class FilmQueryController {

    private RentalQueryService rentalQueryService;

    public FilmQueryController(RentalQueryService rentalQueryService) {
        this.rentalQueryService = rentalQueryService;
    }

    @GetMapping("/filmType")
    public Optional<FilmType> findFilmTypeByTitle(@RequestParam("title") String title) {
        return rentalQueryService.findFilmTypeByTitle(title);
    }
}
