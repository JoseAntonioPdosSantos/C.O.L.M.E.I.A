package br.com.colmeia.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Junior
 */
@Entity
public class Curso extends EntidadeBase {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(sequenceName = "curso_seq", initialValue = 1, name = "curso_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "curso_seq")
    private Long id;
    @Column(nullable=false,unique=true)
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

}
