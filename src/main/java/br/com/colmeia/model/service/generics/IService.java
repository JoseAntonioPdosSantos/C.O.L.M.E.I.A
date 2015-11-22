package br.com.colmeia.model.service.generics;

import java.io.Serializable;
import java.util.List;

public interface IService<T, ID extends Serializable> {

	void gravar(T entity) throws Exception;

	void alterar(T entity) throws Exception;

	void apagar(T entity) throws Exception;

	List<T> buscarTodos() throws Exception;

	T buscarPorId(ID id) throws Exception;

	boolean validarEntity(T entity) throws Exception;
}
