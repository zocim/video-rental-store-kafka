/*
 * Copyright (c) 2024 Zorica Maneva. This source file can not be copied and/or distributed without the express
 * written permission of Zorica Maneva. Any unauthorized use is subject to criminal prosecution.
 */

package com.videostore.rental.cmd.api.commands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.videostore.rental.cmd.domain.RentalAggregate;
import com.videostore.rental.cmd.domain.ReturnAggregate;
import com.videostore.rental.cmd.infrastructure.RentalEventSourcingHandler;
import com.videostore.rental.cmd.infrastructure.ReturnEventSourcingHandler;
import com.videostore.rental.cmd.api.openfeign.RentalQueryClient;
import com.videostore.rental.common.dto.RentalDto;
import com.videostore.rental.common.enums.FilmType;
import com.videostore.rental.common.errorhandling.ResourceNotFoundException;

/**
 * @author Created by Maneva.
 * @since 1.7.24.
 */
@Service
@Validated
public class RentalCommandHandler implements CommandHandler {
    @Value("${film.price.premium}")
    private int premiumPrice;
    @Value("${film.price.basic}")
    private int basicPrice;
    private RentalEventSourcingHandler rentalEventSourcingHandler;
    private ReturnEventSourcingHandler returnEventSourcingHandler;
    private RentalQueryClient rentalQueryClient;
    private int price;
    private int lateFee;

    public RentalCommandHandler(RentalEventSourcingHandler rentalEventSourcingHandler,
                                ReturnEventSourcingHandler returnEventSourcingHandler,
                                RentalQueryClient rentalQueryClient) {
        this.rentalEventSourcingHandler = rentalEventSourcingHandler;
        this.returnEventSourcingHandler = returnEventSourcingHandler;
        this.rentalQueryClient = rentalQueryClient;
    }

    @Override
    public void handle(RentFilmCommand command) {
        Optional<FilmType> filmTypeOpt = rentalQueryClient.findFilmTypeByTitle(command.getFilmTitle());

        if (filmTypeOpt.isPresent()) {
            FilmType filmType = filmTypeOpt.get();
            price = calculateFeeOrPrice(filmType, command.getDays(), 0);
        } else {
            throw new ResourceNotFoundException("Film with title " + command.getFilmTitle() + " is not present!");
        }

        var rentalAggregate = new RentalAggregate(command, price);
        rentalEventSourcingHandler.save(rentalAggregate);
    }

    public int getPrice() {
        return price;
    }

    public int getLateFee() {
        return lateFee;
    }

    @Override
    public void handle(ReturnFilmCommand command) {
        Optional<RentalDto> rentalDtoOpt = rentalQueryClient.findNotReturnedRentalById(command.getRentalId());

        if (rentalDtoOpt.isPresent()) {
            RentalDto rentalDto = rentalDtoOpt.get();

            Optional<FilmType> filmTypeOpt = rentalQueryClient.findFilmTypeByTitle(command.getFilmTitle());
            if (filmTypeOpt.isPresent()) {
                lateFee = calculateFeeOrPrice(filmTypeOpt.get(), rentalDtoOpt.get().getDays(), command.getDaysLate());
                var returnAggregate = new ReturnAggregate(command, lateFee, rentalDto);
                returnEventSourcingHandler.save(returnAggregate);

                var rentalAggregate = rentalEventSourcingHandler.getById(command.getRentalId());
                rentalAggregate.returnFilm(rentalDto);
                rentalEventSourcingHandler.save(rentalAggregate);
            } else {
                throw new ResourceNotFoundException("Film with title " + command.getFilmTitle() + " is not present!");
            }

        } else {
            throw new ResourceNotFoundException("Rental with ID " + command.getRentalId() + " is not present or has "
                                                + " been already returned!");
        }

    }

    public int calculateFeeOrPrice(FilmType filmType, int days, int daysLate) {
        int price = 0, pom = 0;
        switch (filmType) {
            case NEW_RELEASE:
                if (daysLate != 0) {
                    pom = premiumPrice * daysLate;
                } else {
                    pom = premiumPrice * days;
                }
                break;
            case REGULAR:
                if (days + daysLate > 3) {
                    if (daysLate != 0) {
                        pom = basicPrice * daysLate;
                    } else {
                        int moreThanThreeDays = days - 3;
                        pom = basicPrice + basicPrice * moreThanThreeDays;
                    }
                } else {
                    pom = 0;
                }
                break;
            case OLD:
                if (days + daysLate > 5) {
                    if (daysLate != 0) {
                        pom = basicPrice * daysLate;
                    } else {
                        int moreThanFiveDays = days - 5;
                        pom = basicPrice + basicPrice * moreThanFiveDays;
                    }
                } else {
                    pom = 0;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown film type: " + filmType);
        }
        price += pom;

        return price;
    }

}
