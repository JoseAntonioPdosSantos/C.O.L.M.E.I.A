package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.implementacao.UsuarioService;

@ManagedBean
@ViewScoped
public class TopMenuController extends Controller<Usuario, UsuarioService> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	public String deslogar() {
//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
		FacesContext fc = FacesContext.getCurrentInstance();  
	       HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);  
	       session.invalidate();
		return "index";
	}

	@Override
	public void limpar() {
		entidade = Controller.getCurrentInstanceUser();
		setEditando_registro(false);
	}

	@Override
	public UsuarioService getService() {
		return new UsuarioService();
	}

	@Override
	public void setService(UsuarioService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
