/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.singleton;

import com.softwarecalidad.entidades.Materia;
import com.softwarecalidad.negocio.MateriaEJBLocal;
import javax.ejb.EJB;

/**
 *
 * @author Brian
 */
public class SingletonController {

    @EJB
    private MateriaEJBLocal materiaEJB;

    public static SingletonController INSTANCE = null;

    private SingletonController() {

    }

    public static SingletonController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonController();

        }
        return INSTANCE;

    }

    public void adicionarMateria(Materia nuevaMateria) {
        this.materiaEJB.adicionarMateria(nuevaMateria);
    }
}
