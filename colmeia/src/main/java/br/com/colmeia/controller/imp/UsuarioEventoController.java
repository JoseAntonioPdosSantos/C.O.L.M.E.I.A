package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.imp.AtividadeEventoService;
import br.com.colmeia.model.persistence.service.imp.EventoService;
import br.com.colmeia.model.persistence.service.imp.UsuarioEventoService;

@ManagedBean
@ViewScoped
public class UsuarioEventoController extends Controller<UsuarioEvento> {

	private static final long serialVersionUID = 1L;
	private UsuarioEventoService service;
	private Usuario usuario;

	// Aba Eventos Vigente
	private Evento evento;
	private List<Evento> eventosVigentes;
	private boolean evento_vigente_size_maior_q_zero;

	// Aba Eventos Encerrados
	private List<Evento> eventosEncerrados;
	private boolean evento_encerrado_size_maior_q_zero;

	// Aba de Atividades de Eventos Vigentes
	private AtividadeEvento atividadeEvento;
	private List<AtividadeEvento> atividadesEvento;
	private boolean atividade_evento_vigente_size_maior_q_zero;
	
	// Aba de Atividades de Eventos Encerrados
	private boolean atividade_evento_encerrado_size_maior_q_zero;

	//Atividades Inscritas pelo Usuário
	private UsuarioEvento usuarioEvento;
	private List<UsuarioEvento> usuarioEventos;

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
				setEvento_encerrado_size_maior_q_zero(true);
			} else {
				setEvento_encerrado_size_maior_q_zero(false);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			message(ERROR, e.getMessage());
		}
	}

	private void buscarEventosVigentes() {
		try {
			eventosVigentes = new EventoService().getClass().newInstance().buscarTodos();
			if (eventosVigentes.size() > 0) {
				setEvento_vigente_size_maior_q_zero(true);
			} else {
				setEvento_vigente_size_maior_q_zero(false);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			message(ERROR, e.getMessage());
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void buscarAtividadesDesteEvento(Evento evento) {
		setEvento(evento);
		atividadeEvento = new AtividadeEvento();
		atividadeEvento.setAtivo(true);
		atividadeEvento.setEvento(getEvento());
		try {
			atividadesEvento = AtividadeEventoService.class.newInstance().buscar(atividadeEvento);
			if (atividadesEvento != null && atividadesEvento.size() > 0) {
				setAtividade_evento_vigente_size_maior_q_zero(true);
			} else {
				setAtividade_evento_vigente_size_maior_q_zero(false);
			}
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
		
		RequestContext.getCurrentInstance().openDialog("cadastro_atividade_evento");
	}

	public void onCarChosen(SelectEvent event) {
        UsuarioEvento UsuarioEvento = (UsuarioEvento) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Selected", "Id:" );
         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void inscrever(AtividadeEvento atividadeEvento) {
		try {
			service.inscreverEmUmaAtividadeDeEvento(atividadeEvento, usuario);
		} catch (Exception e) {
			message(WORNING, e.getMessage());
		}
		carregarUsuarioEventos();
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

	public void cancelarEvento(UsuarioEvento usuarioEvento) {
		boolean eventoCancelado = false;
		try {
			eventoCancelado = service.cancelarEvento(usuarioEvento);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
		if (eventoCancelado) {
			message(FacesMessage.SEVERITY_INFO, "Cancelado", "Evento cancelado com sucesso");
		} else {
			message(ERROR_UNEXPECTED);
		}
		carregarUsuarioEventos();
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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public boolean isAtividade_evento_encerrado_size_maior_q_zero() {
		return atividade_evento_encerrado_size_maior_q_zero;
	}

	public void setAtividade_evento_encerrado_size_maior_q_zero(boolean atividade_evento_encerrado_size_maior_q_zero) {
		this.atividade_evento_encerrado_size_maior_q_zero = atividade_evento_encerrado_size_maior_q_zero;
	}

	public boolean isAtividade_evento_vigente_size_maior_q_zero() {
		return atividade_evento_vigente_size_maior_q_zero;
	}

	public void setAtividade_evento_vigente_size_maior_q_zero(boolean atividade_evento_vigente_size_maior_q_zero) {
		this.atividade_evento_vigente_size_maior_q_zero = atividade_evento_vigente_size_maior_q_zero;
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

}
