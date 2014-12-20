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
		@NamedQuery(name = "Exame.findExamesDoPaciente", query = "SELECT e FROM Exame e JOIN e.paciente c WHERE c.id = :idpaciente AND e.status = :status "),
		@NamedQuery(name = "Exame.findPacientesPorNome", query = "SELECT c FROM Paciente c INNER JOIN c.pessoa p WHERE p.nome like :nome ORDER BY p.nome"),
		@NamedQuery(name = "Exame.listaVariavelResultado", query = "SELECT r FROM Exame e JOIN e.resultado r WHERE e.id = :idexame"),
		@NamedQuery(name = "Exame.findExamePac", query = "select e from Exame e JOIN e.paciente p where p.id = :idPac")})
public class Exame implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "DT_VALIDADE")
	private Date dtValidade;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true, name = "DT_REALIZACAO")
	private Date dtRealizacao;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "DT_CRIACAO")
	private Date dtCriacao;
	
	@Column(nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "AGENDA_ID")
	private Agenda agenda;

	@ManyToOne
	@JoinColumn(name = "PACIENTE_ID")
	private Paciente paciente;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROCEDIMENTO_ID")
	private Procedimento procedimento;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "exame")
	private List<Resultado> resultado;

	// Getters e Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(Date dtValidade) {
		this.dtValidade = dtValidade;
	}

	public Date getDtRealizacao() {
		return dtRealizacao;
	}

	public void setDtRealizacao(Date dtRealizacao) {
		this.dtRealizacao = dtRealizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public List<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(List<Resultado> resultado) {
		this.resultado = resultado;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
}
