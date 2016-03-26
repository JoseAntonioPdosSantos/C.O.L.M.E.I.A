package br.com.colmeia.model.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.SalaHibernateDAO;
import br.com.colmeia.model.persistence.entity.Sala;
import br.com.colmeia.model.service.generics.Service;
import br.com.colmeia.model.utils.Util;

public class SalaService extends Service<Sala, Long, SalaHibernateDAO> {

	public boolean validarSalvarAlterar(Sala entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");

		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");

		if (entity.getNome().trim().isEmpty())
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		
		if (!isUnique(entity)) {
			throw new Exception("Já existe uma sala com esse nome");
		}
		return true;
	}

	private boolean isUnique(Sala entity) throws Exception{
		Sala salaAtiva = new Sala();
		salaAtiva.setAtivo(true);
		List<Sala> salas = buscar(salaAtiva);
		for (Sala sala : salas) {
			if (Util.compare(sala.getNome(), entity.getNome()) == 0) {
				return false;
			}
		}

		return true;
	}
	
	@Override
	public List<Sala> buscar(Sala entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion ativo = null;

		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.ilike("nome", "%" + entity.getNome() + "%");
			}
			if (entity.getAtivo() != null) {
				ativo = Restrictions.eq("ativo", entity.getAtivo());
			}
		}
		return getDao().findByCriteria(id, nome, ativo);
	}

	@Override
	public SalaHibernateDAO getDao() {
		return new SalaHibernateDAO();
	}

	@Override
	public boolean validarExcluir(Sala entity) throws Exception {
		return true;
	}
}
