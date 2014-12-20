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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "Paciente.findByCODSUS", query = "SELECT p.id FROM Paciente p WHERE p.cns = :cns"),
		@NamedQuery(name = "Paciente.findPacienteByNome", query = "SELECT p FROM Paciente p JOIN p.pessoa pe WHERE pe.nome like :nome"),
		@NamedQuery(name = "Paciente.findByPessoa", query = "SELECT p FROM Paciente p WHERE p.pessoa.id = :idPessoa"),
		@NamedQuery(name = "Paciente.findByNome", query = "SELECT p FROM Paciente p WHERE p.pessoa.ind_paciente = 1 and p.pessoa.nome like :nome and p.pessoa.id not in ("
				+ "SELECT pa.id from Agenda a JOIN a.medico m, a.paciente pa where m.id=:idmedico and a.data=:data and a.status not in ('Excluído'))"),
		@NamedQuery(name = "Paciente.findByNomeEncaixe", query = "SELECT p FROM Paciente p WHERE p.pessoa.ind_paciente = 1 and p.pessoa.nome like :nome and p.id not in (select a.paciente.id from Agenda a JOIN a.ubs u, a.medico m " +
				"where a.data = :data and u.id = :idubs and m.id = :idmedico)")})
public class Paciente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String cns;

	@Column(nullable = true)
	private String observacoes;

	@Column(nullable = true)
	private int quantFalta;

	@Column(nullable = true)
	private boolean indPNE;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "paciente")
	private List<Agenda> agenda;

	@OneToOne (cascade = CascadeType.ALL, mappedBy = "paciente")
	 private Prontuario prontuario;
	 
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "paciente")
	 private List<Receita> receita;
	 

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "paciente")
	private List<Vacinacao> vacinacao;

	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "paciente")
	private List<Exame> exame;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "paciente")
	private List<ReceitaExterna> receitaExterna;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String codigoSUS) {
		this.cns = codigoSUS;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public int getQuantFalta() {
		return quantFalta;
	}

	public void setQuantFalta(int quantFalta) {
		this.quantFalta = quantFalta;
	}

	public boolean isIndPNE() {
		return indPNE;
	}

	public void setIndPNE(boolean indPNE) {
		this.indPNE = indPNE;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public List<Vacinacao> getVacinacao() {
		return vacinacao;
	}

	public void setVacinacao(List<Vacinacao> vacinacao) {
		this.vacinacao = vacinacao;
	}

	public List<ReceitaExterna> getReceitaExterna() {
		return receitaExterna;
	}

	public void setReceitaExterna(List<ReceitaExterna> receitaExterna) {
		this.receitaExterna = receitaExterna;
	}

	public List<Receita> getReceita() {
		return receita;
	}

	public void setReceita(List<Receita> receita) {
		this.receita = receita;
	}

	public List<Exame> getExame() {
		return exame;
	}

	public void setExame(List<Exame> exame) {
		this.exame = exame;
	}

}
