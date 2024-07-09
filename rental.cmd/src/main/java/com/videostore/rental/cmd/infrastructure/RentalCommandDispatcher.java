/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.infrastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.videostore.cqrs.core.commands.BaseCommand;
import com.videostore.cqrs.core.commands.CommandHandlerMethod;
import com.videostore.cqrs.core.infrastructure.CommandDispatcher;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Service
public class RentalCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();
    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) throws MethodArgumentNotValidException {
        var handlers = routes.get(command.getClass());
        if(handlers == null || handlers.size() == 0) {
            throw new RuntimeException("No command handler was registered!");
        }
        if(handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler!");
        }
        handlers.get(0).handle(command);
    }
}
