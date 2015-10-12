/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author WRubianoM
 */
@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {

    @PersistenceContext
    EntityManager dataAcces;

    @Override
    public boolean login(String user, String pass) {
        return true;
    }

    
    
    
}
