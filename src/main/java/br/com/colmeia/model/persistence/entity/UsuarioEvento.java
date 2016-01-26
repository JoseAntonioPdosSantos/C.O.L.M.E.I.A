package br.com.colmeia.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class UsuarioEvento extends EntidadeBase {

	private static final long serialVersionUID = 1189918725646039730L;
	@Id
	@SequenceGenerator(sequenceName = "usuario_evento_seq", initialValue = 1, name = "usuario_evento_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_evento_seq")
	private Long id;
	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private AtividadeEvento atividadeEvento;
	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	private Boolean presenca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AtividadeEvento getAtividadeEvento() {
		return atividadeEvento;
	}

	public void setAtividadeEvento(AtividadeEvento atividadeEvento) {
		this.atividadeEvento = atividadeEvento;
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

	public Boolean getPresenca() {
		return presenca;
	}

	public boolean isPresenca_() {
		if (presenca == null)
			presenca = false;
		return presenca;
	}

	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEvento other = (UsuarioEvento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
