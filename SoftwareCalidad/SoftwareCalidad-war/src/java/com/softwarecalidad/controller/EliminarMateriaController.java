/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import com.softwarecalidad.utilidades.ResultadoOperation;
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
public class EliminarMateriaController {
    @EJB
    private MateriaEJBLocal materiaEJB;
    
    private List<Materia> listaMaterias;
    private int idMateria;
    
    @PostConstruct
    public void init(){
        this.listaMaterias = materiaEJB.getAllMaterias();
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }
    
    public EliminarMateriaController(){
    }
    
    public void eliminarMateria(int idMateria){
        System.out.println(idMateria);
        /*ResultadoOperation resultado = this.materiaEJB.eliminarMateria(cod);
        if(resultado.isOk()){
            listaMaterias.remove(materia);
            System.out.println("La eliminó correctamente");
        } else {
            System.out.println(resultado.getMensaje());
        }*/
    }
}
