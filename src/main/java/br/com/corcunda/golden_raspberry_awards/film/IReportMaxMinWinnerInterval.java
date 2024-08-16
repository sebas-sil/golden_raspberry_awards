package br.com.corcunda.golden_raspberry_awards.film;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface IReportMaxMinWinnerInterval {
    @JsonProperty("max_interval")
    int getMaxInterval();
    @JsonProperty("min_interval")
    int getMinInterval();
}
