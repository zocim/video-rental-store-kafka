package com.videostore.rental.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.videostore.rental.query.domain.enitties.Film;
import com.videostore.rental.query.domain.repositories.FilmRepository;
import com.videostore.rental.common.enums.FilmType;

import jakarta.annotation.PostConstruct;


@SpringBootApplication
@ComponentScan(basePackages = {"com.videostore.rental.query", "com.videostore.rental.common"})
public class QueryApplication {

	private FilmRepository filmRepository;

	public QueryApplication(final FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	@PostConstruct
	public void setup() {
		filmRepository.save(new Film(1L,"Matrix 11", FilmType.NEW_RELEASE));
		filmRepository.save(new Film(2L, "Spider Man", FilmType.REGULAR));
		filmRepository.save(new Film(3L, "Spider Man 2", FilmType.REGULAR));
		filmRepository.save(new Film(4L,"Out of Africa", FilmType.OLD));
	}

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

}
