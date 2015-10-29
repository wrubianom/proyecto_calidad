/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Usuario;
import com.softwarecalidad.negocio.AdminEJBLocal;
import com.softwarecalidad.utilidades.UtilFaces;
import com.softwarecalidad.utilidades.Utilidades;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Personal
 */
@ManagedBean
@RequestScoped
public class AdministradorController {

    @EJB
    private AdminEJBLocal adminEJB;

    private Usuario admin = new Usuario();
    private Utilidades util = new Utilidades();
    private String password;

    public AdministradorController() {
    }

    public void adicionarAdmin() {
        try {
            if (util.validateText(admin.getLogin())) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el Login");
            } else if (util.validateText(admin.getNombre())) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el Nombre");
            } else {
                if (admin.getClave().equals(password)) {
                    boolean ban = adminEJB.crearUsuario(admin);
                    if (ban) {
                        UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "El Administrador ha sido Agregado");
                    } else {
                        UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Error verifique los datos del administrador");
                    }
                } else {
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Error Las contraseñas no coinciden");
                }
            }
        } catch (Exception e) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    public Usuario getAdmin() {
        return admin;
    }

    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
