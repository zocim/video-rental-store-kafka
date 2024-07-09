/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.api.openfeign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.videostore.rental.common.dto.RentalDto;
import com.videostore.rental.common.enums.FilmType;

@FeignClient(name = "rentalQueryClient", url = "${rental.query.url}")
public interface RentalQueryClient {

    @GetMapping("films/filmType")
    Optional<FilmType> findFilmTypeByTitle(@RequestParam("title") String title);


    @GetMapping("/rentals/{rentalId}")
    Optional<RentalDto> findNotReturnedRentalById(@PathVariable String rentalId);
}
