package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.OrganizadorEvento;
import br.com.colmeia.model.persistence.service.imp.OrganizadorEventoService;

@ManagedBean
@ViewScoped
public class OrganizadorEventoController extends Controller<OrganizadorEvento> {

	private static final long serialVersionUID = 1L;
	private OrganizadorEventoService service;
	private OrganizadorEvento organizadorEvento;
	private List<OrganizadorEvento> organizadorEventos;

	public OrganizadorEventoController() {
		service = new OrganizadorEventoService();
		organizadorEvento = new OrganizadorEvento();
		buscar();
	}

	public void gravar() {
		try {
			organizadorEvento.setId(null);
			service.gravar(organizadorEvento);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(organizadorEvento);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar(OrganizadorEvento organizadorEvento) {
		try {
			service.apagar(organizadorEvento);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}

	public void limpar() {
		organizadorEvento = new OrganizadorEvento();
		setEditando_registro(false);
	}
	
	@Override
	public void buscar() {
		try {
			organizadorEventos = service.buscar(organizadorEvento);
			if (organizadorEventos != null)
				setSize_maior_q_zero(organizadorEventos.size() > 0 ? true : false);
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