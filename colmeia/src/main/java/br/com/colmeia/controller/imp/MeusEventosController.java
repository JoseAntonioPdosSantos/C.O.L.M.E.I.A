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
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.imp.UsuarioEventoService;

/**
 *
 * @author CaioAvell G1511 FIRE
 */
@ManagedBean
@ViewScoped
public class MeusEventosController extends Controller<Evento> {

	private static final long serialVersionUID = 1L;
	private UsuarioEventoService service;
	private Usuario usuario;
	private UsuarioEvento usuarioEvento;
	private List<UsuarioEvento> usuarioEventos;

	public MeusEventosController() {
		service = new UsuarioEventoService();
		usuario = getCurrentInstanceUser();
		usuarioEvento = new UsuarioEvento();
		buscar();
	}

	public void gravar() {
	}

	public void cancelarEvento(UsuarioEvento usuarioEvento) {
		try {
			UsuarioEventoController.class.newInstance().cancelarEvento(usuarioEvento);
		} catch (InstantiationException | IllegalAccessException e) {
			message(ERROR, e.getMessage());
		}
	}

	public void alterar() {
	}

	public void apagar(Evento evento) {
	}

	public void limpar() {
	}

	@Override
	public void buscar() {
		try {
			usuarioEvento.setUsuario(usuario);
			usuarioEvento.setAtivo(true);
			usuarioEventos = service.buscar(usuarioEvento);
			if (usuarioEventos != null)
				setSize_maior_q_zero(usuarioEventos.size() > 0 ? true : false);
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
