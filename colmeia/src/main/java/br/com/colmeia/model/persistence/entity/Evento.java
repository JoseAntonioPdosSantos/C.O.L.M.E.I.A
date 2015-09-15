package br.com.colmeia.model.persistence.entity;

import java.sql.Date;

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
 * @author José Antonio
 */
@Entity
public class Evento extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName = "evento_seq", initialValue = 1, name = "evento_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "evento_seq")
	private Long id;
	private String nome;
	@JoinColumn(nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario coordenador;
	private Date dtini;
	private Date dtfim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	public Date getDtini() {
		return dtini;
	}

	public void setDtini(Date dtini) {
		this.dtini = dtini;
	}

	public Date getDtfim() {
		return dtfim;
	}

	public void setDtfim(Date dtfim) {
		this.dtfim = dtfim;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public java.util.Date getDtini_() {
		if (dtini != null)
			return new java.util.Date(dtini.getTime());
		return null;
	}

	public void setDtini_(java.util.Date dtini) {
		if (dtini != null)
			this.dtini = new Date(dtini.getTime());
	}

	public java.util.Date getDtfim_() {
		if (dtfim != null)
			return new java.util.Date(dtfim.getTime());
		return null;
	}

	public void setDtfim_(java.util.Date dtfim) {
		if (dtfim != null)
			this.dtfim = new Date(dtfim.getTime());
	}

}
