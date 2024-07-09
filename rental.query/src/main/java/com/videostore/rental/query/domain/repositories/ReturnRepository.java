/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.videostore.rental.query.domain.enitties.Return;

public interface ReturnRepository extends CrudRepository<Return, String> {
}
