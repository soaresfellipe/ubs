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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity(name="GRUPO_PROCEDIMENTO")
@NamedQueries({ @NamedQuery(name = "GrupoProcedimento.findByName", query = "SELECT g FROM GRUPO_PROCEDIMENTO g WHERE g.nome like :nome ORDER BY g.nome") })
public class GrupoProcedimento implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, name="CODIGO_TUSS")
	private int codigoTuss;
	
	@Column(nullable=false)
	private String nome;
	 
	@OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "grupo")
	private List <Procedimento> procedimento;

	
	//Getters e Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigoTuss() {
		return codigoTuss;
	}

	public void setCodigoTuss(int codigoTuss) {
		this.codigoTuss = codigoTuss;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Procedimento> getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(List<Procedimento> procedimento) {
		this.procedimento = procedimento;
	}
	 	 
}
 
