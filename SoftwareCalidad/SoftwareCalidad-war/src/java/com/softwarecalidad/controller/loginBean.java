/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.negocio.UsuarioEJBLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author WRubianoM
 */
@ManagedBean
@SessionScoped
public class loginBean implements Serializable {

    @EJB
    private UsuarioEJBLocal usuarioEJB;

    private String user;
    private String pwd;

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
    public loginBean() {
    }

    public String doLogin() {
        this.usuarioEJB.login(this.user, this.pwd);
        return "";
    }
}
