package br.com.colmeia.model.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class AtividadeEvento extends EntidadeBase {

	private static final long serialVersionUID = 55820150624622989L;
	@Id
	@SequenceGenerator(sequenceName = "atividade_evento_seq", initialValue = 1, name = "atividade_evento_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "atividade_evento_seq")
	private Long id;
	@Column
	private String nome;
	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Evento evento;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicial;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;
	private Integer quantidadeInscritos;
	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Palestrante palestrante;
	private String descricao;
	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Ingresso ingresso;
	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private Sala sala;

	@Transient
	private boolean usuarioInscrito;

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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Integer getQuantidadeInscritos() {
		return quantidadeInscritos;
	}

	public void setQuantidadeInscritos(Integer quantidadeInscritos) {
		this.quantidadeInscritos = quantidadeInscritos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Palestrante getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

	public boolean isUsuarioInscrito() {
		return usuarioInscrito;
	}

	public void setUsuarioInscrito(boolean usuarioInscrito) {
		this.usuarioInscrito = usuarioInscrito;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
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
		AtividadeEvento other = (AtividadeEvento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
