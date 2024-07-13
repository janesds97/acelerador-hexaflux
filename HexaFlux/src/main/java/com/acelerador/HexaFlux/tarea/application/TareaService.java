package com.acelerador.HexaFlux.tarea.application;

import com.acelerador.HexaFlux.tarea.domain.Tarea;
import com.acelerador.HexaFlux.tarea.infrastructure.TareaRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class for managing Tarea entities.
 */
@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    /**
     * Retrieves all Tarea entities.
     *
     * @return a Flux containing all Tarea entities
     */
    public Flux<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }
    
    /**
     * Retrieves a Tarea entity by its ID.
     *
     * @param id the ID of the Tarea entity to retrieve
     * @return a Mono containing the Tarea entity, or empty if not found
     */
    public Mono<Tarea> getTareaById(String id) {
        return tareaRepository.findById(id);
    }

    /**
     * Creates a new Tarea entity.
     *
     * @param tarea the Tarea entity to create
     * @return a Mono containing the created Tarea entity
     */
    public Mono<Tarea> createTarea(Tarea tarea) {
        tarea.setFechaCreacion(LocalDateTime.now());
        return tareaRepository.save(tarea);
    }

    /**
     * Updates an existing Tarea entity.
     *
     * @param id the ID of the Tarea entity to update
     * @param tarea the Tarea entity with updated data
     * @return a Mono containing the updated Tarea entity, or empty if not found
     */
    public Mono<Tarea> updateTarea(String id, Tarea tarea) {
        return tareaRepository.findById(id)
                .flatMap(existingTarea -> {
                    existingTarea.setNombre(tarea.getNombre());
                    existingTarea.setDescripcion(tarea.getDescripcion());
                    existingTarea.setEstado(tarea.getEstado());
                    existingTarea.setFechaVencimiento(tarea.getFechaVencimiento());
                    existingTarea.setResponsableId(tarea.getResponsableId());
                    return tareaRepository.save(existingTarea);
                });
    }

    /**
     * Deletes a Tarea entity by its ID.
     *
     * @param id the ID of the Tarea entity to delete
     * @return a Mono signaling when the deletion has completed
     */
    public Mono<Void> deleteTarea(String id) {
        return tareaRepository.deleteById(id);
    }
}