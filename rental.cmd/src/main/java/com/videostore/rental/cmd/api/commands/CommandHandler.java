package com.videostore.rental.cmd.api.commands;

import org.springframework.web.bind.MethodArgumentNotValidException;

public interface CommandHandler {
    void handle(RentFilmCommand command) throws MethodArgumentNotValidException;
    void handle(ReturnFilmCommand command);
}
