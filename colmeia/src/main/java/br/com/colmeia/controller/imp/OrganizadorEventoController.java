package br.com.colmeia.controller.imp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.OrganizadorEvento;
import br.com.colmeia.model.persistence.service.imp.OrganizadorEventoService;
import java.util.List;

@ManagedBean
@ViewScoped
public class OrganizadorEventoController extends Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrganizadorEventoService service;
	private OrganizadorEvento organizadorEvento;
	private List<OrganizadorEvento> organizadorEventos;

	public OrganizadorEventoController() {
		service = new OrganizadorEventoService();
		organizadorEvento = new OrganizadorEvento();
		try {
			organizadorEventos = service.buscarTodos();
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void gravar() {
		try {
			service.gravar(organizadorEvento);
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(organizadorEvento);
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar() {
		try {
			service.apagar(organizadorEvento);
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
			organizadorEvento = service.buscarPorId(organizadorEvento.getId());
			if (organizadorEvento == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public void buscar() {
		try {
			organizadorEventos = service.buscar(organizadorEvento);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public OrganizadorEventoService getService() {
		return service;
	}

	public void setService(OrganizadorEventoService service) {
		this.service = service;
	}

	public OrganizadorEvento getOrganizadorEvento() {
		return organizadorEvento;
	}

	public void setOrganizadorEvento(OrganizadorEvento organizadorEvento) {
		this.organizadorEvento = organizadorEvento;
	}

	public List<OrganizadorEvento> getOrganizadorEventos() {
		return organizadorEventos;
	}

	public void setOrganizadorEventos(List<OrganizadorEvento> organizadorEventos) {
		this.organizadorEventos = organizadorEventos;
	}

}