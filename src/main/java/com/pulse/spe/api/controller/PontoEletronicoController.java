package com.pulse.spe.api.controller;

import com.pulse.spe.domain.model.PontoEletronico;
import com.pulse.spe.domain.service.PontoEletronicoService;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
