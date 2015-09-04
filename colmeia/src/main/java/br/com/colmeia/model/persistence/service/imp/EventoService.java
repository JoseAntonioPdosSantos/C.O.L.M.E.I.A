package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.EventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class EventoService extends Service<Evento, Long, EventoHibernateDAO> {

	public boolean validarEntity(Evento entity) {
		return true;
	}

	@Override
	public List<Evento> buscar(Evento entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion coordenador = null;
		Criterion dtini = null;
		Criterion dtfim = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null) {
				if (!entity.getNome().trim().isEmpty())
					nome = Restrictions.eq("nome", entity.getNome());
			}
			if (entity.getCoordenador() != null) {
				coordenador = Restrictions.eq("coordenador", entity.getCoordenador());
			}
			if (entity.getDtini() != null) {
				dtini = Restrictions.eq("dtini", entity.getDtini());
			}
			if (entity.getDtfim() != null) {
				dtfim = Restrictions.eq("dtfim", entity.getDtfim());
			}
		}
		return getDao().findByCriteria(id, nome, coordenador, dtini, dtfim);
	}

	@Override
	public EventoHibernateDAO getDao() {
		return new EventoHibernateDAO();
	}

}
