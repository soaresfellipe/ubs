package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.controle.AgendaControle;
import br.com.modelo.Vacina;
import br.com.servico.VacinaServico;

@ManagedBean
@ViewScoped
public class VacinaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(VacinaBean.class
			.getName());

	private Vacina vacina;
	private List<Vacina> listVacinas = new ArrayList<Vacina>();
	private MensagensBean mensagem;

	public VacinaBean() {
		vacina = new Vacina();
		listVacinas = VacinaServico.getInstance().listarAllVacina();
		mensagem = new MensagensBean();
	}

	public void salvar() {
		try {
			VacinaServico.getInstance().cadastrarVacina(vacina);
			mensagem.mensagemCadastroSucesso();
			logger.info("Vacina "+ vacina.getNome() +" cadastrada.");
			vacina = new Vacina();
		} catch (Exception e) {
			mensagem.mensagemCadastroFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public List<Vacina> getlistarVacina() {
		try {
			listVacinas = VacinaServico.getInstance().listarAllVacina();
			logger.debug("Lista com todas as vacinas carregada.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("getlistarVacina()");
			logger.error(e);
		}
		return listVacinas;
	}

	public void onRowEdit(RowEditEvent event) {
		Vacina e = ((Vacina) event.getObject());
		try {
			VacinaServico.getInstance().editarVacina(e);
			listVacinas = VacinaServico.getInstance().listarAllVacina();
			mensagem.mensagemCadastroSucesso();
			logger.info("Vacina "+ vacina.getNome() +" editada.");
		} catch (Exception e1) {
			mensagem.mensagemCadastroFalha();
			e1.printStackTrace();
			logger.error(e1);
		}
	}

	public void onRowCancel(RowEditEvent event) {
		Vacina v = ((Vacina) event.getObject());
		Vacina excluir = new Vacina();
		try {
			excluir = VacinaServico.getInstance().findUBSById(v.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			VacinaServico.getInstance().excluirVacina(excluir);
			listVacinas = VacinaServico.getInstance().listarAllVacina();
			mensagem.mensagemCadastroSucesso();
			logger.info("Vacina "+ vacina.getNome() +" excluída.");
		} catch (Exception e1) {
			mensagem.mensagemCadastroFalha();
			e1.printStackTrace();
			logger.error(e1);
		}
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public List<Vacina> getListVacinas() {
		return listVacinas;
	}

	public void setListVacinas(List<Vacina> listVacinas) {
		this.listVacinas = listVacinas;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

}
