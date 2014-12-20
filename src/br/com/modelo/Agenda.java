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
	@NamedQuery(name = "Agenda.findHorarios", query = "SELECT a FROM Agenda a JOIN a.medico m WHERE a.data = :data and m.id = :idmed"),
	@NamedQuery(name = "Agenda.findPorMedicoData", query = "SELECT a FROM Agenda a JOIN a.medico m WHERE a.data = :data and m.id = :idmed"),
	@NamedQuery(name = "Agenda.findHorariosMedicoUBSEsp", query = "SELECT a FROM Agenda a JOIN a.medico m, a.especialidade e, a.ubs u WHERE a.data = :data and m.id = :idmed and e.id=:idesp and u.id=:idubs"),
	@NamedQuery(name = "Agenda.findHorariosLivres", query = "SELECT a FROM Agenda a JOIN a.medico m, a.especialidade e, a.ubs u WHERE a.data = :data and m.id = :idmed and e.id=:idesp and u.id=:idubs and a.paciente is NULL and a.status = 'Disponível'"),
	@NamedQuery(name = "Agenda.findHorariosByUBS", query = "SELECT a FROM Agenda a JOIN a.medico m, a.ubs u WHERE a.data = :data and m.id = :idmed and u.id=:idubs"),
	@NamedQuery(name = "Agenda.horariosLivresPaciente", query = "SELECT a FROM Agenda a JOIN a.medico m, a.especialidade e, a.ubs u WHERE a.data = :data and m.id = :idmed and e.id=:idesp and u.id=:idubs and (a.paciente.id = :idpaciente or a.paciente = NULL)")})
public class Agenda implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	 
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;
	 
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	private Date horaInicio;
	 
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	private Date horaFim;
	
	@Column(nullable=false)
	private String status;
	 
	@Column(nullable=false)
	private boolean tagOnline;
	 
	@Column(nullable=true)
	private String justCancelamento;
	
	@Column(nullable=true)
	private String justExclusao;
	 
	@ManyToOne
	@JoinColumn(name="ID_ATENDENTE")
	private Funcionario atendente;
	 
	@ManyToOne
	@JoinColumn(name="ID_PACIENTE")
	private Paciente paciente;
	 
	@ManyToOne
	@JoinColumn(name="ID_MEDICO")
	private Funcionario medico;
	 
	@ManyToOne
	@JoinColumn(name="ID_UBS")
	private UBS ubs;
	
	@ManyToOne
	@JoinColumn(name="ID_ESP")
	private Especialidade especialidade;
	
	@OneToMany
	(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "agenda")
	private List<Receita> receita;
	 
	@OneToMany
	(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "agenda")
	private List<Exame> exame;
	
	
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio2) {
		this.horaInicio = horaInicio2;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getTagOnline() {
		return tagOnline;
	}

	public void setTagOnline(boolean b) {
		this.tagOnline = b;
	}

	public String getJustCancelamento() {
		return justCancelamento;
	}

	public void setJustCancelamento(String justCancelamento) {
		this.justCancelamento = justCancelamento;
	}

	public Funcionario getAtendente() {
		return atendente;
	}

	public void setAtendente(Funcionario atendente) {
		this.atendente = atendente;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Funcionario getMedico() {
		return medico;
	}

	public void setMedico(Funcionario medico) {
		this.medico = medico;
	}

	public UBS getUbs() {
		return ubs;
	}

	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getJustExclusao() {
		return justExclusao;
	}

	public void setJustExclusao(String justExclusao) {
		this.justExclusao = justExclusao;
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
 
