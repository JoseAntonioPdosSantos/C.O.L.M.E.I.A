package br.com.colmeia.model.persistence.dao.imp;

import br.com.colmeia.model.persistance.dao.CursoDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Curso;


public class CursoHibernateDAO extends GenericHibernateDAO<Curso, Long> implements CursoDAO{

}
