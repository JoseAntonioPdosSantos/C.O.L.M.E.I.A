package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.CursoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Curso;
import br.com.colmeia.model.persistence.service.generics.Service;

public class CursoService extends Service<Curso, Long, CursoHibernateDAO> {

	public boolean validarEntity(Curso entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");

		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");

		if (entity.getNome().trim().isEmpty())
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		return true;
	}

	@Override
	public List<Curso> buscar(Curso entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;

		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.eq("nome", entity.getNome());
			}
		}
		return getDao().findByCriteria(id, nome);
	}

	@Override
	public CursoHibernateDAO getDao() {
		return new CursoHibernateDAO();
	}

}
