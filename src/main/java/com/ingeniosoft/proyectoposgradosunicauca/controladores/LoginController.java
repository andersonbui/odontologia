/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeniosoft.proyectoposgradosunicauca.controladores;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author debian
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    private String username;
    private String password;
    private boolean logeado;
    private List<String> listaDirectorios;
    private int actual;
    private int cantidadMuelas;
    private int partes; // partes = {arriba, izquierda, centro, derecha, abajo}
    /**
     * datosMuelas.get(i): estados de la muela número i datosMuelas.get(i)[] =
     * {estado_muela_i_arriba, estado_muela_i_izquierda, estado_muela_i_centro,
     * ...}
     */
    List<int[]> datosMuelas; // 

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        username = "anderson";
        cantidadMuelas = 10;
        partes = 5;
        listaDirectorios = new ArrayList();
        listaDirectorios.add("normal/");
        listaDirectorios.add("caries/");

        datosMuelas = new ArrayList(cantidadMuelas);
        for (int i = 0; i < cantidadMuelas; i++) {
            datosMuelas.add(new int[5]);
        }
        datosMuelas.get(1)[0]=1;
        datosMuelas.get(1)[1]=1;
        datosMuelas.get(3)[0]=1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getLista() {
        return listaDirectorios;
    }

    public void login(int item, String parte) {
        System.out.println("presionaste: " + item + "; parte: " + parte);
    }

    public void definirActual(int actual) {
        this.actual = actual;
        System.out.println("actual: "+actual);
    }

    public int getCantidadMuelas() {
        return cantidadMuelas;
    }

    public String estado(int i_esima_muela, int indiceParte, String cad) {
        System.out.println("i-esima-muela:" + i_esima_muela + "; indiceParte:" + indiceParte +"; cad: "+cad);
        int[] muela_i = datosMuelas.get(i_esima_muela);
        return directorio(muela_i[indiceParte]) + cad;
    }

    public String directorio(int i_esima_directorio) {
        return listaDirectorios.get(i_esima_directorio);
    }

    public void asignarEstado(int i_esima_muela, int indiceParte) {
        int[] muela_i;
        muela_i = datosMuelas.get(i_esima_muela);
        muela_i[indiceParte] = actual;
        System.out.println("i-esima-muela:" + i_esima_muela + "; indiceParte:" + indiceParte +"; actual: "+actual);
        System.out.print("=: "+datosMuelas.get(i_esima_muela)[indiceParte]);
    }
}
