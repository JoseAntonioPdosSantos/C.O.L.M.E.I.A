package br.com.colmeia.controller.imp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Organizador;
import br.com.colmeia.model.persistence.service.imp.OrganizadorService;
import java.util.List;

@ManagedBean
@ViewScoped
public class OrganizadorController extends Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrganizadorService service;
	private Organizador organizador;
	private List<Organizador> organizadores;

	public OrganizadorController() {

	}

	public void gravar() {
		if (validarEntity()) {
			try {
				service.gravar(organizador);
				message(SUCCESS_RECORD);
			} catch (Exception e) {
				message(FAILURE_RECORD);
			}
		}
	}

	public void alterar() {
		if (validarEntity()) {
			try {
				service.alterar(organizador);
				message(SUCCESS_UPDATE);
			} catch (Exception e) {
				message(FAILURE_UPDATE);
			}
		}
	}

	public void apagar() {
		if (validarEntity()) {
			try {
				service.apagar(organizador);
				message(SUCCESS_DELETE);
			} catch (Exception e) {
				message(FAILURE_DELETE);
			}
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
			organizador = service.buscarPorId(organizador.getId());
			if(organizador == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public boolean validarEntity() {
		message(FAILURE_FILL_DATA);
		return false;
	}
	
	@Override
	public void buscar() {
		try {
			organizadores = service.buscar(organizador);
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