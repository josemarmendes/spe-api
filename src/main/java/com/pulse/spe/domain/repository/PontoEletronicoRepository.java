package com.pulse.spe.domain.repository;

import com.pulse.spe.domain.model.PontoEletronico;
import com.pulse.spe.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {

  PontoEletronico findByUsuarioAndData(Usuario usuario, LocalDate data);

}
