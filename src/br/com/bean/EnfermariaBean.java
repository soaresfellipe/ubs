package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.modelo.Pessoa;
import br.com.modelo.Receita;
import br.com.servico.ReceitaServico;

@ManagedBean
@ViewScoped
public class EnfermariaBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(EnfermariaBean.class
			.getName());
	
	private List<Receita> listaReceitas;
	private Receita receitaSelecionada;
	Pessoa usuario = (Pessoa) FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().get("usuario");
	private MensagensBean mensagem;
	
	public EnfermariaBean(){
		listaReceitas = new ArrayList<Receita>();
		receitaSelecionada = new Receita();
		mensagem = new MensagensBean();
	}
	
	
	public void atualizaLista(){
		listaReceitas = ReceitaServico.getInstance().findRecUBSAplicar(2, new Date());
	}

	public void aplicarMedicamento(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		receitaSelecionada.setFuncionario(usuario.getFunc());
		receitaSelecionada.setDtEncerramento(c.getTime());
		mensagem.mensagemReceitaFinalizada();
		logger.info("Receita finalizada - "+ receitaSelecionada.getId());
	}

	
	
	public List<Receita> getListaReceitas() {
		return listaReceitas;
	}

	public void setListaReceitas(List<Receita> listaReceitas) {
		this.listaReceitas = listaReceitas;
	}

	public Receita getReceitaSelecionada() {
		return receitaSelecionada;
	}

	public void setReceitaSelecionada(Receita receitaSelecionada) {
		this.receitaSelecionada = receitaSelecionada;
	}


	public Pessoa getUsuario() {
		return usuario;
	}
	
}
