package br.com.colmeia.model.persistance.dao;

import br.com.colmeia.model.persistence.dao.generics.GenericDAO;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Organizador;
import br.com.colmeia.model.persistence.entity.OrganizadorEvento;

import java.util.List;


public interface OrganizadorEventoDAO extends GenericDAO<OrganizadorEvento, Long>{
    
public List<Evento> getEventosByOrganizador(Organizador organizador);

}


