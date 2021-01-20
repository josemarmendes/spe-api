package com.pulse.spe.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Table(schema = "spe" , name= "perfil")
@Data
@SequenceGenerator(name = "spe.perfil_id_seq", sequenceName = "spe.perfil_id_seq", allocationSize = 1)
public class Perfil implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="perfil_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spe.perfil_id_seq")
	private Long perfilId;
	
	private String nome;

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
