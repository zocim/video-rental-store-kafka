/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videostore.cqrs.core.infrastructure.CommandDispatcher;
import com.videostore.rental.cmd.api.commands.RentFilmCommand;
import com.videostore.rental.cmd.domain.RentFilmResponse;
import com.videostore.rental.cmd.api.commands.RentalCommandHandler;
import com.videostore.rental.common.dto.BaseResponse;

import jakarta.validation.Valid;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */

@RestController
@RequestMapping("/api/v1/rental/rentFilm")
public class RentFilmController {

    private CommandDispatcher commandDispatcher;
    private RentalCommandHandler commandHandler;

    public RentFilmController(CommandDispatcher commandDispatcher, RentalCommandHandler commandHandler) {
        this.commandDispatcher = commandDispatcher;
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> rentFilm(@RequestBody @Valid List<RentFilmCommand> commands) {
        List<String> rentalIds = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        int totalPrice = 0;

        for (RentFilmCommand command : commands) {
            var id = UUID.randomUUID().toString();
            command.setId(id);
            rentalIds.add(id);

            try {
                commandDispatcher.send(command);
                totalPrice += commandHandler.getPrice();
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }

        String message = errors.isEmpty() ? "All rent film requests completed successfully!" : "Some requests failed.";
        RentFilmResponse response = new RentFilmResponse(message, rentalIds, totalPrice, errors);
        return new ResponseEntity<>(response, response.getErrors().isEmpty() ? HttpStatus.CREATED : HttpStatus.MULTI_STATUS);
    }

}
