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
import br.com.colmeia.model.service.implementacao.CursoService;
import br.com.colmeia.model.service.implementacao.InstituicaoService;
import br.com.colmeia.model.service.implementacao.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuarioController extends Controller<Usuario, UsuarioService> {

	private static final long serialVersionUID = 2420576638407918975L;
	private List<Instituicao> instituicoes;
	private List<Curso> cursos;

	private boolean aba_cadastro_usuario_sou_universitario;
	private boolean aba_cadastro_usuario_sou_estacio;
	private boolean aba_cadasto_inst_curso;
	private boolean aba_cadasto_dados_pessoais;
	private boolean combobox_instituicao;
	private boolean atualizarDados;

	@Override
	protected void inicializarVariavel() {
		limpar();
		if (getCurrentInstanceUser() == null) {
			setEntidade(new Usuario());
			tornarAbaVisivel("aba_cadastro_usuario_sou_universitario");
		} else {
			setEntidade(getCurrentInstanceUser());
		}
		carregarInstituicoes();
		carregarCursos();
	}

	@Override
	public void buscar() {
		super.buscar();
		for (Usuario usuario : entidades) {
			usuario.setConfirmarSenha(usuario.getSenha());
		}
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
			String confirmaSenha = getEntidade().getConfirmarSenha();
			String cpf = getEntidade().getCpf();
			if (atualizarDados) {
				Usuario usuario = new Usuario();
				usuario.setCpf(cpf);
				setEntidade(entidade);
				List<Usuario> usuarios = getService().buscar(usuario);
				if (usuarios.size() == 1)
					setEntidade(usuarios.get(0));
				else{
					message(WORNING, "Verifique o CPF digitado");
					return null;
				}
				getEntidade().setSenha(senha);
				getEntidade().setConfirmarSenha(confirmaSenha);
				getService().alterar(getEntidade());
				atualizarDados = false;
			} else {
				getService().gravar(getEntidade());
			}
			Login login = new Login();
			getEntidade().setSenha(senha);
			login.setUsuario(getEntidade());
			login.doLogin();
		} catch (Exception e) {
			message(ERROR, e.getMessage());
			return "";
		}
		return "acessar";
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

	public void alterarSenha() {
		atualizarDados = true;
	}

	public String esqueciMinhaSenha() {
		return "esqueci_minha_senha";
	}

	public void irParaProximaAbaDepoisUniversitario() {
		if (entidade.isUniversitario()) {
			tornarAbaVisivel("aba_cadastro_usuario_sou_estacio");
		} else {
			entidade.setAlunoEstacio(false);
			tornarAbaVisivel("aba_cadasto_dados_pessoais");
		}
	}

	public void irParaCadastroInstituicaoECurso() {
		if (entidade.isAlunoEstacio()) {
			Instituicao instituicao = new Instituicao();
			instituicao.setEstacio(true);
			try {
				List<Instituicao> instituicoes = new InstituicaoService().buscar(instituicao);
				if (instituicoes.size() > 0) {
					entidade.setInstituicao(instituicoes.get(0));
					combobox_instituicao = false;
					if (instituicoes.size() > 1) {
						combobox_instituicao = true;
					}
				}
			} catch (Exception e) {
				message(ERROR, e.getMessage());
			}
		} else {
			entidade.setInstituicao(null);
			combobox_instituicao = true;
		}
		tornarAbaVisivel("aba_cadasto_inst_curso");
	}

	public void irParaCadastroDadosPessoais() {
		if (entidade.getInstituicao() != null) {
			entidade.setAlunoEstacio(entidade.getInstituicao().isEstacio());
			if (entidade.getCurso() != null) {
				tornarAbaVisivel("aba_cadasto_dados_pessoais");
			} else {
				message(WORNING, "Favor escolher seu curso. Caso não-o encontre na lista, selecione 'Outro'");
			}
		} else {
			message(WORNING,
					"Favor escolher sua instituição de ensino. Caso não-a encontre na lista, selecione 'Outra'");
		}
	}

	public void cadastroSouEstacioVoltar() {
		tornarAbaVisivel("aba_cadastro_usuario_sou_universitario");
	}

	public void cadastroInstCursoVoltar() {
		tornarAbaVisivel("aba_cadastro_usuario_sou_estacio");
	}

	public void cadastroDadosPessoaisVotar() {
		if (entidade.isUniversitario())
			tornarAbaVisivel("aba_cadasto_inst_curso");
		else
			tornarAbaVisivel("aba_cadastro_usuario_sou_universitario");
	}

	private void tornarAbaVisivel(final String aba) {
		switch (aba) {
		case "aba_cadastro_usuario_sou_universitario":
			aba_cadastro_usuario_sou_universitario = true;
			aba_cadastro_usuario_sou_estacio = false;
			aba_cadasto_inst_curso = false;
			aba_cadasto_dados_pessoais = false;
			break;
		case "aba_cadastro_usuario_sou_estacio":
			aba_cadastro_usuario_sou_estacio = true;
			aba_cadastro_usuario_sou_universitario = false;
			aba_cadasto_inst_curso = false;
			aba_cadasto_dados_pessoais = false;
			break;
		case "aba_cadasto_inst_curso":
			aba_cadasto_inst_curso = true;
			aba_cadastro_usuario_sou_universitario = false;
			aba_cadastro_usuario_sou_estacio = false;
			aba_cadasto_dados_pessoais = false;
			break;
		case "aba_cadasto_dados_pessoais":
			aba_cadasto_dados_pessoais = true;
			aba_cadastro_usuario_sou_universitario = false;
			aba_cadastro_usuario_sou_estacio = false;
			aba_cadasto_inst_curso = false;
			break;
		}
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

	public boolean isAba_cadasto_inst_curso() {
		return aba_cadasto_inst_curso;
	}

	public void setAba_cadasto_inst_curso(boolean aba_cadasto_inst_curso) {
		this.aba_cadasto_inst_curso = aba_cadasto_inst_curso;
	}

	public boolean isAba_cadasto_dados_pessoais() {
		return aba_cadasto_dados_pessoais;
	}

	public void setAba_cadasto_dados_pessoais(boolean aba_cadasto_dados_pessoais) {
		this.aba_cadasto_dados_pessoais = aba_cadasto_dados_pessoais;
	}

	public boolean isCombobox_instituicao() {
		return combobox_instituicao;
	}

	public void setCombobox_instituicao(boolean combobox_instituicao) {
		this.combobox_instituicao = combobox_instituicao;
	}

	public boolean isAba_cadastro_usuario_sou_estacio() {
		return aba_cadastro_usuario_sou_estacio;
	}

	public void setAba_cadastro_usuario_sou_estacio(boolean aba_cadastro_usuario_sou_estacio) {
		this.aba_cadastro_usuario_sou_estacio = aba_cadastro_usuario_sou_estacio;
	}

	public boolean isAba_cadastro_usuario_sou_universitario() {
		return aba_cadastro_usuario_sou_universitario;
	}

	public void setAba_cadastro_usuario_sou_universitario(boolean aba_cadastro_usuario_sou_universitario) {
		this.aba_cadastro_usuario_sou_universitario = aba_cadastro_usuario_sou_universitario;
	}

}
