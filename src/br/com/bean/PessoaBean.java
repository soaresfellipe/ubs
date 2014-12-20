package br.com.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import br.com.modelo.Especialidade;
import br.com.modelo.Funcionario;
import br.com.modelo.Paciente;
import br.com.modelo.Pessoa;
import br.com.modelo.UBS;
import br.com.modelo.Vacina;
import br.com.modelo.Vacinacao;
import br.com.servico.EspecialidadeServico;
import br.com.servico.FuncionarioServico;
import br.com.servico.PacienteServico;
import br.com.servico.PessoaServico;
import br.com.servico.UBSServico;
import br.com.servico.VacinaServico;
import br.com.servico.VacinacaoServico;

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos
	private int id;
	private String nome;
	private List<Pessoa> listaPessoas;
	private int flag;
	private Pessoa pessoa;
	private Paciente paciente;
	private Funcionario func;
	private int cpfCadastrado;
	private int pacienteCadastrado;
	private int aclinicoCadastrado;
	private int atendenteCadastrado;
	private int enfermeiroCadastro;
	private int estoquistaCadastro;
	private int farmaceuticoCadastro;
	private int medicoCadastro;
	private boolean cadastrado;
	private String cpf;
	private boolean cpfAux;
	private boolean habPaciente;
	private boolean habFunc;
	private boolean iscpf;
	private boolean iscns;
	private DualListModel<Especialidade> especialidades;
	private List<Especialidade> allEsp;
	private List<Especialidade> esp_medico;
	private DualListModel<UBS> dualListUBS;
	private List<UBS> allUBS;
	private List<UBS> func_UBS;
	private DualListModel<UBS> dualListUBSMed;
	private List<UBS> allUBSMed;
	private List<UBS> func_UBSMed;
	private String senha;

	private DualListModel<UBS> dualListUBSAC;
	private List<UBS> allUBSAC;
	private List<UBS> func_UBSAC;
	private DualListModel<UBS> dualListUBSAt;
	private List<UBS> allUBSAt;
	private List<UBS> func_UBSAt;
	private DualListModel<UBS> dualListUBSEnf;
	private List<UBS> allUBSEnf;
	private List<UBS> func_UBSEnf;
	private DualListModel<UBS> dualListUBSEst;
	private List<UBS> allUBSEst;
	private List<UBS> func_UBSEst;
	private DualListModel<UBS> dualListUBSFar;
	private List<UBS> allUBSFar;
	private List<UBS> func_UBSFar;

	private boolean excluir;
	private boolean excluirPessoa;
	private boolean habilitaBusca;
	private boolean habilitaPessoa;
	private boolean habilitaPacienteCad;
	private boolean habilitaPacienteDB;
	private boolean habilitaCBOCad;
	private boolean habilitaCRFCad;
	private boolean habilitaEnfCad;
	private boolean habilitaMedCad;
	private boolean habilitaUFConselhoCad;
	private boolean habilitaTab;
	private int activeTab;
	private Funcionario func1;
	private Funcionario func2;
	private Funcionario func3;
	private Funcionario func4;
	private Funcionario func5;
	private Funcionario func6;
	private boolean modificado;
	private boolean func1Null = false;
	private int idFunc1 = 0;
	private boolean func2Null = false;
	private int idFunc2 = 0;
	private boolean func3Null = false;
	private int idFunc3 = 0;
	private boolean func4Null = false;
	private int idFunc4 = 0;
	private boolean func5Null = false;
	private int idFunc5 = 0;
	private boolean func6Null = false;
	private int idFunc6 = 0;
	private boolean pacNull = false;
	private boolean excAC = false;
	private boolean excAT = false;
	private boolean excENF = false;
	private boolean excEST = false;
	private boolean excFAR = false;
	private boolean excMED = false;
	private int idFunc = 0;
	private String bairro;
	private UBS bairroSelecionado;
	private List<UBS> listaBairro;
	private List<Vacina> vacinas;
	private Vacinacao vacinacao;
	private List<Vacinacao> cadernetaVacinacao;

	private MensagensBean mensagem;

	// Construtor da Classe
	public PessoaBean() throws Exception {
		mensagem = new MensagensBean();
		senha = "";
		cadernetaVacinacao = new ArrayList<Vacinacao>();
		pessoa = new Pessoa();
		paciente = new Paciente();
		func = new Funcionario();
		listaPessoas = new ArrayList<Pessoa>();
		cpfCadastrado = 0;
		pacienteCadastrado = 0;
		aclinicoCadastrado = 0;
		atendenteCadastrado = 0;
		enfermeiroCadastro = 0;
		estoquistaCadastro = 0;
		farmaceuticoCadastro = 0;
		medicoCadastro = 0;
		cadastrado = false;
		cpf = null;
		iscpf = false;
		iscns = false;
		vacinas = VacinaServico.getInstance().listarAllVacina();
		vacinacao = new Vacinacao();

		allEsp = new ArrayList<Especialidade>();
		esp_medico = new ArrayList<Especialidade>();
		allEsp = EspecialidadeServico.getInstance().listarAllEspecialidades();
		especialidades = new DualListModel<Especialidade>(allEsp, esp_medico);

		allUBSAC = new ArrayList<UBS>();
		func_UBSAC = new ArrayList<UBS>();
		dualListUBSAC = new DualListModel<UBS>(allUBSAC, func_UBSAC);

		allUBSAt = new ArrayList<UBS>();
		func_UBSAt = new ArrayList<UBS>();
		dualListUBSAt = new DualListModel<UBS>(allUBSAt, func_UBSAt);

		allUBSEnf = new ArrayList<UBS>();
		func_UBSEnf = new ArrayList<UBS>();
		dualListUBSEnf = new DualListModel<UBS>(allUBSEnf, func_UBSEnf);

		allUBSEst = new ArrayList<UBS>();
		func_UBSEst = new ArrayList<UBS>();
		dualListUBSEst = new DualListModel<UBS>(allUBSEst, func_UBSEst);

		allUBSFar = new ArrayList<UBS>();
		func_UBSFar = new ArrayList<UBS>();
		dualListUBSFar = new DualListModel<UBS>(allUBSFar, func_UBSFar);

		allUBS = new ArrayList<UBS>();
		func_UBS = new ArrayList<UBS>();
		allUBS = UBSServico.getInstance().listarAllUBS();
		setDualListUBS(new DualListModel<UBS>(allUBS, func_UBS));

		allUBSMed = new ArrayList<UBS>();
		func_UBSMed = new ArrayList<UBS>();
		dualListUBSMed = new DualListModel<>(allUBSMed, func_UBSMed);

		habilitaBusca = true;
		habilitaPessoa = false;
		habilitaPacienteCad = false;
		habilitaPacienteDB = false;
		habilitaCBOCad = false;
		habilitaCRFCad = false;
		habilitaEnfCad = false;
		habilitaMedCad = false;
		habilitaUFConselhoCad = false;
		habilitaTab = false;

		func1 = new Funcionario();
		func2 = new Funcionario();
		func3 = new Funcionario();
		func4 = new Funcionario();
		func5 = new Funcionario();
		func6 = new Funcionario();

		try {
			setListaBairro(UBSServico.getInstance().findBairro(bairro));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findByNome() {
		if (nome.equals("")) {
			listaPessoas = null;
			habilitaBusca = true;
			habilitaTab = false;
		} else {
			try {
				listaPessoas = PessoaServico.getInstance().findByNome(nome);
				habilitaBusca = false;
				habilitaTab = true;
				if (pessoa.getInd_paciente() == 1) {
					paciente = pessoa.getPaciente();
				} else if (pessoa.getInd_paciente() == 0) {
					paciente = new Paciente();
				}
				if (pessoa.getInd_func() == 0) {
					func = new Funcionario();
				} else if (pessoa.getInd_func() == 1) {
					func = pessoa.getFunc();
					switch (func.getTipoFuncionario()) {
					case 1:
						func1 = func;
						func2 = new Funcionario();
						func3 = new Funcionario();
						func4 = new Funcionario();
						func5 = new Funcionario();
						func6 = new Funcionario();
						allUBSAC = UBSServico.getInstance().findNaoUBSFunc(
								func1.getId());
						func_UBSAC = UBSServico.getInstance().findUBSFunc(
								func1.getId());
						dualListUBSAC = new DualListModel<UBS>(allUBSAC,
								func_UBSAC);
						allUBSAt = UBSServico.getInstance().listarAllUBS();
						dualListUBSAt = new DualListModel<UBS>(allUBSAt,
								func_UBSAt);
						allUBSEnf = UBSServico.getInstance().listarAllUBS();
						dualListUBSEnf = new DualListModel<UBS>(allUBSEnf,
								func_UBSEnf);
						allUBSEst = UBSServico.getInstance().listarAllUBS();
						dualListUBSEst = new DualListModel<UBS>(allUBSEst,
								func_UBSEst);
						allUBSFar = UBSServico.getInstance().listarAllUBS();
						dualListUBSFar = new DualListModel<UBS>(allUBSFar,
								func_UBSFar);
						allUBSMed = UBSServico.getInstance().listarAllUBS();
						dualListUBSMed = new DualListModel<UBS>(allUBSMed,
								func_UBSMed);
						break;
					case 2:
						func1 = new Funcionario();
						func2 = func;
						func3 = new Funcionario();
						func4 = new Funcionario();
						func5 = new Funcionario();
						func6 = new Funcionario();
						allUBSAt = UBSServico.getInstance().findNaoUBSFunc(
								func2.getId());
						func_UBSAt = UBSServico.getInstance().findUBSFunc(
								func2.getId());
						dualListUBSAt = new DualListModel<UBS>(allUBSAt,
								func_UBSAt);
						allUBSAC = UBSServico.getInstance().listarAllUBS();
						func_UBSAC = UBSServico.getInstance().findUBSFunc(
								func1.getId());
						dualListUBSAC = new DualListModel<UBS>(allUBSAC,
								func_UBSAC);
						allUBSEnf = UBSServico.getInstance().listarAllUBS();
						dualListUBSEnf = new DualListModel<UBS>(allUBSEnf,
								func_UBSEnf);
						allUBSEst = UBSServico.getInstance().listarAllUBS();
						dualListUBSEst = new DualListModel<UBS>(allUBSEst,
								func_UBSEst);
						allUBSFar = UBSServico.getInstance().listarAllUBS();
						dualListUBSFar = new DualListModel<UBS>(allUBSFar,
								func_UBSFar);
						allUBSMed = UBSServico.getInstance().listarAllUBS();
						dualListUBSMed = new DualListModel<UBS>(allUBSMed,
								func_UBSMed);
						break;
					case 3:
						func1 = new Funcionario();
						func2 = new Funcionario();
						func3 = func;
						func4 = new Funcionario();
						func5 = new Funcionario();
						func6 = new Funcionario();
						allUBSEnf = UBSServico.getInstance().findNaoUBSFunc(
								func3.getId());
						func_UBSEnf = UBSServico.getInstance().findUBSFunc(
								func3.getId());
						dualListUBSEnf = new DualListModel<UBS>(allUBSEnf,
								func_UBSEnf);
						allUBSAC = UBSServico.getInstance().listarAllUBS();
						func_UBSAC = UBSServico.getInstance().findUBSFunc(
								func1.getId());
						dualListUBSAC = new DualListModel<UBS>(allUBSAC,
								func_UBSAC);
						allUBSAt = UBSServico.getInstance().listarAllUBS();
						dualListUBSAt = new DualListModel<UBS>(allUBSAt,
								func_UBSAt);
						dualListUBSEst = new DualListModel<UBS>(allUBSEst,
								func_UBSEst);
						allUBSFar = UBSServico.getInstance().listarAllUBS();
						dualListUBSFar = new DualListModel<UBS>(allUBSFar,
								func_UBSFar);
						allUBSMed = UBSServico.getInstance().listarAllUBS();
						dualListUBSMed = new DualListModel<UBS>(allUBSMed,
								func_UBSMed);
						break;
					case 4:
						func1 = new Funcionario();
						func2 = new Funcionario();
						func3 = new Funcionario();
						func4 = func;
						func5 = new Funcionario();
						func6 = new Funcionario();
						allUBSEst = UBSServico.getInstance().findNaoUBSFunc(
								func4.getId());
						func_UBSEst = UBSServico.getInstance().findUBSFunc(
								func4.getId());
						dualListUBSEst = new DualListModel<UBS>(allUBSEst,
								func_UBSEst);
						allUBSAC = UBSServico.getInstance().listarAllUBS();
						func_UBSAC = UBSServico.getInstance().findUBSFunc(
								func1.getId());
						dualListUBSAC = new DualListModel<UBS>(allUBSAC,
								func_UBSAC);
						allUBSAt = UBSServico.getInstance().listarAllUBS();
						dualListUBSAt = new DualListModel<UBS>(allUBSAt,
								func_UBSAt);
						allUBSEnf = UBSServico.getInstance().listarAllUBS();
						dualListUBSEnf = new DualListModel<UBS>(allUBSEnf,
								func_UBSEnf);
						allUBSFar = UBSServico.getInstance().listarAllUBS();
						dualListUBSFar = new DualListModel<UBS>(allUBSFar,
								func_UBSFar);
						allUBSMed = UBSServico.getInstance().listarAllUBS();
						dualListUBSMed = new DualListModel<UBS>(allUBSMed,
								func_UBSMed);
						break;
					case 5:
						func1 = new Funcionario();
						func2 = new Funcionario();
						func3 = new Funcionario();
						func4 = new Funcionario();
						func5 = func;
						func6 = new Funcionario();
						allUBSFar = UBSServico.getInstance().findNaoUBSFunc(
								func5.getId());
						func_UBSFar = UBSServico.getInstance().findUBSFunc(
								func5.getId());
						dualListUBSFar = new DualListModel<UBS>(allUBSFar,
								func_UBSFar);
						allUBSAC = UBSServico.getInstance().listarAllUBS();
						func_UBSAC = UBSServico.getInstance().findUBSFunc(
								func1.getId());
						dualListUBSAC = new DualListModel<UBS>(allUBSAC,
								func_UBSAC);
						allUBSAt = UBSServico.getInstance().listarAllUBS();
						dualListUBSAt = new DualListModel<UBS>(allUBSAt,
								func_UBSAt);
						allUBSEnf = UBSServico.getInstance().listarAllUBS();
						dualListUBSEnf = new DualListModel<UBS>(allUBSEnf,
								func_UBSEnf);
						allUBSEst = UBSServico.getInstance().listarAllUBS();
						dualListUBSEst = new DualListModel<UBS>(allUBSEst,
								func_UBSEst);
						allUBSMed = UBSServico.getInstance().listarAllUBS();
						dualListUBSMed = new DualListModel<UBS>(allUBSMed,
								func_UBSMed);
						break;
					case 6:
						func1 = new Funcionario();
						func2 = new Funcionario();
						func3 = new Funcionario();
						func4 = new Funcionario();
						func5 = new Funcionario();
						func6 = func;
						allEsp = EspecialidadeServico.getInstance().listarAllEspecialidades();
						esp_medico = FuncionarioServico.getInstance()
								.findEspFunc(func6.getId());
						especialidades = new DualListModel<Especialidade>(
								allEsp, esp_medico);
						allUBSMed = UBSServico.getInstance().findNaoUBSFunc(
								func6.getId());
						func_UBSMed = UBSServico.getInstance().findUBSFunc(
								func6.getId());
						dualListUBSMed = new DualListModel<UBS>(allUBSMed,
								func_UBSMed);
						allUBSAC = UBSServico.getInstance().listarAllUBS();
						func_UBSAC = UBSServico.getInstance().findUBSFunc(
								func1.getId());
						dualListUBSAC = new DualListModel<UBS>(allUBSAC,
								func_UBSAC);
						allUBSAt = UBSServico.getInstance().listarAllUBS();
						dualListUBSAt = new DualListModel<UBS>(allUBSAt,
								func_UBSAt);
						allUBSEnf = UBSServico.getInstance().listarAllUBS();
						dualListUBSEnf = new DualListModel<UBS>(allUBSEnf,
								func_UBSEnf);
						allUBSEst = UBSServico.getInstance().listarAllUBS();
						dualListUBSEst = new DualListModel<UBS>(allUBSEst,
								func_UBSEst);
						allUBSFar = UBSServico.getInstance().listarAllUBS();
						dualListUBSFar = new DualListModel<UBS>(allUBSFar,
								func_UBSFar);
						break;
					default:
						break;
					}

				}

			} catch (Exception e) {
			}
		}
	}

	private String convertStringToMd5(String valor) {
		MessageDigest mDigest;
		try {
			// Instanciamos o nosso HASH MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior comparação se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,
						3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void chamaPaginaCadastro() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		context.redirect("pessoa-cadastro.xhtml");
	}

	public String chamaPaginaAlteracao() {
		return "/editarpessoa";
	}

	public void cadastrar() {

		cadastrado = false;
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> parameterMap = context.getExternalContext()
				.getRequestParameterMap();
		if (!parameterMap.isEmpty()) {
			flag = (int) Double.parseDouble((String) parameterMap
					.get("tpessoa"));
		}
		pessoa.setCpf(pessoa.getCpf().toString().replaceAll("[- /.]", ""));
		iscpf = validaCPF(pessoa.getCpf());

		// Indicador de Paciente
		boolean dep = pessoa.isInd_dependente();

		if (iscpf == true) {
			allUBS = dualListUBS.getSource();
			func_UBS = dualListUBS.getTarget();
			try {
				cpfCadastrado = (PessoaServico.getInstance().findByCPF(pessoa
						.getCpf()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			switch (flag) {
			case 1:
				iscns = validaCNS(paciente.getCns());
				if (iscns == true) {
					try {
						pacienteCadastrado = PacienteServico.getInstance()
								.findByCODSUS(paciente.getCns());
						if (((cpfCadastrado == 0) && (pacienteCadastrado == 0))
								|| ((cpfCadastrado != 0) && (pacienteCadastrado != 0))
								&& (dep == true)) {
							pessoa.setInd_paciente(1);
							pessoa.setSenha(convertStringToMd5(senha));
							PessoaServico.getInstance().cadastrarPessoa(pessoa);
							paciente.setPessoa(pessoa);
							pessoa.setPaciente(paciente);
							PacienteServico.getInstance().cadastrarPaciente(
									paciente);

							// Gera a caderneta de Vacinação
							geraCadernetaVacinacao(paciente);

							pessoa = new Pessoa();
							paciente = new Paciente();
							cadastrado = true;

							mensagem.mensagemCadastroSucesso();
						} else {
							cadastrado = false;
						}
					} catch (Exception e) {
						mensagem.mensagemCodidoOuCPFInvalido("CNS");
						e.printStackTrace();
					}
				} else {
					mensagem.mensagemCodidoOuCPFInvalido("CNS");
				}
				break;

			case 2:
				try {
					aclinicoCadastrado = FuncionarioServico.getInstance()
							.findAClinico(func.getCrf(), func.getUfConselho());

					if ((cpfCadastrado == 0) && (aclinicoCadastrado == 0)) {
						func.setTipoFuncionario(1);
						func.setFlagAtivo(true);
						func_UBS = dualListUBS.getTarget();
						func.setUbs(func_UBS);
						func.setPessoa(pessoa);
						pessoa.setFunc(func);
						pessoa.setInd_func(1);
						pessoa.setSenha(convertStringToMd5(senha));
						PessoaServico.getInstance().cadastrarPessoa(pessoa);
						FuncionarioServico.getInstance().cadastrarFunc(func);
						cadastrado = true;
						pessoa = new Pessoa();
						func = new Funcionario();
						mensagem.mensagemCadastroSucesso();
					} else {
						cadastrado = false;
						mensagem.mensagemCodidoOuCPFInvalido("CRF");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 3:
				try {
					atendenteCadastrado = FuncionarioServico.getInstance()
							.findAtendente(pessoa);

					if ((cpfCadastrado == 0) && (atendenteCadastrado == 0)) {
						func.setTipoFuncionario(2);
						func.setFlagAtivo(true);
						func_UBS = dualListUBS.getTarget();
						func.setUbs(func_UBS);
						func.setPessoa(pessoa);
						pessoa.setFunc(func);
						pessoa.setInd_func(1);
						pessoa.setSenha(convertStringToMd5(senha));
						PessoaServico.getInstance().cadastrarPessoa(pessoa);
						FuncionarioServico.getInstance().cadastrarFunc(func);
						cadastrado = true;
						pessoa = new Pessoa();
						func = new Funcionario();
						mensagem.mensagemCadastroSucesso();
					} else {
						cadastrado = false;
						mensagem.mensagemAtendenteEstoquistaJaCadastrado("Atendente");
					}
				} catch (Exception e) {
					mensagem.mensagemCadastroFalha();
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					enfermeiroCadastro = FuncionarioServico.getInstance()
							.findEnfermeiro(func.getCoren(),
									func.getUfConselho());

					if ((cpfCadastrado == 0) && (enfermeiroCadastro == 0)) {
						func.setTipoFuncionario(3);
						func.setFlagAtivo(true);
						func_UBS = dualListUBS.getTarget();
						func.setUbs(func_UBS);
						func.setPessoa(pessoa);
						pessoa.setFunc(func);
						pessoa.setInd_func(1);
						pessoa.setSenha(convertStringToMd5(senha));
						PessoaServico.getInstance().cadastrarPessoa(pessoa);
						FuncionarioServico.getInstance().cadastrarFunc(func);
						cadastrado = true;
						pessoa = new Pessoa();
						func = new Funcionario();
						mensagem.mensagemCadastroSucesso();
					} else {
						cadastrado = false;
						mensagem.mensagemCodidoOuCPFInvalido("COREN");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 5:
				try {
					estoquistaCadastro = FuncionarioServico.getInstance()
							.findEstoquista(pessoa);

					if ((cpfCadastrado == 0) && (estoquistaCadastro == 0)) {
						func.setTipoFuncionario(4);
						func.setFlagAtivo(true);
						func_UBS = dualListUBS.getTarget();
						func.setUbs(func_UBS);
						func.setPessoa(pessoa);
						pessoa.setFunc(func);
						pessoa.setInd_func(1);
						pessoa.setSenha(convertStringToMd5(senha));
						PessoaServico.getInstance().cadastrarPessoa(pessoa);
						FuncionarioServico.getInstance().cadastrarFunc(func);
						cadastrado = true;
						pessoa = new Pessoa();
						func = new Funcionario();
						mensagem.mensagemCadastroSucesso();
					} else {
						cadastrado = false;
						mensagem.mensagemAtendenteEstoquistaJaCadastrado("Estoquista");
					}
				} catch (Exception e) {
					mensagem.mensagemCadastroFalha();
					e.printStackTrace();
				}
				break;
			case 6:
				try {
					farmaceuticoCadastro = FuncionarioServico.getInstance()
							.findFarmaceutico(func.getCrf(),
									func.getUfConselho());

					if ((cpfCadastrado == 0) && (farmaceuticoCadastro == 0)) {
						func.setTipoFuncionario(5);
						func.setFlagAtivo(true);
						func_UBS = dualListUBS.getTarget();
						func.setUbs(func_UBS);
						func.setPessoa(pessoa);
						pessoa.setFunc(func);
						pessoa.setInd_func(1);
						pessoa.setSenha(convertStringToMd5(senha));
						PessoaServico.getInstance().cadastrarPessoa(pessoa);
						FuncionarioServico.getInstance().cadastrarFunc(func);
						cadastrado = true;
						pessoa = new Pessoa();
						func = new Funcionario();
						mensagem.mensagemCadastroSucesso();
					} else {
						cadastrado = false;
						mensagem.mensagemCodidoOuCPFInvalido("CRF");
					}
				} catch (Exception e) {
					mensagem.mensagemCadastroFalha();
					e.printStackTrace();
				}
				break;
			case 7:
				try {
					medicoCadastro = FuncionarioServico.getInstance()
							.findMedico(func.getCrm(), func.getUfConselho());

					if ((cpfCadastrado == 0) && (medicoCadastro == 0)) {
						esp_medico = especialidades.getTarget();
						func_UBS = dualListUBS.getTarget();
						func.setTipoFuncionario(6);
						func.setUbs(func_UBS);
						func.setFlagAtivo(true);
						func.setPessoa(pessoa);
						func.setEspecialidade(esp_medico);
						pessoa.setFunc(func);
						pessoa.setInd_func(1);
						pessoa.setSenha(convertStringToMd5(senha));
						PessoaServico.getInstance().cadastrarPessoa(pessoa);
						FuncionarioServico.getInstance().cadastrarFunc(func);
						cadastrado = true;
						pessoa = new Pessoa();
						func = new Funcionario();
						cadastrado = true;
						mensagem.mensagemCadastroSucesso();
					} else {
						cadastrado = false;
						mensagem.mensagemCodidoOuCPFInvalido("CRM");
					}
				} catch (Exception e) {
					mensagem.mensagemCadastroFalha();
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		} else {
			mensagem.mensagemCPFInvalido();
		}
		try {
			reiniciaPickList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reiniciaPickList() throws Exception {
		allEsp = new ArrayList<Especialidade>();
		esp_medico = new ArrayList<Especialidade>();
		allEsp = EspecialidadeServico.getInstance().listarAllEspecialidades();
		especialidades = new DualListModel<Especialidade>(allEsp, esp_medico);

		allUBSAC = new ArrayList<UBS>();
		func_UBSAC = new ArrayList<UBS>();
		dualListUBSAC = new DualListModel<UBS>(allUBSAC, func_UBSAC);

		allUBSAt = new ArrayList<UBS>();
		func_UBSAt = new ArrayList<UBS>();
		dualListUBSAt = new DualListModel<UBS>(allUBSAt, func_UBSAt);

		allUBSEnf = new ArrayList<UBS>();
		func_UBSEnf = new ArrayList<UBS>();
		dualListUBSEnf = new DualListModel<UBS>(allUBSEnf, func_UBSEnf);

		allUBSEst = new ArrayList<UBS>();
		func_UBSEst = new ArrayList<UBS>();
		dualListUBSEst = new DualListModel<UBS>(allUBSEst, func_UBSEst);

		allUBSFar = new ArrayList<UBS>();
		func_UBSFar = new ArrayList<UBS>();
		dualListUBSFar = new DualListModel<UBS>(allUBSFar, func_UBSFar);

		allUBS = new ArrayList<UBS>();
		func_UBS = new ArrayList<UBS>();
		allUBS = UBSServico.getInstance().listarAllUBS();
		setDualListUBS(new DualListModel<UBS>(allUBS, func_UBS));

		allUBSMed = new ArrayList<UBS>();
		func_UBSMed = new ArrayList<UBS>();
		dualListUBSMed = new DualListModel<>(allUBSMed, func_UBSMed);
	}

	public void editar() throws Exception {
		verificaFuncNull();
		verificaPacienteNull();

		pessoa.setCpf(pessoa.getCpf().toString().replaceAll("[- /.]", ""));
		iscpf = (validaCPF(pessoa.getCpf()));

		cpfCadastrado = PessoaServico.getInstance().findByCPF(pessoa.getCpf());

		if ((iscpf == true)
				&& ((cpfCadastrado == 0) || (cpfCadastrado == pessoa.getId()))) {

			if ((pessoa.getInd_adm() == 1) && (pessoa.getInd_func() == 0)
					&& (pessoa.getInd_paciente() == 0)) {

				pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
				PessoaServico.getInstance().editarPessoa(pessoa);
				mensagem.mensagemEdicaoSucesso();

			}

			if ((pessoa.getInd_paciente() == 1) && (pessoa.getInd_func() == 0)) {

				paciente = PacienteServico.getInstance().findByPessoa(
						pessoa.getId());

				if (pacNull == true) {
					mensagem.mensagemExclusaoFalha();
				} else {
					if ((func1Null == false) || (func2Null == false)
							|| (func3Null == false) || (func4Null == false)
							|| (func5Null == false) || (func6Null == false)) {

						if (func1Null == false) {
							pessoa.setInd_func(1);
							pessoa.setFunc(func1);
							func1.setPessoa(pessoa);
							func_UBSAC = dualListUBSAC.getTarget();
							func1.setUbs(func_UBSAC);
							func1.setTipoFuncionario(1);
							func1.setPessoa(pessoa);
							FuncionarioServico.getInstance().cadastrarFunc(
									func1);
							pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
							PessoaServico.getInstance().editarPessoa(pessoa);
							mensagem.mensagemCadastroSucesso();
						} else if (func2Null == false) {
							pessoa.setInd_func(1);
							pessoa.setFunc(func2);
							func_UBSAt = dualListUBSAt.getTarget();
							func2.setUbs(func_UBSAt);
							func2.setPessoa(pessoa);
							func2.setTipoFuncionario(2);
							func2.setPessoa(pessoa);
							FuncionarioServico.getInstance().cadastrarFunc(
									func2);
							pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
							PessoaServico.getInstance().editarPessoa(pessoa);
							mensagem.mensagemCadastroSucesso();
						} else if (func3Null == false) {
							pessoa.setInd_func(1);
							pessoa.setFunc(func3);
							func_UBSEnf = dualListUBSEnf.getTarget();
							func3.setUbs(func_UBSEnf);
							func3.setPessoa(pessoa);
							func3.setTipoFuncionario(3);
							func3.setPessoa(pessoa);
							FuncionarioServico.getInstance().cadastrarFunc(
									func3);
							pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
							PessoaServico.getInstance().editarPessoa(pessoa);
							mensagem.mensagemCadastroSucesso();
						} else if (func4Null == false) {
							pessoa.setInd_func(1);
							pessoa.setFunc(func4);
							func_UBSEst = dualListUBSEst.getTarget();
							func4.setUbs(func_UBSEst);
							func4.setPessoa(pessoa);
							func4.setTipoFuncionario(4);
							func4.setPessoa(pessoa);
							FuncionarioServico.getInstance().cadastrarFunc(
									func4);
							pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
							PessoaServico.getInstance().editarPessoa(pessoa);
							mensagem.mensagemCadastroSucesso();
						} else if (func5Null == false) {
							pessoa.setInd_func(1);
							pessoa.setFunc(func5);
							func_UBSFar = dualListUBSFar.getTarget();
							func5.setUbs(func_UBSFar);
							func5.setPessoa(pessoa);
							func5.setTipoFuncionario(5);
							func5.setPessoa(pessoa);
							FuncionarioServico.getInstance().cadastrarFunc(
									func5);
							pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
							PessoaServico.getInstance().editarPessoa(pessoa);
							mensagem.mensagemCadastroSucesso();
						} else if (func6Null == false) {
							pessoa.setInd_func(1);
							pessoa.setFunc(func6);
							func_UBSMed = dualListUBSMed.getTarget();
							func6.setUbs(func_UBSMed);
							func6.setPessoa(pessoa);
							func6.setTipoFuncionario(6);
							esp_medico = especialidades.getTarget();
							func6.setEspecialidade(esp_medico);
							func6.setPessoa(pessoa);
							FuncionarioServico.getInstance().cadastrarFunc(
									func6);
							pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
							PessoaServico.getInstance().editarPessoa(pessoa);
							mensagem.mensagemCadastroSucesso();
						}
						PacienteServico.getInstance().editarPaciente(paciente);
						mensagem.mensagemEdicaoSucesso();
					}
				}
			} else if ((pessoa.getInd_paciente() == 0)
					&& (pessoa.getInd_func() == 1)) {
				if (pacNull == false) {
					pessoa.setInd_paciente(1);
					pessoa.setPaciente(paciente);
					paciente.setPessoa(pessoa);
					PacienteServico.getInstance().cadastrarPaciente(paciente);

					// Gera a caderneta de Vacinação
					geraCadernetaVacinacao(paciente);

					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
				}
				func = pessoa.getFunc();
				editarFunc();

			} else if ((pessoa.getInd_paciente() == 1)
					&& (pessoa.getInd_func() == 1)) {
				if (pacNull == true) {
					System.out.println("não é permitido excluir um paciente");
				} else {
					PacienteServico.getInstance().editarPaciente(paciente);
					mensagem.mensagemEdicaoSucesso();
				}

				func = pessoa.getFunc();
				editarFunc();
			}
		}

	}

	public void editarFunc() throws Exception {
		if (func.getTipoFuncionario() == 1) {
			if ((func2Null == false) || (func3Null == false)
					|| (func4Null == false) || (func5Null == false)
					|| (func6Null == false)) {

				if (func2Null == false) {
					pessoa.setInd_func(1);
					func_UBSAt = dualListUBSAt.getTarget();
					func.setUbs(func_UBSAt);
					func.setTipoFuncionario(2);
					func.setCodCBO(func2.getCodCBO());
					func.setUfConselho(null);
					func.setCrf(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Analista Clínico",
							"Atendente");
				} else if (func3Null == false) {
					pessoa.setInd_func(1);
					func_UBSEnf = dualListUBSEnf.getTarget();
					func.setUbs(func_UBSEnf);
					func.setTipoFuncionario(3);
					func.setCodCBO(func3.getCodCBO());
					func.setUfConselho(func3.getUfConselho());
					func.setCrf(0);
					func.setCoren(func3.getCoren());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Analista Clínico",
							"Enfermeiro");
				} else if (func4Null == false) {
					pessoa.setInd_func(1);
					func_UBSEst = dualListUBSEst.getTarget();
					func.setUbs(func_UBSEst);
					func.setTipoFuncionario(4);
					func.setCodCBO(func4.getCodCBO());
					func.setUfConselho(null);
					func.setCrf(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Analista Clínico",
							"Estoquista");
				} else if (func5Null == false) {
					pessoa.setInd_func(1);
					func_UBSFar = dualListUBSFar.getTarget();
					func.setUbs(func_UBSFar);
					func.setTipoFuncionario(5);
					func.setCodCBO(func5.getCodCBO());
					func.setUfConselho(func5.getUfConselho());
					func.setCrf(func5.getCrf());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Analista Clínico",
							"Farmacêutico");
				} else if (func6Null == false) {
					pessoa.setInd_func(1);
					func_UBSMed = dualListUBSMed.getTarget();
					func.setUbs(func_UBSMed);
					func.setTipoFuncionario(6);
					esp_medico = especialidades.getTarget();
					func.setEspecialidade(esp_medico);
					func.setCodCBO(func6.getCodCBO());
					func.setUfConselho(func6.getUfConselho());
					func.setCrf(0);
					func.setCrm(func6.getCrm());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Analista Clínico",
							"Médico");
				}
			} else {
				func_UBSAC = dualListUBSAC.getTarget();
				func.setUbs(func_UBSAC);
				func.setCodCBO(func1.getCodCBO());
				func.setCrf(func1.getCrf());
				func.setUfConselho(func1.getUfConselho());
				FuncionarioServico.getInstance().editarFunc(func);
				pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
				PessoaServico.getInstance().editarPessoa(pessoa);
				mensagem.mensagemEdicaoSucesso();
			}
		} else if (func.getTipoFuncionario() == 2) {
			if ((func1Null == false) || (func3Null == false)
					|| (func4Null == false) || (func5Null == false)
					|| (func6Null == false)) {

				if (func1Null == false) {
					pessoa.setInd_func(1);
					func_UBSAC = dualListUBSAt.getTarget();
					func.setUbs(func_UBSAC);
					func.setTipoFuncionario(1);
					func.setCodCBO(func1.getCodCBO());
					func.setCrf(func1.getCrf());
					func.setUfConselho(null);
					func.setCrf(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Atendente",
							"Analista Clínico");
				} else if (func3Null == false) {
					pessoa.setInd_func(1);
					func_UBSEnf = dualListUBSEnf.getTarget();
					func.setUbs(func_UBSEnf);
					func.setTipoFuncionario(3);
					func.setCodCBO(func3.getCodCBO());
					func.setUfConselho(func3.getUfConselho());
					func.setCoren(func3.getCoren());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Atendente",
							"Enfermeiro");
				} else if (func4Null == false) {
					pessoa.setInd_func(1);
					func_UBSEst = dualListUBSEst.getTarget();
					func.setUbs(func_UBSEst);
					func.setTipoFuncionario(4);
					func.setCodCBO(func4.getCodCBO());
					func.setUfConselho(null);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Atendente",
							"Estoquista");
				} else if (func5Null == false) {
					pessoa.setInd_func(1);
					func_UBSFar = dualListUBSFar.getTarget();
					func.setUbs(func_UBSFar);
					func.setTipoFuncionario(5);
					func.setCodCBO(func5.getCodCBO());
					func.setUfConselho(func5.getUfConselho());
					func.setCrf(func5.getCrf());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Atendente",
							"Farmaceutico");
				} else if (func6Null == false) {
					pessoa.setInd_func(1);
					func_UBSMed = dualListUBSMed.getTarget();
					func.setUbs(func_UBSMed);
					func.setTipoFuncionario(6);
					esp_medico = especialidades.getTarget();
					func.setEspecialidade(esp_medico);
					func.setCodCBO(func6.getCodCBO());
					func.setUfConselho(func6.getUfConselho());
					func.setCrm(func6.getCrm());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Atendente", "Médico");
				}
			} else {
				func_UBSAC = dualListUBSAC.getTarget();
				func.setUbs(func_UBSAC);
				FuncionarioServico.getInstance().editarFunc(func);
				pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
				PessoaServico.getInstance().editarPessoa(pessoa);
				mensagem.mensagemEdicaoSucesso();
			}
		} else if (func.getTipoFuncionario() == 3) {
			if ((func2Null == false) || (func1Null == false)
					|| (func4Null == false) || (func5Null == false)
					|| (func6Null == false)) {

				if (func2Null == false) {
					pessoa.setInd_func(1);
					func_UBSAt = dualListUBSAt.getTarget();
					func.setUbs(func_UBSAt);
					func.setTipoFuncionario(2);
					func.setCodCBO(func2.getCodCBO());
					func.setUfConselho(null);
					func.setCoren(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Enfermeiro",
							"Atendente");
				} else if (func1Null == false) {
					pessoa.setInd_func(1);
					func_UBSAC = dualListUBSAC.getTarget();
					func.setUbs(func_UBSAC);
					func.setTipoFuncionario(1);
					func.setCodCBO(func1.getCodCBO());
					func.setUfConselho(func1.getUfConselho());
					func.setCrf(func1.getCrf());
					func.setCoren(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Enfermeiro",
							"Analista Clínico");
				} else if (func4Null == false) {
					pessoa.setInd_func(1);
					func_UBSEst = dualListUBSEst.getTarget();
					func.setUbs(func_UBSEst);
					func.setTipoFuncionario(4);
					func.setCodCBO(func4.getCodCBO());
					func.setUfConselho(null);
					func.setCoren(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Enfermeiro",
							"Estoquista");
				} else if (func5Null == false) {
					pessoa.setInd_func(1);
					func_UBSFar = dualListUBSFar.getTarget();
					func.setUbs(func_UBSFar);
					func.setTipoFuncionario(5);
					func.setCodCBO(func5.getCodCBO());
					func.setUfConselho(func5.getUfConselho());
					func.setCrf(func5.getCrf());
					func.setCoren(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Enfermeiro",
							"Farmacêutico");
				} else if (func6Null == false) {
					pessoa.setInd_func(1);
					func_UBSMed = dualListUBSMed.getTarget();
					func.setUbs(func_UBSMed);
					func.setTipoFuncionario(6);
					esp_medico = especialidades.getTarget();
					func.setEspecialidade(esp_medico);
					func.setCodCBO(func6.getCodCBO());
					func.setUfConselho(func6.getUfConselho());
					func.setCoren(0);
					func.setCrm(func6.getCrm());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Enfermeiro",
							"Médico");
				}
			} else {
				func_UBSEnf = dualListUBSEnf.getTarget();
				func.setUbs(func_UBSEnf);
				func.setCodCBO(func3.getCodCBO());
				func.setCoren(func3.getCoren());
				func.setUfConselho(func3.getUfConselho());
				FuncionarioServico.getInstance().editarFunc(func);
				pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
				PessoaServico.getInstance().editarPessoa(pessoa);
				mensagem.mensagemEdicaoSucesso();
			}

		} else if (func.getTipoFuncionario() == 4) {
			if ((func2Null == false) || (func3Null == false)
					|| (func1Null == false) || (func5Null == false)
					|| (func6Null == false)) {

				if (func1Null == false) {
					pessoa.setInd_func(1);
					func_UBSAC = dualListUBSAt.getTarget();
					func.setUbs(func_UBSAC);
					func.setTipoFuncionario(1);
					func.setCodCBO(func1.getCodCBO());
					func.setCrf(func1.getCrf());
					func.setUfConselho(null);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Estoquista",
							"Analista Clínico");
				} else if (func3Null == false) {
					pessoa.setInd_func(1);
					func_UBSEnf = dualListUBSEnf.getTarget();
					func.setUbs(func_UBSEnf);
					func.setTipoFuncionario(3);
					func.setCodCBO(func3.getCodCBO());
					func.setUfConselho(func3.getUfConselho());
					func.setCoren(func3.getCoren());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Estoquista",
							"Enfermeiro");
				} else if (func2Null == false) {
					pessoa.setInd_func(1);
					func_UBSAt = dualListUBSAt.getTarget();
					func.setUbs(func_UBSAt);
					func.setTipoFuncionario(2);
					func.setCodCBO(func2.getCodCBO());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Estoquista",
							"Atendente");
				} else if (func5Null == false) {
					pessoa.setInd_func(1);
					func_UBSFar = dualListUBSFar.getTarget();
					func.setUbs(func_UBSFar);
					func.setTipoFuncionario(5);
					func.setCodCBO(func5.getCodCBO());
					func.setUfConselho(func5.getUfConselho());
					func.setCrf(func5.getCrf());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Estoquista",
							"Farmacêutico");
				} else if (func6Null == false) {
					pessoa.setInd_func(1);
					func_UBSMed = dualListUBSMed.getTarget();
					func.setUbs(func_UBSMed);
					func.setTipoFuncionario(6);
					esp_medico = especialidades.getTarget();
					func.setEspecialidade(esp_medico);
					func.setCodCBO(func6.getCodCBO());
					func.setUfConselho(func6.getUfConselho());
					func.setCrm(func6.getCrm());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Estoquista",
							"Médico");
				}
			} else {
				func_UBSEst = dualListUBSEst.getTarget();
				func.setUbs(func_UBSEst);
				FuncionarioServico.getInstance().editarFunc(func);
				pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
				PessoaServico.getInstance().editarPessoa(pessoa);
				mensagem.mensagemEdicaoSucesso();
			}

		} else if (func.getTipoFuncionario() == 5) {
			if ((func2Null == false) || (func3Null == false)
					|| (func4Null == false) || (func5Null == false)
					|| (func6Null == false)) {

				if (func2Null == false) {
					pessoa.setInd_func(1);
					func_UBSAt = dualListUBSAt.getTarget();
					func.setUbs(func_UBSAt);
					func.setTipoFuncionario(2);
					func.setCodCBO(func2.getCodCBO());
					func.setUfConselho(null);
					func.setCrf(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Atendente");
				} else if (func3Null == false) {
					pessoa.setInd_func(1);
					func_UBSEnf = dualListUBSEnf.getTarget();
					func.setUbs(func_UBSEnf);
					func.setTipoFuncionario(3);
					func.setCodCBO(func3.getCodCBO());
					func.setUfConselho(func3.getUfConselho());
					func.setCrf(0);
					func.setCoren(func3.getCoren());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Enfermeiro");
				} else if (func4Null == false) {
					pessoa.setInd_func(1);
					func_UBSEst = dualListUBSEst.getTarget();
					func.setUbs(func_UBSEst);
					func.setTipoFuncionario(4);
					func.setCodCBO(func4.getCodCBO());
					func.setUfConselho(null);
					func.setCrf(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Estoquista");
				} else if (func1Null == false) {
					pessoa.setInd_func(1);
					func_UBSAC = dualListUBSAC.getTarget();
					func.setUbs(func_UBSAC);
					func.setTipoFuncionario(1);
					func.setCodCBO(func1.getCodCBO());
					func.setUfConselho(func1.getUfConselho());
					func.setCrf(func1.getCrf());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Analista Clínico");
				} else if (func6Null == false) {
					pessoa.setInd_func(1);
					func_UBSMed = dualListUBSMed.getTarget();
					func.setUbs(func_UBSMed);
					func.setTipoFuncionario(6);
					esp_medico = especialidades.getTarget();
					func.setEspecialidade(esp_medico);
					func.setCodCBO(func6.getCodCBO());
					func.setUfConselho(func6.getUfConselho());
					func.setCrf(0);
					func.setCrm(func6.getCrm());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Médico");
				}
			} else {
				func_UBSFar = dualListUBSFar.getTarget();
				func.setUbs(func_UBSFar);
				func.setCodCBO(func5.getCodCBO());
				func.setCrf(func5.getCrf());
				func.setUfConselho(func5.getUfConselho());
				FuncionarioServico.getInstance().editarFunc(func);
				pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
				PessoaServico.getInstance().editarPessoa(pessoa);
				mensagem.mensagemEdicaoSucesso();
			}

		} else if (func.getTipoFuncionario() == 6) {
			if ((func2Null == false) || (func3Null == false)
					|| (func4Null == false) || (func5Null == false)
					|| (func1Null == false)) {

				if (func2Null == false) {
					pessoa.setInd_func(1);
					func_UBSAt = dualListUBSAt.getTarget();
					func.setUbs(func_UBSAt);
					func.setTipoFuncionario(2);
					func.setCodCBO(func2.getCodCBO());
					func.setUfConselho(null);
					func.setEspecialidade(null);
					func.setCrm(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Atendente");
				} else if (func3Null == false) {
					pessoa.setInd_func(1);
					func_UBSEnf = dualListUBSEnf.getTarget();
					func.setUbs(func_UBSEnf);
					func.setTipoFuncionario(3);
					func.setCodCBO(func3.getCodCBO());
					func.setUfConselho(func3.getUfConselho());
					func.setEspecialidade(null);
					func.setCrm(0);
					func.setCoren(func3.getCoren());
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Enfermeiro");
				} else if (func4Null == false) {
					pessoa.setInd_func(1);
					func_UBSEst = dualListUBSEst.getTarget();
					func.setUbs(func_UBSEst);
					func.setTipoFuncionario(4);
					func.setCodCBO(func4.getCodCBO());
					func.setUfConselho(null);
					func.setEspecialidade(null);
					func.setCrm(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Estoquista");
				} else if (func5Null == false) {
					pessoa.setInd_func(1);
					func_UBSFar = dualListUBSFar.getTarget();
					func.setUbs(func_UBSFar);
					func.setTipoFuncionario(5);
					func.setCodCBO(func5.getCodCBO());
					func.setUfConselho(func5.getUfConselho());
					func.setCrf(func5.getCrf());
					func.setEspecialidade(null);
					func.setCrm(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Farmacêutico");
				} else if (func1Null == false) {
					pessoa.setInd_func(1);
					func_UBSAC = dualListUBSAC.getTarget();
					func.setUbs(func_UBSAC);
					func.setTipoFuncionario(1);
					func.setCodCBO(func1.getCodCBO());
					func.setUfConselho(func1.getUfConselho());
					func.setCrf(func1.getCrf());
					func.setEspecialidade(null);
					func.setCrm(0);
					FuncionarioServico.getInstance().editarFunc(func);
					pessoa.setSenha(convertStringToMd5(pessoa.getSenha()));
					PessoaServico.getInstance().editarPessoa(pessoa);
					mensagem.mensagemAlteracaoFuncSucesso("Farmacêutico",
							"Analista Clínico");
				}
			} else {
				func_UBSMed = dualListUBSMed.getTarget();
				func.setUbs(func_UBSMed);
				allEsp = especialidades.getSource();
				esp_medico = especialidades.getTarget();
				func.setEspecialidade(esp_medico);
				func.setCrm(func6.getCrm());
				func.setUfConselho(func6.getUfConselho());
				func.setCodCBO(func6.getCodCBO());
				FuncionarioServico.getInstance().editarFunc(func6);
				mensagem.mensagemEdicaoSucesso();
			}
		}
	}

	public void verificaPacienteNull() {
		if ((paciente.getCns().equals(""))
				&& (paciente.getObservacoes().equals(""))
				&& (paciente.getQuantFalta() == 0)) {
			pacNull = true;
		}
	}

	public void verificaFuncNull() {

		if ((func1.getCodCBO() == 0) && (func1.getCrf() == 0)
				&& (func1.getUfConselho().equals(""))) {
			func1Null = true;
		} else {
			func1Null = false;
		}

		if (func2.getCodCBO() == 0) {
			func2Null = true;
		} else {
			func2Null = false;
		}

		if ((func3.getCodCBO() == 0) && (func3.getCoren() == 0)
				&& (func3.getUfConselho() == "")) {
			func3Null = true;
		} else {
			func3Null = false;
		}

		if (func4.getCodCBO() == 0) {
			func4Null = true;
		} else {
			func4Null = false;
		}

		if ((func5.getCodCBO() == 0) && (func5.getCrf() == 0)
				&& (func5.getUfConselho() == "")) {
			func5Null = true;
		} else {
			func5Null = false;
		}

		if ((func6.getCodCBO() == 0) && (func6.getCrm() == 0)
				&& (func6.getUfConselho() == "")
				&& (func6.getEspecialidade() == null)) {
			func6Null = true;
		} else {
			func6Null = false;
		}
	}

	public boolean validaCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111")
				|| CPF.equals("22222222222") || CPF.equals("33333333333")
				|| CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777")
				|| CPF.equals("88888888888") || CPF.equals("99999999999")
				|| (CPF.length() != 11))

			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;
		// "try" - protege o codigo para eventuais erros de conversao de tipo
		// (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);

			if ((r == 10) || (r == 11))
				dig10 = '0';

			else
				dig10 = (char) (r + 48);

			// converte no respectivo caractere numerico
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);
			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public boolean validaCNS(String cns) {
		if (cns.matches("[1-2]\\d{10}00[0-1]\\d")
				|| cns.matches("[7-9]\\d{14}")) {
			return somaPonderada(cns) % 11 == 0;
		}
		return false;
	}

	private int somaPonderada(String s) {
		char[] cs = s.toCharArray();
		int soma = 0;
		for (int i = 0; i < cs.length; i++) {
			soma += Character.digit(cs[i], 10) * (15 - i);
		}
		return soma;
	}

	/*
	 * @Fellipe: Função para geração da caderneta de Vacinação
	 */

	public void geraCadernetaVacinacao(Paciente p) {

		List<Vacina> vacinas = new ArrayList<Vacina>();

		vacinas = VacinaServico.getInstance().listarAllVacina();

		// Atributos do procedimento
		int i = 0;
		int j = 0;
		int quantDoses = 0;
		int addMes = 0;
		boolean indObrigatorio = false;

		while (i < vacinas.size()) {

			indObrigatorio = vacinas.get(i).getObrigatorio();

			addMes = 0;

			Calendar calDataPrevista = Calendar.getInstance();
			calDataPrevista.setTime(new Date());

			Calendar calProximaDose = Calendar.getInstance();
			calProximaDose.setTime(new Date());

			// Verifico se é obrigatória
			if (indObrigatorio == true) {

				quantDoses = vacinas.get(i).getQuantDose();

				j = 0;

				// Repetição de tratamento de cada dose das vacinas
				while (j < quantDoses) {

					Vacinacao vacinacao = new Vacinacao();

					/*
					 * Gero a Data Prevista
					 */

					calDataPrevista.setTime(p.getPessoa().getDtNascimento());

					// Se for a primeira dose, pego a idade (mês) da primeira
					// dose
					if (j == 0) {
						addMes = vacinas.get(i).getIdadePrimeiraDose();
						// do contrário, pego o intervalo até a próxima dose
					} else {
						addMes = vacinas.get(i).getIntervaloDose();
					}

					calDataPrevista.add(Calendar.MONTH, addMes);

					/*
					 * Gero a Próxima Dose
					 */

					// se não for a primeira dose OU não for a última dose
					if ((j < 1) || (j + 1 == quantDoses)) {
						addMes = vacinas.get(i).getIntervaloDose();
						calProximaDose.setTime(calDataPrevista.getTime());
						calProximaDose.add(Calendar.MONTH, addMes);
						// Seto a próxima dose para a vacinação
						vacinacao.setProximaDose(calProximaDose.getTime());
					}

					// Componho o objeto Vacinacao
					vacinacao.setDtPrevistaAplicacao(calDataPrevista.getTime());
					vacinacao.setPaciente(p);
					vacinacao.setNumDose(j + 1);
					// Se for primeira dose, ProximaDose = NULL
					vacinacao.setStatus("Pendente");
					vacinacao.setVacina(vacinas.get(i));

					// Cadastro a Vacina
					try {
						VacinacaoServico.getInstance().cadastrarVacinacao(
								vacinacao);
					} catch (Exception e) {
						e.printStackTrace();
					}
					j++;
				}
			}
			i++;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	public MensagensBean getMensagem() {
		return mensagem;
	}

	public int getCpfCadastrado() {
		return cpfCadastrado;
	}

	public void setCpfCadastrado(int cpfCadastrado) {
		this.cpfCadastrado = cpfCadastrado;
	}

	public boolean isCadastrado() {
		return cadastrado;
	}

	public void setCadastrado(boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	public int getPacienteCadastrado() {
		return pacienteCadastrado;
	}

	public void setPacienteCadastrado(int pacienteCadastrado) {
		this.pacienteCadastrado = pacienteCadastrado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getAclinicoCadastrado() {
		return aclinicoCadastrado;
	}

	public void setAclinicoCadastrado(int aclinicoCadastrado) {
		this.aclinicoCadastrado = aclinicoCadastrado;
	}

	public int getAtendenteCadastrado() {
		return atendenteCadastrado;
	}

	public void setAtendenteCadastrado(int atendenteCadastrado) {
		this.atendenteCadastrado = atendenteCadastrado;
	}

	public int getEnfermeiroCadastro() {
		return enfermeiroCadastro;
	}

	public void setEnfermeiroCadastro(int enfermeiroCadastro) {
		this.enfermeiroCadastro = enfermeiroCadastro;
	}

	public int getEstoquistaCadastro() {
		return estoquistaCadastro;
	}

	public void setEstoquistaCadastro(int estoquistaCadastro) {
		this.estoquistaCadastro = estoquistaCadastro;
	}

	public boolean isHabPaciente() {
		return habPaciente;
	}

	public void setHabPaciente(boolean habPaciente) {
		this.habPaciente = habPaciente;
	}

	public boolean isHabFunc() {
		return habFunc;
	}

	public void setHabFunc(boolean habFunc) {
		this.habFunc = habFunc;
	}

	public boolean isIscpf() {
		return iscpf;
	}

	public void setIscpf(boolean iscpf) {
		this.iscpf = iscpf;
	}

	public int getFarmaceuticoCadastro() {
		return farmaceuticoCadastro;
	}

	public void setFarmaceuticoCadastro(int farmaceuticoCadastro) {
		this.farmaceuticoCadastro = farmaceuticoCadastro;
	}

	public int getMedicoCadastro() {
		return medicoCadastro;
	}

	public void setMedicoCadastro(int medicoCadastro) {
		this.medicoCadastro = medicoCadastro;
	}

	public boolean isIscns() {
		return iscns;
	}

	public void setIscns(boolean iscns) {
		this.iscns = iscns;
	}

	public DualListModel<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(DualListModel<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public boolean isCpfAux() {
		return cpfAux;
	}

	public void setCpfAux(boolean cpfAux) {
		this.cpfAux = cpfAux;
	}

	public List<Especialidade> getAllEsp() {
		return allEsp;
	}

	public void setAllEsp(List<Especialidade> allEsp) {
		this.allEsp = allEsp;
	}

	public List<Especialidade> getEsp_medico() {
		return esp_medico;
	}

	public void setEsp_medico(List<Especialidade> esp_medico) {
		this.esp_medico = esp_medico;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

	public boolean isExcluirPessoa() {
		return excluirPessoa;
	}

	public void setExcluirPessoa(boolean excluirPessoa) {
		this.excluirPessoa = excluirPessoa;
	}

	public boolean isHabilitaBusca() {
		return habilitaBusca;
	}

	public void setHabilitaBusca(boolean habilitaBusca) {
		this.habilitaBusca = habilitaBusca;
	}

	public boolean isHabilitaPessoa() {
		return habilitaPessoa;
	}

	public void setHabilitaPessoa(boolean habilitaPessoa) {
		this.habilitaPessoa = habilitaPessoa;
	}

	public boolean isHabilitaPacienteCad() {
		return habilitaPacienteCad;
	}

	public void setHabilitaPacienteCad(boolean habilitaPacienteCad) {
		this.habilitaPacienteCad = habilitaPacienteCad;
	}

	public boolean isHabilitaPacienteDB() {
		return habilitaPacienteDB;
	}

	public void setHabilitaPacienteDB(boolean habilitaPacienteDB) {
		this.habilitaPacienteDB = habilitaPacienteDB;
	}

	public boolean isHabilitaCBOCad() {
		return habilitaCBOCad;
	}

	public void setHabilitaCBOCad(boolean habilitaCBOCad) {
		this.habilitaCBOCad = habilitaCBOCad;
	}

	public boolean isHabilitaCRFCad() {
		return habilitaCRFCad;
	}

	public void setHabilitaCRFCad(boolean habilitaCRFCad) {
		this.habilitaCRFCad = habilitaCRFCad;
	}

	public boolean isHabilitaEnfCad() {
		return habilitaEnfCad;
	}

	public void setHabilitaEnfCad(boolean habilitaEnfCad) {
		this.habilitaEnfCad = habilitaEnfCad;
	}

	public boolean isHabilitaMedCad() {
		return habilitaMedCad;
	}

	public void setHabilitaMedCad(boolean habilitaMedCad) {
		this.habilitaMedCad = habilitaMedCad;
	}

	public boolean isHabilitaUFConselhoCad() {
		return habilitaUFConselhoCad;
	}

	public void setHabilitaUFConselhoCad(boolean habilitaUFConselhoCad) {
		this.habilitaUFConselhoCad = habilitaUFConselhoCad;
	}

	// public int getRadio() {
	// return radio;
	// }
	//
	// public void setRadio(int radio) {
	// this.radio = radio;
	// }

	public boolean isHabilitaTab() {
		return habilitaTab;
	}

	public void setHabilitaTab(boolean habilitaTab) {
		this.habilitaTab = habilitaTab;
	}

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	public Funcionario getFunc1() {
		return func1;
	}

	public void setFunc1(Funcionario func1) {
		this.func1 = func1;
	}

	public Funcionario getFunc2() {
		return func2;
	}

	public void setFunc2(Funcionario func2) {
		this.func2 = func2;
	}

	public Funcionario getFunc3() {
		return func3;
	}

	public void setFunc3(Funcionario func3) {
		this.func3 = func3;
	}

	public Funcionario getFunc4() {
		return func4;
	}

	public void setFunc4(Funcionario func4) {
		this.func4 = func4;
	}

	public Funcionario getFunc5() {
		return func5;
	}

	public void setFunc5(Funcionario func5) {
		this.func5 = func5;
	}

	public Funcionario getFunc6() {
		return func6;
	}

	public void setFunc6(Funcionario func6) {
		this.func6 = func6;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public boolean isFunc1Null() {
		return func1Null;
	}

	public void setFunc1Null(boolean func1Null) {
		this.func1Null = func1Null;
	}

	public int getIdFunc1() {
		return idFunc1;
	}

	public void setIdFunc1(int idFunc1) {
		this.idFunc1 = idFunc1;
	}

	public boolean isFunc2Null() {
		return func2Null;
	}

	public void setFunc2Null(boolean func2Null) {
		this.func2Null = func2Null;
	}

	public int getIdFunc2() {
		return idFunc2;
	}

	public void setIdFunc2(int idFunc2) {
		this.idFunc2 = idFunc2;
	}

	public boolean isFunc3Null() {
		return func3Null;
	}

	public void setFunc3Null(boolean func3Null) {
		this.func3Null = func3Null;
	}

	public int getIdFunc3() {
		return idFunc3;
	}

	public void setIdFunc3(int idFunc3) {
		this.idFunc3 = idFunc3;
	}

	public boolean isFunc4Null() {
		return func4Null;
	}

	public void setFunc4Null(boolean func4Null) {
		this.func4Null = func4Null;
	}

	public int getIdFunc4() {
		return idFunc4;
	}

	public void setIdFunc4(int idFunc4) {
		this.idFunc4 = idFunc4;
	}

	public boolean isFunc5Null() {
		return func5Null;
	}

	public void setFunc5Null(boolean func5Null) {
		this.func5Null = func5Null;
	}

	public int getIdFunc5() {
		return idFunc5;
	}

	public void setIdFunc5(int idFunc5) {
		this.idFunc5 = idFunc5;
	}

	public boolean isFunc6Null() {
		return func6Null;
	}

	public void setFunc6Null(boolean func6Null) {
		this.func6Null = func6Null;
	}

	public int getIdFunc6() {
		return idFunc6;
	}

	public void setIdFunc6(int idFunc6) {
		this.idFunc6 = idFunc6;
	}

	public boolean isPacNull() {
		return pacNull;
	}

	public void setPacNull(boolean pacNull) {
		this.pacNull = pacNull;
	}

	public boolean isExcAC() {
		return excAC;
	}

	public void setExcAC(boolean excAC) {
		this.excAC = excAC;
	}

	public boolean isExcAT() {
		return excAT;
	}

	public void setExcAT(boolean excAT) {
		this.excAT = excAT;
	}

	public boolean isExcENF() {
		return excENF;
	}

	public void setExcENF(boolean excENF) {
		this.excENF = excENF;
	}

	public boolean isExcEST() {
		return excEST;
	}

	public void setExcEST(boolean excEST) {
		this.excEST = excEST;
	}

	public boolean isExcFAR() {
		return excFAR;
	}

	public void setExcFAR(boolean excFAR) {
		this.excFAR = excFAR;
	}

	public boolean isExcMED() {
		return excMED;
	}

	public void setExcMED(boolean excMED) {
		this.excMED = excMED;
	}

	public int getIdFunc() {
		return idFunc;
	}

	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}

	public DualListModel<UBS> getDualListUBS() {
		return dualListUBS;
	}

	public void setDualListUBS(DualListModel<UBS> dualListUBS) {
		this.dualListUBS = dualListUBS;
	}

	public List<UBS> getAllUBS() {
		return allUBS;
	}

	public void setAllUBS(List<UBS> allUBS) {
		this.allUBS = allUBS;
	}

	public List<UBS> getFunc_UBS() {
		return func_UBS;
	}

	public void setFunc_UBS(List<UBS> func_UBS) {
		this.func_UBS = func_UBS;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public UBS getBairroSelecionado() {
		return bairroSelecionado;
	}

	public void setBairroSelecionado(UBS bairroSelecionado) {
		this.bairroSelecionado = bairroSelecionado;
	}

	public List<UBS> getListaBairro() {
		return listaBairro;
	}

	public void setListaBairro(List<UBS> listaBairro) {
		this.listaBairro = listaBairro;
	}

	public DualListModel<UBS> getDualListUBSMed() {
		return dualListUBSMed;
	}

	public void setDualListUBSMed(DualListModel<UBS> dualListUBSMed) {
		this.dualListUBSMed = dualListUBSMed;
	}

	public List<UBS> getAllUBSMed() {
		return allUBSMed;
	}

	public void setAllUBSMed(List<UBS> allUBSMed) {
		this.allUBSMed = allUBSMed;
	}

	public List<UBS> getFunc_UBSMed() {
		return func_UBSMed;
	}

	public void setFunc_UBSMed(List<UBS> func_UBSMed) {
		this.func_UBSMed = func_UBSMed;
	}

	public DualListModel<UBS> getDualListUBSAC() {
		return dualListUBSAC;
	}

	public void setDualListUBSAC(DualListModel<UBS> dualListUBSAC) {
		this.dualListUBSAC = dualListUBSAC;
	}

	public List<UBS> getAllUBSAC() {
		return allUBSAC;
	}

	public void setAllUBSAC(List<UBS> allUBSAC) {
		this.allUBSAC = allUBSAC;
	}

	public List<UBS> getFunc_UBSAC() {
		return func_UBSAC;
	}

	public void setFunc_UBSAC(List<UBS> func_UBSAC) {
		this.func_UBSAC = func_UBSAC;
	}

	public DualListModel<UBS> getDualListUBSAt() {
		return dualListUBSAt;
	}

	public void setDualListUBSAt(DualListModel<UBS> dualListUBSAt) {
		this.dualListUBSAt = dualListUBSAt;
	}

	public List<UBS> getAllUBSAt() {
		return allUBSAt;
	}

	public void setAllUBSAt(List<UBS> allUBSAt) {
		this.allUBSAt = allUBSAt;
	}

	public List<UBS> getFunc_UBSAt() {
		return func_UBSAt;
	}

	public void setFunc_UBSAt(List<UBS> func_UBSAt) {
		this.func_UBSAt = func_UBSAt;
	}

	public DualListModel<UBS> getDualListUBSEnf() {
		return dualListUBSEnf;
	}

	public void setDualListUBSEnf(DualListModel<UBS> dualListUBSEnf) {
		this.dualListUBSEnf = dualListUBSEnf;
	}

	public List<UBS> getAllUBSEnf() {
		return allUBSEnf;
	}

	public void setAllUBSEnf(List<UBS> allUBSEnf) {
		this.allUBSEnf = allUBSEnf;
	}

	public List<UBS> getFunc_UBSEnf() {
		return func_UBSEnf;
	}

	public void setFunc_UBSEnf(List<UBS> func_UBSEnf) {
		this.func_UBSEnf = func_UBSEnf;
	}

	public DualListModel<UBS> getDualListUBSEst() {
		return dualListUBSEst;
	}

	public void setDualListUBSEst(DualListModel<UBS> dualListUBSEst) {
		this.dualListUBSEst = dualListUBSEst;
	}

	public List<UBS> getAllUBSEst() {
		return allUBSEst;
	}

	public void setAllUBSEst(List<UBS> allUBSEst) {
		this.allUBSEst = allUBSEst;
	}

	public List<UBS> getFunc_UBSEst() {
		return func_UBSEst;
	}

	public void setFunc_UBSEst(List<UBS> func_UBSEst) {
		this.func_UBSEst = func_UBSEst;
	}

	public DualListModel<UBS> getDualListUBSFar() {
		return dualListUBSFar;
	}

	public void setDualListUBSFar(DualListModel<UBS> dualListUBSFar) {
		this.dualListUBSFar = dualListUBSFar;
	}

	public List<UBS> getAllUBSFar() {
		return allUBSFar;
	}

	public void setAllUBSFar(List<UBS> allUBSFar) {
		this.allUBSFar = allUBSFar;
	}

	public List<UBS> getFunc_UBSFar() {
		return func_UBSFar;
	}

	public void setFunc_UBSFar(List<UBS> func_UBSFar) {
		this.func_UBSFar = func_UBSFar;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public Vacinacao getVacinacao() {
		return vacinacao;
	}

	public void setVacinacao(Vacinacao vacinacao) {
		this.vacinacao = vacinacao;
	}

	public List<Vacinacao> getCadernetaVacinacao() {
		return cadernetaVacinacao;
	}

	public void setCadernetaVacinacao(List<Vacinacao> cadernetaVacinacao) {
		this.cadernetaVacinacao = cadernetaVacinacao;
	}

}
//