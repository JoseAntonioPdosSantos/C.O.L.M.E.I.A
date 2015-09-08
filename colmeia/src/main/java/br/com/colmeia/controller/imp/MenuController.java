package br.com.colmeia.controller.imp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Usuario;

@ManagedBean
@ViewScoped
public class MenuController extends Controller<Usuario> {

	private static final long serialVersionUID = 1L;

	public MenuController() {
	}

	@Override
	public void gravar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void apagar(Usuario entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
