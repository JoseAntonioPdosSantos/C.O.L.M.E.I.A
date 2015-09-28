package br.com.colmeia.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TipoAtividade extends EntidadeBase {


	private static final long serialVersionUID = 8311941389888951493L;
	@Id
	@SequenceGenerator(sequenceName = "tipo_atividade_seq", initialValue = 1, name = "tipo_atividade_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tipo_atividade_seq")
	private Long id;
	@Column(nullable = false, unique = true)
	private String nome;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
