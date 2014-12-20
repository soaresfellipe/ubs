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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "Funcionario.findAClinico", query = "SELECT f.id FROM Funcionario f WHERE f.crf = :crf and f.tipoFuncionario = 1 and f.ufConselho = :uf"),
		@NamedQuery(name = "Funcionario.findAtendente", query = "SELECT f.id FROM Funcionario f WHERE f.tipoFuncionario = 2 and f.pessoa = :idPessoa"),
		@NamedQuery(name = "Funcionario.findEnfermeiro", query = "SELECT f.id FROM Funcionario f WHERE f.tipoFuncionario = 3 and f.coren = :coren and f.ufConselho = :uf"),
		@NamedQuery(name = "Funcionario.findEstoquista", query = "SELECT f.id FROM Funcionario f WHERE f.tipoFuncionario = 4 and f.pessoa = :idPessoa"),
		@NamedQuery(name = "Funcionario.findFarmaceutico", query = "SELECT f.id FROM Funcionario f WHERE f.crf = :crf and f.tipoFuncionario = 5 and f.ufConselho = :uf"),
		@NamedQuery(name = "Funcionario.findMedico", query = "SELECT f.id FROM Funcionario f WHERE f.crm = :crm and f.tipoFuncionario = 6 and f.ufConselho = :uf"),
		@NamedQuery(name = "Funcionario.findPessoa", query = "SELECT f.id FROM Funcionario f WHERE f.crm = :crm and f.tipoFuncionario = 6 and f.ufConselho = :uf"),
		@NamedQuery(name = "Funcionario.findFuncByPessoa", query = "SELECT f FROM Funcionario f WHERE f.pessoa.id = :idPessoa"),
		@NamedQuery(name = "Funcionario.findEspFunc", query = "SELECT e from Especialidade e where e.id in (select es.id from Funcionario f JOIN f.ubs u,f.especialidade es where u.id = :idUBS )"),
		@NamedQuery(name = "Funcionario.findMedicoByUBSEsp", query = "SELECT f FROM Funcionario f JOIN f.ubs u, f.especialidade e where u.id=:idubs and e.id=:idesp and f.tipoFuncionario=6"),
		@NamedQuery(name = "Funcionario.findMedicoByEsp", query = "SELECT f FROM Funcionario f JOIN f.especialidade e where e.id=:idesp and f.tipoFuncionario=6"),
		@NamedQuery(name = "Funcionario.findFunc", query = "SELECT f FROM Funcionario f JOIN f.ubs u WHERE u.id = :idubs") })
public class Funcionario implements Serializable {

	/**
	 * Tipo funcionario 1 = analista clinico Tipo funcionario 2 = atendente Tipo
	 * funcionario 3 = enfermeiro Tipo funcionario 4 = estoquista Tipo
	 * funcionario 5 = farmaceutico Tipo funcionario 6 = medico
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int codCBO;

	@Column(nullable = false)
	private int tipoFuncionario;

	@Column(nullable = true)
	private int crf;

	@Column(nullable = true)
	private int crm;

	@Column(nullable = true)
	private int coren;

	@Column(nullable = true)
	private String ufConselho;

	// Flag = 0, apenas se o funcionario não trabalhará mais na UBS, em nenhum
	// cargo,
	// que exija cadastro para utilizar o sistema
	@Column(nullable = true)
	private boolean flagAtivo;

	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	@ManyToMany
	@JoinTable(name = "medico_especialidade", joinColumns = @JoinColumn(name = "medico_id"), inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
	private List<Especialidade> especialidade;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "atendente")
	private List<Agenda> agendaAT;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "medico")
	private List<Agenda> agendaMed;

	@ManyToMany
	@JoinTable(name = "UBS_FUNC", joinColumns = @JoinColumn(name = "func_id"), inverseJoinColumns = @JoinColumn(name = "ubs_id"))
	private List<UBS> ubs;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "enfermeiro")
	private List<Vacinacao> vacinacao;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "estoquista")
	private List<Pedido> pedidoEst;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "funcionario")
	private List<Pedido> pedido;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "funcionario")
	private List<Resultado> resultado;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "funcionario")
	private List<Receita> receita;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodCBO() {
		return codCBO;
	}

	public void setCodCBO(int codCBO) {
		this.codCBO = codCBO;
	}

	public int getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(int tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public int getCrf() {
		return crf;
	}

	public void setCrf(int crf) {
		this.crf = crf;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public int getCoren() {
		return coren;
	}

	public void setCoren(int coren) {
		this.coren = coren;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Especialidade> getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(List<Especialidade> especialidade) {
		this.especialidade = especialidade;
	}

	public String getUfConselho() {
		return ufConselho;
	}

	public void setUfConselho(String ufConselho) {
		this.ufConselho = ufConselho;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public List<Agenda> getAgendaAT() {
		return agendaAT;
	}

	public void setAgendaAT(List<Agenda> agendaAT) {
		this.agendaAT = agendaAT;
	}

	public List<Agenda> getAgendaMed() {
		return agendaMed;
	}

	public void setAgendaMed(List<Agenda> agendaMed) {
		this.agendaMed = agendaMed;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public List<UBS> getUbs() {
		return ubs;
	}

	public void setUbs(List<UBS> ubs) {
		this.ubs = ubs;
	}

	public List<Vacinacao> getVacinacao() {
		return vacinacao;
	}

	public void setVacinacao(List<Vacinacao> vacinacao) {
		this.vacinacao = vacinacao;
	}

}
