package br.com.colmeia.model.persistence.dao.imp;

import br.com.colmeia.model.persistance.dao.EntityDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Entity;


public class EntityHibernateDAO extends GenericHibernateDAO<Entity, Long> implements EntityDAO{

}
