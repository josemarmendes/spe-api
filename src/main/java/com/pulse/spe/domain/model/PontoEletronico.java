package com.pulse.spe.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "spe", name = "ponto_eletronico")
@SequenceGenerator(name = "spe.ponto_eletronico_id_seq", sequenceName = "spe.ponto_eletronico_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PontoEletronico {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spe.ponto_eletronico_id_seq")
  @Column(name = "ponto_eletronico_id")
  private Long id;

  private LocalDate data;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Batida> batidas;

  @OneToMany
  private List<Ocorrencia> ocorrencias;

  private Long deltaMinutos;

  @ManyToOne
  @JoinColumn(name = "usuario_codigo")
  private Usuario usuario;

  @PrePersist
  protected void onCreate() {
    data = LocalDate.now();
  }
}
