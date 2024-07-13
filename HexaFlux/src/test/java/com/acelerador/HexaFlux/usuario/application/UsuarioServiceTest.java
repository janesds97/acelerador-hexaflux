package com.acelerador.HexaFlux.usuario.application;

import com.acelerador.HexaFlux.usuario.domain.Usuario;
import com.acelerador.HexaFlux.usuario.infrastructure.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsuarios() {
        Usuario usuario = new Usuario();
        usuario.setId("10");
        usuario.setNombre("Janes Duran");

        when(usuarioRepository.findAll()).thenReturn(Flux.just(usuario));

        Flux<Usuario> usuarios = usuarioService.getAllUsuarios();

        StepVerifier.create(usuarios)
        	.expectNextMatches(u -> u.getId().equals("10") && u.getNombre().equals("Janes Duran"))
            .verifyComplete();
    }

    @Test
    public void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setId("10");
        usuario.setNombre("Janes Duran");

        when(usuarioRepository.findById("1")).thenReturn(Mono.just(usuario));

        Mono<Usuario> usuarioMono = usuarioService.getUsuarioById("1");

        StepVerifier.create(usuarioMono)
            .expectNextMatches(u -> u.getId().equals("10") && u.getNombre().equals("Janes Duran"))
            .verifyComplete();
    }

    @Test
    public void testCreateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Janes Duran");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(Mono.just(usuario));

        Mono<Usuario> usuarioMono = usuarioService.createUsuario(usuario);

        StepVerifier.create(usuarioMono)
            .expectNextMatches(u -> u.getNombre().equals("Janes Duran") && u.getFechaCreacion() != null)
            .verifyComplete();
    }

    @Test
    public void testUpdateUsuario() {
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId("99");
        existingUsuario.setNombre("Null Sierra");

        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setNombre("Janes Duran");

        when(usuarioRepository.findById("99")).thenReturn(Mono.just(existingUsuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(Mono.just(updatedUsuario));

        Mono<Usuario> usuarioMono = usuarioService.updateUsuario("99", updatedUsuario);

        StepVerifier.create(usuarioMono)
            .expectNextMatches(u -> u.getNombre().equals("Janes Duran"))
            .verifyComplete();
    }

    @Test
    public void testDeleteUsuario() {
        when(usuarioRepository.deleteById("10")).thenReturn(Mono.empty());

        Mono<Void> voidMono = usuarioService.deleteUsuario("10");

        StepVerifier.create(voidMono)
            .verifyComplete();
    }
}
