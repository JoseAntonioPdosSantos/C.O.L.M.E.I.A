package br.com.colmeia.model.persistence.service.generics;

import java.io.Serializable;
import java.util.List;

import br.com.colmeia.model.persistence.dao.generics.GenericDAO;

public abstract class Service<T, ID extends Serializable, DAO extends GenericDAO<T, ID>>
		implements IService<T, ID> {

	protected DAO dao;

	public Service() {
		setDao();
	}
	
	public void gravar(T entity) throws Exception {
		if (validarEntity(entity)) {
			dao.insert(entity);
		}
	}

	public void alterar(T entity) throws Exception {
		if (validarEntity(entity)) {
			dao.update(entity);
		}
	}

	public void apagar(T entity) throws Exception {
		if (validarEntity(entity)) {
			dao.remove(entity);
		}
	}

	public List<T> buscarTodos() throws Exception {
		return dao.findAll();
	}

	public T buscarPorId(ID id) throws Exception {
		return dao.findById(id);
	}

	public abstract List<T> buscar(T entity) throws Exception;

	public DAO getDao() {
		return dao;
	}

	public abstract void setDao();

}
