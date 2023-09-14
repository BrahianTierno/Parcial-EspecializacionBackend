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

    private final IMovieClient iMovieClient;
    private final ISerieClient iSerieClient;
    private final CatalogService catalogService;

    @GetMapping("/movies")
    public List<Movie> findSeries()  {
        return catalogService.findAllMovies();
    }

    @GetMapping("/series")
    public List<Serie> findMovies() {
        return catalogService.findAllSeries();
    }

    @CircuitBreaker(name = "dataService", fallbackMethod = "musicServiceUnavailable")
    @Retry(name = "dataService")
    @PostMapping("/catalog/movie/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        log.info("Intentando conectar con movie-service...");
        return iMovieClient.saveMovie(movie);
    }

    public ResponseEntity<String> musicServiceUnavailable(CallNotPermittedException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Music service actualmente no se encuentra disponible");
    }

    @CircuitBreaker(name = "dataService", fallbackMethod = "serieServiceUnavailable")
    @Retry(name = "dataService")
    @PostMapping("/catalog/serie/save")
    ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        log.info("Intentando conectar con serie-service...");
        return iSerieClient.create(serie);
    }

    public ResponseEntity<String> serieServiceUnavailable(CallNotPermittedException exception) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Serie service actualmente no se encuentra disponible");
    }


    /*

        @GetMapping("/movies")
    public List<Movie> findSeries(@RequestParam(defaultValue = "false") Boolean throwError)  {
        return catalogService.findAllMovies(throwError);
    }

    @GetMapping("/series")
    public List<Serie> findMovies(@RequestParam (defaultValue = "false") Boolean throwError) {
        return catalogService.findAllSeries(throwError);
    }

    @GetMapping("/movieInstanceId/find")
    public ResponseEntity<String> find() {
        return ResponseEntity.ok(iMovieClient.find());
    }

    @GetMapping("/catalog/serie/all")
    public ResponseEntity<List<Serie>> getAllSeries() {
        return iSerieClient.getAll();
    }

    @GetMapping("catalog/movie/{genre}")
    public ResponseEntity<List<Movie>> getMovieCatalogByGenre(@PathVariable String genre) {
        return iMovieClient.getMovieByGenre(genre);
    }

    @GetMapping("catalog/serie/{genre}")
    public ResponseEntity<List<Serie>> getSerieCatalogByGenre(@PathVariable String genre) {
        return iSerieClient.getSerieByGenre(genre);
    }
*/

}
