/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.colmeia.model.persistence.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Takeshi
 */
@Entity
public class Evento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @SequenceGenerator(sequenceName = "evento_seq", initialValue = 1, name = "evento_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "evento_seq")
    private Long id;
    private String nome;
    private Coordenador coordenador;
    private Date dtini;
    private Date dtfim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public Date getDtini() {
        return dtini;
    }

    public Date getDtfim() {
        return dtfim;
    }

}
