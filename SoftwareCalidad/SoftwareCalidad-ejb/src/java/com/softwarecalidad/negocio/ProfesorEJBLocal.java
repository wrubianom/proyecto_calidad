/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.utilidades.ResultadoOperation;
import javax.ejb.Local;

/**
 *
 * @author Brian
 */
@Local
public interface ProfesorEJBLocal {

    public ResultadoOperation crearProfesor(Profesor nuevoProfesor);
}
