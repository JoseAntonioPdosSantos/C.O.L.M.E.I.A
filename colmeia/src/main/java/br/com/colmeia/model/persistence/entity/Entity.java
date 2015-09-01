package br.com.colmeia.model.persistence.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@javax.persistence.Entity
public class Entity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName = "entity_seq", initialValue = 1, name = "entity_seq")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="entity_seq") 
	private Long id;
	private String nome;

	public Entity() {
	
	}

	public Entity(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
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

}
