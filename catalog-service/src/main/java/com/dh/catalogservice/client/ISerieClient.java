package com.dh.catalogservice.client;

import com.dh.catalogservice.model.Serie;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "serie-service")
public interface ISerieClient {

    @PostMapping("/api/v1/series")
    @Headers("Content-Type: application/json")
    ResponseEntity<Serie> create(@RequestBody Serie serie);

}
