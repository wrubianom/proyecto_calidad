/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.HorarioMateriaDAO;
import com.softwarecalidad.DAO.MateriaDAO;
import com.softwarecalidad.entidades.HorarioMateria;
import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.utilidades.ResultadoOperation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author WRubianoM
 */
@Stateless
public class HorarioMateriaEJB implements HorarioMateriaEJBLocal {

    @EJB
    private MateriaDAO materiaDAO;

    @EJB
    private HorarioMateriaDAO horarioMateriaDAO;

    @Override
    public ResultadoOperation crearhorarioMateria(HorarioMateria nuevoGrupo) {
        ResultadoOperation resul = new ResultadoOperation();
        resul.setResultado(false);
        try {

            Materia res;
            res = this.materiaDAO.finByIdMateria(nuevoGrupo.getIdMateria().getIdMateria());
            nuevoGrupo.setIdMateria(res);
            nuevoGrupo.setSemestre(res.getSemestre());
            HorarioMateria ress = horarioMateriaDAO.crearNuevoGrupo(nuevoGrupo);
            if (ress != null && ress.getIdHorarioMateria() != null) {
                resul.setResultado(true);
            }
        } catch (Exception e) {
            resul.setResultado(false);
            resul.setMensaje("Error al crearHroario materia capa Negocio " + e.toString());
        }

        return resul;
    }

    @Override
    public List<HorarioMateria> consultarGrupoMateriaByIdMateria(Integer idMateria) {
        List<HorarioMateria> resultado = null;
        try {
            resultado = this.horarioMateriaDAO.consultarHorarioMateriaByIdMateria(idMateria);
        } catch (Exception ex) {
            Logger.getLogger(HorarioMateriaEJB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return resultado;
    }

    @Override
    public ResultadoOperation eliminarHorarioGrupoByidHorarioMateria(Integer idHorarioMateria) {
        ResultadoOperation resul = new ResultadoOperation();
        resul.setResultado(false);
        try {
            HorarioMateria res = this.horarioMateriaDAO.find(idHorarioMateria);
            this.horarioMateriaDAO.remove(res);
            resul.setResultado(true);
        } catch (Exception e) {
            resul.setMensaje("Error al eliminar el grupo + " + e.toString());
        }

        return resul;
    }

    @Override
    public List<HorarioMateria> consultarTodosLosGrupos() {
        return this.horarioMateriaDAO.findAll();
    }

}
