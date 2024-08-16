package br.com.corcunda.golden_raspberry_awards.film;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface IReportByProducer {
    @JsonProperty("producer")
    String getProducer();
    @JsonProperty("followingWin")
    int getMaxYear();
    @JsonProperty("previousWin")
    int getMinYear();
    @JsonProperty("interval")
    int getQtd();
}
