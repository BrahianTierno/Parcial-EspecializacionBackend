package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SeriesRepository extends MongoRepository<Serie, Long> {
}
