package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Presenca;
import br.com.colmeia.model.persistence.service.imp.PresencaService;

@ManagedBean
@ViewScoped
public class PresencaController extends Controller<Presenca> {

	private static final long serialVersionUID = 1L;
	private PresencaService service;
	private Presenca presenca;
	private List<Presenca> presencas;

	public PresencaController() {
		service = new PresencaService();
		presenca = new Presenca();
		buscar();
	}

	public void gravar() {
		try {
			presenca.setId(null);
			service.gravar(presenca);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(presenca);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar(Presenca presenca) {
		try {
			service.apagar(presenca);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}

	public void limpar() {
		presenca = new Presenca();
		setEditando_registro(false);
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
