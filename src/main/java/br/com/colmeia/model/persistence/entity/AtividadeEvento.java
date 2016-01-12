package br.com.colmeia.model.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class AtividadeEvento extends EntidadeBase {


	private static final long serialVersionUID = 55820150624622989L;
	@Id
	@SequenceGenerator(sequenceName = "atividade_evento_seq", initialValue = 1, name = "atividade_evento_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "atividade_evento_seq")
	private Long id;
	@Column(nullable = false)
	private String nome;
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Evento evento;
	private Timestamp dataInicial;
	private Timestamp dataFinal;
	private Integer quantidadeInscritos;
	@Column(nullable = false)
	private Palestrante palestrante;
	private String descricao;
	@Column(nullable = false)
	private Ingresso ingresso;
	@Column(nullable = false)
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

	public Timestamp getDataInicial() {
		return dataInicial;
	}

	public java.util.Date getDataInicial_() {
		if (dataInicial != null)
			return new java.util.Date(dataInicial.getTime());
		return null;
	}

	public void setDataInicial_(java.util.Date dataInicial) {
		if (dataInicial != null)
			this.dataInicial = new Timestamp(dataInicial.getTime());
	}

	public void setDataInicial(Timestamp dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Timestamp getDataFinal() {
		return dataFinal;
	}

	public java.util.Date getDataFinal_() {
		if (dataFinal != null)
			return new java.util.Date(dataFinal.getTime());
		return null;
	}

	public void setDataFinal_(java.util.Date dataFinal) {
		if (dataFinal != null)
			this.dataFinal = new Timestamp(dataFinal.getTime());
	}

	public void setDataFinal(Timestamp dataFinal) {
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

}