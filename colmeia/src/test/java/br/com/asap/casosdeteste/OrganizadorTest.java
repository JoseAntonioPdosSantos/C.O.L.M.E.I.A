package br.com.asap.casosdeteste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import br.com.colmeia.model.persistance.dao.OrganizadorDAO;
import br.com.colmeia.model.persistence.dao.imp.OrganizadorHibernateDAO;
import br.com.colmeia.model.persistence.entity.Organizador;

public class OrganizadorTest {

	Organizador entity;
	List<Organizador> entities;
	OrganizadorDAO dao = new OrganizadorHibernateDAO();
	String nome;

	@Test
	public void insertTest() {
		entity = new Organizador();
		entity.setNome("m");
		assertTrue(dao.insert(entity));
	}

	// @Test
	public void updateTest() throws Exception {
		entity = new Organizador();
		entity.setNome("m");
		dao.insert(entity);
		entity = dao.findById(5L);
		entity.setNome("mm");
		assertNotNull(dao.update(entity));
	}

	// @Test
	public void delete() {
		entity = new Organizador();
		entity.setId(4L);
		assertNotNull(dao.remove(entity));
	}

	// @Test
	public void buscarPorId() throws Exception {
		entity = dao.findById(5L);
		assertNotNull(entity);
	}

	// @Test
	public void buscarTodos() {
		setNome("Mariana Joaquina");
		setNome("Pedrinho Pedrosa");
		insertTest();
		insertTest();
		entities = dao.findAll();
		assertEquals(2, entities.size());
	}

	@SuppressWarnings("unchecked")
	// @Test
	public void buscarTodosUsandoParametros() {
		entity = new Organizador();
		entity.setNome("Jos�");
		dao.insert(entity);
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("arg1", entity.getNome());
		entities = dao
				.findAll(
						"SELECT new Entity(obj.id,obj.nome) FROM Entity obj WHERE obj.nome = :arg1",
						hashMap);
		assertEquals(1, entities.size());
	}

	@SuppressWarnings("unchecked")
	// @Test
	public void countUsuarioTest() {
		String sql = "SELECT COUNT(obj) FROM Entity obj";
		long count = dao.count(sql);
		assertEquals(17, count);
	}

	@SuppressWarnings("unchecked")
	// @Test
	public void maxUsuarioTest() {
		String sql = "SELECT MAX(obj.id) FROM Entity obj";
		long count = dao.count(sql);
		assertEquals(21, count);
	}

	// @Test
	public void findByCriterionUsuarioTest() {
		String nomeDaPropriedadeNoBanco = "nome";
		String valorDaPropriedade = "Jose";
		Criterion criterion = Restrictions.eq(nomeDaPropriedadeNoBanco,
				valorDaPropriedade);
		entities = dao.findByCriteria(criterion);
		assertEquals(14, entities.size());
	}

	// @Test
	public void executeQueryUsuarioTest() {
		String query = "SELECT obj FROM Entity obj";
		entities = dao.executeQuery(query);
		assertEquals(17, entities.size());
	}

	// @Test
	public void executeNamedQueryUsuarioTest() {
		String namedQuery = "entity.buscarTodos";
		entities = dao.executeNamedQuery(namedQuery);
		assertEquals(17, entities.size());
	}

	// @Test
	public void executeQueryResultUniqueUsuarioTest() throws Exception {
		String query = "SELECT obj FROM Entity obj WHERE obj.id = 5";
		entity = dao.executeQueryResultUnique(query);
		assertNotNull(entity);
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	private String getNome() {
		return this.nome;
	}
}