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
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.service.imp.EventoService;

/**
 *
 * @author CaioAvell G1511 FIRE
 */
@ManagedBean
@ViewScoped
public class EventoController extends Controller<Evento> {

	private static final long serialVersionUID = 1L;
	private EventoService service;
	private Evento evento;
	private List<Evento> eventos;

	public EventoController() {
		service = new EventoService();
		evento = new Evento();
		buscar();
	}

	public void gravar() {
		try {
			evento.setId(null);
			service.gravar(evento);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(evento);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar(Evento evento) {
		try {
			service.apagar(evento);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}

	public void limpar() {
		evento = new Evento();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			eventos = service.buscar(evento);
			if (eventos != null)
				setSize_maior_q_zero(eventos.size() > 0 ? true : false);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public String voltar() {
		return "";
	}

	public EventoService getService() {
		return service;
	}

	public void setService(EventoService service) {
		this.service = service;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		setEditando_registro(true);
		this.evento = evento;
	}

}
