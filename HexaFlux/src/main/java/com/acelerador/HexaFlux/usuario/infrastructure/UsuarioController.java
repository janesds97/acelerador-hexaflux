package com.acelerador.HexaFlux.usuario.infrastructure;

import com.acelerador.HexaFlux.usuario.application.UsuarioService;
import com.acelerador.HexaFlux.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Flux<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Usuario>> getUsuarioById(@PathVariable String id) {
        return usuarioService.getUsuarioById(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario)
                .map(newUsuario -> ResponseEntity.status(HttpStatus.CREATED).body(newUsuario));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Usuario>> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario)
                .map(updatedUsuario -> ResponseEntity.ok(updatedUsuario))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUsuario(@PathVariable String id) {
        return usuarioService.deleteUsuario(id)
                .map(removed -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.noContent().build())
                ;
    }
}