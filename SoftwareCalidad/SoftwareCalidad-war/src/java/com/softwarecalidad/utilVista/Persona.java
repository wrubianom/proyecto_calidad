/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.utilVista;

/**
 *
 * @author Sebastian Vega
 */
public class Persona {

    private String nombre;
    private String rol;
    private String materia;
    private String fecha;
    private String universidad;

    public void Persona() {
    }

    public Persona(String nombre, String rol, String materia, String fecha, String universidad) {
        this.nombre = nombre;
        this.rol = rol;
        this.materia = materia;
        this.fecha = fecha;
        this.universidad = universidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

}
