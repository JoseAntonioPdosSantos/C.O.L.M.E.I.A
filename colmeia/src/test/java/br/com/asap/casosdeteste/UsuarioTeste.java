package br.com.asap.casosdeteste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.colmeia.model.persistance.dao.UsuarioDAO;
import br.com.colmeia.model.persistence.dao.implementacao.UsuarioHibernateDAO;
import br.com.colmeia.model.persistence.entity.Perfil;
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
		entity.setSenha("1");
		entity.setPerfil(Perfil.USUARIO);
		dao = new UsuarioHibernateDAO();
		assertTrue(dao.insert(entity));

		entity = new Usuario();
		entity.setNome("Kaenna V. da Silva Guaola");
		entity.setCpf("1");
		entity.setSenha("1");
		entity.setPerfil(Perfil.ADMINISTRADOR);
		dao = new UsuarioHibernateDAO();
		dao.insert(entity);
		
		entity = new Usuario();
		entity.setNome("Davi Martins dos Santos");
		entity.setCpf("2");
		entity.setSenha("2");
		entity.setPerfil(Perfil.USUARIO);
		dao = new UsuarioHibernateDAO();
		dao.insert(entity);
	}

}
