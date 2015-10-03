package br.com.colmeia.model.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Filter implements javax.servlet.Filter {

	public Filter() {

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requ = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Object user = null;
		user = requ.getSession().getAttribute("usuario");

		if (user != null || requ.getRequestURL().toString().endsWith("login.xhtml")
				|| requ.getRequestURL().toString().endsWith("cadastro.xhtml")
				|| requ.getRequestURL().toString().endsWith("cadastro_usuario_inicial.xhtml.xhtml")
				|| requ.getRequestURL().toString().endsWith("cadastro_usuario_inst_curso.xhtml")
				|| requ.getRequestURL().toString().endsWith("cadastro_usuario_dados_pessoais.xhtml")) {
			chain.doFilter(requ, resp);
		} else {
			resp.sendRedirect("/colmeia/pages/login/login.xhtml");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
