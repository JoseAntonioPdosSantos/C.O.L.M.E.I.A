/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.asap.casosdeteste;

import br.com.colmeia.model.persistance.dao.UsuarioDAO;
import br.com.colmeia.model.persistance.dao.UsuarioEventoDAO;
import br.com.colmeia.model.persistence.dao.imp.UsuarioEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Entity;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import java.sql.Date;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Avell B155 MAX
 */
public class UsuarioEventoTest {
    
    @Test
	public void insertTest() {
		
            Usuario usuario = new Usuario();
            
            UsuarioEvento usuarioevento = new UsuarioEvento();
            usuarioevento.setUsuario(usuario);
            
            UsuarioEventoDAO dao = new UsuarioEventoHibernateDAO();
                
	    assertTrue(dao.insert(usuarioevento));
	}
    
        
        @Test
	public void updateTest() throws Exception {
             Usuario usuario = new Usuario();
             
		UsuarioEvento usuarioevento  = new UsuarioEvento();
		usuarioevento.setUsuario(usuario);
                
                 UsuarioEventoDAO dao = new UsuarioEventoHibernateDAO();
		dao.insert(usuarioevento);
		usuarioevento = dao.findById(2L);
               assertNotNull(dao.update(usuarioevento));
	}
        
        
         @Test
	public void delete() {
                  
           
            UsuarioEvento usuarioevento  = new UsuarioEvento();
		 
		usuarioevento.setId(1L);
                 UsuarioEventoDAO dao = new UsuarioEventoHibernateDAO();
		assertNotNull(dao.remove(usuarioevento));
	}
        
    
}
