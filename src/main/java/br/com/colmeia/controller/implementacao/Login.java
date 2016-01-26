package br.com.colmeia.controller.implementacao;

import static br.com.colmeia.controller.util.Message.ERROR;
import static br.com.colmeia.controller.util.Message.FAILURE_LOGIN;
import static br.com.colmeia.controller.util.Message.message;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.service.implementacao.UsuarioService;

@ManagedBean(name = "login")
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
					usuario.setConfirmarSenha(usuario.getSenha());
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
