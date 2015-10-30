/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.Usuario;
import javax.ejb.Local;

/**
 *
 * @author WRubianoM
 */
@Local
public interface UsuarioEJBLocal {

    public Usuario login(String user, String pass);
}
