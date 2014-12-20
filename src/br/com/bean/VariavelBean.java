package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.controle.AgendaControle;
import br.com.modelo.Variavel;
import br.com.servico.VariavelServico;

@ManagedBean
@ViewScoped
public class VariavelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(VariavelBean.class
			.getName());
	
	Variavel var;
	List<Variavel> lista = new ArrayList<Variavel>();
	private MensagensBean mensagem;
	
	public VariavelBean() {
		var = new Variavel();
		mensagem = new MensagensBean();		
		try {
			lista = VariavelServico.getInstance().listarAllVariavel();
			logger.debug("Listar todas variáveis");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public List<Variavel> getListarVariavel() {
		try {
			lista = VariavelServico.getInstance().listarAllVariavel();			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return lista;
	}
	
	public void salvar() {
		try {
			VariavelServico.getInstance().cadastrarVariavel(var);
			mensagem.mensagemCadastroSucesso();
			logger.info("Variável "+ var.getNome() +" cadastrada.");
			var = new Variavel();
		} catch (Exception e) {
			mensagem.mensagemCadastroFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public void editar() {
		try {
			VariavelServico.getInstance().editarVariavel(var);
			mensagem.mensagemEdicaoSucesso();
			logger.info("Variável "+ var.getNome() +" editada.");
		} catch (Exception e) {
			mensagem.mensagemEdicaoFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		Variavel v = ((Variavel) event.getObject());
		try {
			VariavelServico.getInstance().editarVariavel(v);
			lista = VariavelServico.getInstance().listarAllVariavel();
			mensagem.mensagemEdicaoSucesso();
			logger.info("Variável "+ var.getNome() +" editada.");
		} catch (Exception e) {
			mensagem.mensagemEdicaoFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public void onRowCancel(RowEditEvent event) {
		Variavel v = ((Variavel) event.getObject());
		try {
			VariavelServico.getInstance().excluirVariavel(v);
			lista = VariavelServico.getInstance().listarAllVariavel();
			mensagem.mensagemExclusaoSucesso();
			logger.info("Variável "+ var.getNome() +" excluída.");
		} catch (Exception e) {
			mensagem.mensagemExclusaoFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public Variavel getVariavel() {
		return var;
	}
	
	public void setVariavel(Variavel var) {
		this.var = var;
	}

	public List<Variavel> getLista() {
		return lista;
	}

	public void setLista(List<Variavel> lista) {
		this.lista = lista;
	}
}
