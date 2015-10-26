/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.DAO;

import com.softwarecalidad.entidades.Materia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Brian
 */
@Stateless
public class MateriaDAO extends AbstractFacade<Materia> {

    @PersistenceContext
    EntityManager em;

    public MateriaDAO() {
        super(Materia.class);
    }

    public MateriaDAO(Class<Materia> entityClass) {
        super(Materia.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public Materia buscarMateriaByIdMateria(String codMateria) {
        Materia resultado = null;
        try {
            resultado = (Materia) this.em.createNamedQuery("Materia.findByCodigo").setParameter("codigo", codMateria).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error DAO al buscar profesor by codMateria");
        }
        return resultado;
    }

    public Materia finByIdMateria(Integer idmateria) {
        return (Materia) this.em.createNamedQuery("Materia.findByIdMateria").setParameter("idMateria", idmateria).getSingleResult();
    }

}
