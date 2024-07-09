/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.infrastructure.consumers;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import com.videostore.rental.common.events.FilmRentedEvent;
import com.videostore.rental.common.events.FilmReturnedEvent;

public interface EventConsumer {
    void consume(@Payload FilmRentedEvent event, Acknowledgment ack);
    void consume(@Payload FilmReturnedEvent event, Acknowledgment ack);
}
