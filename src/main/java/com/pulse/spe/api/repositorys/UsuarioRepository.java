package com.pulse.spe.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.spe.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
}
