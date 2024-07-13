package com.acelerador.HexaFlux.usuario.application;

import com.acelerador.HexaFlux.usuario.domain.Usuario;
import com.acelerador.HexaFlux.usuario.infrastructure.UsuarioRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class for managing Usuario entities.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Retrieves all Usuario entities.
     *
     * @return a Flux containing all Usuario entities
     */
    public Flux<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Retrieves a Usuario entity by its ID.
     *
     * @param id the ID of the Usuario entity to retrieve
     * @return a Mono containing the Usuario entity, or empty if not found
     */
    public Mono<Usuario> getUsuarioById(String id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Creates a new Usuario entity.
     *
     * @param usuario the Usuario entity to create
     * @return a Mono containing the created Usuario entity
     */
    public Mono<Usuario> createUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    /**
     * Updates an existing Usuario entity.
     *
     * @param id the ID of the Usuario entity to update
     * @param usuario the Usuario entity with updated data
     * @return a Mono containing the updated Usuario entity, or empty if not found
     */
    public Mono<Usuario> updateUsuario(String id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .flatMap(existingUsuario -> {
                    existingUsuario.setNombre(usuario.getNombre());
                    existingUsuario.setCorreoElectronico(usuario.getCorreoElectronico());
                    existingUsuario.setContrasena(usuario.getContrasena());
                    existingUsuario.setRol(usuario.getRol());
                    return usuarioRepository.save(existingUsuario);
                });
    }

    /**
     * Deletes a Usuario entity by its ID.
     *
     * @param id the ID of the Usuario entity to delete
     * @return a Mono signaling when the deletion has completed
     */
    public Mono<Void> deleteUsuario(String id) {
        return usuarioRepository.deleteById(id);
    }
}