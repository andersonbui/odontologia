/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeniosoft.proyectoposgradosunicauca.controladores;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author debian
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class viewOdontograma {

    private final List<elementoDir> listaDirectorios;
    private int actual;
    private final int CANTIDAD_MUELAS = 32;
    // partes = {arriba, izquierda, centro, derecha, abajo}
    private final int PARTES_MUELA = 5;
    private final String DIRECTORIO_RAIZ_ODONTOGRAMA = "img/odontograma/";
    /**
     * datosMuelas.get(i): estados de la muela número i datosMuelas.get(i)[] =
     * {estado_muela_i_arriba, estado_muela_i_izquierda, estado_muela_i_centro,
     * ...}
     */
    List<int[]> datosMuelas;

    private boolean adulto;

    private final int NUM_ADULTOS = 8;
    int[] idsDientesAdulto = {
        18, 17, 16, 15, 14, 13, 12, 11, 21, 22, 23, 24, 25, 26, 27, 28,
        48, 47, 46, 45, 44, 43, 42, 41, 31, 32, 33, 34, 35, 36, 37, 38
    };

    private final int NUM_INFANTIL = 6;
    int[] idsDientesInfantil = {
        55, 54, 53, 52, 51, 61, 62, 63, 64, 65,
        85, 84, 83, 82, 81, 71, 72, 73, 74, 75
    };

    /**
     * obtiene el indice inicial del bloque identificado por numbloque, de
     * acuerdo a que si es adulto o infantil.
     *
     * @param numbloque
     * @param adulto
     * @return
     */
    public int inicio(int numbloque, boolean adulto) {
        if (adulto) {
            return NUM_ADULTOS * numbloque;
        } else {
            return NUM_INFANTIL * numbloque;
        }
    }

    /**
     * obtiene el indice final del bloque identificado por numbloque, de acuerdo
     * a que si es adulto o infantil.
     *
     * @param numbloque
     * @param adulto
     * @return
     */
    public int fin(int numbloque, boolean adulto) {
        if (adulto) {
            return NUM_ADULTOS * numbloque + NUM_ADULTOS - 1;
        } else {
            return NUM_INFANTIL * numbloque + NUM_INFANTIL - 1;
        }
    }

    /**
     * obtiene el identificador del diente con que referencia al diente de
     * acuerdo al indice del diente y del tipo de paciente adulto o no(infantil)
     *
     * @param indice
     * @param adulto
     * @return
     */
    public int idDiente(int indice, boolean adulto) {
        if (adulto) {
            return idsDientesAdulto[indice];
        } else {
            return idsDientesInfantil[indice];
        }
    }

    public boolean isAdulto() {
        return adulto;
    }

    public boolean isInfantil() {
        return !adulto;
    }

    public void setInfantil(boolean infantil) {
        this.adulto = !infantil;
    }

    public void setAdulto(boolean adulto) {
        this.adulto = adulto;
    }

    private class elementoDir {

        String nombre;
        boolean total;

        public elementoDir(String nombre, boolean total) {
            this.nombre = nombre;
            this.total = total;
        }

    }

    /**
     * Creates a new instance of LoginController
     */
    public viewOdontograma() {

        adulto = false;
        listaDirectorios = new ArrayList();
        listaDirectorios.add(new elementoDir("normal", false));
        listaDirectorios.add(new elementoDir("caries", false));
        listaDirectorios.add(new elementoDir("amalgama", false));
        listaDirectorios.add(new elementoDir("exodoncia", true));
        listaDirectorios.add(new elementoDir("sellante", false));
        listaDirectorios.add(new elementoDir("sinsellante", false));
        listaDirectorios.add(new elementoDir("faltante", true));
        listaDirectorios.add(new elementoDir("faltanteext", true));
        listaDirectorios.add(new elementoDir("obstplastica", false));
        listaDirectorios.add(new elementoDir("obsttemporal", false));
        listaDirectorios.add(new elementoDir("protesis", true));

        datosMuelas = new ArrayList(CANTIDAD_MUELAS);
        for (int i = 0; i < CANTIDAD_MUELAS; i++) {
            datosMuelas.add(new int[5]);
        }
        datosMuelas.get(1)[0] = 1;
        datosMuelas.get(1)[1] = 1;
        datosMuelas.get(3)[0] = 1;
    }

    /**
     * obtiene el tamano de laslista de directorio de diferentes tipo de
     * afecciones
     *
     * @return
     */
    public int tamanioListaDirectorios() {
        return listaDirectorios.size() - 1;
    }

    /**
     * asigna el nuevo valor al indice de la carpeta actual
     *
     * @param nuevoIndice
     */
    public void definirActual(int nuevoIndice) {
        this.actual = nuevoIndice;
        System.out.println("definirActual->actual: " + nuevoIndice);
    }

    /**
     * obtiene el estado en forma de directorio completo donde se encuentra la
     * imagen que representa la j_esima_parte de la i_esima_muela.
     *
     * @param i_esima_muela
     * @param j_esima_parte
     * @param nombre_imagen nombre de la imagen que representa la j_esima_parte
     * @return
     */
    public String estado(int i_esima_muela, int j_esima_parte, String nombre_imagen) {
        System.out.println("estado->i-esima-muela:" + i_esima_muela + "; indiceParte:" + j_esima_parte + "; cad: " + nombre_imagen);
        int[] muela_i = datosMuelas.get(i_esima_muela);
        return directorio(muela_i[j_esima_parte]) + nombre_imagen;
    }

    /**
     * nombre de la carpeta en la posicion indice
     *
     * @param indice
     * @return
     */
    public String directorio(int indice) {
        return DIRECTORIO_RAIZ_ODONTOGRAMA + listaDirectorios.get(indice).nombre + "/";
    }

    /**
     * nombre de la imagen de muestra del directorio nombre_directorio
     *
     * @param nombre_directorio
     * @return
     */
    public String muestra(int nombre_directorio) {
        return directorio(nombre_directorio) + "centro.svg";
    }

    /**
     * asigna el actual estado escogido en la parte de la muela ubicado en
     * indiceMuela de la i_esima_muela
     *
     * @param i_esima_muela
     * @param indiceParte
     */
    public void asignarEstado(int i_esima_muela, int indiceParte) {
        int[] muela_i;
        muela_i = datosMuelas.get(i_esima_muela);
        if (listaDirectorios.get(actual).total) {
            for (int i = 0; i < muela_i.length; i++) {
                muela_i[i] = actual;
            }
        } else {
            if (listaDirectorios.get(muela_i[indiceParte]).total) {
                for (int i = 0; i < muela_i.length; i++) {
                    muela_i[i] = 0;
                }
            }
            System.out.println("asignarEstado->i-esima-muela:" + i_esima_muela + "; indiceParte:" + indiceParte + "; actual: " + muela_i[indiceParte]);
            muela_i[indiceParte] = actual;
        }
        System.out.print("=: " + datosMuelas.get(i_esima_muela)[indiceParte]);
    }

}
