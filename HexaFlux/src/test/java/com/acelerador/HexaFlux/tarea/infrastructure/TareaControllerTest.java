package com.acelerador.HexaFlux.tarea.infrastructure;

import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.acelerador.HexaFlux.tarea.domain.Tarea;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class TareaControllerTest {
	
	private static final Logger logger = Logger.getLogger(TareaControllerTest.class.getName());

	@Autowired
    private WebTestClient webTestClient;
	
	@Test
    public void testCreateTarea() {
        Tarea nuevaTarea = new Tarea("2", "Tarea 2", "descripcion tarea2", "pendiente", LocalDateTime.now(), LocalDateTime.now() , "1");

        webTestClient.post().uri("/tareas")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nuevaTarea)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Tarea.class)
            .consumeWith(response -> {
                Tarea tarea = response.getResponseBody();
                assertNotNull(tarea);
                assertEquals("Tarea 2", tarea.getNombre());
            });
    }

    @Test
    public void testGetTareas() {
        webTestClient.get().uri("/tareas")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(Tarea.class)
            .consumeWith(response -> {
                List<Tarea> tareas = response.getResponseBody();
                assertNotNull(tareas);
                assertThat(tareas).hasSizeGreaterThan(0);
            });
    }

    @Test
    public void testGetTareaById() {
    	Tarea nuevaTarea = new Tarea("2", "Tarea 2", "descripcion tarea2", "pendiente", LocalDateTime.now(), LocalDateTime.now() , "1");

    	webTestClient.post().uri("/tareas")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(nuevaTarea)
        .exchange();
    	
        webTestClient.get().uri("/tareas/2")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Tarea.class)
            .consumeWith(response -> {
                Tarea tarea = response.getResponseBody();
                assertNotNull(tarea);
                assertEquals("2", tarea.getId());
            });
    }

    @Test
    public void testUpdateTarea() {
    	Tarea nuevaTarea = new Tarea("2", "Tarea 2", "descripcion tarea2", "pendiente", LocalDateTime.now(), LocalDateTime.now() , "1");

        webTestClient.post().uri("/tareas")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nuevaTarea)
            .exchange()
            ;
        
        Tarea updatedTarea = new Tarea("2", "Tarea 3", "descripcion tarea3 actualizada", "realizada", LocalDateTime.now(), LocalDateTime.now() , "1");

        webTestClient.put().uri("/tareas/2")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedTarea)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Tarea.class)
            .consumeWith(response -> {
                Tarea tarea = response.getResponseBody();
                assertNotNull(tarea);
                assertEquals("Tarea 3", tarea.getNombre());
            });
    }

    @Test
    public void testDeleteTarea() {

        webTestClient.delete().uri("/tareas/2")
            .exchange()
            .expectStatus().isNoContent();
    }

}
