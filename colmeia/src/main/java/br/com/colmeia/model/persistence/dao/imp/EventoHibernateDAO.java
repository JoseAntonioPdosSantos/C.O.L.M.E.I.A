package br.com.colmeia.model.persistence.dao.imp;

import br.com.colmeia.model.persistance.dao.EventoDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Evento;


public class EventoHibernateDAO extends GenericHibernateDAO<Evento, Long> implements EventoDAO{

}
