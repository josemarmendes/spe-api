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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalTime;

@Data
@Entity
@Builder
@Table(schema = "spe", name = "batida")
@SequenceGenerator(name = "spe.batida_id_seq", sequenceName = "spe.batida_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
public class Batida {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spe.batida_id_seq")
  @Column(name = "batida_id")
  private Long id;
  
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "ponto_eletronico_id")
  private PontoEletronico pontoEletronico;

  private LocalTime hora;

}
