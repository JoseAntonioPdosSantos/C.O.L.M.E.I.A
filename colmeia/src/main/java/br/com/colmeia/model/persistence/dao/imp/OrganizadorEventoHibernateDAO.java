package br.com.colmeia.model.persistence.dao.imp;

import br.com.colmeia.model.persistance.dao.OrganizadorEventoDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Organizador;
import br.com.colmeia.model.persistence.entity.OrganizadorEvento;

import java.util.List;


public class OrganizadorEventoHibernateDAO extends GenericHibernateDAO<OrganizadorEvento, Long> implements OrganizadorEventoDAO{

    public List<Evento> getEventosByOrganizador(Organizador Organizador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
