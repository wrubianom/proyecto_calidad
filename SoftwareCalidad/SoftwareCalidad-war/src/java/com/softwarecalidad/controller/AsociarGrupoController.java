/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.HorarioMateria;
import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.HorarioMateriaEJBLocal;
import com.softwarecalidad.negocio.ProfesorEJBLocal;
import com.softwarecalidad.utilidades.ResultadoOperation;
import com.softwarecalidad.utilidades.UtilFaces;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author William.Rubiano
 */
@ManagedBean
@ViewScoped
public class AsociarGrupoController implements Serializable {

    @EJB
    private HorarioMateriaEJBLocal horarioMateriaEJB;

    @EJB
    private ProfesorEJBLocal profesorEJB;

    private List<Profesor> listaProfesor;
    private Integer idProfesorSelect;
    private Integer idGrupoSelect;
    private List<HorarioMateria> listaGrupoFitrada;
    private boolean renderBotonAsociar;

    public boolean isRenderBotonAsociar() {
        return renderBotonAsociar;
    }

    public void setRenderBotonAsociar(boolean renderBotonAsociar) {
        this.renderBotonAsociar = renderBotonAsociar;
    }

    public Integer getIdGrupoSelect() {
        return idGrupoSelect;
    }

    public void setIdGrupoSelect(Integer idGrupoSelect) {
        this.idGrupoSelect = idGrupoSelect;
    }

    public Integer getIdProfesorSelect() {
        return idProfesorSelect;
    }

    public List<HorarioMateria> getListaGrupoFitrada() {
        return listaGrupoFitrada;
    }

    public void setListaGrupoFitrada(List<HorarioMateria> listaGrupoFitrada) {
        this.listaGrupoFitrada = listaGrupoFitrada;
    }

    public void setIdProfesorSelect(Integer idProfesorSelect) {
        this.idProfesorSelect = idProfesorSelect;
    }

    public List<Profesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public AsociarGrupoController() {

    }

    @PostConstruct
    public void init() {
        this.listaProfesor = profesorEJB.findAllProfesor();
        this.renderBotonAsociar = false;
        this.listaGrupoFitrada = null;
    }

    public void cambioProfesorChan(ValueChangeEvent event) {
        Integer idProfesor = ((Integer) event.getNewValue());
        System.out.println("Entro a buscar el profesor " + idProfesor);
        if (idProfesor != null) {
            this.listaGrupoFitrada = this.horarioMateriaEJB.consultarGruposNoAsociadosAProfesor(idProfesor);
            System.out.println("el size es " + this.listaGrupoFitrada.size());
        }
    }

    private Profesor getProfesorlista() {
        for (Profesor item : listaProfesor) {
            if (item.getIdProfesor().equals(this.idProfesorSelect)) {
                System.out.println("Encontro el profesor de la lista.");
                return item;
            }
        }
        return null;
    }

    public void cambiogrupo(ValueChangeEvent event) {
        Integer grupo = ((Integer) event.getNewValue());

        if (grupo == null) {
            this.renderBotonAsociar = false;
        } else {
            this.renderBotonAsociar = true;
        }
    }

    public void asociarGrupoProfesor() {
        System.out.println("entroooooooo");
        if (idProfesorSelect != null && idGrupoSelect != null) {
            ResultadoOperation res = this.horarioMateriaEJB.asociarGrupoAProfesorManual(idProfesorSelect, idGrupoSelect);
            if (res.isOk()) {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "Se asocio correctamente la asignatura al profesor");
                this.terminarAsociacion();
            } else {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un problema al asociar la asignatura al profesor");
            }
        } else {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar el profesor y el grupo que se le asociara");
        }
    }

    public void terminarAsociacion() {
        this.idGrupoSelect = null;
        this.idProfesorSelect = null;
        this.renderBotonAsociar = false;
        this.listaGrupoFitrada = null;
    }

}
