/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Usuario;
import com.softwarecalidad.negocio.UsuarioEJBLocal;
import com.softwarecalidad.utilidades.Hash;
import com.softwarecalidad.utilidades.UtilFaces;
import com.softwarecalidad.utilidades.Utilidades;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author WRubianoM
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioEJBLocal usuarioEJB;

    private Utilidades util = new Utilidades();
    private String user;
    private String pwd;
    private Usuario currentUsuario;
    private boolean loged;

    private String usuario;

    public Usuario getCurrentUsuario() {
        return currentUsuario;
    }

    public void setCurrentUsuario(Usuario currentUsuario) {
        this.currentUsuario = currentUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Creates a new instance of loginBean
     */
    public LoginBean() {
    }

    public String doLogin() {
        try {
            if (util.validateText(this.user)) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el User");
            } else if (util.validateText(this.pwd)) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el Password");
            } else {
                this.loged = false;
                FacesContext context = FacesContext.getCurrentInstance();
                this.currentUsuario = this.usuarioEJB.login(this.user, Hash.encript(this.pwd));

                if (this.currentUsuario != null && this.currentUsuario.getIdUsuario() != null) {
                    context.getExternalContext().getSessionMap().put("usuario", currentUsuario);
                    this.loged = true;
                    return "HOME";
                } else {
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Error al autenticar ... Ingrege nuevamente sus credenciales");
                    return "LOGIN_FAIL";
                }
            }
        } catch (Exception e) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
        return "LOGIN_FAIL";
    }

    public String cerrarSession() {
        this.loged = false;
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        req.getSession().invalidate();
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("usuario");
        return "LOGIN";
    }

    public boolean isLoged() {
        return loged;
    }

    public void setLoged(boolean loged) {
        this.loged = loged;
    }

}
