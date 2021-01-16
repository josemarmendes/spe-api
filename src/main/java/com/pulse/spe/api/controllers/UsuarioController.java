package com.pulse.spe.api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pulse.spe.api.services.UsuarioService;
import com.pulse.spe.domain.Usuario;
import com.pulse.spe.domain.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/v1/spe")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	

	
	@PostMapping
	//@Secured({"ROLE_ADMIN"})
	public ResponseEntity post(@RequestBody Usuario usuario) {

		UsuarioDTO c = usuarioService.insert(usuario);
		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();

	}

	public URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

	}
}
