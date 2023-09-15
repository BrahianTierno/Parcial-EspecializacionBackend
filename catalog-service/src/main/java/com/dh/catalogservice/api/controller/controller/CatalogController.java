package com.dh.catalogservice.api.controller.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/movies")
    public List<Movie> findSeries()  {
        return catalogService.findAllMovies();
    }

    @GetMapping("/series")
    public List<Serie> findMovies() {
        return catalogService.findAllSeries();
    }

    @PostMapping("/movie/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return catalogService.saveMovieService(movie);
    }

    @PostMapping("/serie/save")
    ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        return catalogService.saveSerieService(serie);
    }

    @GetMapping("/movies/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return catalogService.findMovieByGenre(genre);
    }

    @GetMapping("/series/{genre}")
    public List<Serie> getSeriesByGenre(@PathVariable String genre) {
        return catalogService.findSerieByGenre(genre);
    }

}
