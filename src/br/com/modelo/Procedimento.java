package br.com.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Procedimento.findByNome", query = "SELECT p FROM Procedimento p WHERE p.nome like :nome ORDER BY p.nome"),
	@NamedQuery(name = "Procedimento.variaveisDoProc", query = "SELECT v from Procedimento p JOIN p.variavel v WHERE p.id = :idproc"),
	@NamedQuery(name = "Procedimento.variaveisNaoProc", query = "SELECT v from Variavel v WHERE v.id NOT IN (SELECT var.id from Procedimento p JOIN p.variavel var WHERE p.id = :idproc)")
})
public class Procedimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false,length=50)
	private String nome;
	 
	@Column(nullable=false,name="COD_TUSS")
	private int codTUSS;
	 
	@Column(nullable=false,name="COD_AMB")
	private int codAMB;
	 
	@Column(nullable=false,name="VALOR_REFERENCIA")
	private String valorReferencia;

	@OneToMany(mappedBy="procedimento")
	private List<Exame> exame;
	 
	@ManyToOne
	@JoinColumn(name="GRUPO_ID")
	private GrupoProcedimento grupo;
	
	@ManyToMany
	@JoinTable(name = "PROCEDIMENTO_VARIAVEL", joinColumns = @JoinColumn(name = "PROC_ID"),inverseJoinColumns = @JoinColumn(name = "VAR_ID"))
	private List<Variavel> variavel;
	
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

	public int getCodTUSS() {
		return codTUSS;
	}

	public void setCodTUSS(int codTUSS) {
		this.codTUSS = codTUSS;
	}

	public int getCodAMB() {
		return codAMB;
	}

	public void setCodAMB(int codAMB) {
		this.codAMB = codAMB;
	}

	public String getValorReferencia() {
		return valorReferencia;
	}

	public void setValorReferencia(String valorReferencia) {
		this.valorReferencia = valorReferencia;
	}

//	public List<Exame> getExame() {
//		return exame;
//	}
//
//	public void setExame(List<Exame> exame) {
//		this.exame = exame;
//	}

	public GrupoProcedimento getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProcedimento grupo) {
		this.grupo = grupo;
	}
	
	public List<Variavel> getVariavel() {
		return variavel;
	}

	public void setVariavel(List<Variavel> variavel) {
		this.variavel = variavel;
	}

	public List<Exame> getExame() {
		return exame;
	}

	public void setExame(List<Exame> exame) {
		this.exame = exame;
	}

	 
}
 
