/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import com.softwarecalidad.utilidades.ResultadoOperation;
import com.softwarecalidad.utilidades.UtilFaces;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Brian
 */
@ManagedBean
@RequestScoped
public class AdicionarMateriaController {
    @EJB
    private MateriaEJBLocal materiaEJB;

    private Materia nuevaMateria = new Materia();
    
    @PostConstruct
    public void init(){
        this.nuevaMateria = new Materia();
    }

    public Materia getNuevaMateria() {
        return nuevaMateria;
    }

    public void setNuevaMateria(Materia nuevaMateria) {
        this.nuevaMateria = nuevaMateria;
    }
    
    
    /**
     * Creates a new instance of AdicionarMateriaController
     */
    public AdicionarMateriaController() {
    }

    public void crearMateria(){
        ResultadoOperation resultado = this.materiaEJB.adicionarMateria(nuevaMateria);
        if(resultado.isOk()){
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, resultado.getMensaje());
            nuevaMateria = new Materia();
        } else {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, resultado.getMensaje());
        }
    }
}
