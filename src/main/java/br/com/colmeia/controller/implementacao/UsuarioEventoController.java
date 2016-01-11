package br.com.colmeia.controller.implementacao;

import java.util.List;

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
public class UsuarioEventoController extends Controller<UsuarioEvento, UsuarioEventoService> {

	private static final long serialVersionUID = 6113937366231935225L;

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

	@Override
	protected void inicializarVariavel() {
		usuario = getCurrentInstanceUser();
		buscarEventosVigentes();
		buscarEventosEncerrados();
		carregarAtividadesInscritasDoUsuario();
		limpar();
	}

	private void buscarEventosEncerrados() {
		eventosEncerrados = new EventoService().buscarEventosEncerrados();
		if (eventosEncerrados.size() > 0) {
			setEvento_encerrado_size_maior_q_zero(true);
		} else {
			setEvento_encerrado_size_maior_q_zero(false);
		}
	}

	private void buscarEventosVigentes() {
			eventosVigentes = new EventoService().buscarEventosVigentes();
			if (eventosVigentes.size() > 0) {
				setEvento_vigente_size_maior_q_zero(true);
			} else {
				setEvento_vigente_size_maior_q_zero(false);
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
		carregarAtividadesInscritasDoUsuario();
	}

	public void inscrever(AtividadeEvento atividadeEvento) {
		try {
			String mensage = getService().inscreverEmUmaAtividadeDeEvento(atividadeEvento, usuario);
			message(SUCCESS,mensage);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
		carregarAtividadesInscritasDoUsuario();
	}

	public void carregarAtividadesInscritasDoUsuario() {
		setEntidade(new UsuarioEvento());
		entidade.setUsuario(usuario);
		super.buscar();
		if (atividadesEvento != null) {
			for (AtividadeEvento ae : atividadesEvento) {
				boolean axou = false;
				for (UsuarioEvento usu : entidades) {
					if (usu.getAtividadeEvento().equals(ae)) {
						axou = true;
						break;
					}
				}
				if (axou)
					ae.setUsuarioInscrito(true);
				else
					ae.setUsuarioInscrito(false);

			}
		}
	}

	public void limpar() {
		entidade = new UsuarioEvento();
		setEditando_registro(false);
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
