package com.pulse.spe.domain.service;

import com.pulse.spe.domain.repository.UsuarioRepository;
import com.pulse.spe.domain.model.Usuario;
import com.pulse.spe.api.model.UsuarioDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
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

  public UsuarioDTO insert(Usuario usuario) {
    Assert.isNull(usuario.getId(), "Não foi possível inserir o registro");
    if (isUsuarioCadastrado(usuario.getCpf())) {
      throw new RuntimeException("Usuário já está cadastrado");
    }
    return UsuarioDTO.create(usuarioRepository.save(usuario));
  }


  public Usuario getUsuarioPorCpf(String cpf) {
    return usuarioRepository.findByCpf(cpf);
  }


  public boolean isUsuarioCadastrado(String cpf) {
    Usuario usuario = usuarioRepository.findByCpf(cpf);

    if (usuario != null) {
      return true;
    }

    return false;
  }

}