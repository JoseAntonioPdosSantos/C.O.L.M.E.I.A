package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Organizador;
import br.com.colmeia.model.persistence.service.imp.OrganizadorService;

@ManagedBean
@ViewScoped
public class OrganizadorController extends Controller<Organizador> {

	private static final long serialVersionUID = 1L;
	private OrganizadorService service;
	private Organizador organizador;
	private List<Organizador> organizadores;

	public OrganizadorController() {
		service = new OrganizadorService();
		organizador = new Organizador();
		buscar();
	}

	public void gravar() {
		try {
			organizador.setId(null);
			service.gravar(organizador);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(organizador);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar(Organizador organizador) {
		try {
			service.apagar(organizador);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}

	public void limpar() {
		organizador = new Organizador();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			organizadores = service.buscar(organizador);
			if (organizadores != null)
				setSize_maior_q_zero(organizadores.size() > 0 ? true : false);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public OrganizadorService getService() {
		return service;
	}

	public void setService(OrganizadorService service) {
		this.service = service;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}

	public List<Organizador> getOrganizadores() {
		return organizadores;
	}

	public void setOrganizadores(List<Organizador> organizadores) {
		this.organizadores = organizadores;
	}

}