package br.com.colmeia.controller.imp;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Curso;
import br.com.colmeia.model.persistence.service.imp.CursoService;

@ManagedBean
@ViewScoped
public class CursoController extends Controller{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CursoService service;
	private Curso curso;
	private List<Curso> cursos;

	public CursoController() {

	}

	public void gravar() {
		if (validarEntity()) {
			try {
				service.gravar(curso);
				message(SUCCESS_RECORD);
			} catch (Exception e) {
				message(FAILURE_RECORD);
			}
		}
	}

	public void alterar() {
		if (validarEntity()) {
			try {
				service.alterar(curso);
				message(SUCCESS_UPDATE);
			} catch (Exception e) {
				message(FAILURE_UPDATE);
			}
		}
	}

	public void apagar() {
		if (validarEntity()) {
			try {
				service.apagar(curso);
				message(SUCCESS_DELETE);
			} catch (Exception e) {
				message(FAILURE_DELETE);
			}
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
	public void buscarPorId()  {
		try {
			curso = service.buscarPorId(curso.getId());
			if(curso == null)
				message(ERROR_FIND);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
	}

	@Override
	public boolean validarEntity() {
		message(FAILURE_FILL_DATA);
		return false;
	}
	
	@Override
	public void buscar() {
		try {
			cursos = service.buscar(curso);
		} catch (Exception e) {
			message(ERROR_UNEXPECTED);
		}
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
