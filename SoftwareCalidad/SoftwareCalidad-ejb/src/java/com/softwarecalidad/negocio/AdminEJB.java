/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.DAO.AdminDAO;
import com.softwarecalidad.entidades.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Personal
 */
@Stateless
public class AdminEJB implements AdminEJBLocal {

    @EJB
    private AdminDAO adminDAO;

    @Override
    public boolean crearUsuario(Usuario usuario) {
        Usuario user = adminDAO.buscarUsuarioByNumDoc(usuario.getLogin());
        if (user == null) {
            adminDAO.create(usuario);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        adminDAO.edit(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        adminDAO.remove(usuario);
    }

    @Override
    public List<Usuario> findAllUsuario() {
        return adminDAO.findAll();
    }

}
