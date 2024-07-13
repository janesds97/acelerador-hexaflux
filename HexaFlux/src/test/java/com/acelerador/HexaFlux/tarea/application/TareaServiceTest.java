package com.acelerador.HexaFlux.tarea.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acelerador.HexaFlux.tarea.domain.Tarea;
import com.acelerador.HexaFlux.tarea.infrastructure.TareaRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TareaServiceTest {

	@InjectMocks
    private TareaService tareaService;

    @Mock
    private TareaRepository tareaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTareas() {
        Tarea tarea = new Tarea();
        tarea.setId("10");
        tarea.setNombre("Tarea 1");

        when(tareaRepository.findAll()).thenReturn(Flux.just(tarea));

        Flux<Tarea> tareas = tareaService.getAllTareas();

        StepVerifier.create(tareas)
        	.expectNextMatches(u -> u.getId().equals("10") && u.getNombre().equals("Tarea 1"))
            .verifyComplete();
    }

    @Test
    public void TareastGetTareaById() {
        Tarea tarea = new Tarea();
        tarea.setId("10");
        tarea.setNombre("Tarea 1");

        when(tareaRepository.findById("10")).thenReturn(Mono.just(tarea));

        Mono<Tarea> tareaMono = tareaService.getTareaById("10");

        StepVerifier.create(tareaMono)
            .expectNextMatches(u -> u.getId().equals("10") && u.getNombre().equals("Tarea 1"))
            .verifyComplete();
    }

    @Test
    public void testCreateTarea() {
        Tarea tarea = new Tarea();
        tarea.setNombre("Tarea 1");

        when(tareaRepository.save(any(Tarea.class))).thenReturn(Mono.just(tarea));

        Mono<Tarea> tareaMono = tareaService.createTarea(tarea);

        StepVerifier.create(tareaMono)
            .expectNextMatches(u -> u.getNombre().equals("Tarea 1") && u.getFechaCreacion() != null)
            .verifyComplete();
    }

    @Test
    public void testUpdateTarea() {
        Tarea existingTarea = new Tarea();
        existingTarea.setId("99");
        existingTarea.setNombre("Tarea 99");

        Tarea updatedTarea = new Tarea();
        updatedTarea.setNombre("Tarea 1");

        when(tareaRepository.findById("99")).thenReturn(Mono.just(existingTarea));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(Mono.just(updatedTarea));

        Mono<Tarea> tareaMono = tareaService.updateTarea("99", updatedTarea);

        StepVerifier.create(tareaMono)
            .expectNextMatches(u -> u.getNombre().equals("Tarea 1"))
            .verifyComplete();
    }

    @Test
    public void testDeleteTarea() {
        when(tareaRepository.deleteById("10")).thenReturn(Mono.empty());

        Mono<Void> voidMono = tareaService.deleteTarea("10");

        StepVerifier.create(voidMono)
            .verifyComplete();
    }
}
