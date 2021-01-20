package com.pulse.spe.api.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pulse.spe.domain.dto.UsuarioDTO;
import com.pulse.spe.domain.model.Usuario;
import com.pulse.spe.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		UsuarioDTO carro = usuarioService.getUsuarioById(id);

		return ResponseEntity.ok(carro);
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> get() {
		return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Usuario> getUsuarioPorCpf(@PathVariable String cpf) {
		Usuario usuario = usuarioService.getUsuarioByCpf(cpf);

		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<UsuarioDTO> post(@RequestBody Usuario usuario) throws IOException {
		
		UsuarioDTO u = usuarioService.insert(usuario);
		URI location = getUri(u.getId());
		return ResponseEntity.created(location).build();

	}

	public URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

	}
}
