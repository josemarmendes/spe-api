package com.pulse.spe.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "spe", name = "usuario")
@SequenceGenerator(name = "spe.usuario_id_seq",sequenceName = "spe.usuario_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario implements UserDetails {
	
	@Id
	@Column(name = "usuario_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spe.usuario_id_seq")
	private Long id;
	
	@NotBlank
	@Column(name ="nome")
	private String nome;
	
	@NotBlank
	@Column(name ="sobrenome")
	private String sobrenome;
	
	@NotBlank
	@Column(name ="cpf")
	@CPF
	private String cpf;

	@NotBlank
	@Email
	@Column(name ="email")
	private String email;
	
	@Column(name ="senha")
	private String senha;

	@Column(name ="data_cadastro") 
	private OffsetDateTime dataCadastro = OffsetDateTime.now();
	
	@ManyToMany
	@JoinTable(
			  name = "usuario_perfil", schema = "spe",
			  joinColumns = @JoinColumn(name = "usuario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "perfil_id"))	
	private Collection<Perfil> perfis;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		
		return this.perfis;
	}
	@Override
	public String getPassword() {
		
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.nome;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
