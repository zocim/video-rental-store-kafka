/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.infrastructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.videostore.rental.common.events.FilmRentedEvent;
import com.videostore.rental.common.events.FilmReturnedEvent;
import com.videostore.rental.query.infrastructure.handlers.EventHandler;

/**
 * @author Created by Maneva.
 * @since 2.7.24.
 */
@Service
public class RentalEventConsumer implements EventConsumer {
    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "FilmRentedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FilmRentedEvent event, final Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FilmReturnedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FilmReturnedEvent event, final Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
