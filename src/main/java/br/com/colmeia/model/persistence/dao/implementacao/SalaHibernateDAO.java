package br.com.colmeia.model.persistence.dao.implementacao;

import br.com.colmeia.model.persistence.dao.SalaDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Sala;


public class SalaHibernateDAO extends GenericHibernateDAO<Sala, Long> implements SalaDAO{

}
