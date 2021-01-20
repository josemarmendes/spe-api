package com.pulse.spe.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "spe", name = "ocorrencia")
@SequenceGenerator(name = "spe.ocorrencia_id_seq", sequenceName = "spe.ocorrencia_id_seq", allocationSize = 1)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spe.ocorrencia_id_seq")
  @Column(name = "ocorrencia_id")
  private Long id;

  @ManyToOne
  private PontoEletronico pontoEletronico;

  private TipoOcorrencia tipoOcorrencia;


}

