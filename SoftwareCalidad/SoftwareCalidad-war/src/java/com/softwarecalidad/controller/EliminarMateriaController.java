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
import java.util.List;
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
public class EliminarMateriaController {
    @EJB
    private MateriaEJBLocal materiaEJB;
    
    private Materia materia;
    private List<Materia> listaMaterias;
    private int idMateria;
    
    @PostConstruct
    public void init(){
        this.listaMaterias = materiaEJB.getAllMaterias();
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
    
    public void eliminarMateria(){
        try{
            ResultadoOperation resultado = this.materiaEJB.eliminarMateria(materia.getIdMateria());
            if(resultado.isOk()){
                listaMaterias.remove(materia);
                System.out.println("La eliminó correctamente");
            } else {
                System.out.println(resultado.getMensaje());
            }
        } catch (Exception ex) {
            UtilFaces.getFacesUtil().addMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }
}
