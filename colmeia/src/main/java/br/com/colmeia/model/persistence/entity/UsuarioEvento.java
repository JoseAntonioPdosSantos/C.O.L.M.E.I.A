package br.com.colmeia.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Avell B155 MAX
 */
@Entity
public class UsuarioEvento extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName = "usuario_evento_seq", initialValue = 1, name = "usuario_evento_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_evento_seq")
	private Long id;
	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Evento evento;
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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
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
		if(presenca == null)
			presenca = false;
		return presenca;
	}

	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}

}
