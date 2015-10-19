/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.ProfesorDAO;
import com.softwarecalidad.entidades.Profesor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Sebastian Vega
 */
@Stateless
public class ProfesorEJB implements ProfesorEJBLocal {

    @EJB
    private ProfesorDAO profesorDAO;

    @Override
    public boolean crearProfesor(Profesor nuevoProfesor) {
        Profesor result = this.profesorDAO.buscarProfesorByNumDoc(nuevoProfesor.getCodigo());
        if (result == null) {
            profesorDAO.create(nuevoProfesor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Profesor> findAllProfesor() {
        return profesorDAO.findAll();
    }

    @Override
    public void modificarProfesor(Profesor profesor) {
        profesorDAO.edit(profesor);
    }
}
