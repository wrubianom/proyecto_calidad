/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.MateriaDAO;
import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.utilidades.ResultadoOperation;
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
    public ResultadoOperation adicionarMateria(Materia nuevaMateria) {
        ResultadoOperation resultado = new ResultadoOperation();
        resultado.setResultado(true);
        try{
            Materia result = this.materiaDAO.buscarMateriaByIdMateria(nuevaMateria.getCodigo());
            if(result != null){
                resultado.setResultado(false);
                resultado.setMensaje("La materia con el id " + nuevaMateria.getCodigo() + " ya se encuentra registrada");
            } else {
                this.materiaDAO.create(nuevaMateria);
                resultado.setMensaje("La materia con el id " + nuevaMateria.getCodigo() + " fue creada correctamente");
            }
        } catch(Exception e){
            resultado.setResultado(false);
            resultado.setMensaje("error al crear materia en capa de negocio ResultadoOperation");
            System.out.println("Error al crear materia ");
        }
        return resultado;
    }

    @Override
    public List<Materia> getAllMaterias() {
        return this.materiaDAO.findAll();
    }

    @Override
    public ResultadoOperation eliminarMateria(int idMateria) {
        
        ResultadoOperation resultado = new ResultadoOperation();
        resultado.setResultado(true);
        try{
            Materia result = this.materiaDAO.finByIdMateria(idMateria);
            if(result != null){
                if(result.getHorarioMateriaList().isEmpty()){
                    this.materiaDAO.remove(result);
                    resultado.setResultado(true);
                    resultado.setMensaje("La materia con el id " + result.getCodigo() + " fue eliminada");
                } else {
                    resultado.setResultado(false);
                    resultado.setMensaje("La materia con el id " + result.getCodigo() + " tiene asociado un grupo");
                }
            } else {
                resultado.setResultado(false);
                resultado.setMensaje("La materia con el id " + result.getCodigo() + " no existe");
            }
        } catch(Exception e){
            resultado.setResultado(false);
            resultado.setMensaje("error al eliminar materia en capa de negocio ResultadoOperation");
            System.out.println("Error al eliminar materia ");
        }
        return resultado;
    }

    @Override
    public ResultadoOperation modificarMateria(Materia materia) {
        ResultadoOperation resultado = new ResultadoOperation();
        resultado.setResultado(true);
        try{
            this.materiaDAO.edit(materia);
            resultado.setMensaje("La materia con el id " + materia.getCodigo() + " fue modificada");
        } catch(Exception e){
            resultado.setResultado(false);
            resultado.setMensaje("error al editar materia en capa de negocio ResultadoOperation");
            System.out.println("Error al editar materia ");
        }
        return null;
    }
    
    
}
