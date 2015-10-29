/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Usuario;
import com.softwarecalidad.negocio.AdminEJBLocal;
import com.softwarecalidad.utilidades.UtilFaces;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Sebastian Vega
 */
@ManagedBean
@RequestScoped
public class ModificarAdministradorController {

    @EJB
    private AdminEJBLocal adminEJB;

    private Usuario usuario;
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public ModificarAdministradorController() {
    }

    @PostConstruct
    public void init() {
        //List<Usuario> findAll = adminEJB.findAllUsuario();
        usuarios.clear();
        usuarios.addAll(adminEJB.findAllUsuario());
    }

    public void cargarUsuarios() {
        try {
            List<Usuario> findAll = adminEJB.findAllUsuario();
            if (findAll != null) {
                usuarios.addAll(findAll);
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void eliminarUsuario() {
        try {
            usuarios.remove(usuario);
            adminEJB.eliminarUsuario(usuario);
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void onRowEdit(RowEditEvent event) {
        adminEJB.modificarUsuario((Usuario) event.getObject());
        FacesMessage msg = new FacesMessage("Administrador Actualizado", ((Usuario) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado", ((Usuario) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
