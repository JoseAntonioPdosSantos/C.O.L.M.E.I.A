package br.com.colmeia.model.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.IngressoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Ingresso;
import br.com.colmeia.model.service.generics.Service;

public class IngressoService extends Service<Ingresso, Long, IngressoHibernateDAO> {

	public boolean validarSalvarAlterar(Ingresso entity) throws Exception {
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
	public List<Ingresso> buscar(Ingresso entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;

		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.ilike("nome", "%"+entity.getNome()+"%");
			}
		}
		return getDao().findByCriteria(id, nome);
	}

	@Override
	public IngressoHibernateDAO getDao() {
		return new IngressoHibernateDAO();
	}

	@Override
	public boolean validarExcluir(Ingresso entity) throws Exception {
		return true;
	}

	

}
