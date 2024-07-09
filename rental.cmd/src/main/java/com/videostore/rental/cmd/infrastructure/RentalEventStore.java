/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.videostore.cqrs.core.events.BaseEvent;
import com.videostore.cqrs.core.events.EventModel;
import com.videostore.cqrs.core.exceptions.AggregateNotFoundException;
import com.videostore.cqrs.core.exceptions.ConcurrencyException;
import com.videostore.cqrs.core.infrastructure.EventStore;
import com.videostore.cqrs.core.producers.EventProducer;
import com.videostore.rental.cmd.domain.EventStoreRepository;
import com.videostore.rental.cmd.domain.RentalAggregate;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Service
public class RentalEventStore implements EventStore {
    private EventProducer eventProducer;
    private EventStoreRepository eventStoreRepository;

    public RentalEventStore(final EventProducer eventProducer, final EventStoreRepository eventStoreRepository) {
        this.eventProducer = eventProducer;
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if(expectedVersion != -1 && eventStream.get(eventStream.size()-1).getVersion() != expectedVersion)  {
            throw new ConcurrencyException();
        }

        var version = expectedVersion;
        for(var event: events) {
            version++;
            event.setVersion(version);

            var eventModel = EventModel.builder()
                .timestamp(LocalDateTime.now())
                .aggregateIdentifier(aggregateId)
                .aggregateType(RentalAggregate.class.getTypeName())
                .version(version)
                .eventType(event.getClass().getTypeName())
                .eventData(event)
                .build();

            var persistedEvent = eventStoreRepository.save(eventModel);

            if(!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }

        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);

        if(eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Incorrect account ID provided!");
        }
        return eventStream.stream().map(x->x.getEventData()).collect(Collectors.toList());
    }
}
