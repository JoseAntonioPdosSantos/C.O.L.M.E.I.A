package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.imp.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuarioController extends Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioService service;
	private Usuario usuario;
	private List<Usuario> usuarios;

	public UsuarioController() {
		usuario = getCurrentInstanceUser();
		if(usuario==null)
			usuario = new Usuario();
		service = new UsuarioService();
		try {
			usuarios = service.buscarTodos();
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void gravar() {
		if (validarEntity()) {
			try {
				service.gravar(usuario);
				usuarios = service.buscarTodos();
				message(SUCCESS_RECORD);
			} catch (Exception e) {
				message(FAILURE_RECORD);
			}
		}
	}

	public void alterar() {
		if (validarEntity()) {
			try {
				service.alterar(usuario);
				message(SUCCESS_UPDATE);
			} catch (Exception e) {
				message(FAILURE_UPDATE);
			}
		}
	}

	public void apagar() {
		if (validarEntity()) {
			try {
				service.apagar(usuario);
				message(SUCCESS_DELETE);
			} catch (Exception e) {
				message(FAILURE_DELETE);
			}
		}
	}

	public void buscarTodos() {
		try {
			service.buscarTodos();
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public void buscarPorId() {
		try {
			usuario = service.buscarPorId(usuario.getId());
			if (usuario == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public boolean validarEntity() {
		message(FAILURE_FILL_DATA);
		return false;
	}

	@Override
	public void buscar() {
		try {
			usuarios = service.buscar(usuario);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String voltar() {
		return "";
	}
}
