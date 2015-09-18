package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Curso;
import br.com.colmeia.model.persistence.service.implementacao.CursoService;

@ManagedBean
@ViewScoped
public class CursoController extends Controller<Curso, CursoService> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}

	public void limpar() {
		entidade = new Curso();
		setEditando_registro(false);
	}

	public CursoService getService() {
		return new CursoService();
	}

	public void setService(CursoService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
