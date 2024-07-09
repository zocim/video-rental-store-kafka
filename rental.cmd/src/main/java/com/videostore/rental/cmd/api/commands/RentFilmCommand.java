/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.api.commands;

import com.videostore.cqrs.core.commands.BaseCommand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Data
public class RentFilmCommand extends BaseCommand {
    @NotBlank(message = "Invalid filmTitle: Empty filmTitle")
    @NotNull(message = "Invalid filmTitle: filmTitle is nul")
    private String filmTitle;
    @Min(value = 1, message = "Invalid days: Equals to zero or less than zero")
    private int days;
}