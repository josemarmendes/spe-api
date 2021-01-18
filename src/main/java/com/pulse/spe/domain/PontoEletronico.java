package com.pulse.spe.domain;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "spe", name = "ponto_eletronico")
@SequenceGenerator(name = "spe.ponto_eletronico_id_seq",sequenceName = "spe.ponto_eletronico_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PontoEletronico {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spe.ponto_eletronico_id_seq")
	@Column(name = "ponto_eletronico_id")
	private Long id;

	@Column(name="data_entrada")
	private OffsetDateTime dataEntrada;

	@Column(name="data_saida")
	private OffsetDateTime dataSaida;

	@Enumerated(EnumType.STRING)
	private TipoBatida tipoBatida;

	@ManyToOne
	@JoinColumn(name = "usuario_codigo")
	private Usuario usuario;

	@PrePersist
	protected void onCreate() {
		dataEntrada = OffsetDateTime.now();
	}
}
