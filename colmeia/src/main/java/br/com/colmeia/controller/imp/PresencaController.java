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
public class PresencaController extends Controller<UsuarioEvento> {

	private static final long serialVersionUID = 1L;
	private UsuarioEventoService service;
	private UsuarioEvento usuarioEvento;
	private Evento evento;
	private List<Evento> eventos;
	private List<UsuarioEvento> usuariosEventos;
	private boolean aba_presenca = false;

	public PresencaController() {
		service = new UsuarioEventoService();
		usuarioEvento = new UsuarioEvento();
		usuarioEvento.setUsuario(new Usuario());
		buscarEventos();
		buscar();
	}

	public void irParaAbaDePresenca(Evento evento) {
		setEvento(evento);
		buscarUsuariosEventos();
		aba_presenca = true;
	}

	private void buscarEventos() {
		try {
			eventos = EventoService.class.newInstance().buscarTodos();
			if (eventos != null && eventos.size() > 0) {
				setSize_maior_q_zero(true);
			} else {
				setSize_maior_q_zero(false);
				message(FacesMessage.SEVERITY_INFO, "Evento", "Não há eventos cadastrados");
			}
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	private void buscarUsuariosEventos() {
		UsuarioEvento usuarioEvento = new UsuarioEvento();
		usuarioEvento.setAtivo(true);
		usuarioEvento.setEvento(getEvento());
		try {
			usuariosEventos = UsuarioEventoService.class.newInstance().buscar(usuarioEvento);
			if (usuariosEventos != null && usuariosEventos.size() > 0) {
				setSize_maior_q_zero(true);
			} else {
				setSize_maior_q_zero(false);
			}
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void desmarcarPresenca(UsuarioEvento usuarioEvento) {
		try {
			usuarioEvento = service.desmarcarPresenca(usuarioEvento);
			message(FacesMessage.SEVERITY_INFO, "Registro de Presença", "Presença Desmarcada com Sucesso");
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void marcarPresenca(UsuarioEvento usuarioEvento) {
		try {
			usuarioEvento = service.registrarPresenca(usuarioEvento);
			message(FacesMessage.SEVERITY_INFO, "Registro de Presença", "Presença Registrada com Sucesso");
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void gravar() {
		try {
			usuarioEvento.setId(null);
			service.gravar(usuarioEvento);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void alterar() {
		try {
			service.alterar(usuarioEvento);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void apagar(UsuarioEvento usuarioEvento) {
		try {
			service.apagar(usuarioEvento);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void limpar() {
		usuarioEvento = new UsuarioEvento();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			usuariosEventos = service.buscar(usuarioEvento);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public UsuarioEventoService getService() {
		return service;
	}

	public void setService(UsuarioEventoService service) {
		this.service = service;
	}

	public UsuarioEvento getUsuarioEvento() {
		return usuarioEvento;
	}

	public void setUsuarioEvento(UsuarioEvento usuarioEvento) {
		this.usuarioEvento = usuarioEvento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<UsuarioEvento> getUsuariosEventos() {
		return usuariosEventos;
	}

	public void setUsuariosEventos(List<UsuarioEvento> usuariosEventos) {
		this.usuariosEventos = usuariosEventos;
	}

	public boolean isAba_presenca() {
		return aba_presenca;
	}

	public void setAba_presenca(boolean aba_presenca) {
		this.aba_presenca = aba_presenca;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
