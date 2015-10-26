/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.utilidades;

/**
 *
 * @author Brian
 */
public class ResultadoOperation {

    private String mensaje;
    private boolean resultado;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isOk() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public ResultadoOperation() {

    }

    public ResultadoOperation(boolean res) {
        this.resultado = res;
    }

}
