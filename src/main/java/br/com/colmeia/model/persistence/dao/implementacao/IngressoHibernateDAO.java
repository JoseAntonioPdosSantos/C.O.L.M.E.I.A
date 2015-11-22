package br.com.colmeia.model.persistence.dao.implementacao;

import br.com.colmeia.model.persistence.dao.IngressoDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Ingresso;


public class IngressoHibernateDAO extends GenericHibernateDAO<Ingresso, Long> implements IngressoDAO{

}
