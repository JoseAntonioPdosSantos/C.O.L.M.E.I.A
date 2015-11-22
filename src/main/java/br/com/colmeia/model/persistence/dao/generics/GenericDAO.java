package br.com.colmeia.model.persistence.dao.generics;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;

@SuppressWarnings("unchecked")
public interface GenericDAO<T, ID extends Serializable> {

	Boolean insert(T arg1);

	T update(T arg1);

	Boolean remove(T arg1);

	List<T> findAll(String sql, HashMap<Object, Object>... parans);

	long count(String sql, HashMap<Object, Object>... parans);

	int max(String sql, HashMap<Object, Object>... parans);

	T findById(ID id) throws Exception;

	List<T> findAll();

	List<T> findByCriteria(Criterion... criterion);

	List<T> executeQuery(String query);

	List<T> executeNamedQuery(String namedQuery);

	T executeQueryResultUnique(String query) throws Exception;

}