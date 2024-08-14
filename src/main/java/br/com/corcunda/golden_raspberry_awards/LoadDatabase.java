package br.com.corcunda.golden_raspberry_awards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.corcunda.golden_raspberry_awards.film.Film;
import br.com.corcunda.golden_raspberry_awards.film.FilmRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(FilmRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Film(1L, 1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", true)));
      log.info("Preloading " + repository.save(new Film(2L, 1980, "Cruising", "Lorimar Productions, United Artists", "Jerry Weintraub", false)));
      log.info("Preloading " + repository.save(new Film(3L, 1980, "The Formula, MGM", "United Artists","Steve Shagan", false)));
    };
  }
}