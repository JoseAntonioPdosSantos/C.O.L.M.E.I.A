package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.UsuarioHibernateDAO;
import br.com.colmeia.model.persistence.entity.Perfil;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.generics.Service;
import br.com.colmeia.model.security.Security;
import br.com.colmeia.model.utils.Util;

public class UsuarioService extends Service<Usuario, Long, UsuarioHibernateDAO> {

	public void gravar(Usuario entity) throws Exception {
		if (validarEntity(entity)) {
			entity.setPerfil(Perfil.USUARIO);
			criptografarMD5(entity);
			controleBasicoAuditoria(entity);
			getDao().insert(entity);
		}
	}

	private void criptografarMD5(Usuario entity) {
		entity.setSenha(Security.criptografarMD5(entity.getSenha()));
		entity.setConfirmarSenha(Security.criptografarMD5(entity.getConfirmarSenha()));
	}

	public boolean validarEntity(Usuario entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");
		if (entity.getNome().trim().isEmpty())
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em branco");
		if (entity.getCpf() == null || entity.getCpf().trim().isEmpty())
			throw new Exception("Desculpe! O campo 'CPF' é obrigatório");
		// if (entity.getCurso() == null)
		// throw new Exception("Desculpe! O campo 'Curso' é obrigatório");
		// if (entity.getInstituicao() == null)
		// throw new Exception("Desculpe! O campo 'Instituiçao' é obrigatório");
		// if (entity.getEmail() == null)
		// throw new Exception("Desculpe! O campo 'E-mail' é obrigatório");
		if (entity.getSenha() == null || entity.getSenha().trim().isEmpty())
			throw new Exception("Desculpe! O campo 'Senha' é obrigatório");
		if(entity.getId() != null && entity.getId() > 0 && entity.getConfirmarSenha() == null)
			entity.setConfirmarSenha(entity.getSenha());
		if (entity.getConfirmarSenha() == null || entity.getConfirmarSenha().trim().isEmpty())
			throw new Exception("Desculpe! O campo 'Confirmar Senha' é obrigatório");
		if (!isSenhaValida(entity)) {
			throw new Exception("Desculpe! A senha digitada está inválida");
		}
		if(!Util.isCPFValido(entity.getCpf()))
			throw new Exception("Desculpe! O CPF digitado está inválido");
				
		return true;
	}

	private boolean isSenhaValida(Usuario entity) throws Exception {
		String senha = entity.getSenha();
		String confirmarSenha = entity.getConfirmarSenha();
		if (senha.length() < 4)
			throw new Exception("Desculpe! Sua senha está muito pequena. Insira uma senha com mais de 4 digitos");
		if (senha.contains("1234") || senha.contains("4321"))
			throw new Exception("Desculpe! Sua senha não pode conter caracter sequencial");
		if (!senha.equals(confirmarSenha))
			throw new Exception("Desculpe! Parece que sua senha não foi digitada corretamente. Tente de novo");
		return true;
	}

	public Usuario isUsuario(Usuario usuario) throws Exception {
		if (usuario == null)
			return null;
		if (validaCPFSenha(usuario.getCpf(), usuario.getSenha())) {
			Criterion cpf = Restrictions.eq("cpf", usuario.getCpf());
			Criterion senha = Restrictions.eq("senha", Security.criptografarMD5(usuario.getSenha()));

			List<Usuario> usuarios = getDao().findByCriteria(cpf, senha);
			if (usuarios.size() == 1)
				return usuarios.get(0);
		}
		return null;
	}

	private boolean validaCPFSenha(String cpf, String senha) {
		if (!Util.isCPFValido(cpf))
			return false;
		if (senha == null)
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
				nome = Restrictions.ilike("nome", "%"+entity.getNome()+"%");
			}
			if (entity.getCpf() != null && !entity.getCpf().trim().isEmpty()) {
				cpf = Restrictions.eq("cpf", entity.getCpf());
			}
			if (entity.getRa() != null && !entity.getRa().trim().isEmpty()) {
				ra = Restrictions.ilike("ra", entity.getRa());
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
