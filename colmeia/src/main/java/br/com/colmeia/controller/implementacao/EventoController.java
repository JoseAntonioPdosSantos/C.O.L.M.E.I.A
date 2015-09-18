/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.implementacao.EventoService;

@ManagedBean
@ViewScoped
public class EventoController extends Controller<Evento, EventoService> {

	private static final long serialVersionUID = 1L;
	private Usuario coordenador;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	public void gravar() {
		entidade.setId(null);
		entidade.setCoordenador(coordenador);
		super.gravar();
	}

	public void limpar() {
		entidade = new Evento();
		setEditando_registro(false);
	}

	public EventoService getService() {
		return new EventoService();
	}

	public void setService(EventoService service) {
		this.service = service;
	}

	public Usuario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
