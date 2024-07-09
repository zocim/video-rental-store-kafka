/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.query.domain.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * @author Created by Maneva.
 * @since 6.7.24.
 */
// @Configuration
// @EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    //@Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?>
    kafkaListenerContainerFactory(ConsumerFactory<?, ?> consumerFactory, KafkaErrorHandler errorHandler) {
        ConcurrentKafkaListenerContainerFactory<?, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // factory.setConsumerFactory(consumerFactory);
        // factory.setErrorHandler(errorHandler);
        return factory;
    }
}
