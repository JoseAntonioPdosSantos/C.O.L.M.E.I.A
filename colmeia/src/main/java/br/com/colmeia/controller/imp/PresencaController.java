package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.imp.AtividadeEventoService;
import br.com.colmeia.model.persistence.service.imp.EventoService;
import br.com.colmeia.model.persistence.service.imp.UsuarioEventoService;
import br.com.colmeia.model.persistence.service.imp.UsuarioService;

@ManagedBean
@ViewScoped
public class PresencaController extends Controller<UsuarioEvento> {

	private static final long serialVersionUID = 1L;
	private UsuarioEventoService service;

	// Aba Eventos
	private Evento evento;
	private List<Evento> eventos;
	private boolean eventos_size_maior_q_zero;

	// Aba de Atividades do Evento Selecionado
	private AtividadeEvento atividadeEvento;
	private List<AtividadeEvento> atividadesEvento;
	private boolean aba_atividades_evento;// Torna a Aba de Atividades Visível
	private boolean atividades_evento_size_maior_q_zero;

	// Aba de Inscritos na Atividade do Evento Selecionado
	private UsuarioEvento usuarioEvento;
	private List<UsuarioEvento> usuariosEventos;
	private Usuario usuario;
	private boolean aba_presenca;// Torna a Aba de Check-In

	public PresencaController() {
		service = new UsuarioEventoService();
		buscarEventos();
	}

	// public void irParaAbaDePresenca(AtividadeEvento atividadeEevento) {
	// setAtividadeEvento(atividadeEvento);
	// buscarAtividadeEventos();
	// aba_presenca = true;
	// }

	public void irParaAbaAtividadesDoEvento(Evento evento) {
		setAba_atividades_evento(true);
		setAba_presenca(false);
		setEvento(evento);
		buscarAtividadeEventos();
	}

	private void buscarAtividadeEventos() {
		atividadeEvento = new AtividadeEvento();
		atividadeEvento.setAtivo(true);
		atividadeEvento.setEvento(getEvento());
		try {
			atividadesEvento = AtividadeEventoService.class.newInstance().buscar(atividadeEvento);
			if (atividadesEvento != null && atividadesEvento.size() > 0) {
				setAtividades_evento_size_maior_q_zero(true);
			} else {
				setAtividades_evento_size_maior_q_zero(false);
			}
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void irParaAbaCkeckIn(AtividadeEvento atividadeEvento) {
		setAba_presenca(true);
		setAtividadeEvento(atividadeEvento);
		usuarioEvento = new UsuarioEvento();
		usuarioEvento.setUsuario(usuario);
		buscarUsuariosEventos();
	}

	private void buscarEventos() {
		try {
			eventos = EventoService.class.newInstance().buscarTodos();
			if (eventos != null && eventos.size() > 0) {
				setEventos_size_maior_q_zero(true);
			} else {
				setEventos_size_maior_q_zero(false);
				message(FacesMessage.SEVERITY_INFO, "Evento", "Não há eventos cadastrados");
			}
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	private void buscarUsuariosEventos() {
		usuarioEvento = new UsuarioEvento();
		usuarioEvento.setAtivo(true);
		usuarioEvento.setAtividadeEvento(getAtividadeEvento());
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

	private void buscarUsuario(){
//		usuario = UsuarioService.class.newInstance().buscar(usuario);
	}
	
	@Override
	public void buscar() {
		try {
			usuarioEvento.setAtividadeEvento(getAtividadeEvento());
			usuarioEvento.setAtivo(true);
			usuarioEvento.setUsuario(getUsuario());
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

	public List<UsuarioEvento> getUsuariosEventos() {
		return usuariosEventos;
	}

	public AtividadeEvento getAtividadeEvento() {
		return atividadeEvento;
	}

	public void setAtividadeEvento(AtividadeEvento atividadeEvento) {
		this.atividadeEvento = atividadeEvento;
	}

	public List<AtividadeEvento> getAtividadesEvento() {
		return atividadesEvento;
	}

	public void setAtividadesEvento(List<AtividadeEvento> atividadesEvento) {
		this.atividadesEvento = atividadesEvento;
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

	public boolean isEventos_size_maior_q_zero() {
		return eventos_size_maior_q_zero;
	}

	public void setEventos_size_maior_q_zero(boolean eventos_size_maior_q_zero) {
		this.eventos_size_maior_q_zero = eventos_size_maior_q_zero;
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

	public boolean isAba_atividades_evento() {
		return aba_atividades_evento;
	}

	public void setAba_atividades_evento(boolean aba_atividades_evento) {
		this.aba_atividades_evento = aba_atividades_evento;
	}

	public boolean isAtividades_evento_size_maior_q_zero() {
		return atividades_evento_size_maior_q_zero;
	}

	public void setAtividades_evento_size_maior_q_zero(boolean atividades_evento_size_maior_q_zero) {
		this.atividades_evento_size_maior_q_zero = atividades_evento_size_maior_q_zero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
