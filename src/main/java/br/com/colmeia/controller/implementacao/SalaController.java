package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Sala;
import br.com.colmeia.model.service.implementacao.SalaService;

@ManagedBean
@ViewScoped
public class SalaController extends Controller<Sala, SalaService> {


	private static final long serialVersionUID = -1391059909667003245L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	public void limpar() {
		setEntidade(new Sala());
		setEditando_registro(false);
	}

	public SalaService getService() {
		return new SalaService();
	}

	public void setService(SalaService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
