package com.pulse.spe.api.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.spe.domain.model.PontoEletronico;
import com.pulse.spe.domain.service.PontoEletronicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pontoEletronico")
@RequiredArgsConstructor
public class PontoEletronicoController {

	private final PontoEletronicoService pontoEletronicoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PontoEletronico post(@RequestParam("cpf") String cpf) {
		return pontoEletronicoService.registrarBatida(cpf, LocalDateTime.now());

	}
	
	@GetMapping("/cpf/{cpf}/{data}")
	public ResponseEntity<List<PontoEletronico>> getUsuarioPorCpfAndData(@PathVariable String cpf, @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
		List<PontoEletronico> pontos = pontoEletronicoService.getPontosCpfAndData(cpf, data);
		return ResponseEntity.ok(pontos);
	}
	
	@GetMapping("/cpf/{cpf}}")
	public ResponseEntity<List<PontoEletronico>> getUsuarioPorCpf(@PathVariable String cpf) {
		List<PontoEletronico> pontos = pontoEletronicoService.getPontosCpfAndData(cpf, LocalDate.now());
		return ResponseEntity.ok(pontos);
	}

}
