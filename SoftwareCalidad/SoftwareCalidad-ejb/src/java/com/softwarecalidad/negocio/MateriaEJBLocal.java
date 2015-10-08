/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.Materia;
import javax.ejb.Local;

/**
 *
 * @author Brian
 */
@Local
public interface MateriaEJBLocal {
    public boolean adicionarMateria(Materia nuevaMateria);
}
