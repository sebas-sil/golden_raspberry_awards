package br.com.corcunda.golden_raspberry_awards.film;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;

    public List<IReportByProducer> getMaxWinnersProducers() {
        int max =  repository.findMaxProducerWinnerInterval();
        return repository.findWinnerProducersWithNativeQuery(max);
    }

    public List<IReportByProducer> getMinWinnersProducers() {
        int min =  repository.findMinProducerWinnerInterval();
        return repository.findWinnerProducersWithNativeQuery(min);
    }

}
