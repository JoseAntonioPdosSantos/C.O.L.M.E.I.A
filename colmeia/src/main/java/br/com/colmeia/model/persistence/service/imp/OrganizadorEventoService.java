package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import br.com.colmeia.model.persistence.dao.imp.OrganizadorEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.OrganizadorEvento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class OrganizadorEventoService extends Service<OrganizadorEvento,Long,OrganizadorEventoHibernateDAO>{

    public boolean validarEntity(OrganizadorEvento entity) {
    	return false;
    }

	@Override
	public List<OrganizadorEvento> buscar(OrganizadorEvento entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
