package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.UsuarioHibernateDAO;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.service.generics.Service;

public class UsuarioService extends Service<Usuario, Long, UsuarioHibernateDAO> {

	public boolean validarEntity(Usuario entity) {
		return true;
	}

	public Usuario isUsuario(Usuario usuario) throws Exception {
		Criterion cpf = Restrictions.eq("cpf", usuario.getCpf());
		Criterion senha = Restrictions.eq("senha", usuario.getSenha());

		List<Usuario> usuarios = getDao().findByCriteria(cpf, senha);
		if (usuarios.size() == 1)
			return usuarios.get(0);
		return null;
	}

	@Override
	public List<Usuario> buscar(Usuario entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion cpf = null;
		Criterion ra = null;
		Criterion email = null;
		Criterion data = null;
		Criterion curso = null;
		Criterion instituicao = null;
		Criterion perfil = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.eq("nome", entity.getNome());
			}
			if (entity.getCpf() != null) {
				cpf = Restrictions.eq("cpf", entity.getCpf());
			}
			if (entity.getRa() != null && !entity.getRa().trim().isEmpty()) {
				ra = Restrictions.eq("ra", entity.getRa());
			}
			if (entity.getEmail() != null && !entity.getEmail().trim().isEmpty()) {
				email = Restrictions.eq("email", entity.getEmail());
			}
			if (entity.getData() != null) {
				data = Restrictions.eq("data", entity.getData());
			}
			if (entity.getCurso() != null) {
				curso = Restrictions.eq("curso", entity.getCurso());
			}
			if (entity.getInstituicao() != null) {
				instituicao = Restrictions.eq("instituicao", entity.getInstituicao());
			}
			if(entity.getPerfil() != null){
				perfil = Restrictions.eq("perfil", entity.getPerfil());
			}
		}
		return getDao().findByCriteria(id, nome, cpf, ra, email, data, curso, instituicao,perfil);

	}

	@Override
	public UsuarioHibernateDAO getDao() {
		return new UsuarioHibernateDAO();
	}

}
