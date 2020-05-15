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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@NamedQueries({
	@NamedQuery(name = "pessoa.all", query = "select p from Pessoa p"),
	@NamedQuery(name = "pessoa.id", query = "select p from Pessoa p where p.idPessoa = :idPessoa")
})
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPessoa;
	
	@Size(min = 1, max = 100)
	@Pattern(regexp = "^([^0-9]*)$")
	private String nome;
	
	@Size(min = 11, max = 14)
	private String documento;
	
	@Temporal(TemporalType.DATE)
	private Date dtNascimento;
	
	@Size(min = 1, max = 100)
	@Pattern(regexp = "^([^0-9]*)$")
	private String nomeMae;
	
	@Size(min = 1, max = 100)
	@Pattern(regexp = "^([^0-9]*)$")
	private String nomePai;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistro;
	
	@ManyToOne
	private Operador operador;
	
	private String tipo;

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
