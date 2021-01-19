package com.pulse.spe.api.controller;

import com.pulse.spe.domain.service.PontoEletronicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pontoEletronico")
@RequiredArgsConstructor
public class PontoEletronicoController {

  private final PontoEletronicoService pontoEletronicoService;

  // post para salvar nova batida
}
