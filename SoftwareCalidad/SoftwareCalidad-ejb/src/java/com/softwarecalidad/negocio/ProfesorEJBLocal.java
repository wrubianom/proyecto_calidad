/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.Profesor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sebastian Vega
 */
@Local
public interface ProfesorEJBLocal {

    public boolean crearProfesor(Profesor nuevoProfesor);

    public void modificarProfesor(Profesor profesor);

    public void eliminarProfesor(Profesor profesor);
    
    public List<Profesor> findAllProfesor();
}
