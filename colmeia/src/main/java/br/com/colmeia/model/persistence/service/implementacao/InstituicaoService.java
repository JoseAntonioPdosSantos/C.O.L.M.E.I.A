package br.com.colmeia.model.persistence.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.InstituicaoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Instituicao;
import br.com.colmeia.model.persistence.service.generics.Service;

public class InstituicaoService extends Service<Instituicao, Long, InstituicaoHibernateDAO> {

	public boolean validarEntity(Instituicao entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");
		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		return true;
	}

	@Override
	public List<Instituicao> buscar(Instituicao entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion estacio = null;
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.ilike("nome", "%" + entity.getNome() + "%");
			}
			if (entity.isEstacio()) {
				estacio = Restrictions.ilike("nome", "%" + entity.getNome() + "%");
			}
		}
		return getDao().findByCriteria(id, nome,estacio);
	}

	@Override
	public InstituicaoHibernateDAO getDao() {
		return new InstituicaoHibernateDAO();
	}

}
