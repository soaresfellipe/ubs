package  br.com.modelo;

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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "UBS.findByLocalidade", query = "SELECT DISTINCT u FROM UBS u WHERE u.localidade like :localidade ORDER BY u.localidade"),
	@NamedQuery(name = "UBS.findByNome", query = "SELECT u FROM UBS u WHERE u.nome like :nome ORDER BY u.nome"),
	@NamedQuery(name = "UBS.findBairro", query = "SELECT distinct(u.localidade) FROM UBS u"),
	@NamedQuery(name = "UBS.findUBSFunc", query = "SELECT f.ubs FROM Funcionario f where f.id = :idFunc"),
	@NamedQuery(name = "UBS.findNaoUBSFunc", query = "SELECT u from UBS u where u.id not in (select ub.id from Funcionario f JOIN f.ubs ub where f.id = :idFunc)")})
public class UBS implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 
	@Column(nullable=false)
	private String localidade;
	 
	@Column(nullable=false)
	private String nome;
	 
	@Column(nullable=true)
	private String telefone;
	 
	@Column(nullable=false)
	private String responsavel;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false,name="DT_CADASTRO")
	private Date dtCadastro;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="DT_INICIO")
	private Date dtInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true, name="DT_ENCERRAMENTO")
	private Date dtEncerramento;
	
	@OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "ubs")
	private List <Agenda> agenda;
	
	@ManyToMany(mappedBy="ubs") 
	private List <Funcionario> funcionario;
	
	@OneToMany (cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "ubs")
	private List<Produto> produto;
	 	
	//Getters e Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}

	public List<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	
/*
	public List<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}
	*/ 
}
 
