package br.com.colmeia.model.persistence.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
//

@javax.persistence.Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @SequenceGenerator(sequenceName = "usuario_seq", initialValue = 1, name = "usuario_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_seq")
    private Long id;
    private String cpf;
    private String ra;
    private String nome;
    private String email;
    private String senha;
    private Date data;
    private Curso curso;
    private Instituicao instituicao;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
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

  

}
