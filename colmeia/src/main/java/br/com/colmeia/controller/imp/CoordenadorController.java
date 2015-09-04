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
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.imp.CoordenadorService;
import br.com.colmeia.model.utils.HibernateUtil;

/**
 *
 * @author CaioAvell G1511 FIRE
 */
@ManagedBean
@ViewScoped
public class CoordenadorController extends Controller<Coordenador> {

	private static final long serialVersionUID = 1L;
	private CoordenadorService service;
	private Coordenador coordenador;
	private List<Coordenador> coordenadores;

	public CoordenadorController() {
		service = new CoordenadorService();
		coordenador = new Coordenador();
		buscarTodos();
	}

	public void gravarCoordenador(Usuario usuario) {
		if (usuario != null) {
			if (coordenador == null)
				coordenador = new Coordenador();
			coordenador.setId(usuario.getId());
			coordenador.setNome(usuario.getNome());
			coordenador.setCpf(usuario.getCpf());
			coordenador.setEmail(usuario.getEmail());
			coordenador.setRa(usuario.getRa());
			coordenador.setSenha(usuario.getSenha());
			coordenador.setCurso(usuario.getCurso());
			coordenador.setData(usuario.getData());
			coordenador.setInstituicao(usuario.getInstituicao());
//			coordenador.setDataCadastroCoordenador(HibernateUtil.getCurrentDate());
		}
		try {
			service.alterar(coordenador);
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void gravar() {
		try {
			coordenador.setId(null);
			service.gravar(coordenador);
			buscarTodos();
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

	public void apagar(Coordenador coordenador) {
		try {
			service.apagar(coordenador);
			buscarTodos();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}

	public void buscarTodos() {
		try {
			coordenadores = service.buscarTodos();
			if (coordenadores != null)
				setSize_maior_q_zero(coordenadores.size() > 0 ? true : false);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void limpar() {
		coordenador = new Coordenador();
		setEditando_registro(false);
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
