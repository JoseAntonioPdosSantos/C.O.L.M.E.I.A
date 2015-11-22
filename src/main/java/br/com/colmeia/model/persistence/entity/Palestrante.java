package br.com.colmeia.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Palestrante extends EntidadeBase {

	private static final long serialVersionUID = 4844950168823560860L;
	@Id
	@SequenceGenerator(sequenceName = "palestrante_seq", initialValue = 1, name = "palestrante_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "palestrante_seq")
	private Long id;
	@Column(nullable = false)
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
