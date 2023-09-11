package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private IMovieClient iMovieClient;
    @Autowired
    private ISerieClient iSerieClient;

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

    @PostMapping("catalog/movie/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return iMovieClient.saveMovie(movie);
    }

    @PostMapping("catalog/serie/save")
    ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        return iSerieClient.create(serie);
    }
}
