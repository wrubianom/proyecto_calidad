/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.utilidades.ResultadoOperation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Brian
 */
@Local
public interface MateriaEJBLocal {

    public ResultadoOperation adicionarMateria(Materia nuevaMateria);
    public ResultadoOperation eliminarMateria(int idMateria);
    public ResultadoOperation modificarMateria(Materia materia);
    public List<Materia> getAllMaterias();
}
