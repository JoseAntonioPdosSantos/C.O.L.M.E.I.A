package br.com.colmeia.model.persistence.dao.implementacao;

import br.com.colmeia.model.persistence.dao.InstituicaoDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Instituicao;


public class InstituicaoHibernateDAO extends GenericHibernateDAO<Instituicao, Long> implements InstituicaoDAO{

}
