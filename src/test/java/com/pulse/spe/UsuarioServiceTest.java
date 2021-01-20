package com.pulse.spe;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pulse.spe.domain.dto.UsuarioDTO;
import com.pulse.spe.domain.model.Usuario;
import com.pulse.spe.domain.service.UsuarioService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpeApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServiceTest {
	
	@Autowired
	protected UsuarioService usuarioService;
	
	@Test
	public void testSave() {
		Usuario usuario = new Usuario();
		usuario.setCpf("028.712.070-89");
		usuario.setDataCadastro(OffsetDateTime.now());
		usuario.setEmail("jmattos@gmail.com");
		usuario.setNome("João");
		usuario.setSobrenome("Mattos");
		usuario.setSenha("$2a$10$5zzd6m.A9l5Rq3oZhKewreUuORHibUDCZcli5J7elTNLPO3RT/p/O");
	
		UsuarioDTO u = usuarioService.insert(usuario);
		
		assertNotNull(u);
		
		Long id = u.getId();
		assertNotNull(id);
		
		//Buscar o objeto
		u = usuarioService.getUsuarioById(id);
		assertNotNull(id);
		
		assertEquals("João", u.getNome());
		assertEquals("028.712.070-89", u.getCpf());
		
	}
	
	@Test
    public void testLista() {
        List<UsuarioDTO> usuarios = usuarioService.getUsuarios();
        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
    }
}
