package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.modelo.Especialidade;
import br.com.servico.EspecialidadeServico;

@ManagedBean
@RequestScoped
public class EspecialidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(EspecialidadeBean.class
			.getName());

	private Especialidade esp;
	private List<Especialidade> lista = new ArrayList<Especialidade>();
	private String str;
	private MensagensBean mensagem;

	public EspecialidadeBean() {
		mensagem = new MensagensBean();
		esp = new Especialidade();
		str = "";
		try {
			lista = EspecialidadeServico.getInstance()
					.listarAllEspecialidades();
			logger.debug("Listar todas as especialidades");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}

	public List<Especialidade> getlistarEspecialidade() {
		try {
			lista = EspecialidadeServico.getInstance()
					.listarAllEspecialidades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void salvar() {
		try {
			EspecialidadeServico.getInstance().cadastrarEspecialidade(esp);
			mensagem.mensagemCadastroSucesso();
			logger.info("Especialidade "+ esp.getNome() +" cadastrada com sucesso.");
			esp = new Especialidade();
		} catch (Exception e) {
			mensagem.mensagemCadastroFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		Especialidade e = ((Especialidade) event.getObject());
		try {
			EspecialidadeServico.getInstance().editarEspecialidade(e);
			lista = EspecialidadeServico.getInstance()
					.listarAllEspecialidades();
			mensagem.mensagemEdicaoSucesso();
			logger.info("Especialidade "+ e.getNome() +" editada");
		} catch (Exception e1) {
			mensagem.mensagemEdicaoFalha();
			e1.printStackTrace();
			logger.error(e1);
		}
	}

	public void onRowCancel(RowEditEvent event) {
		Especialidade e = ((Especialidade) event.getObject());
		try {
			EspecialidadeServico.getInstance().excluirEspecialidade(e);
			lista = EspecialidadeServico.getInstance()
					.listarAllEspecialidades();
			mensagem.mensagemExclusaoSucesso();
			logger.info("Especialidade "+ e.getNome() +" exclusão");
		} catch (Exception e1) {
			mensagem.mensagemExclusaoFalha();
			e1.printStackTrace();
			logger.error(e1);
		}
	}

	public Especialidade getEsp() {
		return esp;
	}

	public void setEsp(Especialidade esp) {
		this.esp = esp;
	}

	public List<Especialidade> getLista() {
		return lista;
	}

	public void setLista(List<Especialidade> lista) {
		this.lista = lista;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

	public void setMensagem(MensagensBean mensagem) {
		this.mensagem = mensagem;
	}
}
