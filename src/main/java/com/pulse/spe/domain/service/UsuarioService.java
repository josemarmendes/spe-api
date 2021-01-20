package com.pulse.spe.domain.service;

import com.pulse.spe.domain.repository.UsuarioRepository;
import com.pulse.spe.domain.exception.NegocioException;
import com.pulse.spe.domain.model.Usuario;
import com.pulse.spe.domain.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

	final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarioRepository.findAll().stream().map(UsuarioDTO::create).collect(Collectors.toList());
	}

	public UsuarioDTO getUsuarioById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.map(UsuarioDTO::create).orElseThrow(() -> new NegocioException("Usuário não encontrado"));
	}

	public UsuarioDTO insert(Usuario usuario) {

		Usuario usuarioExistente = usuarioRepository.findByCpf(usuario.getCpf());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse cpf");

		}

		return UsuarioDTO.create(usuarioRepository.save(usuario));
	}

	public Usuario getUsuarioByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}

}