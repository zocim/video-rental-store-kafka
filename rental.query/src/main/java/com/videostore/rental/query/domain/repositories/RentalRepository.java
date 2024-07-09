/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videostore.rental.query.domain.enitties.Rental;

public interface RentalRepository extends JpaRepository<Rental, String> {
    Optional<Rental> findByIdAndFilmTitle(String rentalId, String filmTitle);
    Optional<Rental> findByIdAndReturnedFalse(String rentalId);

}
