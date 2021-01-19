package com.pulse.spe.api.controller;

import com.pulse.spe.domain.model.PontoEletronico;
import com.pulse.spe.domain.service.PontoEletronicoService;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pontoEletronico")
@RequiredArgsConstructor
public class PontoEletronicoController {

  private final PontoEletronicoService pontoEletronicoService;

  @PostMapping
  public ResponseEntity<PontoEletronico> post(String cpf, LocalDateTime horario){
	  pontoEletronicoService.registrarBatida(cpf, horario);
	  return ResponseEntity.ok().build();
  }
 
}
