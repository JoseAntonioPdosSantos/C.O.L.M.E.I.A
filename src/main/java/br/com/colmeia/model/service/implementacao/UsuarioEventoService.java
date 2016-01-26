package br.com.colmeia.model.service.implementacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.UsuarioEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.service.generics.Service;

public class UsuarioEventoService extends Service<UsuarioEvento, Long, UsuarioEventoHibernateDAO> {

	public boolean validarSalvarAlterar(UsuarioEvento entity) throws Exception {
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
		Criterion atividadeEvento = null;
		Criterion dataCadastro = null;
		Criterion usuario = null;
		Criterion ativo = null;
		Criterion presenca = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getAtividadeEvento() != null) {
				atividadeEvento = Restrictions.eq("atividadeEvento", entity.getAtividadeEvento());
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
		return getDao().findByCriteria(id, atividadeEvento, dataCadastro, usuario, ativo, presenca);
	}

	public String inscreverEmUmaAtividadeDeEvento(AtividadeEvento atividadeEvento, Usuario usuario) throws Exception {
		if (!isCadastrado(usuario, atividadeEvento)) {
			UsuarioEvento usuarioEvento = new UsuarioEvento();
			usuarioEvento.setAtividadeEvento(atividadeEvento);
			usuarioEvento.setUsuario(usuario);
			usuarioEvento.setPresenca(false);
			super.gravar(usuarioEvento);
			return "Parabéns! Inscrição realizada com sucesso!";
		} else {
			UsuarioEvento usuarioEvento = new UsuarioEvento();
			usuarioEvento.setAtividadeEvento(atividadeEvento);
			usuarioEvento.setUsuario(usuario);
			usuarioEvento.setAtivo(true);
			List<UsuarioEvento> usuarioEventos = buscar(usuarioEvento);
			if (usuarioEventos == null || usuarioEventos.size() != 1)
				throw new Exception("Ocorreu um erro indevido. Favor contatar o administrador do sistema.");
			cancelarAtividadeDoEvento(usuarioEventos.get(0));
			return "Inscrição cancelada com sucesso!";
		}
	}

	public boolean cancelarAtividadeDoEvento(UsuarioEvento usuarioEvento) throws Exception {
		if (usuarioEvento.getPresenca() != null)
			if (usuarioEvento.getPresenca())
				throw new Exception("Não é possível cancelar um evento que você já tenha recebido presença");
		usuarioEvento.setAtivo(false);
		alterar(usuarioEvento);
		return true;
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
		List<UsuarioEvento> usuariosEvento_ = new ArrayList<UsuarioEvento>();
		List<UsuarioEvento> usuariosEvento = new ArrayList<UsuarioEvento>();
		List<Usuario> usuarios = UsuarioService.class.newInstance().buscar(usuario);

		for (Usuario usu : usuarios) {
			UsuarioEvento usuarioEvento = new UsuarioEvento();
			usuarioEvento.setUsuario(usu);
			usuarioEvento.setAtividadeEvento(atividadeEvento);
			usuarioEvento.setAtivo(true);
			usuariosEvento_ = buscar(usuarioEvento);
			for (UsuarioEvento usuEvento : usuariosEvento_)
				if (!usuariosEvento.contains(usuEvento))
					usuariosEvento.add(usuEvento);
		}
		return usuariosEvento;
	}

	@Override
	public UsuarioEventoHibernateDAO getDao() {
		return new UsuarioEventoHibernateDAO();
	}

	@Override
	public boolean validarExcluir(UsuarioEvento entity) {
		return true;
	}

}
