package br.com.asap.casosdeteste;

import java.util.List;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;

import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.imp.AtividadeEventoService;
import br.com.colmeia.model.persistence.service.imp.UsuarioEventoService;

public class Teste {

	public static void main(String[] args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Administrador");
		
		AtividadeEvento atividadeEvento = AtividadeEventoService.class.newInstance().buscarPorId(2L);
		
		List<UsuarioEvento> usuariosEventos = UsuarioEventoService.class.newInstance().buscarUsuarioEventoPorUsuarioEAtividadeEvento(usuario, atividadeEvento);
		for(UsuarioEvento usuEv : usuariosEventos){
			System.out.println(usuEv.getUsuario().getNome());
		}
	}
}
