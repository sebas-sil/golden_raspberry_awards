package br.com.corcunda.golden_raspberry_awards.film;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value =
        "SELECT producer, maxyear, minyear, qtd FROM (\n" + //
        "    SELECT fp.producer, MAX(f.year) maxyear, MIN(f.year) minyear, MAX(f.year) - MIN(f.year) qtd \n" + //
        "    FROM film_producers fp\n" + //
        "    INNER JOIN films f ON f.id = fp.film_id\n" + //
        "    WHERE f.winner = true " + //
        "    GROUP BY fp.producer) T\n" + //
        "WHERE T.qtd = :#{#val}"
        , nativeQuery = true
    )
    List<IReportByProducer> findWinnerProducersWithNativeQuery(@Param("val") int value);

    @Query(value =
        "SELECT MAX(qtd) maxInterval FROM (\n" + //
        "    SELECT fp.producer, MAX(f.year) - MIN(f.year) as qtd \n" + //
        "    FROM film_producers fp\n" + //
        "    INNER JOIN films f ON f.id = fp.film_id\n" + //
        "    WHERE f.winner = true\n" + //
        "    GROUP BY fp.producer) T\n" + //
        "WHERE T.qtd > 0", nativeQuery = true)
    int findMaxProducerWinnerInterval();

    @Query(value =
    "SELECT MIN(qtd) maxInterval FROM (\n" + //
    "    SELECT fp.producer, MAX(f.year) - MIN(f.year) as qtd \n" + //
    "    FROM film_producers fp\n" + //
    "    INNER JOIN films f ON f.id = fp.film_id\n" + //
    "    WHERE f.winner = true\n" + //
    "    GROUP BY fp.producer) T\n" + //
    "WHERE T.qtd > 0", nativeQuery = true)
    int findMinProducerWinnerInterval();
}
