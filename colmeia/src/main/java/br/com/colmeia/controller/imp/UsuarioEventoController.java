package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.imp.EventoService;
import br.com.colmeia.model.persistence.service.imp.UsuarioEventoService;

@ManagedBean
@ViewScoped
public class UsuarioEventoController extends Controller<UsuarioEvento> {

	private static final long serialVersionUID = 1L;
	private UsuarioEventoService service;
	private Usuario usuario;
	private UsuarioEvento usuarioEvento;
	private List<UsuarioEvento> usuarioEventos;
	private List<Evento> eventosVigentes;
	private List<Evento> eventosEncerrados;
	private boolean evento_vigente_size_maior_q_zero;
	private boolean evento_encerrado_size_maior_q_zero;

	public UsuarioEventoController() {
		usuario = getCurrentInstanceUser();
		if (usuario == null) {
			message(ERROR_UNEXPECTED);
			return;
		}
		service = new UsuarioEventoService();
		buscarEventosVigentes();
		buscarEventosEncerrados();
		carregarUsuarioEventos();
	}

	private void buscarEventosEncerrados() {
		try {
			eventosEncerrados = new EventoService().getClass().newInstance().buscarEventosEncerrados();
			if (eventosEncerrados.size() > 0) {
				evento_encerrado_size_maior_q_zero = true;
			} else {
				evento_encerrado_size_maior_q_zero = false;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			message(ERROR, e.getMessage());
		}
	}

	private void buscarEventosVigentes() {
		try {
			eventosVigentes = new EventoService().getClass().newInstance().buscarTodos();
			if (eventosVigentes.size() > 0) {
				evento_vigente_size_maior_q_zero = true;
			} else {
				evento_vigente_size_maior_q_zero = false;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			message(ERROR, e.getMessage());
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void inscrever(Evento evento) {
		boolean eventoCadastrado = service.isCadastrado(usuario, evento);
		if (eventoCadastrado) {
			message(FacesMessage.SEVERITY_INFO, "Cadastro", "Evento já cadastrado");
		} else {
			cadastrarEvento(evento);
			carregarUsuarioEventos();
		}
	}

	public void carregarUsuarioEventos() {
		try {
			usuarioEvento = new UsuarioEvento();
			usuarioEvento.setUsuario(usuario);
			usuarioEvento.setAtivo(true);
			usuarioEventos = service.buscar(usuarioEvento);
			if (usuarioEventos != null && usuarioEventos.size() > 0) {
				setSize_maior_q_zero(true);
			} else {
				setSize_maior_q_zero(false);
			}
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	private void cadastrarEvento(Evento evento) {
		usuarioEvento = new UsuarioEvento();
		usuarioEvento.setEvento(evento);
		usuarioEvento.setUsuario(usuario);
		gravar();
	}

	public void cancelarEvento(UsuarioEvento usuarioEvento) {
		boolean eventoCancelado = false;
		try {
			eventoCancelado = service.cancelarEvento(usuarioEvento);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
		if (eventoCancelado) {
			message(FacesMessage.SEVERITY_INFO, "Cancelado", "Evento cancelado com sucesso");
			carregarUsuarioEventos();
		}
	}

	public void gravar() {
		try {
			usuarioEvento.setUsuario(usuario);
			usuarioEvento.setAtivo(true);
			service.gravar(usuarioEvento);
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void alterar() {
	}

	public void apagar(UsuarioEvento usuarioEvento) {
	}

	public void limpar() {
	}

	@Override
	public void buscar() {
	}

	public UsuarioEventoService getService() {
		return service;
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

	public UsuarioEvento getUsuarioEvento() {
		return usuarioEvento;
	}

	public void setUsuarioEvento(UsuarioEvento usuarioEvento) {
		this.usuarioEvento = usuarioEvento;
	}

	public List<UsuarioEvento> getUsuarioEventos() {
		return usuarioEventos;
	}

	public void setUsuarioEventos(List<UsuarioEvento> usuarioEventos) {
		this.usuarioEventos = usuarioEventos;
	}

	public List<Evento> getEventosVigentes() {
		return eventosVigentes;
	}

	public void setEventosVigentes(List<Evento> eventosVigentes) {
		this.eventosVigentes = eventosVigentes;
	}

	public List<Evento> getEventosEncerrados() {
		return eventosEncerrados;
	}

	public void setEventosEncerrados(List<Evento> eventosEncerrados) {
		this.eventosEncerrados = eventosEncerrados;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isEvento_vigente_size_maior_q_zero() {
		return evento_vigente_size_maior_q_zero;
	}

	public void setEvento_vigente_size_maior_q_zero(boolean evento_vigente_size_maior_q_zero) {
		this.evento_vigente_size_maior_q_zero = evento_vigente_size_maior_q_zero;
	}

	public boolean isEvento_encerrado_size_maior_q_zero() {
		return evento_encerrado_size_maior_q_zero;
	}

	public void setEvento_encerrado_size_maior_q_zero(boolean evento_encerrado_size_maior_q_zero) {
		this.evento_encerrado_size_maior_q_zero = evento_encerrado_size_maior_q_zero;
	}

}
