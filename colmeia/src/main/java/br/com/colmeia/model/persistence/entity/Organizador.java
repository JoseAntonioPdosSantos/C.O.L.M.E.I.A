package br.com.colmeia.model.persistence.entity;

import java.sql.Date;

//@javax.persistence.Entity
public class Organizador extends Usuario {

	private static final long serialVersionUID = 1L;

	private Date dataCadastroOrganizador;

	public Date getDataCadastroOrganizador() {
		return dataCadastroOrganizador;
	}

	public void setDataCadastroOrganizador(Date dataCadastroOrganizador) {
		this.dataCadastroOrganizador = dataCadastroOrganizador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
