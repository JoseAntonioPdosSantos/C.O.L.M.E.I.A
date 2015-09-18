package br.com.colmeia.controller.implementacao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.service.implementacao.AtividadeEventoService;

@ManagedBean
@ViewScoped
public class AtividadeEventoController extends Controller<AtividadeEvento, AtividadeEventoService> {

	private static final long serialVersionUID = 1L;
	private AtividadeEventoService service;
	private Evento evento;
	private List<AtividadeEvento> atividadeEventos;
	private boolean cadastrar_atividade;

	@Override
	protected void inicializarVariavel() {
		limpar();
		getEntidade().setEvento(evento);
	}

	public void gravar() {
		try {
			entidade.setId(null);
			entidade.setEvento(getEvento());
			service.gravar(getEntidade());
			entidade = new AtividadeEvento();
			entidade.setEvento(evento);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void limpar() {
		entidade = new AtividadeEvento();
		setEditando_registro(false);
	}

	public List<AtividadeEvento> getAtividadeEventos() {
		return atividadeEventos;
	}

	public void setAtividadeEventos(List<AtividadeEvento> atividadeEventos) {
		this.atividadeEventos = atividadeEventos;
	}

	public AtividadeEventoService getService() {
		return new AtividadeEventoService();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		if (evento != null)
			cadastrar_atividade = true;
		else
			cadastrar_atividade = false;
		setEditando_registro(false);
		entidade = new AtividadeEvento();
		entidade.setAtivo(true);
		entidade.setEvento(evento);
		buscar();
		this.evento = evento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isCadastrar_atividade() {
		return cadastrar_atividade;
	}

	public void setCadastrar_atividade(boolean cadastrar_atividade) {
		this.cadastrar_atividade = cadastrar_atividade;
	}

}
