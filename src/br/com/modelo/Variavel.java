package br.com.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Variavel.findByName", query = "SELECT v FROM Variavel v WHERE v.nome like :nome ORDER BY v.nome") })
public class Variavel implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 
	@Column(nullable=false)
	private String nome;
	 
	@OneToMany(mappedBy = "variavel")
	private List<Resultado> resultado; 
	
	@ManyToMany(mappedBy = "variavel")
	private List<Procedimento> procedimento;
	
	//Getters e Setters
	
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

//	public List<Resultado> getResultado() {
//		return resultado;
//	}
//
//	public void setResultado(List<Resultado> resultado) {
//		this.resultado = resultado;
//	}

	public List<Procedimento> getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(List<Procedimento> procedimento) {
		this.procedimento = procedimento;
	}

	public List<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(List<Resultado> resultado) {
		this.resultado = resultado;
	}
}