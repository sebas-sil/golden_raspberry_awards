package br.com.corcunda.golden_raspberry_awards.film;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;

    public List<IReportByProducer> getMaxWinnersProducers() {
        Long max =  repository.findMaxProducerWinnerInterval();
        if(max == null) {
            return List.of();
        }
        return repository.findWinnerProducersWithNativeQuery(max);
    }

    public List<IReportByProducer> getMinWinnersProducers() {
        Long min =  repository.findMinProducerWinnerInterval();
        if(min == null) {
            return List.of();
        }
        return repository.findWinnerProducersWithNativeQuery(min);
    }

}
