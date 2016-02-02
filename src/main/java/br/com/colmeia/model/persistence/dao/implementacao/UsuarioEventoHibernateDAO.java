package br.com.colmeia.model.persistence.dao.implementacao;

import br.com.colmeia.model.persistence.dao.UsuarioEventoDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;

import java.util.List;


public class UsuarioEventoHibernateDAO extends GenericHibernateDAO<UsuarioEvento, Long> implements UsuarioEventoDAO{

    public List<Evento> getEventosByUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
