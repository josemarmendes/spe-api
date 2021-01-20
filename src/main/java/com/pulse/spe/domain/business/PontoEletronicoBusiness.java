package com.pulse.spe.domain.business;

import com.pulse.spe.domain.exception.NegocioException;
import com.pulse.spe.domain.model.Batida;
import com.pulse.spe.domain.model.Ocorrencia;
import com.pulse.spe.domain.model.PontoEletronico;
import com.pulse.spe.domain.model.TipoOcorrencia;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class PontoEletronicoBusiness {

	public void registrarBatida(PontoEletronico pontoEletronico, LocalDateTime horario) {
		Batida batida = Batida.builder().hora(horario.toLocalTime()).pontoEletronico(pontoEletronico).build();

		// pode dar um null pointer aqui
		int size = pontoEletronico.getBatidas().size();

		// regra de negocio do sabado
		if (horario.getDayOfWeek().equals(DayOfWeek.SATURDAY) && size == 2) {
			throw new NegocioException("dia de sabado meu fi so pode bate 2x");
		}

		// Regra de negocio de mais de 4 batidas
		if (size == 4) {
			// não pode bater a 5a vez, por falta de tempo não criei as exceções específicas
			throw new NegocioException("não pode bater a 5a vez");
		}

		pontoEletronico.getBatidas().add(batida);
	}

	/**
	 * metodo para o fechamento de ponto na virada de dia com job
	 */
	public List<Ocorrencia> fechaPonto(PontoEletronico pontoEletronico) {
		Long deltaCalculado;

		List<Batida> batidas = pontoEletronico.getBatidas();

		List<Ocorrencia> ocorrencias = new ArrayList<>();
		int quantidadeBatidas = pontoEletronico.getBatidas().size();

		// Regra de negocio:"1 batida = falta!
		if (quantidadeBatidas == 1) {
			ocorrencias.add(
					Ocorrencia.builder()
					.pontoEletronico(pontoEletronico)
					.tipoOcorrencia(TipoOcorrencia.FALTA)
					.build());
			
			deltaCalculado = -8L; // uma falta é 08:00?

		} else if (quantidadeBatidas == 2 && !pontoEletronico.getData().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			// deduzir das horas do dia trabalhado
			deltaCalculado = 8L - tempoEntre(batidas.get(0).getHora(), batidas.get(1).getHora());

		} else if (quantidadeBatidas == 3) {
			deltaCalculado = 0L;

		} else {
			deltaCalculado = tempoEntre(batidas.get(3).getHora(), batidas.get(2).getHora())
					+ tempoEntre(batidas.get(1).getHora(), batidas.get(0).getHora());
			
			if (deltaCalculado > 10) {
				ocorrencias.add(Ocorrencia.builder().pontoEletronico(pontoEletronico)
						.tipoOcorrencia(TipoOcorrencia.EXCESSO_HORA_EXTRA).build());
			}
		}

		pontoEletronico.setDeltaMinutos(deltaCalculado);
		return ocorrencias;
	}

	// verificar se a ordem importa
	private Long tempoEntre(LocalTime inicio, LocalTime fim) {
		return ChronoUnit.HOURS.between(fim, inicio);
	}

}
