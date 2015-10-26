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
public class OperacionesHorasClase {

    private static List<HorasClaseUtil> traerHorasClaseByRango(Integer inicio, Integer fin) {

        List<HorasClaseUtil> resultado = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            HorasClaseUtil item = new HorasClaseUtil();
            item.setHora(i);
            if (i <= 9) {
                item.setHoraConverter("0" + i + ":00");
            } else {
                item.setHoraConverter(i + ":00");
            }
            resultado.add(item);
        }
        return resultado;
    }

    public static List<HorasClaseUtil> traerHorasDiurnas() {
        return traerHorasClaseByRango(6, 22);
    }

    public static List<HorasClaseUtil> traerHorasEspecial() {
        return traerHorasClaseByRango(18, 22);
    }

    public static List<HorasClaseUtil> traerHorasEspecialSabado() {
        return traerHorasClaseByRango(7, 20);
    }

}
