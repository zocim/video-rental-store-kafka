package com.videostore.rental.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.videostore.cqrs.core.infrastructure.CommandDispatcher;
import com.videostore.rental.cmd.api.commands.CommandHandler;
import com.videostore.rental.cmd.api.commands.RentFilmCommand;
import com.videostore.rental.cmd.api.commands.ReturnFilmCommand;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.videostore.rental.cmd", "com.videostore.rental.common"})
public class CommandApplication {
	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void  registerHandlers() {
		commandDispatcher.registerHandler(RentFilmCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(ReturnFilmCommand.class, commandHandler::handle);
	}

}
