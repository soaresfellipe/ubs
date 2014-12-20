package br.com.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
	@NamedQuery(name = "Medicamento.findByNomeMed", query = "SELECT p FROM Medicamento p where p.nome like :nome and p.tipoMedicamento = 1"),
	@NamedQuery(name = "Medicamento.findByNomeMedUBS", query = "SELECT p FROM Medicamento p JOIN p.ubs u where p.nome like :nome and p.tipoMedicamento = 1 and u.id = :idubs"),
	@NamedQuery(name = "Medicamento.findMed", query = "SELECT m FROM Medicamento m where m.tipoMedicamento = 1 and m.ubs.id=:idubs"),
	@NamedQuery(name = "Medicamento.findMedOutrasUBS", query = "SELECT m FROM Medicamento m where m.tipoMedicamento = 1 and m.ubs.id <> :idubs")})
public class Medicamento extends Produto implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=true)
	private String classificacao;
	
	@OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "medicamento")
	private List<Receita> receita;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "medicamento")
	private List<ReceitaExterna> receitaExterna;
	

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public List<Receita> getReceita() {
		return receita;
	}

	public void setReceita(List<Receita> receita) {
		this.receita = receita;
	}
	
}
 
