package com.pulse.spe.domain.repository;

import com.pulse.spe.domain.model.Batida;
import com.pulse.spe.domain.model.PontoEletronico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatidaRepository extends JpaRepository<Batida, Long> {

  List<Batida> findByPontoEletronico(PontoEletronico pontoEletronico);

}
