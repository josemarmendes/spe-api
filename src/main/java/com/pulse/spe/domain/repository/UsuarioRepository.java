package com.pulse.spe.domain.repository;

import com.pulse.spe.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Usuario findByCpf(String cpf);
}
