package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Perfil;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.imp.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuarioController extends Controller<Usuario> {

	private static final long serialVersionUID = 1L;
	private UsuarioService service;
	private Usuario usuario;
	private List<Usuario> usuarios;

	public UsuarioController() {
		setUsuario(getCurrentInstanceUser());
		if (getUsuario() == null)
			setUsuario(new Usuario());
		service = new UsuarioService();
		buscar();
	}

	public void gravar() {
		try {
			service.gravar(getUsuario());
			usuarios = service.buscarTodos();
			setUsuario(new Usuario());
			buscar();
			limpar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public String novoUsuario() {
		try {
			service.gravar(getUsuario());
		} catch (Exception e) {
			message(ERROR, e.getMessage());
			return "";
		}
		return "/pages/login/login.xhtml";
	}

	public void alterar() {
		try {
			service.alterar(getUsuario());
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void apagar(Usuario usuario) {
		try {
			service.apagar(usuario);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void limpar() {
		setUsuario(new Usuario());
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			usuarios = service.buscar(usuario);
			if (usuarios != null)
				setSize_maior_q_zero(usuarios.size() > 0 ? true : false);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void cadastrarPerfilCoordenador(Usuario usuario) {
		usuario.setPerfil(Perfil.COORDENADOR);
		setUsuario(usuario);
		alterar();
	}

	public void cadastrarPerfilOrganizador(Usuario usuario) {
		usuario.setPerfil(Perfil.ORGANIZADOR);
		setUsuario(usuario);
		alterar();
	}

	public void cadastrarPerfilUsuario(Usuario usuario) {
		usuario.setPerfil(Perfil.USUARIO);
		setUsuario(usuario);
		alterar();
	}

	public void cadastrarPerfilAdministrador(Usuario usuario) {
		usuario.setPerfil(Perfil.ADMINISTRADOR);
		setUsuario(usuario);
		alterar();
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
		setEditando_registro(true);
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
