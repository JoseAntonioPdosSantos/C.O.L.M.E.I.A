package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import br.com.colmeia.model.persistence.dao.imp.OrganizadorHibernateDAO;
import br.com.colmeia.model.persistence.entity.Organizador;
import br.com.colmeia.model.persistence.service.generics.Service;

public class OrganizadorService extends Service<Organizador,Long,OrganizadorHibernateDAO>{

    public boolean validarEntity(Organizador entity) {
    	return true;
    }

	@Override
	public List<Organizador> buscar(Organizador entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
