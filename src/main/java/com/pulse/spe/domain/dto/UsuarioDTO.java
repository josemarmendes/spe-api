package com.pulse.spe.domain.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.modelmapper.ModelMapper;
import com.pulse.spe.domain.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Long id;
	
	private String nome;

	private String cpf;

	private String email;

	private OffsetDateTime dataCadastro;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.email = usuario.getEmail();
		this.dataCadastro = usuario.getDataCadastro();
	}
	
	public UsuarioDTO() {
	
	}

	public static UsuarioDTO create(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

}
