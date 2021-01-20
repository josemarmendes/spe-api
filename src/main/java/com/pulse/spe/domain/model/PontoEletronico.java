package com.pulse.spe.domain.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(schema = "spe", name = "ponto_eletronico")
@SequenceGenerator(name = "spe.ponto_eletronico_id_seq", sequenceName = "spe.ponto_eletronico_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
public class PontoEletronico {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spe.ponto_eletronico_id_seq")
	@Column(name = "ponto_eletronico_id")
	private Long id;

	private LocalDate data;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	private List<Batida> batidas;

	private Long deltaMinutos;

	@ManyToOne
	@JoinColumn(name = "usuario_codigo")
	private Usuario usuario;

	@PrePersist
	protected void onCreate() {
		data = LocalDate.now();
	}
}
