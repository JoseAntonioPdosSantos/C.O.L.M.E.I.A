package br.com.colmeia.controller.implementacao;

import java.io.Serializable;

import static br.com.colmeia.controller.util.Message.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.implementacao.UsuarioService;

@ManagedBean
@RequestScoped
public class Login implements Serializable {

	private static final long serialVersionUID = -2449843197655946960L;
	private UsuarioService service;
	private Usuario usuario;

	public Login() {
		usuario = new Usuario();
		service = new UsuarioService();
	}

	public String doLogin() {
		if (usuario != null) {
			if (usuario.getCpf() != null && usuario.getSenha() != null) {
				try {
					usuario = new UsuarioService().isUsuario(usuario);
				} catch (Exception e) {
					message(ERROR, e.getMessage());
					return "";
				}
				if (usuario != null) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
					return "usuario";
				}
			}
		}
		message(FAILURE_LOGIN);
		return "";
	}

	public Usuario getCurrentUser() {
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}

	public void validatorLoginAndPassword(FacesContext arg0, UIComponent arg1, Object arg2) {
		usuario.setCpf(getUsuario().getCpfUI().getLocalValue().toString());
		usuario.setSenha(arg2.toString());
	}

	public String esqueciMinhaSenha() {
		return "i_forget_my_password";
	}

	public String cadastrar() {
		return "register_user";
	}

	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
