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
		Criterion cpf = Restrictions.eq("cpf",usuario.getCpf());
		Criterion senha = Restrictions.eq("senha",usuario.getSenha());
		
		List<Usuario> usuarios = getDao().findByCriteria(cpf,senha);
		if(usuarios.size() == 1)
			return usuarios.get(0);
		return null;
	}

	@Override
	public List<Usuario> buscar(Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
