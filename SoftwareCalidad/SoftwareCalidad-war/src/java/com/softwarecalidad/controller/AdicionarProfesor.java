/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.negocio.ProfesorEJBLocal;
import com.softwarecalidad.utilidades.ResultadoOperation;
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
public class AdicionarProfesor {

    @EJB
    private ProfesorEJBLocal profesorEJB;

    private Profesor nuevoProfesor;

    @PostConstruct
    public void init() {
        this.nuevoProfesor = new Profesor();
    }

    public Profesor getNuevoProfesor() {
        return nuevoProfesor;
    }

    public void setNuevoProfesor(Profesor nuevoProfesor) {
        this.nuevoProfesor = nuevoProfesor;
    }

    public AdicionarProfesor() {
    }

    public void crearProfesor() {
        this.nuevoProfesor.setDisponibilidad("1");
        this.nuevoProfesor.setTipoContrato("1");
        ResultadoOperation resultado = this.profesorEJB.crearProfesor(nuevoProfesor);
        if (resultado.isOk()) {
            System.out.println("Lo creo correcto");
        } else {
            System.out.println(resultado.getMensaje());
        }
    }

}
