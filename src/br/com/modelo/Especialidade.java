package br.com.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "UBS.findByName", query = "SELECT e FROM Especialidade e WHERE e.nome like :nome ORDER BY e.nome"),
	@NamedQuery(name = "UBS.findEspecialidadePorUBS", query = "SELECT e FROM Especialidade e WHERE e.nome like :nome ORDER BY e.nome")})
public class Especialidade implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=30, nullable=false)
	private String nome;
	
	@ManyToMany(mappedBy="especialidade")
	private List <Funcionario> func;

	@OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "especialidade")
	private List<Agenda> agenda;
	
//	Getters e Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Funcionario> getFunc() {
		return func;
	}

	public void setFunc(List<Funcionario> func) {
		this.func = func;
	}

	public List<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}
}

 
