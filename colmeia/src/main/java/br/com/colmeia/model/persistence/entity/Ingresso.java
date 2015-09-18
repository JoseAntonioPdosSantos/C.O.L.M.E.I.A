package br.com.colmeia.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Ingresso extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName = "ingresso_seq", initialValue = 1, name = "ingresso_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ingresso_seq")
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
