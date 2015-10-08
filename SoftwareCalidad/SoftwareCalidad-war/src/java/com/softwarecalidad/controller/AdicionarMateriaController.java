/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import com.softwarecalidad.singleton.SingletonController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Brian
 */
@ManagedBean
@RequestScoped
public class AdicionarMateriaController {

    private SingletonController instacionControler = SingletonController.getInstance();

    private Materia nuevaMateria = new Materia();

    /**
     * Creates a new instance of AdicionarMateriaController
     */
    public AdicionarMateriaController() {
    }

    public void adicionarMateria() {
        this.nuevaMateria.setCodigo("123");
        this.nuevaMateria.setCreditos("1");
        this.nuevaMateria.setIh("1");
        this.nuevaMateria.setNombre("MAteri");
        this.nuevaMateria.setTipo("1");
        instacionControler.adicionarMateria(nuevaMateria);
    }

}
