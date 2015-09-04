package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.UsuarioEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class UsuarioEventoService extends Service<UsuarioEvento, Long, UsuarioEventoHibernateDAO> {

	public boolean validarEntity(UsuarioEvento entity) {
		return true;
	}

	@Override
	public List<UsuarioEvento> buscar(UsuarioEvento entity) throws Exception {
		Criterion id = null;
		Criterion evento = null;
		Criterion dataCadastro = null;
		Criterion usuario = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getEvento() != null) {
				evento = Restrictions.eq("evento", entity.getEvento());
			}
			if (entity.getDataCadastro() != null) {
				dataCadastro = Restrictions.eq("datacadastro", entity.getDataCadastro());
			}
			if (entity.getUsuario() != null) {
				usuario = Restrictions.eq("usuairo", entity.getUsuario());
			}
		}
		return getDao().findByCriteria(id, evento, dataCadastro, usuario);
	}

	@Override
	public UsuarioEventoHibernateDAO getDao() {
		return new UsuarioEventoHibernateDAO();
	}

}
