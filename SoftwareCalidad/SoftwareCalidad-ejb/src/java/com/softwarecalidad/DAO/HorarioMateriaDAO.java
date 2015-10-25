/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.DAO;

import com.softwarecalidad.entidades.HorarioMateria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author WRubianoM
 */
@Stateless
public class HorarioMateriaDAO extends AbstractFacade<HorarioMateria> {

    @PersistenceContext
    EntityManager em;

    public HorarioMateriaDAO() {
        super(HorarioMateria.class);
    }

    public HorarioMateriaDAO(Class<HorarioMateria> entityClass) {
        super(HorarioMateria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public HorarioMateria crearNuevoGrupo(HorarioMateria nuevoGrupo) throws Exception {

        try {
            this.em.persist(nuevoGrupo);
            this.em.flush();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return nuevoGrupo;
    }

    public List<HorarioMateria> consultarHorarioMateriaByIdMateria(Integer idMateria) throws Exception {
        List<HorarioMateria> resultado = null;
        try {
            Query query = this.em.createNamedQuery("HorarioMateria.findByIdMateria");

            query.setParameter("idMateriaInt", idMateria);

            resultado = query.getResultList();
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return resultado;
    }

}
