/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.HorarioMateria;
import com.softwarecalidad.utilidades.ResultadoOperation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WRubianoM
 */
@Local
public interface HorarioMateriaEJBLocal {

    public ResultadoOperation crearhorarioMateria(HorarioMateria nuevoGrupo);
    
    public List<HorarioMateria> consultarGrupoMateriaByIdMateria(Integer idMateria);
    
    public ResultadoOperation eliminarHorarioGrupoByidHorarioMateria(Integer idHorarioMateria);
    
    public List<HorarioMateria> consultarTodosLosGrupos();
    
    public List<HorarioMateria> consultarGruposNoAsociadosAProfesor(Integer idProfesor);
    
    public ResultadoOperation asociarGrupoAProfesorManual(Integer profesorAsociar, Integer grupoAsociar);
    
}
