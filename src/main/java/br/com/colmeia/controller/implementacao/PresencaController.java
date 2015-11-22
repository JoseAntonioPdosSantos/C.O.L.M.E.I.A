package br.com.colmeia.controller.implementacao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import static br.com.colmeia.controller.util.Message.*;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.service.implementacao.AtividadeEventoService;
import br.com.colmeia.model.service.implementacao.EventoService;
import br.com.colmeia.model.service.implementacao.UsuarioEventoService;

@ManagedBean
@ViewScoped
public class PresencaController extends Controller<UsuarioEvento,UsuarioEventoService> {

	private static final long serialVersionUID = 2696411785475048450L;
	// Aba Eventos
	private Evento evento;
	private List<Evento> eventos;
	private boolean eventos_size_maior_q_zero;

	// Aba de Atividades do Evento Selecionado
	private AtividadeEvento atividadeEvento;
	private List<AtividadeEvento> atividadesEvento;
	private boolean aba_atividades_evento;// Torna a Aba de Atividades Visível
	private boolean atividades_evento_size_maior_q_zero;

	private Usuario usuario;
	private boolean aba_presenca;// Torna a Aba de Check-In

	@Override
	protected void inicializarVariavel() {
		limpar();
		buscarEventos();
	}
	
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
		usuario = new Usuario();
		entidade = new UsuarioEvento();
		System.out.println(usuario);
		System.out.println(entidade);
		buscarUsuariosEventos();
	}

	private void buscarEventos() {
		try {
			evento = new Evento();
			evento.setAtivo(true);
			eventos = new EventoService().buscar(evento);
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
		entidade = new UsuarioEvento();
		entidade.setAtivo(true);
		entidade.setAtividadeEvento(getAtividadeEvento());
		try {
			entidades = getService().buscar(entidade);
			if (entidades != null && entidades.size() > 0) {
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
			usuarioEvento = getService().registrarPresenca(usuarioEvento);
			message(FacesMessage.SEVERITY_INFO, "Registro de Presença", "Presença Registrada com Sucesso");
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void limpar() {
		setEntidade(new UsuarioEvento());
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			entidades = getService().buscarUsuarioEventoPorUsuarioEAtividadeEvento(usuario,atividadeEvento);
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
