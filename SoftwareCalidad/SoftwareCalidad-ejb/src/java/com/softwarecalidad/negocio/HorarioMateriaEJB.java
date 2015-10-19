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
import javax.annotation.PostConstruct;
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

}
