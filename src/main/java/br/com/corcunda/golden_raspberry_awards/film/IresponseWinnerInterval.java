package br.com.corcunda.golden_raspberry_awards.film;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface IresponseWinnerInterval {
    @JsonProperty("max")
    List<IReportByProducer> getMax();
    @JsonProperty("min")
    List<IReportByProducer> getMin();
}
