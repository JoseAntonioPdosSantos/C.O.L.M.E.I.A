package br.com.colmeia.model.persistence.dao;

import br.com.colmeia.model.persistence.dao.generics.GenericDAO;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;

import java.util.List;


public interface UsuarioEventoDAO extends GenericDAO<UsuarioEvento, Long>{
    
public List<Evento> getEventosByUsuario(Usuario usuario);

}


