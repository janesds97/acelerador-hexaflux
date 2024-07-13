package com.acelerador.HexaFlux.usuario.infrastructure;

import com.acelerador.HexaFlux.usuario.application.UsuarioService;
import com.acelerador.HexaFlux.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * REST controller for managing Usuario entities.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Retrieves all Usuario entities.
     *
     * @return a Flux containing all Usuario entities
     */
    @GetMapping
    public Flux<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    /**
     * Retrieves a Usuario entity by its ID.
     *
     * @param id the ID of the Usuario entity to retrieve
     * @return a Mono containing the ResponseEntity with the Usuario entity, or no content if not found
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Usuario>> getUsuarioById(@PathVariable String id) {
        return usuarioService.getUsuarioById(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    /**
     * Creates a new Usuario entity.
     *
     * @param usuario the Usuario entity to create
     * @return a Mono containing the ResponseEntity with the created Usuario entity
     */
    @PostMapping
    public Mono<ResponseEntity<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario)
                .map(newUsuario -> ResponseEntity.status(HttpStatus.CREATED).body(newUsuario));
    }

    /**
     * Updates an existing Usuario entity.
     *
     * @param id the ID of the Usuario entity to update
     * @param usuario the Usuario entity with updated data
     * @return a Mono containing the ResponseEntity with the updated Usuario entity, or no content if not found
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Usuario>> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario)
                .map(updatedUsuario -> ResponseEntity.ok(updatedUsuario))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    /**
     * Deletes a Usuario entity by its ID.
     *
     * @param id the ID of the Usuario entity to delete
     * @return a Mono containing the ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUsuario(@PathVariable String id) {
        return usuarioService.deleteUsuario(id)
                .map(removed -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.noContent().build())
                ;
    }
}