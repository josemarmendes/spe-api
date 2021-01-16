package com.pulse.spe.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	
	private String sobrenome;

	private String cpf;

	private String email;
	
	private String senha;

	private Date dataCadastro;

	@PrePersist
	protected void onCreate() {
		dataCadastro = new Date();
	}
}
