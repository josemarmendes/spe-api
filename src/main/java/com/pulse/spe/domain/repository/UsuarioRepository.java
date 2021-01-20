package com.pulse.spe.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pulse.spe.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByCpf(String cpf);
	
	@Query("select u from Usuario u join fetch u.perfis where u.id = :usuarioId")
	Optional<Usuario> usuarioComGrupos(@Param("usuarioId") Long usuarioId);

}
