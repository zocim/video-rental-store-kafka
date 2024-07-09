/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.infrastructure;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.videostore.cqrs.core.events.BaseEvent;
import com.videostore.cqrs.core.producers.EventProducer;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Service
public class RentalEventProducer implements EventProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;

    public RentalEventProducer(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
}
