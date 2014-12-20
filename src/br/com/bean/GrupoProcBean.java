package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import br.com.modelo.GrupoProcedimento;
import br.com.servico.GrupoProcServico;

@ManagedBean
@ViewScoped
public class GrupoProcBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(GrupoProcBean.class
			.getName());

	GrupoProcedimento grupo;
	List<GrupoProcedimento> lista = new ArrayList<GrupoProcedimento>();
	private MensagensBean mensagem;

	public GrupoProcBean() {
		mensagem = new MensagensBean();
		grupo = new GrupoProcedimento();
		try {
			lista = GrupoProcServico.getInstance().listarAllGrupo();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public List<GrupoProcedimento> getListarGrupo() {
		try {
			lista = GrupoProcServico.getInstance().listarAllGrupo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void salvar() {
		try {
			GrupoProcServico.getInstance().cadastrarGrupo(grupo);
			mensagem.mensagemCadastroSucesso();
			logger.info("Grupo Procedimento "+ grupo.getNome() +" criado com sucesso");
		} catch (Exception e) {
			mensagem.mensagemCadastroFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void editar() {
		try {
			GrupoProcServico.getInstance().editarGrupo(grupo);
			mensagem.mensagemEdicaoSucesso();
			logger.info("Grupo Procedimento "+ grupo.getNome() +" editado");
		} catch (Exception e) {
			mensagem.mensagemEdicaoFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		GrupoProcedimento g = ((GrupoProcedimento) event.getObject());
		try {
			GrupoProcServico.getInstance().editarGrupo(g);
			lista = GrupoProcServico.getInstance().listarAllGrupo();
			mensagem.mensagemEdicaoSucesso();
			logger.info("Grupo Procedimento "+ grupo.getNome() +" editado");
		} catch (Exception e) {
			mensagem.mensagemEdicaoFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void onRowCancel(RowEditEvent event) {
		GrupoProcedimento g = ((GrupoProcedimento) event.getObject());
		try {
			GrupoProcServico.getInstance().excluirGrupo(g);
			lista = GrupoProcServico.getInstance().listarAllGrupo();
			mensagem.mensagemExclusaoSucesso();
			logger.info("Grupo Procedimento "+ grupo.getNome() +" excluído");
		} catch (Exception e) {
			mensagem.mensagemExclusaoFalha();
			e.printStackTrace();
			logger.error(e);
		}
	}

	public GrupoProcedimento getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProcedimento grupo) {
		this.grupo = grupo;
	}

	public List<GrupoProcedimento> getLista() {
		return lista;
	}

	public void setLista(List<GrupoProcedimento> lista) {
		this.lista = lista;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

}
