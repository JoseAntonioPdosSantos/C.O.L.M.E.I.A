package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import br.com.colmeia.model.persistence.dao.imp.EventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Evento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class EventoService extends Service<Evento,Long,EventoHibernateDAO>{

    public boolean validarEntity(Evento entity) {
    	return true;
    }

	@Override
	public List<Evento> buscar(Evento entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
