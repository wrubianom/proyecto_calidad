/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.DAO;

import com.softwarecalidad.entidades.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sebastian Vega
 */
@Stateless
public class ProfesorDAO extends AbstractFacade<Profesor> {

    @PersistenceContext
    EntityManager em;

    public ProfesorDAO() {
        super(Profesor.class);
    }

    public ProfesorDAO(Class<Profesor> entityClass) {
        super(Profesor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public Profesor buscarProfesorByNumDoc(String numDocumento) {
        try {
            return (Profesor) this.em.createNamedQuery("Profesor.findByCodigo").setParameter("codigo", numDocumento).getSingleResult();
        } catch (NoResultException  e) {
            System.out.println("Error DAO al buscar profesor by numodcumento");
            return null;
        }
    }

    public List<Profesor> getListaProfesores() {
        List<Profesor> resultado = null;
        try {
            String consultaNativa = "SELECT p from profesor p where id_profesor = :idProfesorInt ";
            Query query = em.createNativeQuery(consultaNativa, Profesor.class);
            query.setParameter("idProfesorInt", 1);
            resultado = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error capa persistencia getListaProfesores");
        }
        return resultado;
    }

}
