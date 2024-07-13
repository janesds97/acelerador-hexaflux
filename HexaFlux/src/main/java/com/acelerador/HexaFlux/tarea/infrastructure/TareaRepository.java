package com.acelerador.HexaFlux.tarea.infrastructure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.acelerador.HexaFlux.tarea.domain.Tarea;

/**
 * Repository interface for managing Tarea entities in MongoDB.
 */
@Repository
public interface TareaRepository extends ReactiveMongoRepository<Tarea, String> {
}