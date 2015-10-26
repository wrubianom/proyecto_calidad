/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.MateriaEJBLocal;
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
        this.idMateria = null;
    }
    
    private List<Materia> listaMaterias;
    private Integer idMateria;
    private Materia materia;
    
    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public int getIdMateria() {
        return idMateria;
    }
    
    public void modificarMateria(){
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        //materiaEJB.modificarProfesor((Profesor) event.getObject());
        FacesMessage msg = new FacesMessage("Profesor Actualizado", ((Profesor) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado", ((Profesor) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
