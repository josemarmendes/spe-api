package com.pulse.spe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.spe.domain.model.Perfil;
import com.pulse.spe.domain.repository.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public ResponseEntity<Perfil> salvar(@RequestBody Perfil perfil){
		perfilRepository.save(perfil);
		return ResponseEntity.ok().body(perfil);
	}
}
