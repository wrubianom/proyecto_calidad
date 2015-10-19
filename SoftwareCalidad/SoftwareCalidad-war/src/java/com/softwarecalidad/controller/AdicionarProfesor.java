/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.ProfesorEJBLocal;
import com.softwarecalidad.utilidades.UtilFaces;
import com.softwarecalidad.utilidades.Utilidades;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sebastian Vega
 */
@ManagedBean
@RequestScoped
public class AdicionarProfesor implements Serializable {

    @EJB
    private ProfesorEJBLocal profesorEJB;

    private Profesor nuevoProfesor = new Profesor();
    private Utilidades util = new Utilidades();

    @PostConstruct
    public void init() {
    }

    public AdicionarProfesor() {
    }

    public void crearProfesor() {
        try {
            if (util.validateText(nuevoProfesor.getCodigo())) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el Codigo");
            } else if (util.validateText(nuevoProfesor.getNombre())) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el Nombre");
            } else if (util.validateText(nuevoProfesor.getTipoContrato())) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_FATAL, "Error en el Tipo de Contrato");
            } else {
                boolean ban = profesorEJB.crearProfesor(nuevoProfesor);
                if (ban)
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "El Profesor a sido Agregado");
                else 
                    UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Error verifique los datos del profesor");
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public Profesor getNuevoProfesor() {
        return nuevoProfesor;
    }

    public void setNuevoProfesor(Profesor nuevoProfesor) {
        this.nuevoProfesor = nuevoProfesor;
    }

}
