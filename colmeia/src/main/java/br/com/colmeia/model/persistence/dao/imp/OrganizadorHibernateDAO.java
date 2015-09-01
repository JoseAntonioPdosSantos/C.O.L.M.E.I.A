package br.com.colmeia.model.persistence.dao.imp;

import br.com.colmeia.model.persistance.dao.OrganizadorDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Organizador;


public class OrganizadorHibernateDAO extends GenericHibernateDAO<Organizador, Long> implements OrganizadorDAO{
}
