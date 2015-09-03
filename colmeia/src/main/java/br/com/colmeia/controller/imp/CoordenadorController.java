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
import br.com.colmeia.model.persistence.entity.Coordenador;
import br.com.colmeia.model.persistence.service.imp.CoordenadorService;

/**
 *
 * @author CaioAvell G1511 FIRE
 */
@ManagedBean
@ViewScoped
public class CoordenadorController extends Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CoordenadorService service;
	private Coordenador coordenador;
	private List<Coordenador> coordenadores;

	public CoordenadorController() {
		service = new CoordenadorService();
		coordenador = new Coordenador();
		try {
			coordenadores = service.buscarTodos();
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void gravar() {
		try {
			service.gravar(coordenador);
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(coordenador);
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar() {
		try {
			service.apagar(coordenador);
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
	public void buscarPorId() {
		try {
			coordenador = service.buscarPorId(coordenador.getId());
			if (coordenador == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public void buscar() {
		try {
			coordenadores = service.buscar(coordenador);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public CoordenadorService getService() {
		return service;
	}

	public void setService(CoordenadorService service) {
		this.service = service;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public List<Coordenador> getCoordenadores() {
		return coordenadores;
	}

	public void setCoordenadores(List<Coordenador> coordenadores) {
		this.coordenadores = coordenadores;
	}

}
