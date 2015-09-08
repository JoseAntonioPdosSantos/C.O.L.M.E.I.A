package br.com.colmeia.controller.generics;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.security.Security;

public abstract class Controller<T> extends Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean editando_registro = false;
	private boolean size_maior_q_zero = false;

	public abstract void gravar() throws Exception;

	public abstract void alterar() throws Exception;

	public abstract void apagar(T entity) throws Exception;

	public abstract void buscar() throws Exception;

	public abstract void limpar();
	
	public boolean getAcesso(String permissao){
		if(permissao == null) return false;
		Usuario usuario = getCurrentInstanceUser();
		if(usuario==null)return false;
		return Security.getAcesso(usuario.getPerfil(),permissao);
	}

	public static Usuario getCurrentInstanceUser() {
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}

	public boolean isEditando_registro() {
		return editando_registro;
	}

	public void setEditando_registro(boolean editando_registro) {
		this.editando_registro = editando_registro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSize_maior_q_zero() {
		return size_maior_q_zero;
	}

	public void setSize_maior_q_zero(boolean size_maior_q_zero) {
		this.size_maior_q_zero = size_maior_q_zero;
	}

}
