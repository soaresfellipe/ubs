package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import br.com.modelo.GrupoProcedimento;
import br.com.modelo.Procedimento;
import br.com.modelo.Variavel;
import br.com.servico.ProcedimentoServico;
import br.com.servico.VariavelServico;

@ManagedBean
@ViewScoped
public class ProcedimentoBean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6305307645717079323L;
	
	private static final Logger logger = Logger.getLogger(ProcedimentoBean.class
			.getName());

	private Procedimento proc;
	private List<Procedimento> lista = new ArrayList<Procedimento>();
	private GrupoProcedimento grupo;
	private String nome;
	private MensagensBean mensagem;
	private List<Variavel> varListSalvar = new ArrayList<Variavel>();

	// Variáveis para PickList da página procedimento-detalhe.xhtml
	private DualListModel<Variavel> variaveis;
	private List<Variavel> varOrigem;
	private List<Variavel> varDestino;

	/**
	 * Getters and Setters
	 */

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Procedimento getProc() {
		return proc;
	}

	public void setProc(Procedimento proc) {
		this.proc = proc;
	}

	public List<Procedimento> getLista() {
		return lista;
	}

	public void setLista(List<Procedimento> lista) {
		this.lista = lista;
	}

	public GrupoProcedimento getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProcedimento grupo) {
		this.grupo = grupo;
	}

	public DualListModel<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(DualListModel<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public List<Variavel> getVarOrigem() {
		return varOrigem;
	}

	public void setVarOrigem(List<Variavel> varOrigem) {
		this.varOrigem = varOrigem;
	}

	public List<Variavel> getVarDestino() {
		return varDestino;
	}

	public void setVarDestino(List<Variavel> varDestino) {
		this.varDestino = varDestino;
	}

	public List<Variavel> getVarListSalvar() {
		return varListSalvar;
	}

	public void setVarListSalvar(List<Variavel> varListSalvar) {
		this.varListSalvar = varListSalvar;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

	/**
	 * Procedimentos do Bean
	 */

	public ProcedimentoBean() {
		mensagem = new MensagensBean();
		proc = new Procedimento();
		grupo = new GrupoProcedimento();
		nome = "";

		/**
		 * Início do Tratamento da relação Procedimento x Variável
		 */

		varOrigem = new ArrayList<Variavel>();

		try {
			varOrigem = VariavelServico.getInstance().listarAllVariavel();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			logger.error(ex);
		}

		varDestino = new ArrayList<Variavel>();
		variaveis = new DualListModel<Variavel>(varOrigem, varDestino);

		/**
		 * Fim do Tratamento da relação Procedimento x Variável
		 */

		try {
			lista = ProcedimentoServico.getInstance().listarAllProcedimento();
			logger.debug("Lista de Procedimentos carregada.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public List<Procedimento> getListarProcedimento() {
		try {
			lista = ProcedimentoServico.getInstance().listarAllProcedimento();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return lista;
	}

	public void salvar() {
		try {
			proc.setGrupo(grupo);
			varListSalvar = variaveis.getTarget();
			proc.setVariavel(varListSalvar);
			ProcedimentoServico.getInstance().cadastrarProcedimento(proc);
			mensagem.mensagemCadastroSucesso();
			logger.info("Procedimento "+ proc.getNome() +" cadastrado.");
		} catch (Exception e) {
			mensagem.mensagemCadastroFalha();
			e.printStackTrace();
			logger.error(e);
		}	
	}

	public void editar() {
		try {
			proc.setGrupo(grupo);
			varListSalvar = variaveis.getTarget();
			proc.setVariavel(varListSalvar);
			ProcedimentoServico.getInstance().editarProcedimento(proc);
			mensagem.mensagemEdicaoSucesso();
			logger.info("Procedimento "+ proc.getNome() +" editado.");
		} catch (Exception e) {
			mensagem.mensagemEdicaoFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void findByNome() {

		if (nome.equals("")) {
			lista = null;
		} else {
			try {
				lista = ProcedimentoServico.getInstance().findByNome(nome);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void procDetalhe() {
		grupo = proc.getGrupo();
		
		try {
			varOrigem = ProcedimentoServico.getInstance().variaveisNaoProc(proc.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			varDestino = ProcedimentoServico.getInstance().variaveisDoProc(proc.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		variaveis = new DualListModel<Variavel>(varOrigem, varDestino);
	}
}
