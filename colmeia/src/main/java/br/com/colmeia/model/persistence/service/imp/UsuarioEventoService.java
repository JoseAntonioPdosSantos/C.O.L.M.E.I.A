package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.UsuarioEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class UsuarioEventoService extends Service<UsuarioEvento, Long, UsuarioEventoHibernateDAO> {

	public boolean validarEntity(UsuarioEvento entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getEvento() == null)
			throw new Exception("Desculpe! O campo 'Evento' é obrigatório");
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
			if (entity.getEvento() != null) {
				evento = Restrictions.eq("evento", entity.getEvento());
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

	@Override
	public UsuarioEventoHibernateDAO getDao() {
		return new UsuarioEventoHibernateDAO();
	}

	public boolean isCadastrado(Usuario usuario, Evento evento) {
		List<UsuarioEvento> usuariosEventos = getUsuarioEventosAtivos(usuario, evento);
		if (usuariosEventos.size() != 1) {
			return false;
		}
		return true;
	}

	public List<UsuarioEvento> getUsuarioEventosAtivos(Usuario usuario, Evento evento) {
		Criterion ct_usuario = Restrictions.eq("usuario", usuario);
		Criterion ct_evento = Restrictions.eq("evento", evento);
		Criterion ct_ativo = Restrictions.eq("ativo", true);
		return getDao().findByCriteria(ct_usuario, ct_evento, ct_ativo);
	}

	public boolean cancelarEvento(UsuarioEvento usuarioEvento) throws Exception {
		List<UsuarioEvento> usuariosEventos = getUsuarioEventosAtivos(usuarioEvento.getUsuario(),
				usuarioEvento.getEvento());
		if (usuariosEventos.size() == 1) {
			UsuarioEvento ue = usuariosEventos.get(0);
			ue.setAtivo(false);
			alterar(ue);
			return true;
		}
		return false;
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

}
