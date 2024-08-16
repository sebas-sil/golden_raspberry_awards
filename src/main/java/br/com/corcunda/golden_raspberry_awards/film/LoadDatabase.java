package br.com.corcunda.golden_raspberry_awards.film;

import java.net.MalformedURLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.util.StringUtils;

@Configuration
class LoadDatabase {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(FilmRepository repository) {

    return args -> {
      Instant start = Instant.now();
      LOGGER.info("start database loader");
      FlatFileItemReader<Film> reader = readCSV();
      reader.open(new ExecutionContext());
      Film film = null;

      while ((film = reader.read()) != null) {
        repository.save(film);
      }
      long qtd = repository.count();
      Instant end = Instant.now();
      long time = Duration.between(start, end).toMillis();
      LOGGER.info("end database loader with {} records in {} ms", qtd, time);
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
            .studios(Arrays.asList(fieldSet.readString("studios").split("(?i),| and ")).stream().map(x -> StringUtils.capitalize(x.strip())).filter(x -> ! x.isEmpty()).toList())
            .producers(Arrays.asList(fieldSet.readString("producers").split("(?i),| and ")).stream().map(x -> StringUtils.capitalize(x.strip())).filter(x -> ! x.isEmpty()).toList())
            .winner(fieldSet.readString("winner").equalsIgnoreCase("yes"))
            .build())
        .build();
  }
}