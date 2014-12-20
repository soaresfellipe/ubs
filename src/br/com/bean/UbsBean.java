package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.modelo.UBS;
import br.com.servico.UBSServico;

@ManagedBean
@ViewScoped
public class UbsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(UbsBean.class
			.getName());

	UBS ubs;
	List<UBS> lista = new ArrayList<UBS>();
	
	public MensagensBean mensagem = new MensagensBean();

	public UbsBean() {
		ubs = new UBS();
		try {
			lista = UBSServico.getInstance().listarAllUBS();
			logger.debug("listarAllUBS()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}

	public List<UBS> getlistarUBS() {
		try {
			lista = UBSServico.getInstance().listarAllUBS();
			logger.debug("listarAllUBS()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		return lista;
	}

	public void salvar() {
		try {
			ubs.setDtCadastro(new Date());
			UBSServico.getInstance().cadastrarUBS(ubs);
			ubs = new UBS();	
			mensagem.mensagemCadastroSucesso();
			logger.info("UBS "+ ubs.getNome() + " cadastrada.");
		} catch (Exception e) {
			mensagem.mensagemCadastroFalha();
			logger.error(e);
			e.printStackTrace();
		}
	}	

	public void editar() {
		try {
			System.out.println("Começou edição");
			UBSServico.getInstance().editarUBS(ubs);
			mensagem.mensagemEdicaoSucesso();
			logger.info("UBS "+ ubs.getNome() + " editada.");
		} catch (Exception e) {
			mensagem.mensagemEdicaoFalha();
			e.printStackTrace();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		UBS e = ((UBS) event.getObject());
		try {
			UBSServico.getInstance().editarUBS(e);
			lista = UBSServico.getInstance().listarAllUBS();
			mensagem.mensagemEdicaoSucesso();
		} catch (Exception e1) {
			mensagem.mensagemEdicaoFalha();
			logger.error(e1);
			e1.printStackTrace();
		}
	}

	public void onRowCancel(RowEditEvent event) {
		UBS e = ((UBS) event.getObject());
		try {
			UBSServico.getInstance().excluirUBS(e);
			lista = UBSServico.getInstance().listarAllUBS();
			mensagem.mensagemExclusaoSucesso();
			logger.info("UBS "+ e.getNome() + " excluída.");
		} catch (Exception e1) {
			mensagem.mensagemExclusaoSucesso();
			logger.error(e1);
			e1.printStackTrace();
		}
	}

	public UBS getUbs() {
		return ubs;
	}

	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}

	public List<UBS> getLista() {
		return lista;
	}

	public void setLista(List<UBS> lista) {
		this.lista = lista;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

	public void setMensagem(MensagensBean mensagem) {
		this.mensagem = mensagem;
	}

}
