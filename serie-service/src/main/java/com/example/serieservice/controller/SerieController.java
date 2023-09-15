package com.example.serieservice.controller;

import com.example.serieservice.api.queue.SerieSender;
import com.example.serieservice.model.Serie;
import com.example.serieservice.service.SerieService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;
import java.util.Queue;

/**
 * @author vaninagodoy
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieService serieService;

    private final SerieSender serieSender;

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Serie> create(@RequestBody Serie serie) {
        Serie createdSerie = serieService.create(serie);
        serieSender.send(serie);
        return ResponseEntity.noContent().build();
    }
}
