package br.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bean.LoginBean;
import br.com.modelo.Pessoa;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Capturo o ManagedBean chamado "usuario"
		Pessoa usuario = (Pessoa) ((HttpServletRequest) request)
				.getSession().getAttribute("usuario");

		// Verifica se o ManagedBean ainda não foi instanciado
		// ou caso a variável 'logado' seja false, assim saberemos
		// que o usuário não tá logado
		if ((usuario == null) || (!LoginBean.isLogado())) {
			String contextPath = ((HttpServletRequest) request)
					.getContextPath();
			// Redirecionamos o usuário imediatamente para a página de
			// index.xhtml
			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/index.xhtml");
		} else {
			// Caso esteja logado, apenas deixamos rolar =D
			chain.doFilter(request, response);
		}

	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
