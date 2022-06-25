package com.mx.rexnato.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usuario;
	
	private String pass;
	
	
	public void iniciarSesion(){
		
		if(StringUtils.isBlank(this.usuario)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El usuario es requerido"));
			InputText input = (InputText) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:usuario");
			input.setValid(false);
		}else if(StringUtils.isBlank(this.pass)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pass es requerido"));
			Password passInput =(Password) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:pass");
			passInput.setValid(false);
		}else if(!this.usuario.equals("usuario")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El usuario es incorrecto"));
			InputText input = (InputText) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:usuario");
			input.setValid(false);
		}else if(!this.pass.equals("usuario")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El pass es incorrecto"));
			Password passInput =(Password) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:pass");
			passInput.setValid(false);
		}else{
			//almaceno su usuario y le doy un su atributo
			HttpServletRequest request =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.getSession().setAttribute("usuario",usuario);
			
			//redirecciono al formulario
			String urlRedireccionar = request.getContextPath()+"/secciones/formulario.xhtml";
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(urlRedireccionar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
