package br.com.colmeia.model.persistence.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.UsuarioEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class UsuarioEventoService extends Service<UsuarioEvento, Long, UsuarioEventoHibernateDAO> {

	public boolean validarEntity(UsuarioEvento entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getAtividadeEvento() == null)
			throw new Exception("Desculpe! O campo 'Atividade' é obrigatório");
		if (entity.getUsuario() == null)
			throw new Exception("Desculpe! O campo 'Usuário' é obrigatório");
		return true;
	}

	@Override
	public List<UsuarioEvento> buscar(UsuarioEvento entity) throws Exception {
		Criterion id = null;
		Criterion evento = null;
		Criterion dataCadastro = null;
		Criterion usuario = null;
		Criterion ativo = null;
		Criterion presenca = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getAtividadeEvento() != null) {
				evento = Restrictions.eq("atividadeEvento", entity.getAtividadeEvento());
			}
			if (entity.getUsuario() != null) {
				usuario = Restrictions.eq("usuario", entity.getUsuario());
			}
			if (entity.getAtivo() != null) {
				ativo = Restrictions.eq("ativo", entity.isAtivo());
			}
			if (entity.getPresenca() != null) {
				presenca = Restrictions.eq("presenca", entity.getPresenca());
			}
		}
		return getDao().findByCriteria(id, evento, dataCadastro, usuario, ativo, presenca);
	}

	public void inscreverEmUmaAtividadeDeEvento(AtividadeEvento atividadeEvento, Usuario usuario) throws Exception {
		if (!isCadastrado(usuario, atividadeEvento)) {
			UsuarioEvento usuarioEvento = new UsuarioEvento();
			usuarioEvento.setAtividadeEvento(atividadeEvento);
			usuarioEvento.setUsuario(usuario);
			usuarioEvento.setPresenca(false);
			super.gravar(usuarioEvento);
		} else {
			throw new Exception("Você já se inscreveu neste evento");
		}
	}

	public boolean isCadastrado(Usuario usuario, AtividadeEvento atividadeEvento) {
		List<UsuarioEvento> usuariosEventos = getUsuarioAtividadeEventosAtivos(usuario, atividadeEvento);
		if (usuariosEventos.size() != 1) {
			return false;
		}
		return true;
	}

	public List<UsuarioEvento> getUsuarioAtividadeEventosAtivos(Usuario usuario, AtividadeEvento atividadeEvento) {
		Criterion ct_usuario = Restrictions.eq("usuario", usuario);
		Criterion ct_atividade_evento = Restrictions.eq("atividadeEvento", atividadeEvento);
		Criterion ct_ativo = Restrictions.eq("ativo", true);
		return getDao().findByCriteria(ct_usuario, ct_atividade_evento, ct_ativo);
	}

	public boolean cancelarEvento(UsuarioEvento usuarioEvento) throws Exception {
		if (usuarioEvento.getPresenca() != null)
			if (usuarioEvento.getPresenca())
				throw new Exception("Não é possível cancelar um evento que você já tenha recebido presença");
		usuarioEvento.setAtivo(false);
		alterar(usuarioEvento);
		return true;
	}

	public UsuarioEvento registrarPresenca(UsuarioEvento usuarioEvento) throws Exception {
		usuarioEvento.setPresenca(true);
		getDao().insert(usuarioEvento);
		return getDao().findById(usuarioEvento.getId());
	}

	public UsuarioEvento desmarcarPresenca(UsuarioEvento usuarioEvento) throws Exception {
		usuarioEvento.setPresenca(false);
		return getDao().update(usuarioEvento);
	}

	public List<UsuarioEvento> buscarUsuarioEventoPorUsuarioEAtividadeEvento(Usuario usuario,
			AtividadeEvento atividadeEvento) throws Exception {
		List<UsuarioEvento> usuariosEvento = new ArrayList<UsuarioEvento>();
		List<UsuarioEvento> usuariosEvento_ = new ArrayList<UsuarioEvento>();
		List<Usuario> usuarios = UsuarioService.class.newInstance().buscar(usuario);

		for (Usuario usu : usuarios) {
			UsuarioEvento usuarioEvento = new UsuarioEvento();
			usuarioEvento.setUsuario(usu);
			usuarioEvento.setAtividadeEvento(atividadeEvento);
			usuarioEvento.setAtivo(true);
			usuariosEvento = buscar(usuarioEvento);
			for (UsuarioEvento usuEvento : usuariosEvento)
				if (!usuariosEvento_.contains(usuEvento))
					usuariosEvento_.add(usuEvento);
		}
		return usuariosEvento_;
	}

	@Override
	public UsuarioEventoHibernateDAO getDao() {
		return new UsuarioEventoHibernateDAO();
	}

}
