package com.dh.movieservice.controller;

import com.dh.movieservice.api.queue.MovieSender;
import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieSender movieSender;

    @Value("${idRandom}")
    private String port;

    @GetMapping("/instanceId/find")
    public ResponseEntity<String> find() {
        return ResponseEntity.ok("Instance id: " + port);
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        Movie createdmovie = movieService.save(movie);
        movieSender.send(movie);
        return ResponseEntity.noContent().build();
    }

}
