package br.com.colmeia.controller.imp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Presenca;
import br.com.colmeia.model.persistence.service.imp.PresencaService;
import java.util.List;

@ManagedBean
@ViewScoped
public class PresencaController extends Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PresencaService service;
	private Presenca presenca;
	private List<Presenca> presencas;

	public PresencaController() {
		service = new PresencaService();
		presenca = new Presenca();
		try {
			presencas = service.buscarTodos();
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void gravar() {
		try {
			service.gravar(presenca);
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(presenca);
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar() {
		try {
			service.apagar(presenca);
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
			presenca = service.buscarPorId(presenca.getId());
			if (presenca == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public void buscar() {
		try {
			presencas = service.buscar(presenca);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public PresencaService getService() {
		return service;
	}

	public void setService(PresencaService service) {
		this.service = service;
	}

	public Presenca getPresenca() {
		return presenca;
	}

	public void setPresenca(Presenca presenca) {
		this.presenca = presenca;
	}

	public List<Presenca> getPresencas() {
		return presencas;
	}

	public void setPresencas(List<Presenca> presencas) {
		this.presencas = presencas;
	}

}
