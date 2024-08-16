package br.com.corcunda.golden_raspberry_awards.film;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/films")
public class FilmController {

    private final FilmService service;

    FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping("")
    IresponseWinnerInterval getWinnerProducers() {
        return new IresponseWinnerInterval() {

            @Override
            public List<IReportByProducer> getMax() {
                return service.getMaxWinnersProducers();
            }

            @Override
            public List<IReportByProducer> getMin() {
                return service.getMinWinnersProducers();
            }
        };
    }
}
