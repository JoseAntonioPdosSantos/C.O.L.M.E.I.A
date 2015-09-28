package br.com.colmeia.controller.implementacao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import static br.com.colmeia.controller.util.Message.*;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Ingresso;
import br.com.colmeia.model.persistence.entity.Palestrante;
import br.com.colmeia.model.persistence.entity.Sala;
import br.com.colmeia.model.persistence.service.implementacao.AtividadeEventoService;
import br.com.colmeia.model.persistence.service.implementacao.IngressoService;
import br.com.colmeia.model.persistence.service.implementacao.PalestranteService;
import br.com.colmeia.model.persistence.service.implementacao.SalaService;

@ManagedBean
@ViewScoped
public class AtividadeEventoController extends Controller<AtividadeEvento, AtividadeEventoService> {

	private static final long serialVersionUID = 1L;
	private Evento evento;
	private List<AtividadeEvento> atividadeEventos;
	private boolean cadastrar_atividade;
	private List<Palestrante> palestrantes;
	private List<Ingresso> ingressos;
	private List<Sala> salas;

	@Override
	protected void inicializarVariavel() {
		limpar();
		getEntidade().setEvento(evento);
		buscarPalestrantes();
		buscarIngressos();
		buscarSalas();
	}

	private void buscarSalas() {
		Sala sala = new Sala();
		sala.setAtivo(true);
		try {
			salas = new SalaService().buscar(sala);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
		
	}

	private void buscarIngressos() {
		Ingresso ingresso = new Ingresso();
		ingresso.setAtivo(true);
		try {
			ingressos = new IngressoService().buscar(ingresso);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	private void buscarPalestrantes() {
		Palestrante palestrante = new Palestrante();
		palestrante.setAtivo(true);
		try {
			palestrantes = new PalestranteService().buscar(palestrante);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void gravar() {
		try {
			entidade.setId(null);
			entidade.setEvento(getEvento());
			System.out.println(entidade.getSala());
			getService().gravar(getEntidade());
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

	public void setService(AtividadeEventoService service) {
		this.service = service;
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

	public List<Palestrante> getPalestrantes() {
		return palestrantes;
	}

	public void setPalestrantes(List<Palestrante> palestrantes) {
		this.palestrantes = palestrantes;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

}
