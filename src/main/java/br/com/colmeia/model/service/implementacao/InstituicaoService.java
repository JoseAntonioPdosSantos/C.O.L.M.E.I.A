package br.com.colmeia.model.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.InstituicaoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Instituicao;
import br.com.colmeia.model.service.generics.Service;
import br.com.colmeia.model.utils.Util;

public class InstituicaoService extends Service<Instituicao, Long, InstituicaoHibernateDAO> {

	public boolean validarSalvarAlterar(Instituicao entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");
		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");
		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		if(!verificaInstituicao(entity))
			throw new Exception("Já existe uma Instituição com esse nome");
		
		return true;
	}

	@Override
	public List<Instituicao> buscar(Instituicao entity) throws Exception {
		Criterion id = null;
		Criterion nome = null;
		Criterion estacio = null;
		Criterion ativo = null;
		
		if (entity != null) {
			if (entity.getId() != null && entity.getId() > 0) {
				id = Restrictions.eq("id", entity.getId());
			}
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty()) {
				nome = Restrictions.ilike("nome", "%" + entity.getNome() + "%");
			}
			if (entity.isEstacio()) {
				estacio = Restrictions.eq("estacio",entity.isEstacio());
			}
			if(entity.getAtivo() !=null){
				ativo = Restrictions.eq("ativo", entity.getAtivo());
			}
		}
		return getDao().findByCriteria(id, nome,estacio,ativo);
	}

	public boolean verificaInstituicao(Instituicao entity) throws Exception {
		Instituicao instituicaoAtivo = new Instituicao();
		instituicaoAtivo.setAtivo(true);
		List<Instituicao> lista = buscar(instituicaoAtivo);

		for (Instituicao instituicao : lista) {
			if (Util.compare(instituicao.getNome(), entity.getNome()) == 0) {
				return false;
			}
		}

		return true;
	}
	@Override
	public InstituicaoHibernateDAO getDao() {
		return new InstituicaoHibernateDAO();
	}

	@Override
	public boolean validarExcluir(Instituicao entity) throws Exception {
		return true;
	}

	

}
