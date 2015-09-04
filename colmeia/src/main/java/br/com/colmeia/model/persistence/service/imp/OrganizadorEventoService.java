package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.OrganizadorEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.OrganizadorEvento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class OrganizadorEventoService extends Service<OrganizadorEvento, Long, OrganizadorEventoHibernateDAO> {

	public boolean validarEntity(OrganizadorEvento entity) {
		return true;
	}

	@Override
	public List<OrganizadorEvento> buscar(OrganizadorEvento entity) throws Exception {
		Criterion id = null;
		Criterion organizador = null;
		Criterion evento = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getOrganizador() != null) {
				organizador = Restrictions.eq("organizador", entity.getOrganizador());
			}
			if (entity.getEvento() != null) {
				evento = Restrictions.eq("evento", entity.getEvento());
			}
		}
		return getDao().findByCriteria(id, organizador, evento);
	}

	@Override
	public OrganizadorEventoHibernateDAO getDao() {
		return new OrganizadorEventoHibernateDAO();
	}

}
