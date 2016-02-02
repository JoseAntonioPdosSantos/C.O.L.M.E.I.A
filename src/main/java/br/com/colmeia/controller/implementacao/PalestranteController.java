package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Palestrante;
import br.com.colmeia.model.service.implementacao.PalestranteService;

@ManagedBean
@ViewScoped
public class PalestranteController extends Controller<Palestrante, PalestranteService> {

	private static final long serialVersionUID = 2645736014012269825L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	@Override
	public void limpar() {
		entidade = new Palestrante();
		setEditando_registro(false);
	}

	@Override
	public PalestranteService getService() {
		return new PalestranteService();
	}
	
	@Override
	public void setService(PalestranteService service) {
		this.service = service;
	}
}
