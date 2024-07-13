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

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public Flux<Tarea> getAllTareas() {
        return tareaService.getAllTareas();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Tarea>> getTareaById(@PathVariable String id) {
        return tareaService.getTareaById(id)
        		.map(tarea -> ResponseEntity.ok(tarea))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Tarea>> createTarea(@RequestBody Tarea tarea) {
        return tareaService.createTarea(tarea)
        		.map(newTarea -> ResponseEntity.status(HttpStatus.CREATED).body(newTarea));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Tarea>> updateTarea(@PathVariable String id, @RequestBody Tarea tarea) {
        return tareaService.updateTarea(id, tarea)
        		.map(updatedTarea -> ResponseEntity.ok(updatedTarea))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTarea(@PathVariable String id) {
        return tareaService.deleteTarea(id)
        		.map(removed -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
    
}