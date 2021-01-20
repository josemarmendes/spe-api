package com.pulse.spe.domain.repository;

import com.pulse.spe.domain.model.PontoEletronico;
import com.pulse.spe.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {

  PontoEletronico findByUsuarioAndData(Usuario usuario, LocalDate data);
  
  @Query("select p from PontoEletronico p where p.usuario.cpf = :cpf and p.data = :data")
  List<PontoEletronico> buscarPontoPorCpfAndData(@Param("cpf") String cpf, @Param("data") LocalDate data);

}
