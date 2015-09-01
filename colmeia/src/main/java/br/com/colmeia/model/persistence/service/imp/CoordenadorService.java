package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import br.com.colmeia.model.persistence.dao.imp.CoordenadorHibernateDAO;
import br.com.colmeia.model.persistence.entity.Coordenador;
import br.com.colmeia.model.persistence.service.generics.Service;

public class CoordenadorService extends Service<Coordenador,Long,CoordenadorHibernateDAO>{

    public boolean validarEntity(Coordenador entity) {
    	return false;
    }

	@Override
	public List<Coordenador> buscar(Coordenador entity) throws Exception {
		return null;
	}

	@Override
	public CoordenadorHibernateDAO getDao() {
		// TODO Auto-generated method stub
		return null;
	}





}
