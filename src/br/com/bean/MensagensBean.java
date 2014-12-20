package br.com.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.modelo.Agenda;
import br.com.modelo.Paciente;
import br.com.modelo.Produto;

@ManagedBean
@ViewScoped
public class MensagensBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void mensagemCadastroFalha() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
						"Cadastro não realizado!"));
	}

	public void mensagemCadastroSucesso() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Cadastro realizado com sucesso"));
	}

	public void mensagemEdicaoSucesso() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Registro editado com sucesso!"));
	}

	public void mensagemEdicaoFalha() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!",
						"Não foi possível editar o registro!"));
	}

	public void mensagemExclusaoSucesso() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Registro excluído com sucesso!"));
	}

	public void mensagemExclusaoFalha() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!",
						"Registro não excluído!"));
	}

	public void mensagemResultadoExameSucesso() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Exame finalizado com sucesso!"));
	}

	public void mensagemResultadoExameFalha() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!",
						"Exame não finalizado!"));
	}

	public void mensagemPreencherCamposFalha() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!",
						"Por favor, preencha os campos com *!"));
	}

	public void mensagemConsultaDesmarcada() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Consulta desmarcada!!"));
	}

	public void mensagemConsultaNaoDesmarcada() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!",
						"Esta consulta não pode mais ser desmarcada!"));
	}

	public void mensagemFaltaSucesso(Paciente p) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"O paciente possui " + p.getQuantFalta() + " faltas."));
	}

	public void mensagemFaltaFalha() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Erro!",
								"Só é possivel marcar uma falta se a consulta estiver agendada para um paciente e se hora atual for maior que a hora da consulta!"));
	}

	public void mensagemEntreEmContato(Agenda a) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Entre em contato com o Paciente "
								+ a.getPaciente().getPessoa().getNome()
								+ "nos telefones: "
								+ a.getPaciente().getPessoa().getTelefone()
								+ " ou "
								+ a.getPaciente().getPessoa().getCelular()));
	}

	public void mensagemAgendaEncaixeFalha() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"",
								"O medico não possui agenda para a UBS selecionada, no dia que vc deseja incluir um encaixe, ou está com horario livre na agenda"));
	}

	public void mensagemPacienteAgendado() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Paciente agendado para a consulta"));
	}

	public void mensagemPacienteNaoAgendado() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"",
								"Não é permitido incluir um paciente em uma consulta wue não esteja Disponível, ou que já tenha passado do horário"));
	}

	public void mensagemAgendaNaoCadastrada() {

		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "",
								"Não é permitido gerar agenda para dias anteriores ao atual!!!"));
	}

	public void mensagemAgendaLotada() {

		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "",
								"O médico já possui horários cadastrados para o dia selecionado"));
	}

	public void mensagemReceitaFinalizada() {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Receita finalizada e medicamento aplicado!"));
	}

	public void mensagemReceitaExternaCadastroSucesso() {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Receita externa cadastrada e medicamento retirado"));
	}

	public void mensagemRetiradaSucesso() {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Item retirado do estoque!"));
	}

	public void mensagemFaltaQuantidadeSolicitada() {

		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "",
								"Não há a quantidade suficiente em estoque para atender a solicitação!"));
	}

	public void mensagemCodidoOuCPFInvalido(String s) {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "CPF OU " + s
						+ " JÁ CADASTRADOS"));
	}

	public void mensagemAtendenteEstoquistaJaCadastrado(String s) {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"CPF já cadastrado como" + s));
	}

	public void mensagemCPFInvalido() {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"CPF inválido!"));
	}

	public void mensagemAlteracaoFuncSucesso(String s1, String s2) {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
						"Mudança de " + s1 + " para " + s2
								+ " realizada com sucesso"));
	}

	public void mensagemVacinaAplicadaSucesso() {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Vacina aplicada no Paciente!"));
	}
	
	public void produtoJaNaLista() {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Este produto já se encontra na lista de pedido!"));
	}
	
	public void produtoRetirado(Produto p) {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						"Produto "+p.getNome()+" retirado do estoque."));
	}

	public void semQuantOuProd() {

		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"Preencha o campo funcionário, medicamento ou verifique se a quantidade indicada não é menor ou igual a 0."));
	}
	
	public void naoHaQuantProdEstoque(Produto p) {
		  FacesContext
		    .getCurrentInstance()
		    .addMessage(
		      null,
		      new FacesMessage(
		        FacesMessage.SEVERITY_ERROR,
		        "",
		        "Não há o produto "
		          + p.getNome()
		          + " no estoque da UBS, na quantidade desejada!"));
		 }

}
