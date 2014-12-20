package br.com.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p where p.nome like :nome and p.tipoMedicamento = 0")})
public class Produto implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	 
	@Column(nullable=false)
	private String nome;
	 
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dtValidade;
	 
	@Column(nullable=false)
	private String unidadeMedida;
	 
	@Column(nullable=false)
	private int quantAtual;
	 
	@Column(nullable=false)
	private int quantMin;
	 
	@Column(nullable=false)
	private boolean tipoMedicamento;

	@OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "produto")
	private List<Item> item;
	 	 
	@ManyToOne
	@JoinColumn(name="ID_UBS")
	private UBS ubs;
	
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

	public Date getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(Date dtValidade) {
		this.dtValidade = dtValidade;
	}

	
	public int getQuantAtual() {
		return quantAtual;
	}

	public void setQuantAtual(int quantAtual) {
		this.quantAtual = quantAtual;
	}

	public int getQuantMin() {
		return quantMin;
	}

	public void setQuantMin(int quantMin) {
		this.quantMin = quantMin;
	}

	public boolean isTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(boolean tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public UBS getUbs() {
		return ubs;
	}

	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}
	 
}
 
