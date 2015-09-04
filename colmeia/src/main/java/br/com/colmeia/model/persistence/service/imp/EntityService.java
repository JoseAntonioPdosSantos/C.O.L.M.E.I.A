package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import br.com.colmeia.model.persistence.dao.imp.EntityHibernateDAO;
import br.com.colmeia.model.persistence.entity.Entity;
import br.com.colmeia.model.persistence.service.generics.Service;

public class EntityService extends Service<Entity,Long,EntityHibernateDAO>{

    public boolean validarEntity(Entity entity) {
    	return true;
    }

	@Override
	public List<Entity> buscar(Entity entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityHibernateDAO getDao() {
		return new EntityHibernateDAO();
	}

}
