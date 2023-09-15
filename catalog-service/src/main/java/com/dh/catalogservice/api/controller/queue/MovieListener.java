package com.dh.catalogservice.api.controller.queue;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieListener {

    private final CatalogService catalogService;

    @RabbitListener(queues = {"${queue1.movie.name}"})
    public void receive(@Payload Movie movie) {
        catalogService.createMovie(movie);
    }
}
