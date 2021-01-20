package com.pulse.spe.domain.service;

import com.pulse.spe.domain.business.PontoEletronicoBusiness;
import com.pulse.spe.domain.model.Batida;
import com.pulse.spe.domain.model.PontoEletronico;
import com.pulse.spe.domain.model.Usuario;
import com.pulse.spe.domain.repository.BatidaRepository;
import com.pulse.spe.domain.repository.PontoEletronicoRepository;
import com.pulse.spe.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PontoEletronicoService {

	private final UsuarioRepository usuarioRepository;
	private final BatidaRepository batidaRepository;
	private final PontoEletronicoRepository pontoEletronicoRepository;
	private final PontoEletronicoBusiness pontoEletronicoBusiness;
	
	@Transactional
	public PontoEletronico registrarBatida(String cpf, LocalDateTime horario) {
		Usuario usuario = usuarioRepository.findByCpf(cpf);

		// por enquanto vem com as batidas ja do banco, se mudar para LAZY, carregar as
		// batidas na mão
		 //pontoEletronico;

		/*
		 * try { pontoEletronico =
		 * pontoEletronicoRepository.findByUsuarioAndData(usuario,
		 * horario.toLocalDate());
		 * 
		 * } catch (RuntimeException e) {
		 */
		// procurar a excecao que esses métodos do pontoEletronicoRepository deve
		// retornar uma excecao se nao achar dados no banco

		// }

		PontoEletronico pontoEletronico = PontoEletronico.builder()
			.data(horario.toLocalDate())
			.usuario(usuario).build();
		
		Batida batida = Batida.builder().hora(horario.toLocalTime()).pontoEletronico(pontoEletronico).build();
		List<Batida> batidas = new ArrayList<>();
		batidas.add(batida);

		pontoEletronico.setDeltaMinutos(Long.valueOf(batida.getHora().getMinute()));
		pontoEletronico.setBatidas(batidas);

		batidaRepository.save(batida);

		pontoEletronicoBusiness.registrarBatida(pontoEletronico, horario);

		return pontoEletronicoRepository.save(pontoEletronico);
	}

	

}
