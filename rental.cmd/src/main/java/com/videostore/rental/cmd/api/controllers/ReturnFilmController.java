/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videostore.cqrs.core.infrastructure.CommandDispatcher;
import com.videostore.rental.cmd.api.commands.ReturnFilmCommand;
import com.videostore.rental.cmd.domain.ReturnFilmResponse;
import com.videostore.rental.cmd.api.commands.RentalCommandHandler;
import com.videostore.rental.common.dto.BaseResponse;
import com.videostore.rental.common.errorhandling.ResourceNotFoundException;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@RestController
@RequestMapping("/api/v1/rental/returnFilm")
public class ReturnFilmController {

    private final Logger logger = Logger.getLogger(ReturnFilmController.class.getName());

    private CommandDispatcher commandDispatcher;
    private RentalCommandHandler commandHandler;

    public ReturnFilmController(CommandDispatcher commandDispatcher, RentalCommandHandler commandHandler) {
        this.commandDispatcher = commandDispatcher;
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> returnFilm(@RequestBody List<ReturnFilmCommand> commands) {

        List<String> returnIds = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        int lateFee = 0;

        for (ReturnFilmCommand command : commands) {
            var id = UUID.randomUUID().toString();
            command.setId(id);
            returnIds.add(id);

            try {
                commandDispatcher.send(command);
                lateFee += commandHandler.getLateFee();
            } catch (Exception e) {
                errors.add(e.getMessage());

            }
        }

        String message = errors.isEmpty() ? "All return film requests completed successfully!" : "Some requests failed.";
        ReturnFilmResponse response = new ReturnFilmResponse(message, returnIds, lateFee, errors);
        return new ResponseEntity<>(response, response.getErrors().isEmpty() ? HttpStatus.CREATED : HttpStatus.MULTI_STATUS);
    }

}
