/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.infrastructure;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videostore.cqrs.core.domain.AggregateRoot;
import com.videostore.cqrs.core.handlers.EventSourcingHandler;
import com.videostore.cqrs.core.infrastructure.EventStore;
import com.videostore.rental.cmd.domain.ReturnAggregate;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Service
public class ReturnEventSourcingHandler implements EventSourcingHandler<ReturnAggregate> {
    @Autowired
    private EventStore eventStore;
    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
    }

    @Override
    public ReturnAggregate getById(String id) {
        var aggregate = new ReturnAggregate();

        var events = eventStore.getEvents(id);
        if(events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion())
                .max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }
}
