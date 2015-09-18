package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.implementacao.UsuarioEventoService;

@ManagedBean
@ViewScoped
public class MeusEventosController extends Controller<UsuarioEvento, UsuarioEventoService> {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	@Override
	protected void inicializarVariavel() {
		usuario = getCurrentInstanceUser();
		limpar();
	}

	public void cancelarEvento(UsuarioEvento usuarioEvento) {
		try {
			UsuarioEventoService.class.newInstance().cancelarEvento(usuarioEvento);
			buscar();
		} catch (InstantiationException | IllegalAccessException e) {
			message(ERROR, e.getMessage());
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void limpar() {
		entidade = new UsuarioEvento();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			entidade.setUsuario(usuario);
			entidade.setAtivo(true);
			entidades = service.buscar(entidade);
			if (entidades != null)
				setSize_maior_q_zero(entidades.size() > 0 ? true : false);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public UsuarioEventoService getService() {
		return new UsuarioEventoService();
	}

	public void setService(UsuarioEventoService service) {
		this.service = service;
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
