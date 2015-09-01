package br.com.colmeia.model.persistence.dao.generics;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import br.com.colmeia.model.utils.HibernateUtil;


@SuppressWarnings("unchecked")
public class GenericHibernateDAO<T, ID extends Serializable> extends HibernateUtil implements GenericDAO<T, ID> {

	private Class<T> persistentClass;

	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Class<T> getPersistentClass() {
		return persistentClass;
	}

	public Boolean insert(T arg1) {
		try {
			beginTransaction();
			em.persist(arg1);
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			e.printStackTrace();
		}
		return true;
	}

	public T update(T arg1) {
		beginTransaction();
		T Object = ((T) em.merge(arg1));
		commitTransaction();
		return Object;
	}

	public Boolean remove(T arg1) {
		try {
			beginTransaction();
			arg1 = em.merge(arg1);
			em.remove(arg1);
			commitTransaction();
		} catch (Exception e) {
			em.close();
			return false;
		}
		return true;
	}

	/**
	 * Os parametros sao os objetos String onde e passado o SQL e, um HashMap
	 * (opcional), onde e passado um conjunto de parametros contendo uma chave e
	 * um valor. Essa chave deve obedecer ao principio de primeira chave deve
	 * ser chamada de arg1, a segunda de arg2 e assim por diante com seus
	 * respectivos valores.
	 * 
	 * @param sql
	 * @param parans
	 * @return
	 */
	public List<T> findAll(String sql, HashMap<Object, Object>... parans) {
		try {
			Query query = em.createQuery(sql);
			int count = 1;
			for (HashMap<Object, Object> ps : parans) {
				String key = "arg" + count;
				query.setParameter(key, ps.get(key));
				count++;
			}
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return null;
	}

	public long count(String sql, HashMap<Object, Object>... parans) {
		Query query = em.createQuery(sql);
		int count = 1;
		for (HashMap<Object, Object> ps : parans) {
			String key = "arg" + count;
			query.setParameter(key, ps.get(key));
			count++;
		}
		return (Long) query.getSingleResult();
	}

	public int max(String sql, HashMap<Object, Object>... parans) {
		Query query = em.createQuery(sql);
		int count = 1;
		for (HashMap<Object, Object> ps : parans) {
			String key = "arg" + count;
			query.setParameter(key, ps.get(key));
			count++;
		}
		return (Integer) query.getSingleResult();
	}

	public T findById(ID id) throws Exception {
		return em.find(persistentClass, id);
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	public List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = ((Session) em.getDelegate())
				.createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			if (c != null) {
				crit.add(c);
			}
		}

		return crit.list();
	}

	public List<T> executeQuery(String query) {
		org.hibernate.Query q = getSession().createQuery(query);
		return q.list();
	}

	public List<T> executeNamedQuery(String namedQuery) {
		org.hibernate.Query q = getSession().getNamedQuery(namedQuery);
		return q.list();
	}

	public T executeQueryResultUnique(String query) throws Exception {
		org.hibernate.Query q = getSession().createQuery(query);
		List<T> list = q.list();
		if (list.size() != 1) {
			throw new Exception(
					"O SQL executado retornou mais de um registro. Favor verificar: "
							+ query);
		}
		return (T) q.list().get(0);
	}
	public T executeQueryResultUnique(String query,HashMap<Object,Object> parans) throws Exception {
		org.hibernate.Query q = getSession().createQuery(query);
		int count = 1;
		for (int i = 0; i < parans.size(); i++) {
			String key = "arg" + count;
			q.setParameter(key, parans.get(key));
			count++;
		}
		List<T> list = q.list();
		if (list.size() != 1) {
			throw new Exception(
					"O SQL executado retornou mais de um registro. Favor verificar: "
							+ query);
		}
		return (T) q.list().get(0);
	}

}