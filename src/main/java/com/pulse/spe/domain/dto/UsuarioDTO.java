package com.pulse.spe.domain.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pulse.spe.domain.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Long id;
	
	private String nome;

	private String cpf;

	private String email;

	private Date dataCadastro;

	public static UsuarioDTO create(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

}
