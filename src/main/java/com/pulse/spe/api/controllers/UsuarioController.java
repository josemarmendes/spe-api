package com.pulse.spe.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.pulse.spe.api.services.UsuarioService;
import com.pulse.spe.domain.Usuario;
import com.pulse.spe.domain.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> get(){
		return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity getUsuarioPorCpf(String cpf) {
		List<UsuarioDTO> usuarioDTO = usuarioService.getUsuariosPorCpf(cpf);
		
		return usuarioDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarioDTO);
	}
		
	@PostMapping
	public ResponseEntity post(@RequestBody Usuario usuario) {
		
		UsuarioDTO u = usuarioService.insert(usuario);
		URI location = getUri(u.getId());
		return ResponseEntity.created(location).build();

	}

	public URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

	}
}
