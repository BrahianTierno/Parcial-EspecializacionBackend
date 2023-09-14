package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MoviesRepository;
import com.dh.catalogservice.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final MoviesRepository moviesRepository;
    private final SeriesRepository seriesRepository;

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
        return moviesRepository.findByGenre(genre);
    }

    public List<Serie> findSerieByGenre(String genre) {
        return seriesRepository.findByGenre(genre);
    }



    /*

        public List<Movie> findAllMovies(Boolean throwError) {
        if (throwError) {
            throw new RuntimeException();
        }
        return moviesRepository.findAll();
    }

    public List<Serie> findAllSeries(Boolean throwError) {
        if (throwError) {
            throw new RuntimeException();
        }
        return seriesRepository.findAll();
    }
     */

}
