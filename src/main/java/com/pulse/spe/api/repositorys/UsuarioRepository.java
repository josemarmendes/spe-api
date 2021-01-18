package com.pulse.spe.api.repositorys;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.spe.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	//Usuario findByLogin(String cpf);

	List<Usuario> findByCpf(String cpf);
	
}
