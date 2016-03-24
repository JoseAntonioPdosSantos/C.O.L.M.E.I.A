package br.com.colmeia.model.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento extends EntidadeBase {

	private static final long serialVersionUID = 4591691756212749346L;
	@Id
	@SequenceGenerator(sequenceName = "evento_seq", initialValue = 1, name = "evento_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "evento_seq")
	private Long id;
	private String nome;
	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario coordenador;
	@Temporal(TemporalType.DATE)
	private Date dataInicial;
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	@Column
	@OneToMany(fetch=FetchType.LAZY,mappedBy="evento")
	private List<AtividadeEvento> atividadesEvento;
	
	private String email;
    private String telefone;
    
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

	public Usuario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setdataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getdataFinal() {
		return dataFinal;
	}

	public void setdataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public java.util.Date getdataInicial_() {
		if (dataInicial != null)
			return new java.util.Date(dataInicial.getTime());
		return null;
	}

	public void setdataInicial_(java.util.Date dataInicial) {
		if (dataInicial != null)
			this.dataInicial = new Date(dataInicial.getTime());
	}

	public java.util.Date getDataFinal() {
		if (dataFinal != null)
			return new java.util.Date(dataFinal.getTime());
		return null;
	}

	public void setdataFinal_(java.util.Date dataFinal) {
		if (dataFinal != null)
			this.dataFinal = new Date(dataFinal.getTime());
	}

	public List<AtividadeEvento> getAtividadesEvento() {
		return atividadesEvento;
	}

	public void setAtividadesEvento(List<AtividadeEvento> atividadesEvento) {
		this.atividadesEvento = atividadesEvento;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
