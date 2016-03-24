package br.com.colmeia.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Instituicao extends EntidadeBase {

	private static final long serialVersionUID = 587021967698553209L;
	@Id
	@SequenceGenerator(sequenceName = "instituicao_seq", initialValue = 1, name = "instituicao_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "instituicao_seq")
	private Long id;
	@Column
	private String nome;
	@Column
	private Boolean estacio;

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

	public Boolean getEstacio() {
		return estacio;
	}

	public Boolean isEstacio() {
		if (estacio == null)
			return false;
		return estacio;
	}

	public void setEstacio(Boolean estacio) {
		this.estacio = estacio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Instituicao other = (Instituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
