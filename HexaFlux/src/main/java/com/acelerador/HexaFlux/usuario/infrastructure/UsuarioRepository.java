package com.acelerador.HexaFlux.usuario.infrastructure;

import com.acelerador.HexaFlux.usuario.domain.Usuario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Usuario entities in MongoDB.
 */
@Repository
public interface UsuarioRepository extends ReactiveMongoRepository<Usuario, String> {
}
