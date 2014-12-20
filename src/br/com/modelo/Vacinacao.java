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
	@NamedQuery(name = "Vacinacao.cadernetaDoPaciente", query = "SELECT v FROM Vacinacao v JOIN v.paciente p WHERE p.id = :idpaciente"),
	@NamedQuery(name = "Vacinacao.findPacientePorNome", query = "SELECT c FROM Paciente c JOIN c.pessoa p WHERE p.nome LIKE :nome")
})
public class Vacinacao implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 
	@Column(nullable=false)
	private String status;
	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="DT_HR_APLICACAO")
	private Date dtHrAplicacao;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true,name="DT_PREVISTA_APLICACAO")
	private Date dtPrevistaAplicacao;
	
	@Column(nullable=false,name="NUM_DOSE")
	private int numDose;
	 
	@Temporal(TemporalType.DATE)
	@Column(nullable=true,name="PROXIMA_DOSE")
	private Date proximaDose;
	 
	@ManyToOne
	@JoinColumn(name="ID_ENFERMEIRO")
	private Funcionario enfermeiro;
	 
	@ManyToOne
	@JoinColumn(name="ID_PACIENTE")
	private Paciente paciente;
	 
	@ManyToOne
	@JoinColumn(name="ID_VACINA")
	private Vacina vacina;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDtHrAplicacao() {
		return dtHrAplicacao;
	}

	public void setDtHrAplicacao(Date dtHrAplicacao) {
		this.dtHrAplicacao = dtHrAplicacao;
	}

	public int getNumDose() {
		return numDose;
	}

	public void setNumDose(int numDose) {
		this.numDose = numDose;
	}

	public Date getProximaDose() {
		return proximaDose;
	}

	public void setProximaDose(Date proximaDose) {
		this.proximaDose = proximaDose;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Funcionario getEnfermeiro() {
		return enfermeiro;
	}

	public void setEnfermeiro(Funcionario enfermeiro) {
		this.enfermeiro = enfermeiro;
	}

	public Date getDtPrevistaAplicacao() {
		return dtPrevistaAplicacao;
	}

	public void setDtPrevistaAplicacao(Date dtPrevistaAplicacao) {
		this.dtPrevistaAplicacao = dtPrevistaAplicacao;
	}	 
}
 
