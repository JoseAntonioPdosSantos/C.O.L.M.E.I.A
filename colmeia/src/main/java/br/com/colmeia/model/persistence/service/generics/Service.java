package br.com.colmeia.model.persistence.service.generics;

import java.io.Serializable;
import java.util.List;

import br.com.colmeia.model.persistence.dao.generics.GenericDAO;

public abstract class Service<T, ID extends Serializable, DAO extends GenericDAO<T, ID>>
		implements IService<T, ID> {

	public Service() {
		
	}
	
	public void gravar(T entity) throws Exception {
		if (validarEntity(entity)) {
			getDao().insert(entity);
		}
	}

	public void alterar(T entity) throws Exception {
		if (validarEntity(entity)) {
			getDao().update(entity);
		}
	}

	public void apagar(T entity) throws Exception {
		if (validarEntity(entity)) {
			getDao().remove(entity);
		}
	}

	public List<T> buscarTodos() throws Exception {
		return getDao().findAll();
	}

	public T buscarPorId(ID id) throws Exception {
		return getDao().findById(id);
	}

	public abstract List<T> buscar(T entity) throws Exception;

	public abstract DAO getDao();

}
