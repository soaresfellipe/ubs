package br.com.servico;

import java.util.Date;

import br.com.controle.LoginControle;
import br.com.modelo.Pessoa;


public class LoginServico {
	
	private LoginControle controller;
	
	private static LoginServico instance = new LoginServico();

	public static LoginServico getInstance() {
		return instance;
	}

	public LoginServico() {
		controller = new LoginControle();
	}
	
	public Pessoa validaLogin(String cpf, String senha, Date dataNasc) throws Exception {
		return (Pessoa) controller.validaLogin(cpf, senha, dataNasc);
	}
	
}
