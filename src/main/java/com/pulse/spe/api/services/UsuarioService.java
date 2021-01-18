package com.pulse.spe.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.pulse.spe.api.repositorys.UsuarioRepository;
import com.pulse.spe.domain.Usuario;
import com.pulse.spe.domain.dto.UsuarioDTO;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<UsuarioDTO> getUsuarios() {
		return usuarioRepository.findAll().stream().map(UsuarioDTO::create).collect(Collectors.toList());
	}

	public UsuarioDTO insert(Usuario usuario) {
		Assert.isNull(usuario.getId(), "Não foi possível inserir o registro");
		
		return UsuarioDTO.create(usuarioRepository.save(usuario));
	}

	public List<UsuarioDTO> getUsuariosPorCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf).stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

}
