/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.PerfilDAO;
import com.softwarecalidad.entidades.Perfil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Sebastian Vega
 */
@Stateless
public class PerfilEJB implements PerfilEJBLocal {

    @EJB
    private PerfilDAO perfilDAO;

    @Override
    public boolean crearPerfil(Perfil perfil) {
        try {
            perfilDAO.create(perfil);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Perfil> cargarPerfiles() {
        try {
            List<Perfil> aux = perfilDAO.findAll();
            return aux;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void eliminarPerfil(Perfil perfil) {
        perfilDAO.remove(perfil);
    }

}
