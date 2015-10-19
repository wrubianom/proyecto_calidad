/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.ProfesorEJBLocal;
import com.softwarecalidad.utilidades.UtilFaces;
import java.io.Serializable;
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
public class ModificarProfesor implements Serializable {

    @EJB
    private ProfesorEJBLocal profesorEJB;

    private ArrayList<Profesor> profesores = new ArrayList<Profesor>();

    public ModificarProfesor() {
    }

    @PostConstruct
    public void init() {
        List<Profesor> findAll = profesorEJB.findAllProfesor();
        profesores.clear();
        profesores.addAll(findAll);
    }

    public void cargarProfesores() {
        try {
            List<Profesor> findAll = profesorEJB.findAllProfesor();
            if (findAll != null) {
                profesores.addAll(findAll);
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void onRowEdit(RowEditEvent event) {
        profesorEJB.modificarProfesor((Profesor) event.getObject());
        FacesMessage msg = new FacesMessage("Profesor Actualizado", ((Profesor) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado", ((Profesor) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

}
