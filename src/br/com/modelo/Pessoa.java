package br.com.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome like :nome ORDER BY p.nome"),
	@NamedQuery(name = "Pessoa.findByCPF", query = "SELECT p.id FROM Pessoa p WHERE p.cpf = :cpf "),
	@NamedQuery(name = "Pessoa.validaLogin", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf AND p.senha = :senha and p.dtNascimento = :dataNasc")})
public class Pessoa implements Serializable {
 
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 
	@Column(nullable=false)
	private String cpf;
	 
	@Column(nullable=true)
	private String rg;
	 
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=true)
	private String celular;

	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date dtNascimento;
	 
	@Column(nullable=true)
	private String logradouro;
	 
	@Column(nullable=true)
	private String cidade;
	 
	@Column(nullable=true)
	private String bairro;
	 
	@Column(nullable=true)
	private String uf;
	 
	@Column(nullable=true)
	private String cep;
	
	@Column(nullable=true)
	private String telefone;
	 
	@Column(nullable=true)
	private String senha;
	 
	@Column(nullable=true)
	private int ind_adm;
	
	@Column(nullable=true)
	private int ind_paciente;
	
	@Column(nullable=true)
	private int ind_func;
	
	@Column(nullable=true)
	private boolean ind_dependente;

	@OneToOne (cascade = CascadeType.ALL, mappedBy = "pessoa")
	private Paciente paciente; 
	 
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "pessoa")
	private Funcionario func;

	
	//Getters e Setters
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Funcionario getFunc() {
return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getInd_adm() {
		return ind_adm;
	}

	public void setInd_adm(int ind_adm) {
		this.ind_adm = ind_adm;
	}

	public int getInd_paciente() {
		return ind_paciente;
	}

	public void setInd_paciente(int ind_paciente) {
		this.ind_paciente = ind_paciente;
	}

	public int getInd_func() {
		return ind_func;
	}

	public void setInd_func(int ind_func) {
		this.ind_func = ind_func;
	}

	public boolean isInd_dependente() {
		return ind_dependente;
	}

	public void setInd_dependente(boolean ind_dependente) {
		this.ind_dependente = ind_dependente;
	}

	
	 	 
}
 
