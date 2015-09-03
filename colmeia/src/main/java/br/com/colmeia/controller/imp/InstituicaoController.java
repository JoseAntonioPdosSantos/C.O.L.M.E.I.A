package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Instituicao;
import br.com.colmeia.model.persistence.service.imp.InstituicaoService;

@ManagedBean
@ViewScoped
public class InstituicaoController extends Controller {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InstituicaoService service;
	private Instituicao instituicao;
	private List<Instituicao> instituicoes;

	public InstituicaoController() {
		service = new InstituicaoService();
		instituicao = new Instituicao();
		try {
			instituicoes = service.buscarTodos();
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void gravar() {
		try {
			service.gravar(instituicao);
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(instituicao);
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar() {
		try {
			service.apagar(instituicao);
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
			instituicao = service.buscarPorId(instituicao.getId());
			if (instituicao == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public void buscar() {
		try {
			instituicoes = service.buscar(instituicao);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public InstituicaoService getService() {
		return service;
	}

	public void setService(InstituicaoService service) {
		this.service = service;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

}
