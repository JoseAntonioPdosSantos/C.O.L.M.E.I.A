package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import static br.com.colmeia.controller.util.Message.*;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Instituicao;
import br.com.colmeia.model.persistence.service.implementacao.InstituicaoService;

@ManagedBean
@ViewScoped
public class InstituicaoController extends Controller<Instituicao, InstituicaoService> {

	private static final long serialVersionUID = -1975142712336043764L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	public void apagar(Instituicao instituicao) {
		try {
			service.apagar(instituicao);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void limpar() {
		entidade = new Instituicao();
		setEditando_registro(false);
	}

	public InstituicaoService getService() {
		return new InstituicaoService();
	}

	public void setService(InstituicaoService service) {
		this.service = service;
	}

}
