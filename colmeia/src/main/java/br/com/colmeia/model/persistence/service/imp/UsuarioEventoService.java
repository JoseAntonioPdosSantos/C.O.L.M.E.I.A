package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import br.com.colmeia.model.persistence.dao.imp.UsuarioEventoHibernateDAO;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.generics.Service;

public class UsuarioEventoService extends Service<UsuarioEvento,Long,UsuarioEventoHibernateDAO>{

    public boolean validarEntity(UsuarioEvento entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public List<UsuarioEvento> buscar(UsuarioEvento entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDao() {
		dao = new UsuarioEventoHibernateDAO();
	}


}
