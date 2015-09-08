package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.UsuarioHibernateDAO;
import br.com.colmeia.model.persistence.entity.Perfil;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.generics.Service;

public class UsuarioService extends Service<Usuario, Long, UsuarioHibernateDAO> {

	public void gravar(Usuario entity) throws Exception {
		if (entity != null)
			entity.setPerfil(Perfil.USUARIO);
		super.gravar(entity);
	}

	public boolean validarEntity(Usuario entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");
		if (entity.getNome().trim().isEmpty())
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em branco");
		if (entity.getCpf() == null)
			throw new Exception("Desculpe! O campo 'CPF' é obrigatório");
		if (!isCPF(entity.getCpf()))
			throw new Exception("Desculpe! O CPF digitado é inválido. Tente de novo");
		if (entity.getCurso() == null)
			throw new Exception("Desculpe! O campo 'Curso' é obrigatório");
		if (entity.getInstituicao() == null)
			throw new Exception("Desculpe! O campo 'Instituiçao' é obrigatório");
		if (entity.getEmail() == null)
			throw new Exception("Desculpe! O campo 'E-mail' é obrigatório");
		if (entity.getSenha() == null)
			throw new Exception("Desculpe! O campo 'Senha' é obrigatório");
		if (entity.getConfirmarSenha() == null)
			throw new Exception("Desculpe! O campo 'Confirmar Senha' é obrigatório");
		if (isSenhaValida(entity.getSenha(), entity.getConfirmarSenha()))
			throw new Exception("Desculpe! Parece que você não digitou sua senha corretamente");
		;
		return true;
	}

	private boolean isSenhaValida(String senha, String confirmarSenha) throws Exception {
		if (senha.length() < 4)
			throw new Exception("Desculpe! Sua senha está muito pequena. Insira uma senha com mais de 4 digitos");
		if (senha.contains("1234") || senha.contains("4321"))
			throw new Exception("Desculpe! Sua senha não pode conter caracter sequencial");
		if (!senha.equals(confirmarSenha))
			throw new Exception("Desculpe! Parece que sua senha não foi digitada corretamente. Tente de novo");
		return true;
	}

	private boolean isCPF(String cpf) throws Exception {
		if(cpf.length()!=11)
			throw new Exception("Desculpe! Parece que seu CPF está inválido. Tente de novo");
		return true;
	}

	public Usuario isUsuario(Usuario usuario) throws Exception {
		if (usuario == null)
			return null;
		if (validaCPFSenha(usuario.getCpf(), usuario.getSenha())) {
			Criterion cpf = Restrictions.eq("cpf", usuario.getCpf());
			Criterion senha = Restrictions.eq("senha", usuario.getSenha());

			List<Usuario> usuarios = getDao().findByCriteria(cpf, senha);
			if (usuarios.size() == 1)
				return usuarios.get(0);
		}
		return null;
	}

	private boolean validaCPFSenha(String cpf, String senha) {
		if (cpf == null)
			return false;
		if (senha == null)
			return false;
		if (cpf.trim().isEmpty())
			return false;
		if (senha.trim().isEmpty())
			return false;
		if (senha.length() < 4)
			return false;
		return true;
	}

	@Override
	public List<Usuario> buscar(Usuario entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion cpf = null;
		Criterion ra = null;
		Criterion email = null;
		Criterion curso = null;
		Criterion instituicao = null;
		Criterion perfil = null;
		Criterion senha = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.eq("nome", entity.getNome());
			}
			if (entity.getCpf() != null && !entity.getCpf().trim().isEmpty()) {
				cpf = Restrictions.eq("cpf", entity.getCpf());
			}
			if (entity.getRa() != null && !entity.getRa().trim().isEmpty()) {
				ra = Restrictions.eq("ra", entity.getRa());
			}
			if (entity.getEmail() != null && !entity.getEmail().trim().isEmpty()) {
				email = Restrictions.eq("email", entity.getEmail());
			}
			if (entity.getCurso() != null) {
				curso = Restrictions.eq("curso", entity.getCurso());
			}
			if (entity.getInstituicao() != null) {
				instituicao = Restrictions.eq("instituicao", entity.getInstituicao());
			}
			if (entity.getPerfil() != null) {
				perfil = Restrictions.eq("perfil", entity.getPerfil());
			}
			if (entity.getSenha() != null && !entity.getSenha().trim().isEmpty()) {
				senha = Restrictions.eq("senha", entity.getSenha());
			}
		}
		return getDao().findByCriteria(id, nome, cpf, ra, email, curso, instituicao, perfil, senha);
	}

	@Override
	public UsuarioHibernateDAO getDao() {
		return new UsuarioHibernateDAO();
	}

}
