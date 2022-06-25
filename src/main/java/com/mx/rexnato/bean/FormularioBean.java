package com.mx.rexnato.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class FormularioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name ;
	
	private String apellido ;
	
	private Integer pais = 0;
	
	@PostConstruct
	public void inicio(){
		System.out.println("hola mundo"+name);
	}
	
	public void cerrarSession() throws IOException{
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().removeAttribute("usuario");
		FacesContext.getCurrentInstance().getExternalContext().redirect(request.getRequestURI());
	}
	

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}