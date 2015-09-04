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
		usuario = getCurrentInstanceUser();
		if (usuario == null)
			usuario = new Usuario();
		service = new UsuarioService();
		buscar();
	}

	public void gravar() {
		try {
			service.gravar(usuario);
			usuarios = service.buscarTodos();
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(usuario);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar(Usuario usurio) {
		try {
			service.apagar(usuario);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}
	
	public void limpar() {
		usuario = new Usuario();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			usuarios = service.buscar(usuario);
			if (usuarios != null)
				setSize_maior_q_zero(usuarios.size() > 0 ? true : false);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void cadastrarPerfilCoordenador(Usuario usuario){
		usuario.setPerfil(Perfil.COORDENADOR);
		this.usuario = usuario;
		alterar();
	}
	
	public void cadastrarPerfilOrganizador(Usuario usuario){
		usuario.setPerfil(Perfil.ORGANIZADOR);
		this.usuario = usuario;
		alterar();
	}
	
	public void cadastrarPerfilUsuario(Usuario usuario){
		usuario.setPerfil(Perfil.USUARIO);
		this.usuario = usuario;
		alterar();
	}
	
	public void cadastrarPerfilAdministrador(Usuario usuario){
		usuario.setPerfil(Perfil.ADMINISTRADOR);
		this.usuario = usuario;
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
