/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.controller;

import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.utilVista.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sebastian Vega
 */
@ManagedBean
@RequestScoped
public class HomeController implements Serializable {

    private List<Persona> personas;
    private Persona persona;

    public HomeController() {
    }

    @PostConstruct
    public void init() {
        personas = new ArrayList<Persona>();
        personas.add(new Persona("William Rubiano", "Estudiante", "Calidad de Software", "Noviembre/03/2015", "Universidad Central"));
        personas.add(new Persona("Brian Suarez", "Estudiante", "Calidad de Software", "Noviembre/03/2015", "Universidad Central"));
        personas.add(new Persona("Stheven Rincon", "Estudiante", "Calidad de Software", "Noviembre/03/2015", "Universidad Central"));
        personas.add(new Persona("Sebastian Vega", "Estudiante", "Calidad de Software", "Noviembre/03/2015", "Universidad Central"));
        personas.add(new Persona("Jorge Protella", "Docente", "Calidad de Software", "Noviembre/03/2015", "Universidad Central"));
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
