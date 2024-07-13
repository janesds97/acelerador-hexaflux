package com.acelerador.HexaFlux.tarea.infrastructure;

import com.acelerador.HexaFlux.tarea.application.TareaService;
import com.acelerador.HexaFlux.tarea.domain.Tarea;
import com.acelerador.HexaFlux.usuario.domain.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing Tarea entities.
 */
@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    /**
     * Retrieves all Tarea entities.
     *
     * @return a Flux containing all Tarea entities
     */
    @GetMapping
    public Flux<Tarea> getAllTareas() {
        return tareaService.getAllTareas();
    }

    /**
     * Retrieves a Tarea entity by its ID.
     *
     * @param id the ID of the Tarea entity to retrieve
     * @return a Mono containing the ResponseEntity with the Tarea entity, or no content if not found
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Tarea>> getTareaById(@PathVariable String id) {
        return tareaService.getTareaById(id)
        		.map(tarea -> ResponseEntity.ok(tarea))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    /**
     * Creates a new Tarea entity.
     *
     * @param tarea the Tarea entity to create
     * @return a Mono containing the ResponseEntity with the created Tarea entity
     */
    @PostMapping
    public Mono<ResponseEntity<Tarea>> createTarea(@RequestBody Tarea tarea) {
        return tareaService.createTarea(tarea)
        		.map(newTarea -> ResponseEntity.status(HttpStatus.CREATED).body(newTarea));
    }

    /**
     * Updates an existing Tarea entity.
     *
     * @param id the ID of the Tarea entity to update
     * @param tarea the Tarea entity with updated data
     * @return a Mono containing the ResponseEntity with the updated Tarea entity, or no content if not found
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Tarea>> updateTarea(@PathVariable String id, @RequestBody Tarea tarea) {
        return tareaService.updateTarea(id, tarea)
        		.map(updatedTarea -> ResponseEntity.ok(updatedTarea))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    /**
     * Deletes a Tarea entity by its ID.
     *
     * @param id the ID of the Tarea entity to delete
     * @return a Mono containing the ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTarea(@PathVariable String id) {
        return tareaService.deleteTarea(id)
        		.map(removed -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
    
}