/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.DAO;

import com.softwarecalidad.entidades.HorarioProfesor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author William.Rubiano
 */
@Stateless
public class HorarioProfesorDAO extends AbstractFacade<HorarioProfesor> {

    @PersistenceContext
    EntityManager em;

    public HorarioProfesorDAO() {
        super(HorarioProfesor.class);
    }

    public HorarioProfesorDAO(Class<HorarioProfesor> entityClass) {
        super(HorarioProfesor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
