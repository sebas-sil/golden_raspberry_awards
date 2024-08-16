package br.com.corcunda.golden_raspberry_awards.film;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @Builder @ToString
@Table(name = "films")
public class Film {
    private @Id @GeneratedValue Long id;
    private int year;
    private String title;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @Column(name = "studio")
    private List<String> studios;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @Column(name = "producer")
    private List<String> producers;
    private boolean winner;
}
