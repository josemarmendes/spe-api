package com.pulse.spe.domain.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Data
@Entity
@Builder
public class Batida {
  @Id
  private Long id;

  @ManyToOne
  @JoinColumn(name = "adw")
  private PontoEletronico pontoEletronico;

  private LocalTime hora;

}
