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
    @Column(nullable=false,unique=true)
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
		if(estacio == null)
			return false;
		return estacio;
	}

	public void setEstacio(Boolean estacio) {
		this.estacio = estacio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
