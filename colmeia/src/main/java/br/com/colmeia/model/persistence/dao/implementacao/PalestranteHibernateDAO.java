package br.com.colmeia.model.persistence.dao.implementacao;

import br.com.colmeia.model.persistance.dao.PalestranteDAO;
import br.com.colmeia.model.persistence.dao.generics.GenericHibernateDAO;
import br.com.colmeia.model.persistence.entity.Palestrante;


public class PalestranteHibernateDAO extends GenericHibernateDAO<Palestrante, Long> implements PalestranteDAO{

}
