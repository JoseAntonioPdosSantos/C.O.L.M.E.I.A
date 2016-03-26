package br.com.colmeia.model.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.PalestranteHibernateDAO;
import br.com.colmeia.model.persistence.entity.Palestrante;
import br.com.colmeia.model.service.generics.Service;
import br.com.colmeia.model.utils.Util;

public class PalestranteService extends Service<Palestrante, Long, PalestranteHibernateDAO> {

	public boolean validarSalvarAlterar(Palestrante entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");

		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");

		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		if(!isUnique(entity))
			throw new Exception("Já existe um Palestrante com esse nome");
		
		return true;
	}

	private boolean isUnique(Palestrante entity) throws Exception{
		Palestrante palestranteAtivo = new Palestrante();
		palestranteAtivo.setAtivo(true);
		List<Palestrante> palestrantes = buscar(palestranteAtivo);
		
		for (Palestrante palestrante : palestrantes) {
			if (Util.compare(palestrante.getNome(), entity.getNome()) == 0) {
				return false;
			}
		}

		return true;
	}

	@Override
	public List<Palestrante> buscar(Palestrante entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion ativo = null;
		
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.ilike("nome", "%"+entity.getNome()+"%");
			}
			if(entity.getAtivo() !=null){
				ativo = Restrictions.eq("ativo", entity.getAtivo());
			}
		}
		return getDao().findByCriteria(id, nome,ativo);
	}

	@Override
	public PalestranteHibernateDAO getDao() {
		return new PalestranteHibernateDAO();
	}

	@Override
	public boolean validarExcluir(Palestrante entity) throws Exception {
		return true;
	}
	

}
