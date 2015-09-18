package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Ingresso;
import br.com.colmeia.model.persistence.service.implementacao.IngressoService;

@ManagedBean
@ViewScoped
public class IngressoController extends Controller<Ingresso, IngressoService> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	@Override
	public void limpar() {
		entidade = new Ingresso();
		setEditando_registro(false);
	}

	@Override
	public IngressoService getService() {
		return new IngressoService();
	}

	public void setService(IngressoService service) {
		this.service = service;
	}

}
