/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.DAO;

import com.softwarecalidad.entidades.Perfil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sebastian Vega
 */
@Stateless
public class PerfilDAO extends AbstractFacade<Perfil> {

    @PersistenceContext
    EntityManager em;

    public PerfilDAO() {
        super(Perfil.class);
    }

    public PerfilDAO(Class<Perfil> entityClass) {
        super(Perfil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
