package com.acelerador.HexaFlux.tarea.application;

import com.acelerador.HexaFlux.tarea.domain.Tarea;
import com.acelerador.HexaFlux.tarea.infrastructure.TareaRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Flux<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }

    public Mono<Tarea> getTareaById(String id) {
        return tareaRepository.findById(id);
    }

    public Mono<Tarea> createTarea(Tarea tarea) {
        tarea.setFechaCreacion(LocalDateTime.now());
        return tareaRepository.save(tarea);
    }

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

    public Mono<Void> deleteTarea(String id) {
        return tareaRepository.deleteById(id);
    }
}