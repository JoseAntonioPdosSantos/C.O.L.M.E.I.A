package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.TipoAtividade;
import br.com.colmeia.model.service.implementacao.TipoAtividadeService;

@ManagedBean
@ViewScoped
public class TipoAtividadeController extends Controller<TipoAtividade,TipoAtividadeService>{


	private static final long serialVersionUID = 2581628615604632657L;

	@Override
	protected void inicializarVariavel() {
		limpar();
	}
	
	@Override
	public void limpar() {
		entidade = new TipoAtividade();
		setEditando_registro(false);
	}

	@Override
	public TipoAtividadeService getService() {
		return new TipoAtividadeService();
	}
	
	@Override
	public void setService(TipoAtividadeService serivce) {
		this.service = serivce;
	}
}
