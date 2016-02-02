package br.com.colmeia.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@javax.persistence.Entity
public class Usuario extends EntidadeBase {

	private static final long serialVersionUID = -3864722570142381907L;
	@Id
	@SequenceGenerator(sequenceName = "usuario_seq", initialValue = 1, name = "usuario_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_seq")
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column
	private String ra;
	@Column
	private String email;
	@Column(nullable = false)
	private String senha;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Curso curso;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Instituicao instituicao;
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Perfil perfil;

	@Transient
	private String confirmarSenha;
	@Transient
	private boolean alunoEstacio;
	@Transient
	private boolean universitario;

	public Usuario() {
	}

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

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf != null)
			cpf = cpf.replace(".", "").replace("-", "");
		this.cpf = cpf;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public boolean isAlunoEstacio() {
		return alunoEstacio;
	}

	public void setAlunoEstacio(boolean alunoEstacio) {
		this.alunoEstacio = alunoEstacio;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public boolean isUniversitario() {
		return universitario;
	}

	public void setUniversitario(boolean universitario) {
		this.universitario = universitario;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
