/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.HorarioProfesor;
import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.HorarioMateriaEJBLocal;
import com.softwarecalidad.negocio.ProfesorEJBLocal;
import com.softwarecalidad.utilidades.UtilFaces;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author William.Rubiano
 */
@ManagedBean
@ViewScoped
public class ConsultarMateriaProfesorController implements Serializable {

    @EJB
    private HorarioMateriaEJBLocal horarioMateriaEJB;

    @EJB
    private ProfesorEJBLocal profesorEJB;

    private List<Profesor> listaProfesores;
    private Integer idProfesor;
    private List<HorarioProfesor> listaHorarioProfesor;
    private boolean encontroResultado;

    @PostConstruct
    public void init() {
        this.listaProfesores = this.profesorEJB.findAllProfesor();
        this.idProfesor = null;
        this.listaHorarioProfesor = null;
        this.encontroResultado = false;
    }

    public boolean isEncontroResultado() {
        return encontroResultado;
    }

    public List<HorarioProfesor> getListaHorarioProfesor() {
        return listaHorarioProfesor;
    }

    public void setListaHorarioProfesor(List<HorarioProfesor> listaHorarioProfesor) {
        this.listaHorarioProfesor = listaHorarioProfesor;
    }

    public void setEncontroResultado(boolean encontroResultado) {
        this.encontroResultado = encontroResultado;
    }

    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public ConsultarMateriaProfesorController() {

    }

    public void buscarProfesor() {
        System.out.println("entrooo");
        this.encontroResultado = false;
        Profesor res = this.buscarProfesorByList(this.idProfesor);
        if (res != null) {
            if (!res.getHorarioProfesorList().isEmpty()) {
                this.listaHorarioProfesor = res.getHorarioProfesorList();
                this.encontroResultado = true;

            } else {
                UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados");
            }
        }
    }

    private Profesor buscarProfesorByList(Integer idprofesor) {
        Profesor res = null;
        for (Profesor item : this.listaProfesores) {
            if (item.getIdProfesor().equals(idprofesor)) {
                res = item;
                break;
            }
        }
        return res;
    }
}
