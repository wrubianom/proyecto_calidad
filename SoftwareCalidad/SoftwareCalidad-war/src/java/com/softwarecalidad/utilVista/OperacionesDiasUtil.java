/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecalidad.utilVista;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William.Rubiano
 */
public class OperacionesDiasUtil {

    public static List<DiasClaseUtil> getDiasEntreSemanaConSabado(boolean conSabado) {
        List<DiasClaseUtil> resul = new ArrayList<>();

        DiasClaseUtil item = new DiasClaseUtil();
        item.setDia(1);
        item.setNombreDia("Lunes");
        resul.add(item);
        item = new DiasClaseUtil();
        item.setDia(2);
        item.setNombreDia("Martes");
        resul.add(item);
        item = new DiasClaseUtil();
        item.setDia(3);
        item.setNombreDia("Miercoles");
        resul.add(item);
        item = new DiasClaseUtil();
        item.setDia(4);
        item.setNombreDia("Jueves");
        resul.add(item);
        item = new DiasClaseUtil();
        item.setDia(5);
        item.setNombreDia("Viernes");
        resul.add(item);
        if (conSabado) {
            item = new DiasClaseUtil();
            item.setDia(6);
            item.setNombreDia("Sabado");
            resul.add(item);
        }
        return resul;
    }

}
