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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReceitaExterna implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int quant;
	
	@Column(nullable = false)
	private String medico;
	
	@Column(nullable = false)
	private String local;
	
	@Column(nullable = false)
	private int crm;
	
	@Temporal(TemporalType.DATE)
	private Date dtSolicitacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dtRetirada;
	
	@ManyToOne
	@JoinColumn(name = "ID_PACIENTE")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "ID_MEDICAMENTO")
	private Medicamento medicamento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public Date getDtSolicitacao() {
		return dtSolicitacao;
	}

	public void setDtSolicitacao(Date dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
	}

	public Date getDtRetirada() {
		return dtRetirada;
	}

	public void setDtRetirada(Date dtRetirada) {
		this.dtRetirada = dtRetirada;
	}
	
}
