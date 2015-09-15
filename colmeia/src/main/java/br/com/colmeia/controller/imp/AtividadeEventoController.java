/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.AtividadeEvento;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.service.imp.AtividadeEventoService;

/**
 *
 * @author José Antonio
 */
@ManagedBean
@ViewScoped
public class AtividadeEventoController extends Controller<AtividadeEvento> {

	private static final long serialVersionUID = 1L;
	private AtividadeEventoService service;
	private Evento evento;
	private AtividadeEvento atividadeEvento;
	private List<AtividadeEvento> atividadeEventos;
	private boolean cadastrar_atividade;

	public AtividadeEventoController() {
		service = new AtividadeEventoService();
		setAtividadeEvento(new AtividadeEvento());
		buscar();
	}

	public void gravar() {
		try {
			atividadeEvento.setId(null);
			atividadeEvento.setEvento(getEvento());
			service.gravar(getAtividadeEvento());
			atividadeEvento = new AtividadeEvento();
			atividadeEvento.setEvento(evento);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void alterar() {
		try {
			service.alterar(atividadeEvento);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void apagar(AtividadeEvento atividadeEvento) {
		try {
			service.apagar(atividadeEvento);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void limpar() {
		atividadeEvento = new AtividadeEvento();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			atividadeEventos = service.buscar(atividadeEvento);
			if (atividadeEventos != null)
				setSize_maior_q_zero(atividadeEventos.size() > 0 ? true : false);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public List<AtividadeEvento> getAtividadeEventos() {
		return atividadeEventos;
	}

	public void setAtividadeEventos(List<AtividadeEvento> atividadeEventos) {
		this.atividadeEventos = atividadeEventos;
	}

	public AtividadeEventoService getService() {
		return service;
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
		atividadeEvento.setEvento(evento);
		buscar();
		this.evento = evento;
	}

	public AtividadeEvento getAtividadeEvento() {
		return atividadeEvento;
	}

	public void setAtividadeEvento(AtividadeEvento atividadeEvento) {
		setEditando_registro(true);
		this.atividadeEvento = atividadeEvento;
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
