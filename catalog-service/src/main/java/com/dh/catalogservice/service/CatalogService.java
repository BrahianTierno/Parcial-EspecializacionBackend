package com.dh.catalogservice.service;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MoviesRepository;
import com.dh.catalogservice.repository.SeriesRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {

    private final MoviesRepository moviesRepository;
    private final SeriesRepository seriesRepository;
    private final IMovieClient iMovieClient;
    private final ISerieClient iSerieClient;

    public List<Movie> findAllMovies() {
        return moviesRepository.findAll();
    }

    public List<Serie> findAllSeries() {
        return seriesRepository.findAll();
    }

    public Serie createSerie(Serie serie) {
        return seriesRepository.save(serie);
    }

    public Movie createMovie(Movie movie) {
        return moviesRepository.save(movie);
    }

    public List<Movie> findMovieByGenre(String genre) {
        return moviesRepository.findAllByGenre(genre);
    }

    public List<Serie> findSerieByGenre(String genre) {
        return seriesRepository.findAllByGenre(genre);
    }

    @CircuitBreaker(name = "dataService", fallbackMethod = "musicServiceUnavailable")
    @Retry(name = "dataService")
    public ResponseEntity<Movie> saveMovieService(@RequestBody Movie movie) {
        log.info("Intentando conectar con movie-service...");
        return iMovieClient.saveMovie(movie);
    }

    public ResponseEntity<String> musicServiceUnavailable(CallNotPermittedException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Music service actualmente no se encuentra disponible");
    }

    @CircuitBreaker(name = "dataService", fallbackMethod = "serieServiceUnavailable")
    @Retry(name = "dataService")
    public ResponseEntity<Serie> saveSerieService(@RequestBody Serie serie) {
        log.info("Intentando conectar con serie-service...");
        return iSerieClient.create(serie);
    }

    public ResponseEntity<String> serieServiceUnavailable(CallNotPermittedException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Serie service actualmente no se encuentra disponible");
    }

}
