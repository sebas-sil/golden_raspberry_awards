package br.com.corcunda.golden_raspberry_awards.film;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {

    private final FilmRepository repository;

    FilmController(FilmRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/films")
    List<Film> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]
}
