package br.com.colmeia.model.persistence.service.imp;

import java.util.List;

import br.com.colmeia.model.persistence.dao.imp.InstituicaoHibernateDAO;
import br.com.colmeia.model.persistence.entity.Instituicao;
import br.com.colmeia.model.persistence.service.generics.Service;

public class InstituicaoService extends Service<Instituicao,Long,InstituicaoHibernateDAO>{

    public boolean validarEntity(Instituicao entity) {
    	return false;
    }

	@Override
	public List<Instituicao> buscar(Instituicao entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDao() {
		// TODO Auto-generated method stub
		
	}


}
