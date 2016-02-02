package br.com.colmeia.model.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.TipoAtividadeHibernateDAO;
import br.com.colmeia.model.persistence.entity.TipoAtividade;
import br.com.colmeia.model.service.generics.Service;

public class TipoAtividadeService extends Service<TipoAtividade, Long, TipoAtividadeHibernateDAO> {

	public boolean validarSalvarAlterar(TipoAtividade entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");

		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");

		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		if(!verificaTipoAtividade(entity)){
			throw new Exception("Já existe uma atividade com esse nome");
		}
		return true;
	}

	@Override
	public List<TipoAtividade> buscar(TipoAtividade entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;

		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.ilike("nome", "%" + entity.getNome() + "%");
			}
		}
		return getDao().findByCriteria(id, nome);
	}
	
	public boolean verificaTipoAtividade(TipoAtividade entity) throws Exception{
		List<TipoAtividade> lista = buscar(entity);
		for (TipoAtividade atividade : lista){
			if(atividade.getNome().equals(entity.getNome())){
				return false;
			}
			}
		return true;
		}

	@Override
	public TipoAtividadeHibernateDAO getDao() {
		return new TipoAtividadeHibernateDAO();
	}

	@Override
	public boolean validarExcluir(TipoAtividade entity) throws Exception {
		return true;
	}

	

}
