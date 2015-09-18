package br.com.colmeia.controller.implementacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.colmeia.controller.generics.Controller;
import br.com.colmeia.model.persistence.entity.Usuario;
import br.com.colmeia.model.persistence.entity.UsuarioEvento;
import br.com.colmeia.model.persistence.service.implementacao.UsuarioEventoService;

@ManagedBean
@ViewScoped
public class MeusCertificadosController extends Controller<UsuarioEvento, UsuarioEventoService> {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	@Override
	protected void inicializarVariavel() {
		usuario = getCurrentInstanceUser();
		limpar();
	}

	public void gerarCertificado(UsuarioEvento usuarioEvento) {
		// TODO gerar certificado
	}

	public void limpar() {
		entidade = new UsuarioEvento();
		setEditando_registro(false);
	}

	@Override
	public void buscar() {
		entidade.setUsuario(usuario);
		entidade.setPresenca(true);
		super.buscar();
	}

	public UsuarioEventoService getService() {
		return new UsuarioEventoService();
	}

	public void setService(UsuarioEventoService service) {
		this.service = service;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
