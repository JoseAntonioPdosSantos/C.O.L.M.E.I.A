package br.com.colmeia.controller.implementacao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import static br.com.colmeia.controller.util.Message.*;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Curso;
import br.com.colmeia.model.persistence.entity.Instituicao;
import br.com.colmeia.model.persistence.entity.Perfil;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.implementacao.CursoService;
import br.com.colmeia.model.persistence.service.implementacao.InstituicaoService;
import br.com.colmeia.model.persistence.service.implementacao.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuarioController extends Controller<Usuario, UsuarioService> {

	private static final long serialVersionUID = 2420576638407918975L;
	private List<Instituicao> instituicoes;
	private List<Curso> cursos;

	@Override
	protected void inicializarVariavel() {
		limpar();
		setEntidade(getCurrentInstanceUser());
		carregarInstituicoes();
		carregarCursos();
	}

	private void carregarInstituicoes() {
		try {
			Instituicao instituicao = new Instituicao();
			instituicao.setAtivo(true);
			instituicoes = new InstituicaoService().buscar(instituicao);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	private void carregarCursos() {
		try {
			Curso curso = new Curso();
			curso.setAtivo(true);
			cursos = new CursoService().buscar(curso);
		} catch (Exception e) {
			message(ERROR, e.getMessage());
		}
	}

	public String novoUsuario() {
		try {
			String senha = getEntidade().getSenha();
			service.gravar(getEntidade());
			Login login = new Login();
			getEntidade().setSenha(senha);
			login.setUsuario(getEntidade());
			login.doLogin();
		} catch (Exception e) {
			message(ERROR, e.getMessage());
			return "";
		}
		return "/pages/borders/index.xhtml";
	}

	public void limpar() {
		setEntidade(new Usuario());
		setEditando_registro(false);
	}

	public void cadastrarPerfilCoordenador(Usuario usuario) {
		usuario.setPerfil(Perfil.COORDENADOR);
		setEntidade(usuario);
		alterar();
	}

	public void cadastrarPerfilOrganizador(Usuario usuario) {
		usuario.setPerfil(Perfil.ORGANIZADOR);
		setEntidade(usuario);
		alterar();
	}

	public void cadastrarPerfilUsuario(Usuario usuario) {
		usuario.setPerfil(Perfil.USUARIO);
		setEntidade(usuario);
		alterar();
	}

	public void cadastrarPerfilAdministrador(Usuario usuario) {
		usuario.setPerfil(Perfil.ADMINISTRADOR);
		setEntidade(usuario);
		alterar();
	}

	public UsuarioService getService() {
		return new UsuarioService();
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
