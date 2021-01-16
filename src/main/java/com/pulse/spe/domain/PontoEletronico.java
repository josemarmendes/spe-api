package com.pulse.spe.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PontoEletronico {
	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dataBatida;

	@Temporal(TemporalType.TIME)
	private Date horaBatida;

	@Enumerated(EnumType.STRING)
	private TipoBatida tipoBatida;

	@ManyToOne
	private Usuario usuario;

	@PrePersist
	protected void onCreate() {
		dataBatida = new Date();
		horaBatida = new Date();
	}
}
