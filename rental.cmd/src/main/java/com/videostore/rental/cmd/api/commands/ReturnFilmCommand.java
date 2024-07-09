/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.api.commands;

import com.videostore.cqrs.core.commands.BaseCommand;

import lombok.Data;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Data
public class ReturnFilmCommand extends BaseCommand {
    private String rentalId;
    private String filmTitle;
    private int daysLate;
}
