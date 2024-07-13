package com.acelerador.HexaFlux.usuario.infrastructure;

import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.acelerador.HexaFlux.usuario.domain.Usuario;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UsuarioControllerTest {
	
	private static final Logger logger = Logger.getLogger(UsuarioControllerTest.class.getName());

	@Autowired
    private WebTestClient webTestClient;
	
	@Test
    public void testCreateUsuario() {
        Usuario nuevoUsuario = new Usuario("2", "Janes Duran", "janes.duran@example.com", "password", "USER", LocalDateTime.now());

        webTestClient.post().uri("/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nuevoUsuario)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Usuario.class)
            .consumeWith(response -> {
                Usuario usuario = response.getResponseBody();
                assertNotNull(usuario);
                assertEquals("Janes Duran", usuario.getNombre());
            });
    }

    @Test
    public void testGetUsuarios() {
        webTestClient.get().uri("/usuarios")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(Usuario.class)
            .consumeWith(response -> {
                List<Usuario> usuarios = response.getResponseBody();
                assertNotNull(usuarios);
                assertThat(usuarios).hasSizeGreaterThanOrEqualTo(0);
            });
    }

    @Test
    public void testGetUsuarioById() {
    	Usuario nuevoUsuario = new Usuario("2", "Janes Duran", "janes.duran@example.com", "password", "USER", LocalDateTime.now());

    	webTestClient.post().uri("/usuarios")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(nuevoUsuario)
        .exchange();
    	
        webTestClient.get().uri("/usuarios/2")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Usuario.class)
            .consumeWith(response -> {
                Usuario usuario = response.getResponseBody();
                assertNotNull(usuario);
                assertEquals("2", usuario.getId());
            });
    }

    @Test
    public void testUpdateUsuario() {
    	Usuario nuevoUsuario = new Usuario("2", "Janes Duran", "janes.duran@example.com", "password", "USER", LocalDateTime.now());

        webTestClient.post().uri("/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nuevoUsuario)
            .exchange()
            ;
        
        Usuario updatedUsuario = new Usuario("2", "Janes Updated", "janes.updated@example.com", "newpassword", "ADMIN", LocalDateTime.now());

        webTestClient.put().uri("/usuarios/2")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(updatedUsuario)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Usuario.class)
            .consumeWith(response -> {
                Usuario usuario = response.getResponseBody();
                assertNotNull(usuario);
                assertEquals("Janes Updated", usuario.getNombre());
            });
    }

    @Test
    public void testDeleteUsuario() {

        webTestClient.delete().uri("/usuarios/2")
            .exchange()
            .expectStatus().isNoContent();
    }
}
