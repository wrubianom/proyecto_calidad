/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.ProfesorDAO;
import com.softwarecalidad.entidades.Profesor;
import com.softwarecalidad.utilidades.ResultadoOperation;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brian
 */
@Stateless
public class ProfesorEJB implements ProfesorEJBLocal {

    @EJB
    private ProfesorDAO profesorDAO;
    
    

    @Override
    public ResultadoOperation crearProfesor(Profesor nuevoProfesor) {
        ResultadoOperation resultado = new ResultadoOperation();
        resultado.setResultado(true);
        try {
            Profesor result = this.profesorDAO.buscarProfesorByNumDoc(nuevoProfesor.getCodigo());
            if (result != null) {
                resultado.setResultado(false);
                resultado.setMensaje("El profesor con la identificacion " + nuevoProfesor.getCodigo() + " ya se encuentra registrado");
            } else {
                this.profesorDAO.create(nuevoProfesor);
            }
        } catch (Exception e) {
            resultado.setResultado(false);
            resultado.setMensaje("error al crear profesor en capa de negocio ResultadoOperation");
            System.out.println(" Error al crear profesor ");
        }
        return resultado;
    }
}
