package br.com.colmeia.controller.imp;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.colmeia.controller.generics.Message;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.imp.UsuarioService;

@ManagedBean
@ViewScoped
public class Login extends Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
					message(Message.ERROR_UNEXPECTED);
				}
				if (usuario != null) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
					return "user";
				}
			}
		}
		message(Message.FAILURE_LOGIN);
		return "";
	}

	public Usuario getCurrentUser() {
		return (Usuario) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("user");
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
