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
			setInstituicao(new Instituicao());
			buscar();
			limpar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public void alterar() {
		try {
			service.alterar(instituicao);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
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
			message(ERROR, e.getMessage());
		}
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public void setInstituicao(Instituicao instituicao) {
		setEditando_registro(true);
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
