/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.utilidades;

/**
 *
 * @author Sebastian Vega
 */
public class Utilidades {

    public Utilidades() {
    }
    
    public boolean validateText(String line){
        return line.trim().isEmpty() || line.trim() == null || line.trim().equals("null");
    }
}
