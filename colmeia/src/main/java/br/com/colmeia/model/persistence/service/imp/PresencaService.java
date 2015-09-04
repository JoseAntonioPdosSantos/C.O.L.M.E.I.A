package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.colmeia.model.persistence.dao.imp.PresencaHibernateDAO;
import br.com.colmeia.model.persistence.entity.Presenca;
import br.com.colmeia.model.persistence.service.generics.Service;

public class PresencaService extends Service<Presenca, Long, PresencaHibernateDAO> {

	public boolean validarEntity(Presenca entity) {
		return true;
	}

	@Override
	public List<Presenca> buscar(Presenca entity) throws Exception {
		Criterion id = null;
		Criterion usuario = null;
		Criterion evento = null;
		Criterion data = null;
		if(entity!=null){
			if(entity.getId() != null && entity.getId() > 0){
				id = Restrictions.eq("id", entity.getId());
			}
			if(entity.getUsuario() != null){
				usuario = Restrictions.eq("usuario", usuario);
			}
			if(entity.getEvento() != null){
				evento = Restrictions.eq("evento", entity.getEvento());
			}
			if(entity.getDate() != null){
				data = Restrictions.eq("data", entity.getDate());
			}
		}
		return getDao().findByCriteria(id, usuario, evento, data);
	}

	@Override
	public PresencaHibernateDAO getDao() {
		return new PresencaHibernateDAO();
	}

}
