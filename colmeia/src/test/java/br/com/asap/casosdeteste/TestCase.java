package br.com.asap.casosdeteste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import br.com.colmeia.model.persistance.dao.EntityDAO;
import br.com.colmeia.model.persistence.dao.imp.EntityHibernateDAO;
import br.com.colmeia.model.persistence.entity.Entity;

/**
 * Criem uma classe de teste para cada entidade do projeto. Crie um metodo
 * testando cada m�todo para verificar se todos estao funcionando corretamente.
 * Execute cada caso de teste separadamente, pois os casos de testes nao seguem
 * uma ordem de execucao, podendo n gerar o resultado esperado.
 * 
 * @author antonio
 * 
 */

public class TestCase {

	Entity entity;
	List<Entity> entities;
	EntityDAO dao = new EntityHibernateDAO();
	String nome;

	 @Test
	public void insertTest() {
		entity = new Entity();
		entity.setNome(getNome());
		assertTrue(dao.insert(entity));
	}

	// @Test
	public void updateTest() throws Exception {
		entity = new Entity();
		entity.setNome("Jos�");
		dao.insert(entity);
		entity = dao.findById(5L);
		entity.setNome("Jos� Antonio");
		assertNotNull(dao.update(entity));
	}

	// @Test
	public void delete() {
		entity = new Entity();
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
		entity = new Entity();
		entity.setNome("Jos�");
		dao.insert(entity);
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
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
		String valorDaPropriedade = "Jos�";
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
