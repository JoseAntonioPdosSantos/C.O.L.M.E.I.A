package br.com.asap.casosdeteste;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.colmeia.model.persistance.dao.UsuarioDAO;
import br.com.colmeia.model.persistence.dao.imp.UsuarioHibernateDAO;
import br.com.colmeia.model.persistence.entity.Usuario;

public class UsuarioTeste {
	Usuario entity;
	List<Usuario> entities;
	UsuarioDAO dao;// = new UsuarioHibernateDAO();
	String nome;

	@Test
	public void insertTest() {
		entity = new Usuario();
		entity.setNome("José Antonio");
		entity.setCpf("99198916149");
		entity.setSenha("123");
		dao = new UsuarioHibernateDAO();
		assertTrue(dao.insert(entity));
	}

}
