package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Curso;
import br.com.colmeia.model.persistence.service.imp.CursoService;

@ManagedBean
@ViewScoped
public class CursoController extends Controller<Curso> {

	private static final long serialVersionUID = 1L;
	private CursoService service;
	private Curso curso;
	private List<Curso> cursos;

	public CursoController() {
		service = new CursoService();
		curso = new Curso();
		buscar();
	}

	public void gravar() {
		try {
			curso.setId(null);
			service.gravar(curso);
			buscar();
			message(SUCCESS_RECORD);
		} catch (Exception e) {
			message(FAILURE_RECORD);
		}
	}

	public void alterar() {
		try {
			service.alterar(curso);
			buscar();
			message(SUCCESS_UPDATE);
		} catch (Exception e) {
			message(FAILURE_UPDATE);
		}
	}

	public void apagar(Curso curso) {
		try {
			service.apagar(curso);
			message(SUCCESS_DELETE);
		} catch (Exception e) {
			message(FAILURE_DELETE);
		}
	}

	@Override
	public void buscar() {
		try {
			cursos = service.buscar(curso);
			if (cursos != null)
				setSize_maior_q_zero(cursos.size() > 0 ? true : false);

		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	public void limpar() {
		curso = new Curso();
		setEditando_registro(false);
	}

	public CursoService getService() {
		return service;
	}

	public void setService(CursoService service) {
		this.service = service;
	}

	public Curso getcurso() {
		return curso;
	}

	public void setcurso(Curso curso) {
		this.curso = curso;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
