package com.prova.mirante.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@NamedQueries({
	@NamedQuery(name = "operador.login", query = "select o from Operador o where o.login = :login and o.senha = :senha"),
	@NamedQuery(name = "operador.all", query = "select o from Operador o"),
	@NamedQuery(name = "operador.id", query = "select o from Operador o where o.idOperador = :idOperador")
})
public class Operador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperador;
	
	@Size(min = 1, max = 100)
	@Pattern(regexp = "^([^0-9]*)$")
	private String nome;
	
	@Size(min = 1, max = 15)
	@Pattern(regexp = "^[a-zA-Z_-]*$")
	private String login;
	
	@Size(min = 6, max = 15)
	@Pattern(regexp = "^([^\\s]*)$")
	private String senha;
	
	@ManyToOne
	private Perfil perfil;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistro;
	
	@Transient
	private String token;

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
