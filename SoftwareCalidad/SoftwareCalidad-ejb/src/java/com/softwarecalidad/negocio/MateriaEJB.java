/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.MateriaDAO;
import com.softwarecalidad.entidades.Materia;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brian
 */
@Stateless
public class MateriaEJB implements MateriaEJBLocal {

    @EJB
    MateriaDAO materiaDAO;

    @Override
    public boolean adicionarMateria(Materia nuevaMateria) {
        boolean resultado = false;
        try {
            this.materiaDAO.create(nuevaMateria);
            resultado = Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("Error al adicionar materia");
        }
        return resultado;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
