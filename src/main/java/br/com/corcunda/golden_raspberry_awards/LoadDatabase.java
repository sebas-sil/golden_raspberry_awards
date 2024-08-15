package br.com.corcunda.golden_raspberry_awards;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import br.com.corcunda.golden_raspberry_awards.film.Film;
import br.com.corcunda.golden_raspberry_awards.film.FilmRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(FilmRepository repository) {

    return args -> {
      // log.info("Preloading " + repository.save(Film.builder()
      // .year(1980).title("Can't Stop the Music")
      // .studios("Associated Film Distribution").producers("Allan
      // Carr").winner(true).build()));
      // log.info("Preloading " + repository.save(Film.builder()
      // .year(1980).title("Cruising").studios("Lorimar Productions, United Artists")
      // .producers("Jerry Weintraub").winner(false).build()));
      // log.info("Preloading " + repository.save(Film.builder()
      // .year(1980).title("The Formula")
      // .studios("MGM, United Artists").producers("Steve
      // Shagan").winner(false).build()));
      FlatFileItemReader<Film> reader = readCSV();
      reader.open(new ExecutionContext());
      Film film = null;

      while ((film = reader.read()) != null) {
        repository.save(film);
      }
    };
  }

  private FlatFileItemReader<Film> readCSV() throws MalformedURLException {
    return new FlatFileItemReaderBuilder<Film>()
        .linesToSkip(1)
        .name("movielistReader")
        .resource(new PathResource("movielist.csv"))
        .delimited()
        .delimiter(";")
        .names("year", "title", "studios", "producers", "winner")
        .fieldSetMapper(fieldSet -> Film.builder()
            .year(fieldSet.readInt("year"))
            .title(fieldSet.readString("title"))
            .studios(fieldSet.readString("studios"))
            .producers(fieldSet.readString("producers"))
            .winner(fieldSet.readBoolean("winner"))
            .build())
        .build();
  }
}