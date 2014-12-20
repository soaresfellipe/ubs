package br.com.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
		@NamedQuery(name = "Receita.findRecPac", query = "select r from Receita r JOIN r.paciente p where p.id = :idPac"),
		@NamedQuery(name = "Receita.findRecUBSRetirar", query = "select r from Receita r JOIN r.agenda a where a.ubs.id= :idUBS and r.status = 'Pendente'  and r.tipoAtendimento = 'Farmácia UBS' and r.dtValidade>=:dtValidade"),
		@NamedQuery(name = "Receita.findRecUBSAplicar", query = "select r from Receita r JOIN r.agenda a where a.ubs.id= :idUBS and r.status = 'Pendente'  and r.tipoAtendimento = 'Enfermaria' and r.dtValidade>=:dtValidade")})
public class Receita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dtCriacao;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dtValidade;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date dtEncerramento;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private String tipoAtendimento;

	@Column(nullable = false)
	private int quant;

	@Column(nullable = false)
	private String descricao;


	@ManyToOne
	@JoinColumn(name = "ID_ATENDENTE")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "ID_MEDICAMENTO")
	private Medicamento medicamento;

	@ManyToOne
	@JoinColumn(name = "ID_AGENDA")
	private Agenda agenda;

	@ManyToOne
	@JoinColumn(name = "ID_PACIENTE")
	private Paciente paciente;

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Date getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}
	
}
