package br.com.colmeia.model.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class EntidadeBase implements Serializable {

	private static final long serialVersionUID = -2896653165482488556L;
	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	protected Usuario usuarioAlteracao;
	protected Boolean ativo;
	@Version
	protected Timestamp versao;

	public abstract void setId(Long id);

	public abstract Long getId();

	public Timestamp getVersao() {
		return versao;
	}

	public void setVersao(Timestamp versao) {
		this.versao = versao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public boolean isAtivo() {
		if (ativo == null)
			ativo = false;
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
