/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.AdminDAO;
import com.softwarecalidad.entidades.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author WRubianoM
 */
@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {

    @EJB
    private AdminDAO adminDAO;

    @Override
    public Usuario login(String user, String pass) {
        return this.adminDAO.getUsserLogin(user, pass);
    }

}
