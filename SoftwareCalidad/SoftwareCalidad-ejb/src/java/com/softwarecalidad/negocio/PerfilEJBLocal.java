/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.negocio;

import com.softwarecalidad.entidades.Perfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Personal
 */
@Local
public interface PerfilEJBLocal {

    public boolean crearPerfil(Perfil perfil);

    public List<Perfil> cargarPerfiles();
    
    public void eliminarPerfil(Perfil perfil);
}
