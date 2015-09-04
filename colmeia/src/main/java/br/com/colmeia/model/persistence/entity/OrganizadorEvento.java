/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.colmeia.model.persistence.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Takeshi
 */
//@Entity
public class OrganizadorEvento implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @SequenceGenerator(sequenceName = "organizador_evento_seq", initialValue = 1, name = "organizador_evento_seq")
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "organizador_evento_seq")
    private Long id;
    private Organizador organizador;
    private Evento evento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }


}
