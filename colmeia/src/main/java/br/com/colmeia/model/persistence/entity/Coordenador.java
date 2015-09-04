/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.colmeia.model.persistence.entity;

import java.sql.Date;

/**
 *
 * @author CaioAvell G1511 FIRE
 */
//@javax.persistence.Entity
public class Coordenador extends Usuario {
	private static final long serialVersionUID = 1L;

	private Date dataCadastroCoordenador;

	public Date getDataCadastroCoordenador() {
		return dataCadastroCoordenador;
	}

	public void setDataCadastroCoordenador(Date dataCadastroCoordenador) {
		this.dataCadastroCoordenador = dataCadastroCoordenador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
