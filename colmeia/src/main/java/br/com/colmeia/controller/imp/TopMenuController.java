package br.com.colmeia.controller.imp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Usuario;

@ManagedBean
@ViewScoped
public class TopMenuController extends Controller<Usuario> {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public TopMenuController() {
		usuario = getCurrentInstanceUser();
	}

	public void deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
	}

	@Override
	public void gravar() throws Exception {

	}

	@Override
	public void alterar() throws Exception {

	}

	@Override
	public void apagar(Usuario entity) throws Exception {

	}

	@Override
	public void buscar() throws Exception {

	}

	@Override
	public void limpar() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
