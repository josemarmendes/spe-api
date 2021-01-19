package com.pulse.spe.api.controller;

import com.pulse.spe.domain.model.Usuario;
import com.pulse.spe.api.model.UsuarioDTO;
import com.pulse.spe.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public ResponseEntity<List<UsuarioDTO>> get() {
    return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
  }


  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<Usuario> getUsuarioPorCpf(String cpf) {
    Usuario usuarioDTO = usuarioService.getUsuarioPorCpf(cpf);

    return ResponseEntity.ok(usuarioDTO);
  }


  @PostMapping
  public ResponseEntity<UsuarioDTO> post(@RequestBody Usuario usuario) {

    UsuarioDTO u = usuarioService.insert(usuario);
    URI location = getUri(u.getId());
    return ResponseEntity.created(location).build();

  }

  public URI getUri(Long id) {
    return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

  }
}
