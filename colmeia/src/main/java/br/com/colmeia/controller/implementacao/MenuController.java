package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.implementacao.UsuarioService;

@ManagedBean
@ViewScoped
public class MenuController extends Controller<Usuario, UsuarioService> {

	private static final long serialVersionUID = 798122193214715935L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	@Override
	public void limpar() {
		entidade = new Usuario();
		setEditando_registro(false);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public UsuarioService getService() {
		return new UsuarioService();
	}

}
