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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author William.Rubiano
 */
@ManagedBean
@ViewScoped
public class AsociarGrupoController {

    @EJB
    private HorarioMateriaEJBLocal horarioMateriaEJB;

    @EJB
    private ProfesorEJBLocal profesorEJB;

    private List<Profesor> listaProfesor;
    private Integer idProfesorSelect;
    private Integer idGrupoSelect;
    private List<HorarioMateria> listaGrupoFitrada;

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
    }

    public void cambioProfesorChan(ValueChangeEvent event) {
        Integer idProfesor = ((Integer) event.getNewValue());

        if (idProfesor != null) {
            List<HorarioMateria> res = this.horarioMateriaEJB.consultarTodosLosGrupos();

            if (res != null && !res.isEmpty()) {
                // Validar que no se muestren las que ya estan asociadas
                this.listaGrupoFitrada = res;
            } else {
                System.out.println("llega vacio o nulo");
            }
        }
    }

}
