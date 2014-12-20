package br.com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.modelo.Funcionario;
import br.com.modelo.Item;
import br.com.modelo.Medicamento;
import br.com.modelo.Pedido;
import br.com.modelo.Pessoa;
import br.com.modelo.Produto;
import br.com.modelo.UBS;
import br.com.servico.FuncionarioServico;
import br.com.servico.ItemServico;
import br.com.servico.MedicamentoServico;
import br.com.servico.PedidoServico;
import br.com.servico.ProdutoServico;
import br.com.servico.UBSServico;

@ManagedBean
@ViewScoped
public class ProdutoMedicamentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(ProdutoMedicamentoBean.class.getName());

	private Produto prod;
	private Medicamento med;
	private String unidadeMedida;
	private String nome;
	private List<Produto> listaProdutos;
	private List<Medicamento> listaMed;
	private boolean habilitaBusca;
	private boolean habilitaBusca2;
	private boolean habilitaBusca3;
	private List<UBS> listaUBS;
	private UBS ubs;
	private Produto prod2 = new Produto();
	private Medicamento med2 = new Medicamento();
	private Item item;
	private List<Item> itens;
	private int quant = 0;
	private Pedido pedido;
	private Funcionario func;
	private List<Funcionario> listaFunc;
	private MensagensBean mensagem;
	private Pessoa usuario;

	public ProdutoMedicamentoBean() {
		func = new Funcionario();
		// Remover esta lista quando o login for criado
		try {
			listaFunc = FuncionarioServico.getInstance().listarAllFunc();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// listaFunc = FuncionarioServico.getInstance().findFunc(id da ubs do
		// funcionario logado)
		prod = new Produto();
		med = new Medicamento();
		unidadeMedida = "";
		nome = "";
		listaProdutos = new ArrayList<Produto>();
		listaMed = new ArrayList<Medicamento>();
		habilitaBusca = false;
		habilitaBusca2 = false;
		habilitaBusca3 = true;
		mensagem = new MensagensBean();
		try {
			listaUBS = UBSServico.getInstance().listarAllUBS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ubs = new UBS();
		item = new Item();
		itens = new ArrayList<Item>();
		pedido = new Pedido();
		nome = "";

		usuario = (Pessoa) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
	}
	
	

	public void findByNome() {
		logger.debug("findByNome()");
		if (nome.equals("")) {
			listaProdutos = null;
			listaMed = null;
		} else {
			try {
				listaProdutos = ProdutoServico.getInstance().findByNomeProd(
						nome);
				listaMed = MedicamentoServico.getInstance().findByNomeMed(nome);
			} catch (Exception e) {
			}
		}
	}

	public void mudaCampos() {
		if (prod.isTipoMedicamento()) {
			habilitaBusca = true;
		} else {
			habilitaBusca = false;
		}
		if ((!(prod == null)) || (!(med == null))) {
			prod2 = prod;
			med2 = med;
			habilitaBusca2 = true;
			habilitaBusca3 = false;
		}
	}

	public void populaLista() {
		boolean existe = false;
		System.out.println("Método para popular lista");
		if ((func.getId() == 0) || (prod.getId() == 0) || (quant <= 0)) {
			mensagem.semQuantOuProd();
		} else {
			if (quant <= prod.getQuantAtual()) {
				if (itens.size() == 0) {
					item.setProduto(getProd());
					item.setQuant(getQuant());
					itens.add(item);
					item = new Item();
				} else {
					while (existe == false) {
						for (int i = 0; i < itens.size(); i++) {
							if (itens.get(i).getProduto().getId() == prod
									.getId()) {
								mensagem.produtoJaNaLista();
								existe = true;
							} else {
								item.setProduto(getProd());
								item.setQuant(getQuant());
								itens.add(item);
								item = new Item();
								existe = false;
							}
						}
					}
				}
			} else {
				mensagem.naoHaQuantProdEstoque(prod);
			}
		}
	}

	
	public void salvarPedido() {
		if (itens.size() != 0) {
			pedido.setDtSolicitacao(new Date());
			pedido.setDtEntrega(new Date());
			pedido.setEstoquista(usuario.getFunc());
			pedido.setFuncionario(func);
			try {
				PedidoServico.getInstance().cadastrarPedido(pedido);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < itens.size(); i++) {
				try {
					itens.get(i).setPedido(pedido);
					ItemServico.getInstance().cadastrarItem(itens.get(i));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// Retira produto do estoque
			Produto pRetirar = new Produto();
			for (int i = 0; i < itens.size(); i++) {
				try {
					pRetirar = ProdutoServico.getInstance().findById(
							itens.get(i).getProduto().getId());
					pRetirar.setQuantAtual(pRetirar.getQuantAtual()
							- itens.get(i).getQuant());
					ProdutoServico.getInstance().editarProduto(pRetirar);
					mensagem.produtoRetirado(pRetirar);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			itens = new ArrayList<Item>();
			prod = new Produto();
			quant = 0;
			func = new Funcionario();
			mensagem.mensagemCadastroSucesso();
		} else {
			mensagem.mensagemPreencherCamposFalha();
		}

	}



	public String chamaPaginaProduto() {
		return "/produto";
	}

	public void cancelar() {
		prod = new Produto();
		item = new Item();
		itens = new ArrayList<Item>();
	}

	public void salvar() throws Exception {
		System.out.println("");
		if (!(med.getNome() == null)) {
			ubs = UBSServico.getInstance().findUBSById(ubs.getId());
			med.setUbs(ubs);
			med.setTipoMedicamento(true);
			med.setUbs(ubs);
			try {
				MedicamentoServico.getInstance().cadastrarMedicamento(med);
				findByNome();
				mensagem.mensagemCadastroSucesso();
				med = new Medicamento();

			} catch (Exception e) {
				mensagem.mensagemCadastroFalha();
				e.printStackTrace();
			}
		} else if (!(prod.getNome() == null)) {
			try {
				prod.setTipoMedicamento(false);
				ubs = UBSServico.getInstance().findUBSById(ubs.getId());
				prod.setUbs(ubs);
				ProdutoServico.getInstance().cadastrarProduto(prod);
				prod = new Produto();
				findByNome();
				mensagem.mensagemCadastroSucesso();

			} catch (Exception e) {
				mensagem.mensagemCadastroFalha();
				e.printStackTrace();
			}
		}
	}

	public void excluir() {
		if (prod2.getId() != 0) {
			try {
				prod = ProdutoServico.getInstance().findById(prod2.getId());
				ProdutoServico.getInstance().excluirProduto(prod);
				prod2 = new Produto();
				habilitaBusca2 = false;
				habilitaBusca3 = true;
				mensagem.mensagemExclusaoSucesso();
			} catch (Exception e) {
				mensagem.mensagemExclusaoFalha();
				e.printStackTrace();
			}
		} else if (med2.getId() != 0) {
			try {
				med = MedicamentoServico.getInstance().findById(med.getId());
				MedicamentoServico.getInstance().excluirMedicamento(med);
				med2 = new Medicamento();
				habilitaBusca2 = false;
				habilitaBusca3 = true;
				mensagem.mensagemExclusaoSucesso();
			} catch (Exception e) {
				mensagem.mensagemExclusaoFalha();
				e.printStackTrace();
			}
		}
	}

	public void editar() {
		if (prod.getId() != 0) {
			try {
				ProdutoServico.getInstance().editarProduto(prod2);
				mensagem.mensagemEdicaoSucesso();
			} catch (Exception e) {
				mensagem.mensagemEdicaoFalha();
				e.printStackTrace();
			}
		}
		if (med.getId() != 0) {
			try {
				MedicamentoServico.getInstance().editarMedicamento(med2);
				mensagem.mensagemEdicaoSucesso();
			} catch (Exception e) {
				mensagem.mensagemEdicaoFalha();
				e.printStackTrace();
			}
		}
	}

	public Produto getProd() {
		return prod;
	}

	public void setProd(Produto prod) {
		this.prod = prod;
	}

	public Medicamento getMed() {
		return med;
	}

	public void setMed(Medicamento med) {
		this.med = med;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		System.out.println(nome);
	}

	public boolean isHabilitaBusca() {
		return habilitaBusca;
	}

	public void setHabilitaBusca(boolean habilitaBusca) {
		this.habilitaBusca = habilitaBusca;
	}

	public List<UBS> getListaUBS() {
		return listaUBS;
	}

	public void setListaUBS(List<UBS> listaUBS) {
		this.listaUBS = listaUBS;
	}

	public UBS getUbs() {
		return ubs;
	}

	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}

	public boolean isHabilitaBusca2() {
		return habilitaBusca2;
	}

	public void setHabilitaBusca2(boolean habilitaBusca2) {
		this.habilitaBusca2 = habilitaBusca2;
	}

	public Produto getProd2() {
		return prod2;
	}

	public void setProd2(Produto prod2) {
		this.prod2 = prod2;
	}

	public boolean isHabilitaBusca3() {
		return habilitaBusca3;
	}

	public Medicamento getMed2() {
		return med2;
	}

	public void setMed2(Medicamento med2) {
		this.med2 = med2;
	}

	public void setHabilitaBusca3(boolean habilitaBusca3) {
		this.habilitaBusca3 = habilitaBusca3;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Medicamento> getListaMed() {
		return listaMed;
	}

	public void setListaMed(List<Medicamento> listaMed) {
		this.listaMed = listaMed;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	public List<Funcionario> getListaFunc() {
		return listaFunc;
	}

	public void setListaFunc(List<Funcionario> listaFunc) {
		this.listaFunc = listaFunc;
	}

}
