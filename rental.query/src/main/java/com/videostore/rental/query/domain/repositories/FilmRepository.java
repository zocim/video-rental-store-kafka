/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.videostore.rental.query.domain.enitties.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

    Optional<Film> findByTitle(String title);
}
