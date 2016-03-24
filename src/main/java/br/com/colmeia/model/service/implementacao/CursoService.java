package br.com.colmeia.model.service.implementacao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.implementacao.CursoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Curso;
import br.com.colmeia.model.service.generics.Service;

public class CursoService extends Service<Curso, Long, CursoHibernateDAO> {

	public boolean validarSalvarAlterar(Curso entity) throws Exception {
		if (entity == null)
			throw new Exception("Desculpe! Ocorreu um Erro Inesperado");

		if (entity.getNome() == null)
			throw new Exception("Desculpe! O campo 'Nome' é obrigatório");

		if (entity.getNome().trim().isEmpty())
			throw new Exception(
					"Desculpe! O campo 'Nome' é obrigatório. Evite cadastrar campos com espaços em brancos");
		if(!isUnique(entity))
			throw new Exception("Desculpe! Já existe um curso cadastrado com este nome");
		
		return true;
	}

	private boolean isUnique(Curso entity) throws Exception{
		Curso curso = new Curso();
		curso.setNome(entity.getNome());
		List<Curso> cursos = buscar(curso);
		return cursos==null || cursos.size() == 0;
	}
	
	@Override
	public List<Curso> buscar(Curso entity) throws Exception {
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
			if(entity.getAtivo() != null){
				ativo = Restrictions.eq("ativo", entity.getAtivo());
			}
		}
		return getDao().findByCriteria(id, nome,ativo);
	}

	@Override
	public CursoHibernateDAO getDao() {
		return new CursoHibernateDAO();
	}

	@Override
	public boolean validarExcluir(Curso entity) throws Exception {
		return true;
	}



}
