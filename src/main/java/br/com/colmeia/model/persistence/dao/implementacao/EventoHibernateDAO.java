package br.com.colmeia.model.persistence.dao.implementacao;

import br.com.colmeia.model.persistence.dao.EventoDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Evento;


public class EventoHibernateDAO extends GenericHibernateDAO<Evento, Long> implements EventoDAO{

}
