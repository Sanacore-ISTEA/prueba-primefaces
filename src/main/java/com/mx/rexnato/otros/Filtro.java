package com.mx.rexnato.otros;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter("/*")
public class Filtro implements Filter {

	HashMap<String,Boolean> sitios = new HashMap<>(); 
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String usuario = null; 
		if(httpRequest.getSession().getAttribute("usuario") != null){
			usuario = httpRequest.getSession().getAttribute("usuario").toString();
		}
		
		String uriAcceso = httpRequest.getRequestURI();
		String contexto = httpRequest.getContextPath();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//acceso a parte privada si no tiene un atributo usuario manda al login
		if(uriAcceso.contains("secciones") && usuario == null){
			
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.xhtml");
			return;
		}
		
		if(uriAcceso.equals(contexto+"/login.xhtml") && usuario != null){
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/secciones/formulario.xhtml");
			return;
		}
		
		System.out.println("estoy en el filtrio con usuario "+usuario);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
