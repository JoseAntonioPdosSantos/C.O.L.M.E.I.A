package br.com.colmeia.controller.generics;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.com.colmeia.model.persistence.entity.Usuario;


public abstract class Controller extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void gravar() throws Exception;

	public abstract void alterar() throws Exception;

	public abstract void apagar() throws Exception;

	public abstract void buscarTodos() throws Exception;

	public abstract void buscar() throws Exception;

	public abstract void buscarPorId() throws Exception;

	public abstract boolean validarEntity();
	
	public Usuario getCurrentInstanceUser(){
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}
	
}
