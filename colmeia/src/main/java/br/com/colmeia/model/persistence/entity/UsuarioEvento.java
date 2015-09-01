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
 * @author Avell B155 MAX
 */
@Entity
public class UsuarioEvento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @SequenceGenerator(sequenceName = "usuario_evento_seq", initialValue = 1, name = "usuario_evento_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_evento_seq")
    private Long id;
    private Evento evento;
    private Date datacadastro;
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void getEvento(Evento evento) {
        this.evento = evento;
    }

    public Date getDate() {
        return datacadastro;
    }

    public void setDate(Date datacadastro) {
        this.datacadastro = datacadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
