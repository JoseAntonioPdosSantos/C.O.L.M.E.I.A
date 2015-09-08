package br.com.colmeia.model.persistence.entity;

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
public class Instituicao extends EntidadeBase {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(sequenceName = "instituicao_seq", initialValue = 1, name = "instituicao_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "instituicao_seq")
    private Long id;
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
