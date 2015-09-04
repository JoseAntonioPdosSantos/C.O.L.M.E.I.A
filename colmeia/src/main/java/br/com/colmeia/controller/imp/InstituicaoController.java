package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Instituicao;
import br.com.colmeia.model.persistence.service.imp.InstituicaoService;

@ManagedBean
@ViewScoped
public class InstituicaoController extends Controller<Instituicao> {

	private static final long serialVersionUID = 1L;
	private InstituicaoService service;
	private Instituicao instituicao;
	private List<Instituicao> instituicoes;

	public InstituicaoController() {
		service = new InstituicaoService();
		instituicao = new Instituicao();
		buscar();
	}

	public void gravar() {
		try {
			instituicao.setId(null);
			service.gravar(instituicao);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(instituicao);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar(Instituicao instituicao) {
		try {
			service.apagar(instituicao);
			buscar();
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}

	public void limpar() {
		instituicao = new Instituicao();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		try {
			instituicoes = service.buscar(instituicao);
			if (instituicoes != null)
				setSize_maior_q_zero(instituicoes.size() > 0 ? true : false);
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
