package br.com.corcunda.golden_raspberry_awards.film;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Film {
    private @Id @GeneratedValue Long id;
    private int year;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;
}
