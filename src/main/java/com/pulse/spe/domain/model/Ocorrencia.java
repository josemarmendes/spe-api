package com.pulse.spe.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia {

  @Id
  private Long id;

  @ManyToOne
  private PontoEletronico pontoEletronico;

  private TipoOcorrencia tipoOcorrencia;


}

