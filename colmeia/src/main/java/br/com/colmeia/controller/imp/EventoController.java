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
public class EventoController extends Controller{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EventoService service;
	private Evento evento;
	private List<Evento> eventos;

	public EventoController() {
		service = new EventoService();
		evento = new Evento();
		try {
			eventos = service.buscarTodos();
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void gravar() {
			try {
				service.gravar(evento);
				message(SUCCESS_RECORD);
			} catch (Exception e) {
				message(FAILURE_RECORD);
			}
		}

	public void alterar() {
			try {
				service.alterar(evento);
				message(SUCCESS_UPDATE);
			} catch (Exception e) {
				message(FAILURE_UPDATE);
			}
	}

	public void apagar() {
			try {
				service.apagar(evento);
				message(SUCCESS_DELETE);
			} catch (Exception e) {
				message(FAILURE_DELETE);
			}
	}

	public void buscarTodos() {
		try {
			service.buscarTodos();
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public void buscarPorId()  {
		try {
			evento = service.buscarPorId(evento.getId());
			if(evento == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public void buscar() {
		try {
			eventos = service.buscar(evento);
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

	public String voltar(){
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
		this.evento = evento;
	}

}
