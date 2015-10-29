/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sebastian Vega
 */
@Local
public interface AdminEJBLocal {

    public boolean crearUsuario(Usuario usuario);

    public void modificarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);

    public List<Usuario> findAllUsuario();
}
