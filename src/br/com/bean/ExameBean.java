package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.modelo.Exame;
import br.com.modelo.Paciente;
import br.com.modelo.Pessoa;
import br.com.modelo.Procedimento;
import br.com.modelo.Resultado;
import br.com.modelo.Variavel;
import br.com.servico.ExameServico;
import br.com.servico.ResultadoServico;

@ManagedBean
@ViewScoped
public class ExameBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4212294487029480841L;
	
	private static final Logger logger = Logger.getLogger(ExameBean.class
			.getName());

	String status;
	String nome;
	Exame exame;
	Paciente paciente;
	Procedimento procedimento;
	Variavel variavel;
	Resultado resultado;
	List<Paciente> listaPaciente = new ArrayList<Paciente>();
	List<Exame> lista = new ArrayList<Exame>();
	List<Resultado> listaVarResultado = new ArrayList<Resultado>();
	List<Resultado> listaResultado = new ArrayList<Resultado>();
	private MensagensBean mensagem = new MensagensBean();
	Pessoa usuario;
	/*
	 * @Fellipe Campo CPF e STATUS pegam valores da tela
	 */

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
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

	public Variavel getVariavel() {
		return variavel;
	}

	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public List<Exame> getLista() {
		return lista;
	}

	public void setLista(List<Exame> lista) {
		this.lista = lista;
	}

	public List<Resultado> getListaVarResultado() {
		return listaVarResultado;
	}

	public void setListaVarResultado(List<Resultado> listaVarResultado) {
		this.listaVarResultado = listaVarResultado;
	}

	public List<Resultado> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<Resultado> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

	public void setMensagem(MensagensBean mensagem) {
		this.mensagem = mensagem;
	}

	public ExameBean() {
		paciente = new Paciente();
		resultado = new Resultado();
		// Tratamento para visualização de Exame pela tela do Paciente
		usuario = (Pessoa) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
		if (usuario.getInd_paciente() == 1) {
			paciente.setId(usuario.getPaciente().getId());
		}
	}

	/*
	 * @Fellipe Procedimentos específicos do BEAN
	 */

	public void atualizaTabelaExame() {
		try {
			lista = ExameServico.getInstance().findExamesDoPaciente(
					paciente.getId(), status);
			logger.debug("Atualiza tabela de exames");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void exameDetalhe() {
		try {
			listaVarResultado = ExameServico.getInstance()
					.listaVariavelResultado(exame.getId());
			logger.debug("Exibe detalhamento");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	// Procedimento para Gravar o Exame como Concluído
	public void finalizaExame() {
		try {
			listaResultado = ExameServico.getInstance().listaVariavelResultado(
					exame.getId());
			logger.debug("Listagem de resultado do procecimento");
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error(e1);
		}
		if (isResultado(listaResultado) == true) {
			exame.setStatus("Concluído");
			try {
				ExameServico.getInstance().editarExame(exame);
				mensagem.mensagemResultadoExameSucesso();
				logger.info("Exame editado.");
			} catch (Exception e) {
				mensagem.mensagemResultadoExameFalha();
				e.printStackTrace();
				logger.error(e);
			}
		} else {
			mensagem.mensagemPreencherCamposFalha();
			logger.warn("Preencher todos os campos.");
		}
	}

	public boolean isResultado(List<Resultado> res) {
		// Percorro a lista de Resultados
		int i = 0;
		boolean situacao = true;
		int valor = 0;
		while (i < res.size()) {
			valor = res.get(i).getValor();
			if (valor == 0) {
				situacao = false; // Existe resultado não preenchido ainda
			}
			i++;
		}
		return situacao;
	}

	public void onRowEdit(RowEditEvent event) {
		Resultado e = ((Resultado) event.getObject());
		try {
			ResultadoServico.getInstance().editarResultado(e);
			listaResultado = ExameServico.getInstance().listaVariavelResultado(
					e.getExame().getId());
			mensagem.mensagemEdicaoSucesso();
			logger.info("Edição realizada com sucesso. Exame: " + e.getId());
		} catch (Exception e1) {
			mensagem.mensagemEdicaoFalha();
			e1.printStackTrace();
			logger.error(e1);
		}
	}

	public void findPacientesPorNome() {
		if (nome.equals("")) {
			listaPaciente = null;
		} else {
			try {
				listaPaciente = ExameServico.getInstance()
						.findPacientesPorNome(nome);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}