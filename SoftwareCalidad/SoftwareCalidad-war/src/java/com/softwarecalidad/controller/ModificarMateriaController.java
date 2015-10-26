/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Brian
 */
@ManagedBean
@RequestScoped
public class ModificarMateriaController {
    
    @EJB
    private MateriaEJBLocal materiaEJB;
    
    @PostConstruct
    public void init(){
        this.listaMaterias = materiaEJB.getAllMaterias();
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
    
    private List<Materia> listaMaterias;
    private int idMateria;

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public int getIdMateria() {
        return idMateria;
    }
    
    public void modificarMateria(){
        
    }
}