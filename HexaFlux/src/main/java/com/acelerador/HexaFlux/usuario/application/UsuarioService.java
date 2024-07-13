package com.acelerador.HexaFlux.usuario.application;

import com.acelerador.HexaFlux.usuario.domain.Usuario;
import com.acelerador.HexaFlux.usuario.infrastructure.UsuarioRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Flux<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Mono<Usuario> getUsuarioById(String id) {
        return usuarioRepository.findById(id);
    }

    public Mono<Usuario> createUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

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

    public Mono<Void> deleteUsuario(String id) {
        return usuarioRepository.deleteById(id);
    }
}