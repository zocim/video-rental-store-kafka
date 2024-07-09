/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.infrastructure.handlers;

import com.videostore.rental.common.events.FilmRentedEvent;
import com.videostore.rental.common.events.FilmReturnedEvent;

public interface EventHandler {
    void on(FilmRentedEvent event);
    void on(FilmReturnedEvent event);
}
